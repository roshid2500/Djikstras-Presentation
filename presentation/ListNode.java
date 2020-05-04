package presentation;

public class ListNode {
	private Node v; 
	private int w; 
	
	public ListNode(Node vert, int weight) {
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
