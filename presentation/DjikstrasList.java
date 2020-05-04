package presentation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DjikstrasList {
	private boolean[] visited; 
	private int[] distance;
	private int[] prev; 
	private int size; 
	private int minPathSum; 
	
	public DjikstrasList(GraphList g, int start, int dest) {
		size = g.getSize(); 
		visited = new boolean[size]; 
		distance = new int[size];
		prev = new int[size];
		pathDistance(g, start, dest); 
		//printPath(dest);
	}
	
	
	/*
	 * Calculates the minimum path between the starting node and 
	 * destination node 
	 */
	public void pathDistance(GraphList g, int start, int dest) {
		if(start == dest) {					//no self loops 
			minPathSum = 0;
			return;
		}
		
		HashMap<Node,Integer> m = g.getList(start);			//adjacency list of starting node
		
		List<ListNode> l = new ArrayList<ListNode>(); 		//list to hold edges
		l.add(new ListNode(g.getNode(start), 0));
		
		for(int i = 0; i < size; i++) {
			distance[i] = Integer.MAX_VALUE; 	//set distances to "infinity" 
			visited[i] = false; 			//set all nodes to unvisited
			prev[i] = -1; 
		}
		
		distance[start] = 0; 				//starting vertex set to 0 
		
		ListNode current;
		while(!l.isEmpty()) {
			current = l.get(0);
			int ind = 0; 
			for(int i = 1; i < l.size(); i++) {				//get minimum weight node
				if(l.get(i).getWeight() < current.getWeight()) {
					current = l.get(i);
					ind = i; 
				}
			}
			l.remove(ind);
			m = current.getNode().getList();
			for(Node k:m.keySet()) {				//relax edges
				if(!visited[k.getInd()] && m.get(k) + distance[current.getNode().getInd()] < distance[k.getInd()]) {
					distance[k.getInd()] = m.get(k) + distance[current.getNode().getInd()];
					l.add(new ListNode(k,m.get(k)));
					prev[k.getInd()] = current.getNode().getInd();
				}	
			}
			visited[current.getNode().getInd()] = true; 		//each iteration of the while loop visits a node
		}
		minPathSum = distance[dest];
	}
	
	/*
	 * Prints the shortest path
	 */
	public void printPath(int dest) {
		List<Integer> path = new ArrayList<Integer>(); 
		path.add(dest);
		int currentInd = dest; 
		while(prev[currentInd] != -1) {
			path.add(0 , prev[currentInd]); 
			currentInd = prev[currentInd];
		}
		
		System.out.println("PATH_____________");
		for(int i = 0; i < path.size(); i++) {
			System.out.println("Node " + i + ": " + path.get(i));
		}
	}
	
	/*
	 * Returns minPath
	 */
	public int minPath() {
		return minPathSum; 
	}
	
}
