package application;

import java.util.ArrayList;

public class TestMain {
	static Graph graph = new Graph();
	
	public static void main(String args[]) {
		Node<Town> a = new Node<>(new Town("Town A"));
		Node<Town> b = new Node<>(new Town("Town B"));
		Node<Town> c = new Node<>(new Town("Town C"));
		Node<Town> d = new Node<>(new Town("Town D"));
		
		graph.addNode(a);
		graph.addNode(b);
		graph.addNode(c);
		graph.addNode(d);
		
		graph.connect(a, b, new Edge());
		graph.connect(a, c, new Edge());
		graph.connect(a, d, new Edge());
		graph.connect(b, d, new Edge());
		graph.connect(c, d, new Edge());
		
		System.out.println();
		ArrayList<Node<?>> allNodes = graph.getNodes();
		System.out.println();
	}
}
