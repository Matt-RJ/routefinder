package application;

public class AdjacencyMatrix {
	private Object[][] matrix;
	
	public AdjacencyMatrix() {
		
	}
	
	public AdjacencyMatrix(int size) {
		this.matrix = new Object[size][size]; // All null by default
	}
		public void setMatrix(Object[][] matrix) {
		this.matrix = matrix;
	}
	public Object[][] getMatrix() {
		return this.matrix;
	}
	
}
