package application;

/**
 * 
 * @author Mantas Rajackas
 *
 * @param <C> - The contents of the node.
 */
public class Node<C> {
	private C contents;
	private int nodeID;
	private int cost = Integer.MAX_VALUE; // Used for Dijkstra's Algorithm
	
	public Node() {
		
	}
	
	public Node(C contents) {
		this.contents = contents;
	}
	
	public void setContents(C contents) {
		this.contents = contents;
	}
	public C getContents() {
		return this.contents;
	}
	
	public void setNodeID(int nodeID) {
		this.nodeID = nodeID;
	}
	public int getNodeID() {
		return this.nodeID;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
	
	public int getCost() {
		return cost;
	}

}
