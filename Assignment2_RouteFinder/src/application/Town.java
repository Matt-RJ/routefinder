package application;

public class Town {
	private String name;
	private int x, y;
	
	public Town() {
		
	}
	
	public Town(String name) {
		this.name = name;
	}
	
	public Town(String name, int x, int y) {
		this.name = name;
		this.x = x;
		this.y = y;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
