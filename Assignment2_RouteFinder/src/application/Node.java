package application;

import java.util.HashMap;

/**
 * 
 * @author Mantas Rajackas
 *
 * @param <C> - The contents of the node.
 */
public class Node<C> {
	private C contents;
	private int nodeID;
	
	/*
	 * Each key is a connected Node object, each value is the edge
	 * that connects this node to the node in the key.
	 */
	private HashMap<Node<?>, Edge> adjMap = new HashMap<>();
	private int cost = Integer.MAX_VALUE; // Used for Dijkstra's Algorithm
	
	public Node() {
		
	}
	
	public Node(C contents) {
		this.contents = contents;
	}

	public C getContents() {
		return contents;
	}

	public void setContents(C contents) {
		this.contents = contents;
	}

	public int getNodeID() {
		return nodeID;
	}

	public void setNodeID(int nodeID) {
		this.nodeID = nodeID;
	}

	public HashMap<Node<?>, Edge> getAdjMap() {
		return adjMap;
	}

	public void setAdjMap(HashMap<Node<?>, Edge> adjList) {
		this.adjMap = adjList;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

}
