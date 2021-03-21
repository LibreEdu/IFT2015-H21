package pedigree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Random;

import pedigree.Sim.Sex;

public class Main {
	
	// Living male sims... for dating
	private static Heap<Sim> males;
	
	// Living sims… for coalescence
	private static PriorityQueue<Sim> popForFather;
	private static PriorityQueue<Sim> popForMother;
	
	// Already used ancestors… for coalescence
	private static HashSet<Sim> ancestors;
	
	// The lineage of the ancestors… for coalescence
	private static ArrayList<String> forfathers;
	private static ArrayList<String> foremothers;

	private static PriorityQueue<Event> events;
	private static AgeModel ageModel;
	private static double reproductionRate;
	private static Random RDM = new Random();
	private static int populationSize = 0;
	private static int year = 0; // Start date of the simulation
	
	private static final int DEFAULT_POPULATION_SIZE = 5000;
	private static final int DEFAULT_MAXIMUM_TIME = 20000;
    private static final double DEFAULT_ACCIDENT_RATE = 0.01; // 1% chance of dying per year
    private static final double DEFAULT_DEATH_RATE = 12.5;
    private static final double DEFAULT_SCALE = 100.0; // "maximum" age [with death rate 1]
	private static final double FIDELITY = 0.9;
	private static final int TIME_SMP_SIZE = 100;
	private static final String FIELD_SEPARATOR = "\t";
	private static final String YEAR_WORDING = "Year";
	private static final String POPULATION_WORDING = "Population";
	private static final String FORFATHER_WORDING = "Forfather";
	private static final String FOREMOTHER_WORDING = "Formother";

	public static void main(String[] args) {
		int argIdx = 0;
        int populationSize = DEFAULT_POPULATION_SIZE;
        double Tmax = DEFAULT_MAXIMUM_TIME;
        double accidentRate = DEFAULT_ACCIDENT_RATE;
        double deathRate = DEFAULT_DEATH_RATE;
        double ageScale = DEFAULT_SCALE;
        
        if (argIdx < args.length)
        	populationSize = Integer.parseInt(args[argIdx++]);
        if (argIdx < args.length)
        	Tmax = Integer.parseInt(args[argIdx++]);
        if (argIdx < args.length)
        	accidentRate = Double.parseDouble(args[argIdx++]);
        if (argIdx < args.length)
        	deathRate = Double.parseDouble(args[argIdx++]);
        if (argIdx < args.length)
        	ageScale = Double.parseDouble(args[argIdx++]);
        
        males = new Heap<Sim>();
        popForFather = new PriorityQueue<Sim>(new birthComparator<Sim>());
        popForMother = new PriorityQueue<Sim>(new birthComparator<Sim>());
        ancestors = new HashSet<Sim>();
        forfathers = new ArrayList<String>();
        foremothers = new ArrayList<String>();
		events = new PriorityQueue<Event>();
		ageModel = new AgeModel(accidentRate, deathRate, ageScale);
		reproductionRate = 2.0/ageModel.expectedParenthoodSpan(Sim.MIN_MATING_AGE_F, Sim.MAX_MATING_AGE_F);
		
		simulate(populationSize, Tmax);
		coalescence();
	}   
	
	static void simulate(int n, double Tmax){
		System.out.println(YEAR_WORDING + FIELD_SEPARATOR + POPULATION_WORDING);
		
		// Creation of the initial population
		creation(n);
		
		// Browsing the list of events
		Event event;
		while (!events.isEmpty()){
			event = events.poll();
			if (printPopulationSize(event.getYear(), Tmax))
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
	 * Creation of the initial population
	 * 
	 * @param size Initial population size
	 */
	private static void creation(int size) {
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
	private static boolean printPopulationSize(Double year, Double Tmax) {
  	   if (year > Main.year) {
 		  System.out.println(Main.year + "\t" + populationSize);
 		  Main.year += TIME_SMP_SIZE;
 		   if (year > Tmax)
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
	
	private static void coalescence() {
		getPop();

		int founderM = 0;
		int founderF = 0;
		Sim kid;
		Sim parent;
		
		while(founderM != popForMother.size() && founderF != popForFather.size()) {
			kid = popForMother.poll();
			if(founderM != popForMother.size()) {
				parent = kid.getFather();
				if (ancestors.contains(parent)) {
					String s = (int)kid.getBirthTime()+"\t"+popForMother.size(); 
					forfathers.add(s);
				}
				else {
					if (parent.isFounder()) {
						founderM++;
					}
					else popForMother.add(parent);
					ancestors.add(parent);
				}
			}
			if(founderF != popForFather.size()) {
				parent = kid.getMother();
				if (ancestors.contains(parent)) {
					String s = (int)kid.getBirthTime()+"\t"+popForMother.size();
					foremothers.add(s);
				}
				else {
					if (parent.isFounder()) founderF++;
					else popForFather.add(parent);
					ancestors.add(parent);
				}
			}
		}
		
		printAncestors(YEAR_WORDING + FIELD_SEPARATOR + FORFATHER_WORDING,
				forfathers, founderM);
		printAncestors(YEAR_WORDING + FIELD_SEPARATOR + FOREMOTHER_WORDING,
				foremothers, founderF);

	}
	
	private static void printAncestors(String Wording, ArrayList<String> lineage, 
			int nbFounder) {
		System.out.println();
		System.out.println(Wording);
		System.out.println("0\t" + nbFounder);
		for (int i = lineage.size()-1 ; i > 0; i--) {
			System.out.println(lineage.get(i));	
		}
	}
	
	// We collect all the dead events to create a maximum heap of the living,
	// from the youngest (the biggest year) to the oldest
	private static void getPop() {
		int size = events.size();
		for (int i = 0; i < size; i++) {
			Event event = events.poll();
			if (event.getType() ==  Event.Type.Death) {
				popForFather.add(event.getSim());
				popForMother.add(event.getSim());
			}			
		}
		
	}


}
