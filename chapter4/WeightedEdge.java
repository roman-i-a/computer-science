package chapter4;


public class WeightedEdge extends Edge implements Comparable<WeightedEdge> {
	public final double weight;
	
	public WeightedEdge(int u, int v, double weight) {
		super(u, v);
		this.weight = weight;		
	}
	
	public WeightedEdge reversed() {
		return new WeightedEdge(v, u, weight);
	}
	
	@Override
	public int compareTo(WeightedEdge other) {
		Double mine = this.weight;
		Double their = other.weight;
		return mine.compareTo(their);
	}
	
	@Override
	public String toString() {
		return u + " " + weight + "> " + v;
	}
	
	public static void main (String[] args) {
		System.out.println(new WeightedEdge(1, 2, 13.0));
	}
}

