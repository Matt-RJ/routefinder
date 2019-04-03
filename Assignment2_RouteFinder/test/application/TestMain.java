package application;

import application.Graph;
import application.Node;

/**
 * Testing class for Graph with no GUI.
 * @author Mantas Rajackas
 *
 */
public class TestMain {
	
	public static void main(String args[]) {
		Graph graph = new Graph(5);
		
		// Creates nodes with contents a through j
		for (char i = 'a'; i < 'f'; i++) {
			graph.addNode(new Node<Character>(i));
			System.out.println(i);
			graph.connect(3, 4, new Edge());
			graph.connect(2, 0, new Edge());
		}
		
		Object[][] mat = graph.getMatrix().getAdjMat();
		for (int i = 0; i < mat[0].length; i++) {
			System.out.println();
			for (int j = 0; j < mat[1].length; j++) {
				if (mat[i][j] != null) {
					System.out.print(1);
				}
				else {
					System.out.print(0);
				}
			}
		}
	}
}
