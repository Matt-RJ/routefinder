package application;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

/**
 * Manages an entire graph - including the nodes, edges, and matrix.
 * 
 * @author Mantas Rajackas
 *
 */
public class Graph {
	private ArrayList<Node<?>> nodes = new ArrayList<>();
	private ArrayList<Edge> edges = new ArrayList<>();
	private ArrayList<Town> towns = new ArrayList<>();

	public Graph() {
		
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
		this.nodes.add(node);
	}
	
	/**
	 * Connects two nodes that are already in the graph.
	 * @param source - Source node.
	 * @param dest - Destination node.
	 * @param edge - The Edge that connects the nodes.
	 */
	public void connect(Node<?> source, Node<?> dest, Edge edge) {
		edge.setSource(source);
		edge.setDest(dest);
		
		// Updating adjacency maps for both nodes
		HashMap<Node<?>, Edge> sourceMap = source.getAdjMap();
		HashMap<Node<?>, Edge> destMap = dest.getAdjMap();
		sourceMap.put(dest, edge);
		destMap.put(source, edge);
		
		this.edges.add(edge);
		
		// TODO: Test
	}
	
	/**
	 * Gets the Edge that connects two nodes.
	 * @param source - The source node.
	 * @param dest - The destination node.
	 * @return The Edge between the two nodes.
	 */
	public Edge getEdge(Node<?> source, Node<?> dest) {
		// TODO: Test
		return source.getAdjMap().get(dest);
	}
	
	/**
	 * Finds a node by the name of its town.
	 * @param name - The name of the town.
	 * @return The node in the graph which holds a town with the required name.
	 */
	public Node<?> getNodeByTownName(String name) {
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
		// TODO
		return null;
	}
	
}
