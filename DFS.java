package graphsearch;

import java.util.Stack;

class Vertex {
	
	public int lab ;
	public boolean visited ;
	public Vertex(int lab) {
		this.lab = lab;
		this.visited = false;
	}
}

class Graph {
	
	private final int maxVertices = 8;
	private int adjMatrix[][];
	private Vertex vertexList[];
	private Stack stack;
	
	public Graph(){
		
		vertexList = new Vertex[maxVertices];
		adjMatrix = new int[maxVertices][maxVertices];
		stack = new Stack();
	}
	
	public void createVertexList(){
		for(int i=0; i < maxVertices; i++){
			vertexList[i] = new Vertex(i);
		}
	}
	
	public void displayVertex(int v){
		System.out.println(vertexList[v].lab);
	}
	
	public void printGraph(){
		
		for(int i = 0; i < this.maxVertices; i++){
			for(int j=0; j < this.maxVertices; j++){
				System.out.print(adjMatrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public void generateAdjMatrix (){
		
		for(int i = 0; i < this.maxVertices; i++){
			for(int j = 0; j < this.maxVertices; j++){
				adjMatrix[i][j] = 0;
			}
		}


		adjMatrix[0][1] = 1 ;
		adjMatrix[1][0] = 1;
		adjMatrix[1][2] = 1;
		adjMatrix[1][7] = 1;
		adjMatrix[2][1] = 1;
		adjMatrix[2][3] = 1;
		adjMatrix[2][4] = 1;
		adjMatrix[3][2] = 1;
		adjMatrix[4][2] = 1;
		adjMatrix[4][5] = 1;
		adjMatrix[4][6] = 1;
		adjMatrix[4][7] = 1;
		adjMatrix[5][4] = 1;
		adjMatrix[6][4] = 1;
		adjMatrix[7][1] = 1;
		adjMatrix[7][4] = 1;

	}
	
	public int getAdjacentVertex(int v){
	
		for(int j=0; j < maxVertices; j++){
			
			if(adjMatrix[v][j] == 1 && vertexList[j].visited == false){
				return j;
			}
		}
		return -1;
	}
	
	
	
	public void dfs(){
		
		this.generateAdjMatrix();
		this.printGraph();
		createVertexList();
		
		vertexList[0].visited = true;
		this.displayVertex(0);
		stack.push(0);
		while(!stack.isEmpty()){

			int v = this.getAdjacentVertex((Integer) stack.peek());
			System.out.println("Peeking" + stack.peek() + "with adjacent "+ v);
			if(v == -1){
				stack.pop();
			}else{
				vertexList[v].visited = true;
				displayVertex(v);
				stack.push(v);
			}
		}
	}
}

public class DFS {
	
	Graph g = new Graph();
	
	public void dfs() {
		g.dfs();
	}
	
	public static void main(String[] args){
		
		DFS d = new DFS ();
		d.dfs();
	}
}

