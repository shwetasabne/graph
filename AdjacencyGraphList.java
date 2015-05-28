package graphsearch;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AdjacencyGraphList implements IGraph {
	
	private int maxVertices;

	public int getMaxVertices() {
		return maxVertices;
	}

	public void setMaxVertices(int maxVertices) {
		this.maxVertices = maxVertices;
	}
	
	private HashMap<Integer, List> alist ;

	public List getAdjacencies (int vertex) {
		
		List adj = alist.get(vertex);
		return adj;
	}
	
	public AdjacencyGraphList (int maxVertices){
		
		this.maxVertices = maxVertices;
		
		this.alist = new HashMap<Integer, List>();
		
		for(int i=0; i < this.maxVertices; i++){
		
			this.alist.put(i, new LinkedList());
		}
	}
	
	public void addEdge(int s, int d){
		


		List temp = (LinkedList)this.alist.get(s);
		temp.add(d);
		this.alist.put(s, temp);
		
		temp = (LinkedList)this.alist.get(d);
		temp.add(s);
		this.alist.put(d, temp);
	}
	
	public void deleteEdge(int s, int d){
	//	System.out.println("Deleting "+s + " " +d );
		List temp = (LinkedList)this.alist.get(s);
	//	System.out.println("orig source "+ temp.toString());
		for(Iterator<Integer> it = temp.iterator(); it.hasNext();){
			if(it.next() == d){
				it.remove();
				break;
			}
		}
	//	System.out.println("change source "+ temp.toString());
		this.alist.put(s, temp);
		
		List temp1 = (LinkedList)this.alist.get(d);
	//	System.out.println("dest source "+ temp1.toString());
		for(Iterator<Integer> it = temp1.iterator(); it.hasNext();){
			if(it.next() == s){
				it.remove();
				break;
			}
		}
		this.alist.put(d, temp1);
	//	System.out.println("change dest "+ temp1.toString());
	}
	
	public void printGraph(){
		
		Iterator itr = this.alist.entrySet().iterator();
		while(itr.hasNext()){
			
			Map.Entry pair = (Map.Entry)itr.next();
			
			List temp = (LinkedList)pair.getValue();
			Iterator itrList = temp.iterator();
			System.out.print(pair.getKey() + " --> ");
			while(itrList.hasNext()){
				System.out.print(itrList.next() + "  --> ");
			} 
			System.out.println();
		}
	}
}
