package application;

import java.util.ArrayList;

/**
 * Manages the entire graph, including the nodes, edges, and matrix.
 * @author Mantas Rajackas
 *
 */
public class Graph {
	private AdjacencyMatrix matrix = null;
	private ArrayList<Node<?>> nodes = new ArrayList<>();
	private ArrayList<Edge> edges = new ArrayList<>();
	int nextNodeID = 0;
	
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

	public int getNextNodeID() {
		return nextNodeID;
	}
	public void setNextNodeID(int nextNodeID) {
		this.nextNodeID = nextNodeID;
	}

	/**
	 * Adds a new node into the graph
	 * @param node - The node to add
	 */
	public void addNode(Node<?> node) {
		this.nodes.add(node);
		node.setNodeID(nextNodeID++); // TODO: Ensure nextNodeID iterates
	}
	
	/**
	 * Connects two nodes that are already in the graph.
	 * @param source - Source node
	 * @param dest - Destination node
	 * @param edge - The Edge that connects the nodes
	 */
	public void connect(Node<?> source, Node<?> dest, Edge edge) {
		// TODO
	}
	
	/**
	 * Connects two nodes that are already in the graph by their IDs
	 * @param sourceID - The ID of the source node
	 * @param destID - The ID of the destination node
	 * @param edge - The Edge that connects the nodes
	 */
	public void connectByID(int sourceID, int destID, Edge edge) {
		// TODO
	}
	
}
