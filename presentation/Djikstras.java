package presentation;

import java.util.HashMap;

public class Djikstras {
	private boolean[] visited; 
	private int[] distance;
	private int size; 
	private int minPathSum; 
	
	public Djikstras(GraphList g, int start, int dest) {
		size = g.getSize(); 
		visited = new boolean[size]; 
		distance = new int[size];
		pathDistance(g, start, dest); 
	}
	
	public void pathDistance(GraphList g, int start, int dest) {
		if(start == dest)					//no self loops 
			minPathSum = 0; 
		
		Heap minH = new Heap(); 			//min heap 
		minH.insert(new HeapNode(g.getNode(start), 0));
		HashMap<Node,Integer> m = g.getList(start);	
//		for(Node k:m.keySet())				//load heap with adjacent vertices
//			minH.insert(new HeapNode(k, m.get(k)));
//		
		for(int i = 0; i < size; i++) {
			distance[i] = Integer.MAX_VALUE; 	//set distances to "infinity" 
			visited[i] = false; 			//set all nodes to unvisited
		}
		
		distance[start] = 0; 				//starting vertex set to 0 
		
		HeapNode current = new HeapNode(g.getNode(start), 0);
		while(!minH.isEmpty()) {
			current = minH.getPriority();
			m = current.getNode().getList();
			for(Node k:m.keySet()) {
				if(!visited[k.getInd()] && m.get(k) + distance[current.getNode().getInd()] < distance[k.getInd()]) {
					distance[k.getInd()] = m.get(k) + distance[current.getNode().getInd()];
					minH.insert(new HeapNode(k,m.get(k)));
				}	
			}
			visited[current.getNode().getInd()] = true; 
		}
		minPathSum = distance[dest];
		System.out.println(minPathSum);
	}
	
	
	
}
