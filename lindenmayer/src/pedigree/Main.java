package pedigree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Random;

import pedigree.Event.Type;

public class Main {
	
	private static ArrayList<Sim> population;
	private static HashMap<Integer, Integer> vivants;
	private static PriorityQueue<Event> evenements;
	private static final int SMP_SIZE = 1000;

	public static void main(String[] args) {
		population = new ArrayList<Sim>(SMP_SIZE);
		vivants = new HashMap<Integer, Integer>(SMP_SIZE);
		evenements = new PriorityQueue<Event>(SMP_SIZE);
		
        
		//System.out.println("test");
        public void simulate(int n, double Tmax){
            //PQ eventQ = new PQ(); // file de priorité
        	AgeModel ageModel = new AgeModel();
    		double[] lifespan = ageModel.lifeSpan(SMP_SIZE);
    		double span = ageModel.expectedParenthoodSpan(Sim.MIN_MATING_AGE_F, Sim.MAX_MATING_AGE_F);
            double rate = 2.0/span;	
            Sim sim;
            Random random=new Random();
		    double time;
		   
           for (int i=0; i<n; i++)
           {
        	   
        	   if(Math.random() < 0.5) {
        		   sim = new Sim(Sim.Sex.F);
   				   Event event = new Event(0.0, sim, Event.Type.Birth);
   			   	   evenements.add(event);
   			   	   
   				}
   				else {
   					sim = new Sim(Sim.Sex.M);
   					Event event = new Event(0.0, sim, Event.Type.Birth);
        	   		evenements.add(event); // insertion dans la file de priorité
        	   		
   				}
        	   
           }
           while (!evenements.isEmpty())
           {
              Event E = evenements.poll(); // prochain événement
              if (E.getYear() > Tmax) break; // arrêter à Tmax
              if (E.getType() == Event.Type.Death){
            	  
            	  if(E.getSim().getSex()== Sim.Sex.M) {
            		//enlever du tas min si un homme meurt
            	  }
            	  continue;
              } // else rien à faire avec E car son sujet est mort
              
              else if(E.getType() == Event.Type.Birth) {
            	  
            	  if(E.getSim().getSex()== Sim.Sex.M) {
            			  //ajouter au heap
            	  }
            	  else {
            		  
            		  time = ageModel.randomWaitingTime(random, rate);
      				  Event event = new Event(time, sim, Event.Type.Mating);
      				  evenements.add(event);
            	  }
            	
            	  time = ageModel.randomAge(random) + E.getYear();
            	  sim.setDeath(time);
            	  population.add(sim.getSim_ident(),sim);
              }
              else {
            	  if (E.getYear()>E.getSim().getDeathTime()) continue;
            	  if (E.getSim().getBirthTime()- Sim.MIN_MATING_AGE_F > 0 &&
            			  E.getSim().getBirthTime() + E.getYear()< Sim.MAX_MATING_AGE_F) {
            		  Random RND = new Random(); // générateur de nombres pseudoaléatoires
            		  Sim x = E.getSim();
            		  Sim y = null; // choisir pere y
            		  if (!x.isInARelationship(E.getYear()) || RND.nextDouble()>fidelite)
            		  { // partenaire au hasard
            		     do
            		     {
            		       Sim z = random sim;
            		        if (z.isMatingAge(E.getYear())) // isMatingAge() vérifie si z est de l'age acceptable
            		        {
            		           if (x.isInARelationship(E.getYear()) // z accepte si x est infidèle
            		               || !z.isInARelationship(E.getYear())
            		               || RND.nextDouble()>fidelite)
            		           {  y = z;}
            		        }
            		     } while (y==null);
            		  } else
            		  {
            		     y = partenaire précédent de x
            		  }
            	  }
            	  time = ageModel.randomWaitingTime(random, rate);
  				  Event event = new Event(time, sim, Event.Type.Mating);
  				  evenements.add(event);
            	  
              }
              
           }
     
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

			// Ã‚ge de mort
			sim.setDeath(lifespan[i]);
			
			// Ajout Ã  la population de tous les sims depuis la crÃ©ation
			population.add(sim);
			
			// Ajout Ã  la population des sims vivants
			vivants.put(sim.getSim_ident(), sim.getSim_ident());
			
			// Ajout de lâ€™Ã©vÃ¨nement de mort
			Event event = new Event(sim.getDeathTime(), sim, Event.Type.Death);
			evenements.add(event);
		}
		
		/*int i = 0;
		while(evenements.size()>0) {
			Event event = evenements.poll();
			//System.out.println(i++);
			System.out.println(event.toString());
		}*/
		
		
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
}
