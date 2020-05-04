package presentation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class DjikstrasHeap {
	private boolean[] visited; 
	private int[] distance;
	private int[] prev; 
	private int size; 
	private int minPathSum; 
	
	public DjikstrasHeap(GraphList g, int start, int dest) {
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
		
		//Minimum Priority Queue
		PriorityQueue<HeapNode> minH = new PriorityQueue<HeapNode>((Comparator<? super HeapNode>) new Comparator<HeapNode>() {
	        @Override
	        public int compare(HeapNode o1, HeapNode o2) {
	            return o1.getWeight() - o2.getWeight();
	        }
	    });			
		
		
		minH.add(new HeapNode(g.getNode(start), 0));	//first element is the starting node 
		HashMap<Node,Integer> m = g.getList(start);		//adj list of starting node 

		for(int i = 0; i < size; i++) {
			distance[i] = Integer.MAX_VALUE; 	//set distances to "infinity" 
			visited[i] = false; 			//set all nodes to unvisited
			prev[i] = -1; 					//set all previous nodes to "NULL" 
		}
		
		distance[start] = 0; 				//starting vertex set to 0 
		
		HeapNode current;
		while(!minH.isEmpty()) {
			current = minH.remove();		//remove priority 
			m = current.getNode().getList();		
			for(Node k:m.keySet()) {				//relax edges 
				if(!visited[k.getInd()] && m.get(k) + distance[current.getNode().getInd()] < distance[k.getInd()]) {
					distance[k.getInd()] = m.get(k) + distance[current.getNode().getInd()];
					minH.add(new HeapNode(k,m.get(k)));
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
