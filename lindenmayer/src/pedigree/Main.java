package pedigree;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Random;

import pedigree.Sim.Sex;

public class Main {
	
	// Living male sims... for dating
	private static Heap<Sim> males;
	
	// Living sims... for coalescence
	private static PriorityQueue<Sim> population;
	
	// The ancestors... for coalescence
	private static HashSet<Sim> forebear;

	private static PriorityQueue<Event> events;
	private static AgeModel ageModel;
	private static double reproductionRate;
	private static Random RDM = new Random();
	private static int populationSize = 0;
	private static int year = 0; // Start date of the simulation
	private static int maximumTime;
	
	private static final int DEFAULT_POPULATION_SIZE = 5000;
	private static final int DEFAULT_MAXIMUM_TIME = 20000;
    private static final double DEFAULT_ACCIDENT_RATE = 0.01; // 1% chance of dying per year
    private static final double DEFAULT_DEATH_RATE = 12.5;
    private static final double DEFAULT_SCALE = 100.0; // "maximum" age [with death rate 1]
	private static final double FIDELITY = 0.9;
	private static final int TIME_SMP_SIZE = 100;

	public static void main(String[] args) {
		int arg_idx = 0;
        int foundingPopulation = DEFAULT_POPULATION_SIZE;
        maximumTime = DEFAULT_MAXIMUM_TIME;
        double accident_rate = DEFAULT_ACCIDENT_RATE;
        double death_rate = DEFAULT_DEATH_RATE;
        double age_scale = DEFAULT_SCALE;
        
        if (arg_idx < args.length)
        	foundingPopulation = Integer.parseInt(args[arg_idx++]);
        if (arg_idx < args.length)
        	maximumTime = Integer.parseInt(args[arg_idx++]);
        if (arg_idx < args.length)
        	accident_rate = Double.parseDouble(args[arg_idx++]);
        if (arg_idx < args.length)
        	death_rate = Double.parseDouble(args[arg_idx++]);
        if (arg_idx < args.length)
        	age_scale = Double.parseDouble(args[arg_idx++]);
        
        males = new Heap<Sim>();
        population = new PriorityQueue<Sim>();
        forebear = new HashSet<Sim>();
		events = new PriorityQueue<Event>();
		ageModel = new AgeModel(accident_rate, death_rate, age_scale);
		reproductionRate = 2.0/ageModel.expectedParenthoodSpan(Sim.MIN_MATING_AGE_F, Sim.MAX_MATING_AGE_F);
		
		simulate(foundingPopulation, maximumTime);
	}   
	
	static void simulate(int n, double Tmax){
		founderPopulation(n);
		Event event;
		while (!events.isEmpty()){
			event = events.poll(); // next event
			if (printPopulationSize(event.getYear()))
				break;
			switch(event.getType()) {
				case Birth :
					updatePopulationByBirth(event.getSim(), event.getYear());
					break;
				case Mating :
					updatePopulationByMating(event.getSim(), event.getYear());
					break;
				case Death :
					updatePopulationByDeath(event.getSim());
					break;
			}
		}
	}
	
	/**
	 * Add the birth event
	 * 
	 * @param sim Sim for which we add the event
	 * @param year Date of the event
	 */
	private static void addBirthEvent(Sim sim, double year) {
		events.add(new Event(year, sim, Event.Type.Birth));	
	}
	
	/**
	 * Birth events of the founding population
	 * 
	 * @param size Initial population size
	 */
	private static void founderPopulation(int size) {
        for (int i = 0; i < size; i++){
        	addBirthEvent(new Sim(randomSex()), year);
        }
	}
	
	/**
	 * Every TIME_SMP_SIZE years, print the population size and test if the 
	 * maximum time of the simulation has been reached.
	 * 
	 * @param year Year for which we want to print the population size
	 * @return True if the maximum time has been exceeded, false otherwise
	 */
	private static boolean printPopulationSize(Double year) {
  	   if (year > Main.year) {
 		   System.out.println(Main.year + "\t" + populationSize);
 		  Main.year += TIME_SMP_SIZE;
 		   if (year > maximumTime)
 			   return true;
 	   }
  	   return false;
	}
	
	/**
	 * Update the size of the population and if necessary the heap containing
	 * the males
	 * 
	 * @param sim Sim that we want to remove from the heap
	 */
	private static void updatePopulationByDeath(Sim sim) {
		if (sim.isMale())
			males.poll(); // We remove the male from the heap.
		populationSize--;
	}

	/**
	 * 
	 * @param sim
	 * @param year
	 */
	private static void updatePopulationByBirth(Sim sim, Double year) {
		if (sim.isMale())
			males.add(sim);    // [n3]
		else
			mating(sim, year); // [n2]
		setDeath(sim, year);   // [n1]
		populationSize++;
	}
	
	/**
	 * 
	 * @param sim
	 * @param year
	 */
	private static void updatePopulationByMating(Sim sim, Double year) {
		// The sim is dead
		if (year > sim.getDeathTime())
			return;
		
		// Sim at reproductive age
		if (sim.isMatingAge(year)) {
			Sim x = sim; // Mother
			Sim y = null; // Father
			
			// Random partner
			if (!x.isInARelationship(year) || RDM.nextDouble() > FIDELITY){ // [p2]
				do {
					Sim z = random();
					// isMatingAge() check if z is of acceptable age
					if (z.isMatingAge(year)) { 
						// z accepts that x is unfaithful
						if (x.isInARelationship(year) 
								|| !z.isInARelationship(year)
								|| RDM.nextDouble() > FIDELITY) {
														y = z;}	  
						}
					}
				while (y==null);
			} else { // existing partner
				y = sim.getMate();
			}
			//creation du sim enfant
			addBirthEvent(new Sim(x,y,year,randomSex()), year);
		}

		//nouveau temps de reproduction
		mating(sim, year); 
	}
	
	/**
	 * 
	 * @param sim
	 * @param year
	 */
	private static void mating(Sim sim, double year) {
		double matingDate = year + 
				AgeModel.randomWaitingTime(RDM, reproductionRate);
		events.add(new Event(matingDate, sim, Event.Type.Mating));
	}
	
	/**
	 * 
	 * @param sim
	 * @param year
	 */
	private static void setDeath(Sim sim, double year) {
		double deathDate = year + ageModel.randomAge(RDM);
		sim.setDeath(deathDate);
		events.add(new Event(deathDate, sim, Event.Type.Death));	
	}

	/**
	 * Return the sex of the new sim
	 * 
	 * @return Sim.Sex.F or Sim.Sex.M
	 */
	private static Sex randomSex() {
		return RDM.nextDouble() > 0.5 ? Sim.Sex.F : Sim.Sex.M;
	}

	/**
	 * Choose a male at random
	 * 
	 * @return The chosen male
	 */
	private static Sim random() {
		return males.get(RDM.nextInt(males.size()));
	}

}
