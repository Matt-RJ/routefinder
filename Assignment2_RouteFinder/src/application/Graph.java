package application;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Manages the entire graph, including the nodes, edges, and matrix.
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

	/**
	 * Adds a new node into the graph
	 * @param node - The node to add
	 */
	public void addNode(Node<?> node) {
		node.setNodeID(matrix.getNodeCount());
		matrix.setNodeCount(matrix.getNodeCount()+1);
		this.nodes.add(node);
	}
	
	/**
	 * Connects two nodes that are already in the graph.
	 * @param source - Source node's ID
	 * @param dest - Destination node's ID
	 * @param edge - The Edge that connects the nodes
	 */
	public void connect(int source, int dest, Edge edge) {
		this.getMatrix().connect(source, dest, edge);
	}
	
	
	// PATHFINDING
	
	/* 
	 * Instead of having 3 Dijkstra's algorithm methods - one for distance, one for
	 * danger, one for ease, using a comparator ought to work instead. When the
	 * method is called from the controller, it's called with a lambda expression
	 * based on the field required in the edges between nodes. The current method
	 * is a skeleton to convey the idea - the actual implementation details can be
	 * discussed in person.
	 * 
	 * - Mantas.
	 */
	
	/** 
	 * Finds the shortest route from the nodes 'start' to 'lookingfor', based
	 * on a comparator c, which uses edge values.
	 * @param start
	 * @param lookingFor
	 * @param c
	 * @return
	 */
	public ArrayList<Node<?>> findPath(
			Node<?> start,Node<?> lookingFor, Comparator<Node<?>> c) {
		 
		ArrayList<Node<?>> path = new ArrayList<Node<?>>(); // Contains the final path
		ArrayList<Node<?>> encountered = new ArrayList<Node<?>>();
		ArrayList<Node<?>> unencountered = new ArrayList<Node<?>>();
		
		start.setCost(0);
		unencountered.add(start);
		Node<?> currentNode;
		
		do { // Loops through encountered list
			
			currentNode = unencountered.remove(0);
			encountered.add(currentNode);
			
			if (currentNode.equals(lookingFor)) { // TODO: Change the conditional here to suit sys
				// TODO: Reassemble path and return it
			}
			
		} while (!unencountered.isEmpty());
		
		
		return null;
	}
	
}
