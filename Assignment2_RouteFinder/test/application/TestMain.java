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
		Graph graph = new Graph(10);
		
		// Creates nodes with contents a through j
		for (char i = 'a'; i < 'k'; i++) {
			graph.addNode(new Node<Character>(i));
			System.out.println(i);
		}
	}
}
