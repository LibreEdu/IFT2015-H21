package pedigree;

public class Event implements Comparable<Event> {
	public enum Type {Birth, Death, Mating};
	
	private double year;
	private Sim sim;
	private Type type;
	
	public Event(double year, Sim sim, Type type) {
		this.year = year;
		this.sim = sim;
		this.type = type;
	}

	public double getYear() {
		return year;
	}

	public Sim getSim() {
		return sim;
	}

	public Type getType() {
		return type;
	}

	@Override
	public int compareTo(Event e) {
		return Double.compare(this.year,e.year);
	}
	
	@Override
    public String toString() {

		StringBuilder sb = new StringBuilder(Double.toString(year));
        sb.append(" : ");
        
        switch(type) {
        case Birth: sb.append("Birth"); break;
        case Death: sb.append("Death"); break;
        case Mating: sb.append("Mating"); break;
        }
        return sb.toString();
    }
}
