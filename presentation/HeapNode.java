package presentation;

public class HeapNode {
	private Node v; 
	private int w; 
	
	public HeapNode(Node vert, int weight) {
		v = vert; 
		w = weight; 
	}
	
	public Node getNode() {
		return v; 
	}
	
	public int getWeight() {
		return w; 
	}
	
}
