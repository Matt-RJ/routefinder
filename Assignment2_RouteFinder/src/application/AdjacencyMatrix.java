package application;

/**
 * 
 * @author Mantas Rajackas
 *
 */
public class AdjacencyMatrix {
	
	private Object[][] adjMat; // The adjacency matrix itself
	int nodeCount; // The number of nodes to be in the matrix
	
	public AdjacencyMatrix() {
		
	}
	
	public AdjacencyMatrix(int size) {
		this.adjMat = new Object[size][size]; // All null by default
		this.nodeCount = size;
	}
	
	public void setAdjMat(Object[][] matrix) {
		this.adjMat = matrix;
	}
	public Object[][] getAdjMat() {
		return this.adjMat;
	}

	public int getNodeCount() {
		return nodeCount;
	}

	public void setNodeCount(int nodeCount) {
		this.nodeCount = nodeCount;
	}
	
	public void increaseSize() {
		// TODO: Fix this
		int size = this.adjMat[0].length+1;
		Object[][] newAdjMat = new Object[size][size];
		System.arraycopy(this.adjMat, 0, newAdjMat, 0, size);
	}
	
	/**
	 * Connects two nodes in the graph with an edge.
	 * @param sourceID - The ID of the source node
	 * @param destID - The ID of the destination node
	 * @param edge - The Edge object that connects them
	 */
	public void connect(int sourceID, int destID, Edge edge) {
		adjMat[destID][sourceID] = adjMat[sourceID][destID] = edge;
	}
	
	/**
	 * Disconnects two nodes in the graph.
	 * @param sourceID - The ID of the source node
	 * @param destID - The ID of the destination node
	 */
	public void disconnect(int sourceID, int destID) {
		adjMat[destID][sourceID] = adjMat[sourceID][destID] = null;
	}
	
	/**
	 * Determines whether two nodes are connected by an edge
	 * @param sourceID
	 * @param destID
	 * @return True if an edge exists between the two nodes, false otherwise.
	 */
	public boolean isConnected(int sourceID, int destID) {
		return adjMat[destID][sourceID] == null;
	}
	
}
