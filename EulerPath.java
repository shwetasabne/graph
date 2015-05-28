package graphsearch;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author shwetasabne
 * 
 * Based on http://www.geeksforgeeks.org/fleurys-algorithm-for-printing-eulerian-path/
 *
 */
public class EulerPath {

	public  static IGraph graph = new AdjacencyGraphList(3);
	
	public  boolean [] visited = new boolean[3];
	/**
	 * Method generates a graph with 4 vertices by adding edges
	 * 
	 * @param None
	 * 
	 * @return None
	 */
	public  void generateGraph(){
		
		graph.addEdge(0, 1);
		graph.addEdge(0, 2);
		//graph.addEdge(2, 3);
		graph.addEdge(2, 1);
	}
	
	public  boolean isEdgeBridge(int source, int dest){
		
		// count1 : Nodes source can reach with source - dest edge
		// count2 : Nodes source can reach without source - dest edge
		// if count 1 > count 2 then the edge is bridge
		// don't burn the bridges
		IGraph dummy = new AdjacencyGraphList(3);
		dummy  = graph;
		int count1 = dfsCount(dummy,source);
	//	System.out.println("Count with edge is "+ source + " " + dest + " "+ count1);
		dummy.deleteEdge(source, dest);
	//	System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		for(int i=0; i < visited.length; i++){
			visited[i] = false;
		}
		int count2 = dfsCount(dummy,source);
		
	//	System.out.println("Count without edge is "+ source + " " + dest + " "+ count2);
		dummy.addEdge(source, dest);
		return (count1 > count2)? false: true;
		
	}
	
	public void printEulerPath(int source){
		
	//	System.out.println("#####");
		//System.out.println("Source is " +source);
		List adjacents = graph.getAdjacencies(source);
	//	System.out.println("Adjacencies " + adjacents.toString() + " size is " + adjacents.size());
		Iterator itr = adjacents.iterator();
		while(itr.hasNext()  && adjacents.size() != 0)
		{
			int temp = (Integer)itr.next();
			//System.out.println("Checking " + temp);
			if(this.isEdgeBridge(source, temp) || adjacents.size() <= 1)
			{
			
				System.out.println( "PATH : " +  source + " => " + temp);
				graph.deleteEdge(source, temp);
			//	System.out.println("@@@");
		//		graph.printGraph();
			//	return;
				printEulerPath(temp);

			}
		}
		
	}
	
	public  int dfsCount (IGraph g, int source){
		int count = 1;
		visited[source] = true;
		List adjacentNodes = g.getAdjacencies(source);
	//	System.out.println("*******");
	//	//System.out.println("Source is " + source);
		//System.out.println(Arrays.toString(visited));
		Iterator itr = adjacentNodes.iterator();
		while(itr.hasNext()){
			int temp = (Integer)itr.next();
			if(!visited[temp]){
			//	System.out.println("Count before addition is " + count);
				count+=dfsCount(g,temp);
			}
		}
		return count;
	}
	
	
	public static void main(String[] args) {

		// Generate the graph
		
		EulerPath ep = new EulerPath();
	//	System.out.println(ep.isEdgeBridge(2,0));
		ep.generateGraph();
		
		ep.graph.printGraph();
		//System.out.println("=+=+=+=+=+=+=+=+=+=");
	
			ep.printEulerPath(2);
		
	}

}
