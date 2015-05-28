package graphsearch;

import java.util.List;

public class AdjacencyGraphMatrix implements IGraph {

	private int maxVertices;

	public int getMaxVertices() {
		return maxVertices;
	}

	public void setMaxVertices(int maxVertices) {
		this.maxVertices = maxVertices;
	}
	
	private int[][] matrix;
	
	public AdjacencyGraphMatrix(int maxVertices){
		
		this.maxVertices = maxVertices;
		this.matrix = new int [this.maxVertices][this.maxVertices];
		for(int i=0; i< this.maxVertices ; i++){
			for(int j=0; j< this.maxVertices; j++){
				if(i == j)
					this.matrix[i][j] = 1;
				else
					this.matrix[i][j] = 0;
			}
		}
	}
	
	public void addEdge(int s, int d){
		
		this.matrix[s][d] = 1;
		this.matrix[d][s] = 1;
	}
	
	public void deleteEdge(int s, int d){
		this.matrix[s][d] = 1;
		this.matrix[d][s] = 1;
	}
	
	public void printGraph(){
		
		for(int i=0; i<this.maxVertices; i++){
			for(int j=0; j< this.maxVertices; j++){
				
				System.out.print(this.matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	@Override
	public List getAdjacencies(int i) {
		// TODO Auto-generated method stub
		return null;
	}
}
