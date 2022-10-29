
package question;

public class House {
	
	private String name;
	private int x;
	private int y;
	
	/* Create Getters, setters for the House class for each attribute */
	
	public House(String name, int x, int y) {
		this.name = name;
		this.x = x;
		this.y = y;
	}
	
	//DO_NOT_EDIT_ANYTHING_ABOVE_THIS_LINE
	
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
		
	public String toString() {
		return name +" " + x + " "+ y;
	}
	//DO_NOT_EDIT_ANYTHING_BELOW_THIS_LINE
	
	
}

