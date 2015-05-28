package graphsearch;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Vertx implements Comparable<Vertx> {
	
	public String name ;
	public Edge[] adjacencies;
	public double minDistance = Double.POSITIVE_INFINITY;
	public Vertx previous ;
	public Vertx (String label){
		this.name  = label;
	}
	@Override
	public int compareTo(Vertx o) {
		// TODO Auto-generated method stub
		return Double.compare(minDistance, o.minDistance);
	}
	public String toString() { return name; }
	
}

class Edge {
	
	public Vertx target;
	public double weight;
	
	public Edge(Vertx t, double w) {
		
		this.target = t;
		this.weight = w;
	}
}


public class Djikstra {


	public static void computePath(Vertx source){
		
		PriorityQueue<Vertx> pq = new PriorityQueue<Vertx>();
		source.minDistance = 0;
		pq.add(source);
		
		while(!pq.isEmpty()){
			
			Vertx u = pq.poll();
			
			for(Edge e : u.adjacencies){
				
				Vertx v = e.target;
				double weight = e.weight;
				double distance = weight + u.minDistance;
				if(distance < v.minDistance){
					pq.remove(v);
					v.minDistance = distance;
					v.previous = u;
					pq.add(v);
				}
				
			}
		}
	}
	
	public static List getPath(Vertx target){
		
		List<Vertx> paths = new ArrayList<Vertx>();
		for(Vertx v = target; v !=null; v = v.previous){
			paths.add(v);
		}
		return paths;
	}
	
	public static void main(String[] args) {
		
		// Initialize vertices and stuff
		Vertx v0 = new Vertx("A");
		Vertx v1 = new Vertx("B");
		Vertx v2 = new Vertx("C");
		
		// Add edges
		v0.adjacencies = new Edge[]{ new Edge(v1, 10), new Edge(v2, 1)};
		v1.adjacencies = new Edge[]{ new Edge(v0, 10), new Edge(v2, 3)};
		v2.adjacencies = new Edge[]{ new Edge(v0, 1), new Edge(v1, 3)};
		
		Vertx [] vertices = { v0, v1, v2};
		
		computePath(v0);
        for (Vertx v : vertices)
        {
		    System.out.println("Distance to " + v + ": " + v.minDistance);
		    List<Vertex> path = getPath(v);
		    System.out.println("Path: " + path);
        }

	}

}
