package graphsearch;

import java.util.List;

public interface IGraph {

	public void addEdge(int s, int d);
	
	public void deleteEdge(int s, int d);
	
	public void printGraph();
	
	public void setMaxVertices(int maxVertices) ;

	public List getAdjacencies(int i);
	
}
