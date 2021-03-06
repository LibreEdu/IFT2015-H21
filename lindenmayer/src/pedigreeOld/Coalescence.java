package pedigreeOld;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;

import pedigree.Event;
import pedigree.Sim;
import pedigree.birthComparator;
import pedigree.Sim.Sex;

public class Coalescence {
	
	private static ArrayList<Sim> allSims = new ArrayList();
	private static PriorityQueue<Event> events = new PriorityQueue<Event>();
	private static PriorityQueue<Sim> living;
	private static HashSet<Sim> forefathers;
	private static ArrayList<int[]> paternalLineages = new ArrayList<int[]>();
	

	public static void main(String[] args) {
		living = new PriorityQueue<Sim>(new birthComparator<Sim>());
		forefathers= new HashSet<Sim>();
		/*
		insert(1);
		insert(3);
		insert(2);
		insert(4);
		insert(0);
		insert(6);
		insert(5);
		insert(7);
		int size = living.size();
		for (int i = 0; i < size; i++) {
			System.out.println(living.poll().getBirthTime());			
		}
		*/
		/*
		for(int i=0;i<10;i++) {
			Sim m1 = new Sim(null, null, 0, Sim.Sex.M);
			allSims.add(m1);
			living.add(m1);
		}
		for(int i=0;i<10;i++) {
			Sim m1 = new Sim(allSims.get(i),allSims.get(i),1,Sim.Sex.F);
			forefathers.add(m1);
			allSims.add(m1);
			living.add(m1);
		}
		/*
		Sim m1 = new Sim(null, null, 0, Sim.Sex.M);
		Sim f1 = new Sim(null, null, 1, Sim.Sex.M);
		Sim m2 = new Sim(f1, m1, 25, Sim.Sex.F);
		Sim f2 = new Sim(f1, m1, 26, Sim.Sex.F);
		
		events.add(new Event(0, m1, Event.Type.Birth));
		events.add(new Event(25, m2, Event.Type.Death));
		events.add(new Event(26, f1, Event.Type.Mating));
		events.add(new Event(27, f2, Event.Type.Death));
		events.add(new Event(28, f1, Event.Type.Death));
		*/
		/*
		Event event;
		
		// We collect all the dead events to create a maximum heap of the living,
		// from the youngest (the biggest year) to the oldest
		int size = events.size();
		for (int i = 0; i < size; i++) {
			event = events.poll();
			if (event.getType() ==  Event.Type.Death) {
				living.add(event.getSim());
			}			
		}
		*/
		/* Test
		size = living.size();
		for (int i = 0; i < size; i++) {
			System.out.println(living.poll().getBirthTime());			
		}
		*/
		/*
		allSims.add(m1);
		allSims.add(m2);
		allSims.add(f1);
		allSims.add(f2);
		*/
		//System.out.println(f2.getSim_ident());
		int fondateur = 0;
		Sim kid;
		Sim parent;
		while(fondateur!=living.size()) {
			kid = living.poll();
			String a = "getFather()";
			parent = kid.getFather();
			if (forefathers.contains(parent)) {
				int[] data = new int[2];
				data[0]=living.size();
				data[1]= (int)kid.getBirthTime();
				paternalLineages.add(data);
			}
			else {
				if (parent.isFounder()) fondateur++;
				forefathers.add(parent);
			}
		}
	
	for (int i= paternalLineages.size(); i>0;i--) {
		int[] data = paternalLineages.get(i);
		System.out.println(data[1]+"\t"+data[0]);
		
	}
	/*	
		for(int i = 0; i < 100; i++) {
			jeune = récupérer(poll) le plus jeune
			père = jeune.père
			Si père dans set
				ajouter dans tableau (annép, size)
			Sinon
				ajouter père set
				si père = fondateur alors fondateur++
		}
		
		fondateur != living.size
		
	}
	*/
	/*
	private static void insert(int i) {
		living.add(new Sim(null, null, i, Sim.Sex.M));
	}
	*/
}
}
