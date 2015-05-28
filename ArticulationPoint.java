package graphsearch;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public class ArticulationPoint {

	public static IGraph al = new AdjacencyGraphList(4);

	public static boolean [] visited = new boolean[4];
	
	public static int [] depth = new int[4];
	
	public static int [] low = new int[4];
	
	public static int[] parent = new int[4];
	
	public static boolean [] isArticulation = new boolean[4];
	
	public static void generateGraph(){
		al.addEdge(0, 1);
		al.addEdge(1, 2);
		al.addEdge(1, 3);
		al.addEdge(2,3);
		al.printGraph();
	}
	
	public static void getArticulationPoint(int i, int d){
		
		System.out.println("*************************");
		System.out.println("Processing node " + i + " with depth "+ d);
		visited[i] = true;
		depth[i] = low[i] = d;
		int childCount = 0;
		isArticulation[i] = false;
		System.out.println("^^^^^ Array at this point are ");
		System.out.println("Visited "+ Arrays.toString(visited));
		System.out.println("Depth " + Arrays.toString(depth));
		System.out.println("IA " + Arrays.toString(isArticulation));
		System.out.println("Parent " + Arrays.toString(parent));
		System.out.println("Low " + Arrays.toString(low));
		System.out.println("^^^^^");
		List adjacencies = al.getAdjacencies(i);
		Iterator iList = adjacencies.iterator();
		while(iList.hasNext()){
			int ni = (Integer) iList.next();
			System.out.println("  "+" Working on child "+ ni + " spawned from "+ i);
			if(!visited[ni]){
				parent[ni] = i;
				System.out.println("Updated parent "+ Arrays.toString(parent));
				getArticulationPoint(ni, d+1);
				System.out.println("Return point " + i);
				childCount = childCount + 1 ;
				if(low[ni] >= depth[i]){
					System.out.println("low["+ni+"] = " + low[ni]+" and depth["+i+"] = "+depth[i]);
					isArticulation[i] = true;
				}
				low[i] = Math.min(low[i], low[ni]);
				
			}
			else if( ni != parent[i]){
				System.out.println(ni+" has been visited and is not equal to " + parent[i]);
				low[i] = Math.min(low[i], depth[ni]);
			}
		}
		if( (parent[i] != -1 && isArticulation[i]) ||(parent[i] == -1 && childCount > 1) ){
			System.out.println("Root is bad " + Arrays.toString(parent));
		}
	}

	public static void main(String[] args) {
		
		System.out.println("Generating Graph");
		generateGraph();
		
		System.out.println();
		System.out.println();
		System.out.println();
		parent[0] = -1;
		getArticulationPoint(0,0);
		
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("Ultimate");
		System.out.println(Arrays.toString(isArticulation));
	}

}
