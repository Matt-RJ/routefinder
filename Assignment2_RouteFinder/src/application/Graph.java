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
	
}
