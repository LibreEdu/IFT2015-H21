package pedigree;

import java.util.PriorityQueue;

public class Coalescence {
	
	private static PriorityQueue<Event> events = new PriorityQueue<Event>(10);
	private static PriorityQueue<Sim> living;

	public static void main(String[] args) {
		living = new PriorityQueue<Sim>(1000, new birthComparator<Sim>());
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
		
		Sim m1 = new Sim(null, null, 0, Sim.Sex.M);
		Sim m2 = new Sim(null, null, 1, Sim.Sex.M);
		Sim f1 = new Sim(null, null, 2, Sim.Sex.F);
		Sim f2 = new Sim(null, null, 3, Sim.Sex.F);
		
		events.add(new Event(0, m1, Event.Type.Birth));
		events.add(new Event(25, m2, Event.Type.Death));
		events.add(new Event(26, f1, Event.Type.Mating));
		events.add(new Event(27, f2, Event.Type.Death));
		events.add(new Event(28, f1, Event.Type.Death));
		
		Event event;
		
		// Début
		int size = events.size();
		for (int i = 0; i < size; i++) {
			event = events.poll();
			if (event.getType() ==  Event.Type.Death) {
				living.add(event.getSim());
			}			
		}
		// Fin
		
		// Test
		size = living.size();
		for (int i = 0; i < size; i++) {
			System.out.println(living.poll().getBirthTime());			
		}
	}
	
	private static void insert(int i) {
		living.add(new Sim(null, null, i, Sim.Sex.M));
	}

}
