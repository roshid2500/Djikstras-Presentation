package presentation;

import java.util.ArrayList;
import java.util.List;

public class Heap {
	private List<HeapNode> heap;  
	
	public Heap() {
		heap = new ArrayList<HeapNode>(); 
	}
	
	private int getLeft(int pos) {
		return 2 * pos + 1; 
	}
	
	private int getRight(int pos) {
		return 2 * pos + 2;
	}
	
	private int getParent(int pos) {
		return (pos - 1) / 2; 
	}
	
	private boolean isLeaf(int pos) {
		return (pos >= heap.size() / 2) && (pos < heap.size());
	}
	
	
	private void swap(int a, int b) {
		HeapNode temp = heap.get(b); 
		heap.set(b, heap.get(a));
		heap.set(a, temp);
	}
	
	
	
	
	private void siftDown(int pos) {
		//If there are only two elements in the heap then this is the only check needed 
		if(pos < 0 || pos >= heap.size()) 
			return; 
		while(!isLeaf(pos)) {
			int j = getLeft(pos);
			//check if the right child is greater than the left 
			if((j + 1) < heap.size() && heap.get(j+1).getWeight() < heap.get(j).getWeight()) 
				j++;
			//pos is in the right spot 
			if(heap.get(pos).getWeight() < heap.get(j).getWeight())
				return;

			swap(pos, j);
			//check whether the position u swapped with is in the right spot 
			pos = j; 
		}
	}
	
	
	
	public void insert(HeapNode n) {
		System.out.println("Here");
		heap.add(n);
		if(heap.size() == 1 )
			return;
		siftDown(0);
	}
	
	
	
	
	public HeapNode getPriority() {
		if(heap.size() == 0)
			throw new IllegalArgumentException("No priority");
		HeapNode retVal = heap.get(0);
		swap(0, heap.size() - 1);
		heap.remove(heap.size() - 1);
		siftDown(0);
		return retVal; 
	}
	
	
	
	
	public void prettyPrint() {
		for(int i = 0; i < heap.size(); i++) {
			System.out.print(heap.get(i) + " ");
		}
		System.out.println(); 
	}
	
	
	
	public boolean isEmpty() {
		return heap.isEmpty();
	}
	
	public boolean contains(Node n) {
		if(heap.isEmpty())
			return false; 
		for(int i = 0; i < heap.size(); i++)
			if(heap.get(i).getNode() == n) 
				return true; 
		
		return false; 
	}

}
