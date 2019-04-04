package application;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class GraphTest {
	
	private Graph graph = new Graph(17);
	
	@Before
	public void setup() {
		
		// Creates nodes with towns A through Q in them.
		for (char i = 'A'; i <= 'Q'; i++) {
			graph.addNode(new Node<Town>(new Town(String.valueOf(i))));
		}
		
		// Connects nodes
		graph.connect(0, 1, new Edge(3,4,2));
		graph.connect(0, 4, new Edge(2,1,1));
		graph.connect(1, 2, new Edge(3,2,1));
		graph.connect(1, 5, new Edge(7,3,5));
		graph.connect(2, 3, new Edge(2,1,1));
		graph.connect(2, 13, new Edge(1,5,3));
		graph.connect(3, 8, new Edge(4,5,1));
		graph.connect(3, 16, new Edge(4,2,8));
		graph.connect(4, 11, new Edge(5,9,10));
		graph.connect(5, 11, new Edge(1,2,1));
		graph.connect(5, 6, new Edge(4,3,6));
		graph.connect(5, 13, new Edge(8,2,6));
		graph.connect(6, 14, new Edge(1,1,9));
		graph.connect(6, 9, new Edge(5,4,8));
		graph.connect(6, 12, new Edge(6,7,2));
		graph.connect(6, 7, new Edge(3,5,6));
		graph.connect(6, 13, new Edge(3,4,2));
		graph.connect(7, 8, new Edge(1,1,9));
		graph.connect(7, 16, new Edge(1,2,3));
		graph.connect(8, 13, new Edge(1,2,3));
		graph.connect(9, 10, new Edge(3,4,3));
		graph.connect(9, 15, new Edge(1,2,20));
		graph.connect(9, 12, new Edge(3,10,4));
		graph.connect(10, 11, new Edge(4,5,6));
		graph.connect(11, 14, new Edge(2,1,1));
		graph.connect(12, 16, new Edge(2,18,3));
		graph.connect(14, 15, new Edge(3,2,5));
		
		printAdjMatrix();
	}
	
	@Test
	public void DijkstraTest() {
		
		System.out.println(((Town) graph.getNodes().get(0).getContents()).getName());
		System.out.println(((Town) graph.getNodes().get(14).getContents()).getName());
		
		
		graph.findPath(graph.getNodes().get(0),
					   graph.getNodes().get(14),
			(a,b) -> new Integer(a.getDistance()).compareTo(new Integer(b.getDistance())));
		
	}
	
	@Test
	public void getNodeByTownNameReturnsCorrectTown() {
		Node<Town> newTown = new Node<>(new Town("testTown"));
		graph.addNode(newTown);
		assertEquals(newTown, graph.getNodeByTownName("testTown"));
	}
	
	@Test
	public void getNodeByTownNameReturnsNullWithNoMatch() {
		Node<Town> newTown = new Node<>(new Town("testTown"));
		assertEquals(null, graph.getNodeByTownName("myTown"));
	}
	
	@Test
	public void getEdgeReturnsCorrectEdge() {
		Node<Town> node1 = new Node<>(new Town("Town1"));
		Node<Town> node2 = new Node<>(new Town("Town2"));
		Edge newEdge = new Edge();
		graph.addNode(node1);
		graph.addNode(node2);
		graph.connect(15, 16, newEdge);
		printAdjMatrix();
		assertEquals(newEdge, graph.getEdge(15, 16));
	}
	
	/**
	 * Prints out the adjacency matrix to the console.
	 */
	public void printAdjMatrix() {
		Object[][] mat = graph.getMatrix().getAdjMat();
		System.out.print("  ");
		for (int x = 0; x < mat[1].length; x++) {
			System.out.print(((Town) graph.getNodes().get(x).getContents()).getName()+ " ");
		}
		
		for (int i = 0; i < mat[0].length; i++) {
			System.out.println();
			System.out.print(((Town) graph.getNodes().get(i).getContents()).getName()+ " ");
			for (int j = 0; j < mat[1].length; j++) {
				if (mat[i][j] != null) {
					System.out.print(1 + " ");
				}
				else {
					System.out.print(0 + " ");
				}
			}
		}
		System.out.println();
	}
}
