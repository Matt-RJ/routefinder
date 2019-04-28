package application;

/**
 * 
 * @author Mantas Rajackas
 *
 */
public class Edge {
	
	private int distance;
	private int ease;
	private int danger;
	
	// The two nodes that the edge is connected to
	private Node<?> source; 
	private Node<?> dest;
	
	public Edge() {
		
	}
	
	public Edge(int distance, int ease, int danger) {
		this.distance = distance;
		this.ease = ease;
		this.danger = danger;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getEase() {
		return ease;
	}

	public void setEase(int ease) {
		this.ease = ease;
	}

	public int getDanger() {
		return danger;
	}

	public void setDanger(int danger) {
		this.danger = danger;
	}

	public Node<?> getSource() {
		return source;
	}

	public void setSource(Node<?> source) {
		this.source = source;
	}

	public Node<?> getDest() {
		return dest;
	}

	public void setDest(Node<?> dest) {
		this.dest = dest;
	}

}
