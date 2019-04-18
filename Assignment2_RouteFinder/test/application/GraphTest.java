package application;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class GraphTest {
	
	private Graph graph = new Graph();
	
	@Before
	public void setup() {
		
		// Creates nodes with towns A through Q in them.
		for (char i = 'A'; i <= 'Q'; i++) {
			graph.addNode(new Node<Town>(new Town(String.valueOf(i))));
		}
		
		// Connects nodes
		// TODO
		
	}
	
	@Test
	public void DijkstraTest() {
		
	}
	
	@Test
	public void getNodeByTownNameReturnsCorrectTown() {

	}
	
	@Test
	public void getNodeByTownNameReturnsNullWithNoMatch() {

	}
	
	@Test
	public void getEdgeReturnsCorrectEdge() {

	}
	
}
