package application;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Manages the entire graph - including the nodes, edges, and matrix.
 * 
 * @author Mantas Rajackas
 *
 */
public class Graph {
	private AdjacencyMatrix matrix = null;
	private ArrayList<Node<?>> nodes = new ArrayList<>();
	private ArrayList<Edge> edges = new ArrayList<>();
	private ArrayList<Town> towns = new ArrayList<>();
	
	public ArrayList<Town> getTowns() {
		return this.towns;
	}

	public void setTowns(ArrayList<Town> towns) {
		this.towns = towns;
	}

	public Graph() {
		
	}
	
	public Graph(int size) {
		this.matrix = new AdjacencyMatrix(size);
	}
	
	public AdjacencyMatrix getMatrix() {
		return matrix;
	}
	public void setMatrix(AdjacencyMatrix matrix) {
		this.matrix = matrix;
	}

	public ArrayList<Node<?>> getNodes() {
		return nodes;
	}
	public void setNodes(ArrayList<Node<?>> nodes) {
		this.nodes = nodes;
	}

	public ArrayList<Edge> getEdges() {
		return edges;
	}
	public void setEdges(ArrayList<Edge> edges) {
		this.edges = edges;
	}

	public ArrayList<Town> getTowns() {
		return towns;
	}

	public void setTowns(ArrayList<Town> towns) {
		this.towns = towns;
	}

	/**
	 * Adds a new node into the graph.
	 * @param node - The node to add.
	 */
	public void addNode(Node<?> node) {
		node.setNodeID(matrix.getNodeCount());
		this.matrix.setNodeCount(matrix.getNodeCount()+1);
		this.nodes.add(node);
		// TODO: Increase matrix size
	}
	
	/**
	 * Connects two nodes that are already in the graph.
	 * @param source - Source node's ID.
	 * @param dest - Destination node's ID.
	 * @param edge - The Edge that connects the nodes.
	 */
	public void connect(int source, int dest, Edge edge) {
		this.getMatrix().connect(source, dest, edge);
	}
	
	/**
	 * Gets the Edge that connects two nodes.
	 * @param sourceNodeID - The source node's ID.
	 * @param destNodeID - The destination node's ID.
	 * @return The Edge between the two nodes.
	 */
	public Edge getEdge(int sourceNodeID, int destNodeID) {
		// TODO: Test this method
		return (Edge) this.matrix.getAdjMat()[sourceNodeID][destNodeID];
	}
	
	/**
	 * Finds a node by the name of its town.
	 * @param name - The name of the town.
	 * @return The node in the graph which holds a town with the required name.
	 */
	public Node<?> getNodeByTownName(String name) {
		// TODO: Test this method
		for (Node<?> n : this.nodes) {
			if (((Town) n.getContents()).getName().equals(name)) return n;
		}
		return null;
	}
	
	// PATHFINDING
	
	/** 
	 * Finds the shortest route from the nodes 'start' to 'lookingfor', based
	 * on a comparator c, which uses edge values.
	 * @param start - The node to start on.
	 * @param lookingFor - The node to end on.
	 * @param c - The comparator which determines the value to use as a cost for traveling paths.
	 * @return An array of Node objects, in order from start to finish.
	 */
	public ArrayList<Node<?>> findPath(
			Node<?> startNode,Node<?> lookingFor, Comparator<Edge> c) {
		 
		ArrayList<Node<?>> path = new ArrayList<Node<?>>(); // Contains the final path
		int pathCost = 0;
		ArrayList<Node<?>> encountered = new ArrayList<Node<?>>();
		ArrayList<Node<?>> unencountered = new ArrayList<Node<?>>();
		
		startNode.setCost(0);
		unencountered.add(startNode);
		Node<?> currentNode;
		
		do { // Loops through encountered list until it's empty
			
			currentNode = unencountered.remove(0);
			encountered.add(currentNode); // Adds current node to encountered list
			
			if (currentNode.getContents().equals(lookingFor)) { // TODO: Change the conditional here to suit sys
				// TODO: Reassemble path and return it
				
				path.add(lookingFor); // Add the destination node to the path list first.
				pathCost = currentNode.getCost();
				
				while (currentNode != startNode) {
					boolean foundPrevPathNode = false;
					for (Node<?> n : encountered) {
						// TODO
					}
				}
				
			}
			
		} while (!unencountered.isEmpty());
		
		return null; // No valid path found
	}
	
}
