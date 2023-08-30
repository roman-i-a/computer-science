package chapter4;

import java.util.Map;

public class DijkstraNode implements Comparable<DijkstraNode> {
	
	public final int vertex;
	public final double distance;
	
	public DijkstraNode(int vertex, double distance) {
		this.vertex = vertex;
		this.distance = distance;
	}
	
	@Override
	public int compareTo(DijkstraNode other) {
		Double mine = distance;
		Double their = other.distance;
		return mine.compareTo(their);
	}
	
	public static final class DijkstraResult {
		public final double[] distances;
		public final Map<Integer, WeightedEdge> pathMap;
		
		public DijkstraResult(double[] distances, Map<Integer, WeightedEdge> pathMap) {
			this.distances = distances;
			this.pathMap = pathMap;
		}
	}
	
	public DijkstraResult dijkstra(V root) {
		
	}
}
