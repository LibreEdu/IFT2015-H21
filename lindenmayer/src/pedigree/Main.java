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
	private static final double  fidelity= 0.9;
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
			if (printPopulationSize(event)) break;
        	if (event.getType() == Event.Type.Death){
        		updatePopulation(event);

        		   continue;
              } 
              else if(event.getType() == Event.Type.Birth) { // evenement de naissance 
            	  
            	  if(event.getSim().getSex()== Sim.Sex.M) males.add(event.getSim()); // homme nait on l'ajoute au tas 
            	  else matingTime(event.getSim(),event.getYear());  // femme nait on lui donne un temps reproduction
            	  death(event.getSim(),event.getYear()); // temps de mort a tous et ajout a la population
            	  populationSize++;
            	  continue;
              }
              else  { // evenement reproduction
            	  
            	  if (event.getYear()>event.getSim().getDeathTime()) continue; // sim mort on ne fait rien
            	  if (event.getSim().isMatingAge(event.getYear())) { // sim en age de reproduction
            		  
            		  Sim x = event.getSim(); //mere
            		  Sim y = null; // choisir pere y
            	
            		  if (!x.isInARelationship(event.getYear()) || RDM.nextDouble() > fidelity){ // partenaire au hasard
            		     
            			  do{
            				  Sim z = random();
            				  if (z.isMatingAge(event.getYear())) { // isMatingAge() v�rifie si z est de l'age acceptable
            					  if (x.isInARelationship(event.getYear()) // z accepte si x est infid�le
            							  || !z.isInARelationship(event.getYear())
            							  || RDM.nextDouble() > fidelity)
            		           {  y = z;}	  
            		        }
            		     } while (y==null);
            		  } 
            		  else{ //partenaire existant
            		     y = event.getSim().getMate();
            		  }
      				  //creation du sim enfant
            		  addBirthEvent(new Sim(x,y,event.getYear(),randomSex()), 
      						event.getYear());
            	  }
            	  //nouveau temps de reproduction
        		  matingTime(event.getSim(),event.getYear()); 
              }
              
           }

        }
	
	/**
	 * Add the birth event
	 * 
	 * @param sim
	 * @param year
	 */
	private static void addBirthEvent(Sim sim, double year) {
		events.add(new Event(year, sim, Event.Type.Birth));	
	}
	
	private static void founderPopulation(int size) {
        for (int i = 0; i < size; i++){
        	addBirthEvent(new Sim(randomSex()), year);
        }
	}
	
	private static boolean printPopulationSize(Event event) {
  	   if (event.getYear() > year) {
 		   System.out.println(year + "\t" + populationSize);
 		   year += TIME_SMP_SIZE;
 		   if (event.getYear() > maximumTime) return true;
 	   }
  	   return false;
	}
	
	private static void updatePopulation(Event event) {
		if (event.getSim().getSex()== Sim.Sex.M)
			males.poll();
		populationSize--;
	}
		private static void death(Sim sim, double year) {
			
			double time = ageModel.randomAge(RDM) + year;
      	    sim.setDeath(time);
      	    Event event = new Event(time, sim, Event.Type.Death);
      	  events.add(event);     	    
			
		}
		private static void matingTime(Sim sim, double year) {
			double time = AgeModel.randomWaitingTime(RDM, reproductionRate)+ year;
			Event event = new Event(time, sim, Event.Type.Mating);
			events.add(event);
			
		}

		private static Sex randomSex() {
			if (RDM.nextDouble()>0.5) return Sim.Sex.F;
			else return Sim.Sex.M;
		}
		private static Sim random() {
			int i = RDM.nextInt(males.size());
			return males.get(i);
		}
		


}

