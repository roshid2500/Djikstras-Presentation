package presentation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Node {
	private HashMap<Node, Integer> adj; 	//adjacency list
	private int listIndex; 					//index in GraphList.lists[]
	
	public Node(int ind) {
		adj = new HashMap<Node,Integer>();
		listIndex = ind; 
	}
	
	/*
	 * Adds an edge between given vert to dest 
	 * Note this is an undirected graph 
	 */
	public void addEdge(Node vert, Node dest, int w) {
		this.adj.put(dest, w);
	}
	
	/*
	 * returns adj list
	 */
	public HashMap<Node,Integer> getList(){
		return adj; 
	}
	
	/*
	 * returns given GraphList.lists[] index
	 */
	public int getInd() {
		return listIndex; 
	}
	
	/*
	 * Prints out all the nodes this.Node is connected to
	 */
	public void prettyPrint() {
		for(Node vert: adj.keySet()) {
			System.out.print(vert.getInd() + " ");
		}
		System.out.println(); 
	}
}
