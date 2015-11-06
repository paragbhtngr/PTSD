package shape;

/**
 * The base structure Coord is a 2 dimensional structure, containing 2 doubles x and y, which
 * have the x and y coordinates of a point
 * @author Parag
 *
 */
public class Coord {
	public double x;
	public double y;
	
	public double EPS = 0.00001;
	
	/**
	 * Coord constructor: takes in 2 double values and creates a Coord
	 * @param _x, x-coordinate
	 * @param _y, y-coordinate
	 */
	public Coord(double _x, double _y) {
		this.x = _x;
		this.y = _y;
	}
	
	/**
	 * Compares 2 coordinates to see if they are equal, within an acceptable margin of error
	 * @param b, the coordinate to be compared against this one
	 * @return boolean based on whether the Coord is equal or not
	 */
	public boolean equals(Coord b){
		boolean xe = (Math.abs(this.x - b.x) <= EPS);
		boolean ye = (Math.abs(this.y - b.y) <= EPS);
		return (xe && ye);
	}
}
