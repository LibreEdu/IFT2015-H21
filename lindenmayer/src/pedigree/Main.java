package pedigree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Random;

public class Main {
	
	private static ArrayList<Sim> population;
	private static HashMap<Integer, Integer> vivants;
	private static PriorityQueue<Event> evenements;
	private static final int SMP_SIZE = 1000;

	public static void main(String[] args) {
		population = new ArrayList<Sim>(SMP_SIZE);
		vivants = new HashMap<Integer, Integer>(SMP_SIZE);
		evenements = new PriorityQueue<Event>(SMP_SIZE);
		
		AgeModel ageModel = new AgeModel();
		double[] lifespan = ageModel.lifeSpan(SMP_SIZE);
		double span = ageModel.expectedParenthoodSpan(Sim.MIN_MATING_AGE_F, Sim.MAX_MATING_AGE_F);
        double stable_rate = 2.0/span;
		//System.out.println("test");
		
		for(int i = 0; i < SMP_SIZE; i++) {
			Sim sim;
			Random random=new Random();
			double time;
			
			// Choix du sexe
			if(Math.random() < 0.5) {
				sim = new Sim(Sim.Sex.F);
				time = ageModel.randomWaitingTime(random, stable_rate);
				Event event = new Event(time, sim, Event.Type.Mating);
				evenements.add(event);
			}
			else sim = new Sim(Sim.Sex.M);

			// Âge de mort
			sim.setDeath(lifespan[i]);
			
			// Ajout à la population de tous les sims depuis la création
			population.add(sim);
			
			// Ajout à la population des sims vivants
			vivants.put(sim.getSim_ident(), sim.getSim_ident());
			
			// Ajout de l’évènement de mort
			Event event = new Event(sim.getDeathTime(), sim, Event.Type.Death);
			evenements.add(event);
		}
		
		int i = 0;
		while(evenements.size()>0) {
			Event event = evenements.poll();
			//System.out.println(i++);
			System.out.println(event.toString());
		}
		
		/*for(int i = 0; i < population.size(); i++) {
			System.out.println(i);
			System.out.println(population.get(i).toString());
		}*/
		
		/*for(int i = 0; i < vivants.size(); i++) {
			System.out.println(i);
			System.out.println(vivants.get(i).toString());
		}*/
	}

}
