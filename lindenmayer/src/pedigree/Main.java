package pedigree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Random;

import pedigree.Event.Type;
import pedigree.Sim.Sex;

public class Main {

	private static ArrayList<Sim> population;
	private static MinHeap min = new MinHeap();
	private static HashMap<Integer, Integer> vivants;
	private static PriorityQueue<Event> evenements;
	private static final int SMP_SIZE = 1000;
	private static final double  fidelity= 0.9;
	private static AgeModel ageModel = new AgeModel();
	private static double span = ageModel.expectedParenthoodSpan(Sim.MIN_MATING_AGE_F, Sim.MAX_MATING_AGE_F);
    private static double rate = 2.0/span;
	private static Random RDM = new Random();

	public static void main(String[] args) {
		population = new ArrayList<Sim>(SMP_SIZE);
		vivants = new HashMap<Integer, Integer>(SMP_SIZE);
		evenements = new PriorityQueue<Event>(SMP_SIZE);
		
	}        
		//System.out.println("test");
        public void simulate(int n, double Tmax){
            Sim sim;
		   
           for (int i=0; i<n; i++){
        	   sim = new Sim(sex());
        	   Birth(sim,0.0);
           }
           while (!evenements.isEmpty()){
              
        	   Event E = evenements.poll(); // prochain événement
        	   if (E.getYear() > Tmax) break; // arrêter à Tmax
        	   if (E.getType() == Event.Type.Death){//evenement de mort
        		   
        		   if(E.getSim().getSex()== Sim.Sex.M) min.deleteMin(); // homme meurt on l'enleve du tas
        		   continue;
              } 
              else if(E.getType() == Event.Type.Birth) { // evenement de naissance 
            	  
            	  if(E.getSim().getSex()== Sim.Sex.M) min.insert(E.getSim()); // homme nait on l'ajoute au tas            	  
            	  else matingTime(E.getSim(),E.getYear());  // femme nait on lui donne un temps reproduction
            	  death(E.getSim(),E.getYear()); // temps de mort a tous et ajout a la population  
              }
              else { // evenement reproduction
            	  
            	  if (E.getYear()>E.getSim().getDeathTime()) continue; // sim mort on ne fait rien
            	  if (E.getSim().isMatingAge(E.getYear())) { // sim en age de reproduction
            		  
            		  Sim x = E.getSim(); //mere
            		  Sim y = null; // choisir pere y
            	
            		  if (!x.isInARelationship(E.getYear()) || RDM.nextDouble() > fidelity){ // partenaire au hasard
            		     
            			  do{
            				  Sim z = random();
            				  if (z.isMatingAge(E.getYear())) { // isMatingAge() v�rifie si z est de l'age acceptable
            					  if (x.isInARelationship(E.getYear()) // z accepte si x est infid�le
            							  || !z.isInARelationship(E.getYear())
            							  || RDM.nextDouble() > fidelity)
            		           {  y = z;}	  
            		        }
            		     } while (y==null);
            		  } 
            		  else{ //partenaire existant
            		     y = E.getSim().getMate();
            		  }
            		  //nouveau temps de reproduction
            		  matingTime(E.getSim(),E.getYear());
      				  //creation du sim enfant
      				  sim = new Sim(x,y,E.getYear(),sex());
      				  Birth(sim,E.getYear());
            	  }            	  
              }
              
           }

        }
		private void death(Sim sim, double year) {
			
			double time = ageModel.randomAge(RDM) + year;
      	    sim.setDeath(time);
      	    population.add(sim.getSim_ident(),sim);
			
		}
		private void matingTime(Sim sim, double year) {
			double time = ageModel.randomWaitingTime(RDM, rate)+ year;
			Event event = new Event(time, sim, Event.Type.Mating);
			evenements.add(event);
			
		}
		private void Birth(Sim sim, double t) {
			Event event = new Event(t, sim, Event.Type.Birth);
		   	evenements.add(event);
			
		}
		private Sex sex() {
			if (RDM.nextDouble()>0.5) return Sim.Sex.F;
			else return Sim.Sex.M;
		}
		private Sim random() {
			int i = RDM.nextInt(min.size());
			return min.getSim(i);
		}

}

