package pedigree;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Random;

import pedigree.Sim.Sex;

public class Main {
	
	// Living male sims... for dating
	private static Heap<Sim> males;

	private static PriorityQueue<Event> events;
	private static AgeModel ageModel;
	private static double reproductionRate;
	private static Random RDM = new Random();
	private static int populationSize;
	private static int year; // Year counter
	
	private static final int DEFAULT_POPULATION_SIZE = 1000;
	private static final int DEFAULT_MAXIMUM_TIME = 10000;
    private static final double DEFAULT_ACCIDENT_RATE = 0.01; // 1% chance of dying per year
    private static final double DEFAULT_DEATH_RATE = 12.5;
    private static final double DEFAULT_SCALE = 100.0; // "maximum" age [with death rate 1]
	private static final double FIDELITY = 0.9;
	private static final int TIME_SMP_SIZE = 100;
	private static final int INITIAL_YEAR = 0;
	private static final String FIELD_SEPARATOR = "\t";
	private static final String YEAR_LABEL = "Year";
	private static final String POPULATION_LABEL = "Population";
	private static final String FORFATHER_LABEL = "Forfathers";
	private static final String FOREMOTHER_LABEL = "Formothers";

	public static void main(String[] args) {
		int argIdx = 0;
        populationSize = DEFAULT_POPULATION_SIZE;
        double Tmax = DEFAULT_MAXIMUM_TIME;
        double accidentRate = DEFAULT_ACCIDENT_RATE;
        double deathRate = DEFAULT_DEATH_RATE;
        double ageScale = DEFAULT_SCALE;
        year = INITIAL_YEAR;
        
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
        
        males = new Heap<Sim>((int)(populationSize * 1.3));
		events = new PriorityQueue<Event>(populationSize * 4);
		ageModel = new AgeModel(accidentRate, deathRate, ageScale);
		reproductionRate = 2.0/ageModel.expectedParenthoodSpan(Sim.MIN_MATING_AGE_F, Sim.MAX_MATING_AGE_F);
		
		simulate(populationSize, Tmax);
		coalescence();
	}   
	
	/**
	 * Simulation of the evolution of the population
	 * 
	 * @param n Initial population size
	 * @param Tmax Year the simulation is stopped
	 */
	static void simulate(int n, double Tmax){
		System.out.println(YEAR_LABEL + FIELD_SEPARATOR + POPULATION_LABEL);
		
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
 		  System.out.println(Main.year + FIELD_SEPARATOR + populationSize);
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
	 * Update of the population and events, when a new birth occurs
	 * 
	 * @param sim Sim who is born
	 * @param year Current date
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
	 * Update of the population and events, when a mate is made
	 * 
	 * @param sim Female who will mate
	 * @param year Current date
	 * 
	 * https://ift2015h21.wordpress.com/2021/02/24/projet-2-nos-ancetres-communs/
	 */
	private static void updatePopulationByMating(Sim sim, Double year) {
		// The sim is dead
		if (year > sim.getDeathTime())
			return;
		
		// Sim at reproductive age
		if (sim.isMatingAge(year)) {
			Sim x = sim;  // Mother
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
									y = z;
									x.setMate(y);
									y.setMate(x);
						}
					}
				}
				while (y==null);
			} else { // existing partner
				y = sim.getMate();
			}
			// creation of the child sim
			addBirthEvent(new Sim(x,y,year,randomSex()), year);
		}

		// New date of mating
		mating(sim, year); 
	}
	
	/**
	 * Calculation of the mating date
	 * 
	 * @param sim Female who will reproduce
	 * @param year Current date
	 */
	private static void mating(Sim sim, double year) {
		double matingDate = year + 
				AgeModel.randomWaitingTime(RDM, reproductionRate);
		events.add(new Event(matingDate, sim, Event.Type.Mating));
	}
	
	/**
	 * Planning for death
	 * 
	 * @param sim Sim who will die
	 * @param year Current date
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
	
	/**
	 * Calcul des coalescences
	 */
	private static void coalescence() {
		// Structures that will contain the entire population for the search of 
		// male and female coalescence
		PriorityQueue<Sim> popForFather = 
				new PriorityQueue<Sim>((int)(populationSize * 3.8), new BirthComparator<Sim>());
		PriorityQueue<Sim> popForMother = 
				new PriorityQueue<Sim>((int)(populationSize * 3.8), new BirthComparator<Sim>());
		
		// Structures to save the evolution of coalescences
		ArrayList<String> forefathers = new ArrayList<String>((int)(populationSize * 3.8));
		ArrayList<String> foremothers = new ArrayList<String>((int)(populationSize * 3.8));
        
		// We get the population from the list of events
        getPop(popForFather, popForMother);
        
        // Search for coalescence
        findFounders(popForFather, forefathers, "getFather");
        findFounders(popForMother, foremothers, "getMother");
        
        // Output
		printAncestors(FORFATHER_LABEL, forefathers);
		printAncestors(FOREMOTHER_LABEL, foremothers);

	}
	
	/**
	 * Retrouve les fondateurs d’une population
	 * 
	 * @param population End of simulation population
	 * @param forebear ArrayList of String that will contain the output data
	 * @param methodName Name of the method that allow to get the parent of a sim
	 */
	private static void findFounders(PriorityQueue<Sim> population, ArrayList<String> forebear, String methodName) {
		// Set of ancestors already present in the population
		HashSet<Sim> ancestors = new HashSet<Sim>();
		
		// Number of founders at the origin of the current population
		int nbFounders = 0;
		
		// Sim that will be replaced by its parent
		Sim sim;
		
		Sim parent = null;
		Method method = null;
		
		// As long as the population is not reduced to its founders
		while(nbFounders != population.size() && population.size() != 0) {
			
			// Get the youngest sim
			sim = population.poll();
			
			// Dynamically invokes the method that gets the parent (father or 
			// mother) of the sim
			try {
				method = sim.getClass().getMethod(methodName);
				parent = (Sim) method.invoke(sim);
			} catch (NoSuchMethodException | SecurityException | 
					IllegalAccessException | IllegalArgumentException | 
					InvocationTargetException e) {
				e.printStackTrace();
			}
			
			// If the parent of the sim is already a listed ancestor, the
			// population just decreased by 1, we save the information
			if (ancestors.contains(parent)) {
				forebear.add((int)sim.getBirthTime() + FIELD_SEPARATOR
						+ population.size());
			} else {
				if (parent.isFounder()) {
					nbFounders++;
				} else {
					population.add(parent);
					ancestors.add(parent);
				}
			}
		}
		
		// Number of founders at the origin of the population
		forebear.add(INITIAL_YEAR + FIELD_SEPARATOR + nbFounders);
	}
	
	/**
	 * Print the lineage of the ancestors
	 * 
	 * @param Label Column labels
	 * @param lineage
	 */
	private static void printAncestors(String Label, ArrayList<String> lineage) {
		System.out.println("\n" + YEAR_LABEL + FIELD_SEPARATOR + Label);
		for (int i = lineage.size()-1 ; i > 0; i--) {
			System.out.println(lineage.get(i));	
		}
	}
	
	/**
	 * Recover the population from the death events
	 * 
	 * @param popForFather The population that will allow to find the forefathers.
	 * @param popForMother The population that will make it possible to find the foremothers.
	 */
	private static void getPop(PriorityQueue<Sim> popForFather, PriorityQueue<Sim> popForMother) {
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
