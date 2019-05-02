package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Manages an entire graph - including the nodes, edges, and matrix.
 * The graph
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
	 * Adds a new node into the graph. Ignores duplicate nodes.
	 * @param node - The node to add.
	 */
	public void addNode(Node<?> node) {
		for (Node<?> n : this.nodes) {
			if (n.equals(node)) return;
		}
		this.nodes.add(node);
	}
	
	/**
	 * Connects two nodes that are already in the graph.
	 * @param source - Source node.
	 * @param dest - Destination node.
	 * @param edge - The Edge that connects the nodes.
	 */
	public void connect(Node<?> source, Node<?> dest, Edge edge) {
		for (Edge e: this.edges) {
			if (e.equals(edge)) return; // Checks for duplicate edges
		}
		
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
	
	public ArrayList<ArrayList<Node<?>>> findPathPermutations(Node<?> startNode, Node<?> lookingFor, ArrayList<Node<?>> encountered) {
		
		// TODO
		
		ArrayList<ArrayList<Node<?>>> result = null, temp2;
		
		if (startNode.equals(lookingFor)) {
			ArrayList<Node<?>> temp = new ArrayList<>();
			temp.add(startNode);
			result = new ArrayList<>();
			result.add(temp);
			return result;
		}
		
		if (encountered == null) encountered = new ArrayList<>();
		encountered.add(startNode);
		
		for (Node<?> adjNode : startNode.getAdjMap().keySet()) {
			if (!encountered.contains(adjNode)) {
				temp2 = findPathPermutations(adjNode, lookingFor, new ArrayList<>(encountered));
				
				if (temp2 != null) {
					for (ArrayList<Node<?>> x : temp2) {
						x.add(0,startNode);
					}
					if (result == null) result = temp2;
					else result.addAll(temp2);
				}
			}
		}
		
		return result;
	}
	
	/** 
	 * Finds the shortest route from the nodes 'start' to 'lookingfor', based
	 * on a comparator c, which uses edge values.
	 * @param startNode - The node to start on.
	 * @param lookingFor - The node to end on.
	 * @param weightType - Determines what Edge weight to use, can be "distance", "ease", or "danger".
	 * @return An array of Node objects, in order from start to finish.
	 */
	public ArrayList<Node<?>> findShortestPath(Node<?> startNode, Node<?> lookingFor, String weightType) {
		// TODO: Only uses the distance for now. Update to allow the use of any variable in an Edge
		
		ArrayList<Node<?>> path = new ArrayList<>(); // Will contain the final path
		ArrayList<Node<?>> encountered = new ArrayList<>(); // Nodes already visited
		ArrayList<Node<?>> unencountered = new ArrayList<>(); // Nodes yet to visit
		
		startNode.setCost(0);
		unencountered.add(startNode);
		Node<?> currentNode;
		
		do { // Until no more nodes left in unencountered
			currentNode = unencountered.remove(0); // Gets the next unencountered node as current node
			encountered.add(currentNode);		   // and makes it encountered
			
			if (currentNode.equals(lookingFor)) { // Destination node found - Assemble path and return it
				path.add(currentNode);
				// TODO: Consider adding a way to get the cost of the entire path
				
				while(currentNode != startNode) {
					boolean foundPrevPathNode = false;
					for (Node<?> n : encountered) {
						for (Node<?> adjNode : n.getAdjMap().keySet()) { // For each node connected to n
							// TODO
							if (adjNode == currentNode && currentNode.getCost() 
									- n.getAdjMap().get(adjNode).getWeight(weightType) == n.getCost()) {
								path.add(0, n);
								currentNode = n;
								foundPrevPathNode = true;
								break;
							}
							if (foundPrevPathNode) break;
						}
					}
				}
				
				// Resets node costs to default
				for (Node<?> n : encountered) n.setCost(Integer.MAX_VALUE);
				for (Node<?> n : unencountered) n.setCost(Integer.MAX_VALUE);
				
				return path; // Shortest path found
			}
			
			// Destination node not found yet
			HashMap<Node<?>,Edge> adjMap = currentNode.getAdjMap();
			for (Node<?> n : adjMap.keySet()) { // For each adjacent node in currentNode
				if (!encountered.contains(n)) {
					
					// TODO: The line below uses getDistance instead a chosen method
					n.setCost(Integer.min(n.getCost(), currentNode.getCost()+adjMap.get(n).getWeight(weightType)));
					unencountered.add(n);
				}
			}
			Collections.sort(unencountered, (n1,n2)->n1.getCost()-n2.getCost());
		}
		while (!unencountered.isEmpty());
		return null; // No path found
	}
}
