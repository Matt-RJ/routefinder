package application;

/**
 * Connects two nodes together in a Graph object.
 * @author Mantas Rajackas
 *
 */
public class Edge {
	
	// Weight types
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
	
	/**
	 * Gets either the distance, ease, or danger of an Edge 
	 * @param weightType - The weight type to get, can be either "distance", "ease", or "danger".
	 * @return 
	 * @throws IllegalArgumentException if weightType doesn't match a weight field in Edge.
	 */
	public int getWeight(String weightType) throws IllegalArgumentException {
		switch (weightType.toLowerCase()) {
		case ("distance"):
			return this.distance;
		case ("ease"):
			return this.ease;
		case ("danger"):
			return this.danger;
		default:
			throw new IllegalArgumentException("The field " + weightType.toLowerCase() 
										 + " does not exist in Edge.");
		}
	}

}
