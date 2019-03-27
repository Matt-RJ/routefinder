package application;

public class AdjacencyMatrix {
	private Object[][] adjMat;
	int nodeCount;
	
	public AdjacencyMatrix() {
		
	}
	
	public AdjacencyMatrix(int size) {
		this.adjMat = new Object[size][size]; // All null by default
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
	
	
	public void connect(int sourceID, int destID, Edge edge) {
		adjMat[destID][sourceID] = adjMat[sourceID][destID] = edge;
	}
	
}
