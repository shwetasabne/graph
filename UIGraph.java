package graphsearch;

public class UIGraph {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("List representation");
		IGraph al = new AdjacencyGraphList(4);
		al.addEdge(0, 1);
		al.addEdge(1, 2);
		al.addEdge(1, 3);
		al.addEdge(2,3);
		al.printGraph();
	}

}
