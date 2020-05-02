package presentation;

import java.util.HashMap;

public class GraphList {
	private Node[] lists; 		//list of the nodes in graph 
	private int size; 			//number of vertices in graph 
	
	/*
	 * Initialize the adjacency list.
	 * Note that number of vertices must be known prior to creation 
	 */
	public GraphList(int size) {
		this.size = size; 
		lists = new Node[size];
		for(int i = 0; i < size; i++) {
			lists[i] = new Node(i); 
		}
	}
	
	/*
	 * Adds an edge to specified node
	 */
	public boolean addEdge(int vert, int dest, int weight) {
		if(vert >= size || dest >= size)
			return false; 
		lists[vert].addEdge(lists[dest], lists[dest], weight);
		return true;
	}
	
	/*
	 * Gets node associated with lists index
	 */
	public Node getNode(int vert) {
		return lists[vert]; 
	}
	
	/*
	 * Gets adj list associated with lists index
	 */
	public HashMap<Node,Integer> getList(int vert) {
		return lists[vert].getList(); 
	}
	
	/*
	 * Prints out all the vertices each vertex is connected to 
	 */
	public void prettyPrint() {
		for(int i = 0; i < size; i++) {
			System.out.print("Node " + i + ": " );
			lists[i].prettyPrint();
		}
	}
	
	/*
	 * returns #vertices
	 */
	public int getSize() {
		return size;
	}
	
	public int getWeight(Node a, Node b) {
		return a.getList().get(b);
	}
}
