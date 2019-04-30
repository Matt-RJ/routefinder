package application;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class GraphTest {
	
	private Graph graph = new Graph();
	
	Node<Town> nodeA = new Node<>(new Town("Town A"));
	Node<Town> nodeB = new Node<>(new Town("Town B"));
	Node<Town> nodeC = new Node<>(new Town("Town C"));
	Node<Town> nodeD = new Node<>(new Town("Town D"));
	Node<Town> nodeE = new Node<>(new Town("Town E"));
	Node<Town> nodeF = new Node<>(new Town("Town F"));
	Node<Town> nodeG = new Node<>(new Town("Town G"));
	Node<Town> nodeH = new Node<>(new Town("Town H"));
	Node<Town> nodeI = new Node<>(new Town("Town I"));
	Node<Town> nodeJ = new Node<>(new Town("Town J"));
	Node<Town> nodeK = new Node<>(new Town("Town K"));
	Node<Town> nodeL = new Node<>(new Town("Town L"));
	Node<Town> nodeM = new Node<>(new Town("Town M"));
	Node<Town> nodeN = new Node<>(new Town("Town N"));
	Node<Town> nodeO = new Node<>(new Town("Town O"));
	Node<Town> nodeP = new Node<>(new Town("Town P"));
	Node<Town> nodeQ = new Node<>(new Town("Town Q"));
	
	Node<Town> extra1 = new Node<>(new Town("Extra 1"));
	Node<Town> extra2 = new Node<>(new Town("Extra 2"));
	Node<Town> extra3 = new Node<>(new Town("Extra 3"));
	Node<Town> extra4 = new Node<>(new Town("Extra 4"));
	Node<Town> extra5 = new Node<>(new Town("Extra 5"));
	Node<Town> extra6 = new Node<>(new Town("Extra 6"));
	Node<Town> extra7 = new Node<>(new Town("Extra 7"));
	Node<Town> extra8 = new Node<>(new Town("Extra 8"));
	Node<Town> extra9 = new Node<>(new Town("Extra 9"));
	Node<Town> extra10 = new Node<>(new Town("Extra 10"));
	Node<Town> extra11 = new Node<>(new Town("Extra 11"));
	Node<Town> extra12 = new Node<>(new Town("Extra 12"));
	Node<Town> extra13 = new Node<>(new Town("Extra 13"));
	Node<Town> extra14 = new Node<>(new Town("Extra 14"));
	Node<Town> extra15 = new Node<>(new Town("Extra 15"));
	Node<Town> extra16 = new Node<>(new Town("Extra 16"));
	Node<Town> extra17 = new Node<>(new Town("Extra 17"));
	
	@Before
	public void setup() {
		
		// Creates nodes with towns A through Q in them.
		graph.addNode(nodeA);
		graph.addNode(nodeB);
		graph.addNode(nodeC);
		graph.addNode(nodeD);
		graph.addNode(nodeE);
		graph.addNode(nodeF);
		graph.addNode(nodeG);
		graph.addNode(nodeH);
		graph.addNode(nodeI);
		graph.addNode(nodeJ);
		graph.addNode(nodeK);
		graph.addNode(nodeL);
		graph.addNode(nodeM);
		graph.addNode(nodeN);
		graph.addNode(nodeO);
		graph.addNode(nodeP);
		graph.addNode(nodeQ);
		
		Edge edgeAB = new Edge(3,4,2);
		Edge edgeAE = new Edge(2,1,1);
		Edge edgeBC = new Edge(3,1,1);
		
		// Connects nodes
		// TODO
		graph.connect(nodeA, nodeB, edgeAB);
		graph.connect(nodeA, nodeE, edgeAE);
		graph.connect(nodeB, nodeC, edgeBC);
		graph.connect(nodeB, nodeF, new Edge(7,3,5));
		graph.connect(nodeC, nodeD, new Edge(2,1,1));
		graph.connect(nodeC, nodeN, new Edge(1,5,3));
		graph.connect(nodeD, nodeI, new Edge(4,5,1));
		graph.connect(nodeD, nodeQ, new Edge(4,2,8));
		graph.connect(nodeE, nodeL, new Edge(5,9,10));
		graph.connect(nodeF, nodeL, new Edge(1,2,1));
		graph.connect(nodeF, nodeN, new Edge(8,2,6));
		graph.connect(nodeF, nodeG, new Edge(4,3,6));
		graph.connect(nodeG, nodeN, new Edge(3,4,2));
		graph.connect(nodeG, nodeH, new Edge(3,5,6));
		graph.connect(nodeG, nodeM, new Edge(6,7,2));
		graph.connect(nodeG, nodeJ, new Edge(5,4,8));
		graph.connect(nodeG, nodeO, new Edge(1,1,9));
		graph.connect(nodeH, nodeI, new Edge(1,1,9));
		graph.connect(nodeH, nodeQ, new Edge(1,2,3));
		graph.connect(nodeI, nodeN, new Edge(1,2,3));
		graph.connect(nodeJ, nodeK, new Edge(3,4,3));
		graph.connect(nodeJ, nodeP, new Edge(1,1,20));
		graph.connect(nodeJ, nodeM, new Edge(3,10,4));
		graph.connect(nodeK, nodeL, new Edge(4,5,6));
		graph.connect(nodeL, nodeO, new Edge(2,1,1));
		graph.connect(nodeM, nodeQ, new Edge(2,18,3));
		graph.connect(nodeO, nodeP, new Edge(3,2,5));
		
		
	}
	
	@Test
	public void permutationTest() {
		
		// Extra nodes for permutation testing
		
		graph.addNode(extra1);
		graph.addNode(extra2);
		graph.addNode(extra3);
		graph.addNode(extra4);
		graph.addNode(extra5);
		graph.addNode(extra6);
		graph.addNode(extra7);
		graph.addNode(extra8);
		graph.addNode(extra9);
		graph.addNode(extra10);
		graph.addNode(extra11);
		graph.addNode(extra12);
		graph.addNode(extra13);
		graph.addNode(extra14);
		graph.addNode(extra15);
		graph.addNode(extra16);
		graph.addNode(extra17);
		
		graph.connect(extra1, nodeA, new Edge());
		graph.connect(extra2, nodeB, new Edge());
		graph.connect(extra3, nodeC, new Edge());
		graph.connect(extra4, nodeD, new Edge());
		graph.connect(extra5, nodeE, new Edge());
		graph.connect(extra6, nodeF, new Edge());
		graph.connect(extra7, nodeG, new Edge());
		graph.connect(extra8, nodeH, new Edge());
		graph.connect(extra9, nodeI, new Edge());
		graph.connect(extra10, nodeJ, new Edge());
		graph.connect(extra11, nodeK, new Edge());
		graph.connect(extra12, nodeL, new Edge());
		graph.connect(extra13, nodeM, new Edge());
		graph.connect(extra14, nodeN, new Edge());
		graph.connect(extra15, nodeO, new Edge());
		graph.connect(extra16, nodeP, new Edge());
		graph.connect(extra17, nodeQ, new Edge());
		
		graph.connect(extra1, extra2, new Edge());
		graph.connect(extra2, extra3, new Edge());
		graph.connect(extra3, extra4, new Edge());
		graph.connect(extra4, extra5, new Edge());
		graph.connect(extra5, extra6, new Edge());
		graph.connect(extra6, extra7, new Edge());
		graph.connect(extra7, extra8, new Edge());
		graph.connect(extra8, extra9, new Edge());
		graph.connect(extra9, extra10, new Edge());
		graph.connect(extra10, extra11, new Edge());
		graph.connect(extra11, extra12, new Edge());
		graph.connect(extra12, extra13, new Edge());
		graph.connect(extra13, extra14, new Edge());
		graph.connect(extra14, extra15, new Edge());
		graph.connect(extra15, extra16, new Edge());
		graph.connect(extra16, extra17, new Edge());
		graph.connect(extra17, extra1, new Edge());
		
		ArrayList<ArrayList<Node<?>>> permutations = graph.findPathPermutations(nodeA, nodeF, null);
		int nr = 0;
		for (ArrayList<Node<?>> i : permutations) {
			System.out.println("Permutation " + nr + ":");
			for (Node<?> j : i) {
				System.out.println(((Town) j.getContents()).getName());
			}
			System.out.println();
			nr++;
		}
	}
	
	@Test
	public void DijkstraTestDistance() {
		ArrayList<Node<?>> path = graph.findShortestPath(nodeA, nodeB, "distance");
		System.out.println("A-F by distance");
		for (Node<?> n : path) {
			System.out.println(((Town) n.getContents()).getName());
		}
		System.out.println();
	}
	
	@Test
	public void DijkstraTestEase() {
		ArrayList<Node<?>> path = graph.findShortestPath(nodeA, nodeF, "ease");
		System.out.println("A-F by ease");
		for (Node<?> n : path) {
			System.out.println(((Town) n.getContents()).getName());
		}
		System.out.println();
	}
	
	@Test
	public void DijkstraTestDanger() {
		ArrayList<Node<?>> path = graph.findShortestPath(nodeA, nodeF, "danger");
		System.out.println("A-F by danger");
		for (Node<?> n : path) {
			System.out.println(((Town) n.getContents()).getName());
		}
		System.out.println();
	}
	
	@Test
	public void DijkstraTestInvalidWeight() {
		assertThrows(IllegalArgumentException.class, () -> graph.findShortestPath(nodeA, nodeF, "otherVariable"));	
		
	}
	
	@Test
	public void getNodeByTownNameReturnsCorrectNode() {
		assertEquals(nodeA, graph.getNodeByTownName("Town A"));
		assertEquals(nodeD, graph.getNodeByTownName("Town D"));
		assertEquals(nodeQ, graph.getNodeByTownName("Town Q"));
		assertEquals(nodeH, graph.getNodeByTownName("Town H"));
		assertEquals(nodeE, graph.getNodeByTownName("Town E"));
		assertEquals(nodeB, graph.getNodeByTownName("Town B"));
		assertEquals(nodeC, graph.getNodeByTownName("Town C"));
	}
	
	@Test
	public void getNodeByTownNameReturnsNullWithNoMatch() {
		assertEquals(null, graph.getNodeByTownName("Town Z"));
	}
	
	
}
