package shape;

import java.util.*;
/**
 * The Shape class is the groundwork on which the Triangle, Quadrilateral and Ellipse classes are based on.
 * It creates a Shape object with a list of coordinates (coordList), angles (angleList) and sides (sideList)
 * It has 2 doubles to store the x and y coordinates and a double to store the angle of rotation
 * 
 * It also has an enum ShapeType which can have one of the accepted values.
 *  
 * @author Parag
 *
 */
public class Shape {
	public double EPS = 0.00001;
	
	public List<Coord> coordList = null;
	public List<Double> angleList = new ArrayList<Double>(); //Generated from coordList
	public List<Double> sideList = new ArrayList<Double>(); //Generated from coordList
	
	public double xCoord = 0.0;
	public double yCoord = 0.0;
	public double rotateAngle = 0.0;
	
	public ShapeType type = ShapeType.UNDEFINED;
	
	public Shape(){
		//base constructor to create an empty shape
	};
	
	/**
	 * Function to set/reset the coordinates of the shape
	 * @param _coordList, the list of the coordinates
	 * @return an object of type Shape
	 */
	public void setCoords(List<Coord> _coordList) {
		this.coordList = _coordList;
		calcAttr();
	}
	
	/**
	 * Returns the length of a segment between any two given coordinates 
	 * @param p1, the first coordinate
	 * @param p2, the second coordinate
	 * @return double length
	 */
	public double getLength(Coord p1, Coord p2) {
		return Math.sqrt( (Math.pow((p1.x - p2.x), 2)) + (Math.pow(p1.y - p2.y, 2)));
	}
	
	/**
	 * Returns an angle given three sides s12 s23 and s13 such that points 1,2 and 3 are connected
	 * The angle returned is the angle at vertex 2, i.e. the angle between s12 and s23
	 * @param s12
	 * @param s23
	 * @param s13
	 * @return double angle
	 */
	//Coord p2 is the vertex, so the function returns angle bounded by sides s12 and s23
	public double getAngle(double s12, double s23, double s13){
		return Math.toDegrees(Math.acos((Math.pow(s12, 2)+Math.pow(s23, 2)-Math.pow(s13, 2))/(2*s12*s23)));
	}
	public Shape( List<Coord> _coordList ) {
		coordList = _coordList;
		calcAttr();
	}

	/**
	 * A function that calculates the attributes of the shape based on the list of coordinates.
	 * The function assumes that a coordinate list is provided and then calculates the angle list
	 * and side list from those coordinates, as well as the center of the shape
	 * 
	 * Returns nothing, but sets the values of angleList, sideList, xCoord and yCoord
	 */
	public void calcAttr() {
		
		double sumX = 0;
		double sumY = 0;
		
		this.angleList = new ArrayList<Double>(); //Generated from coordList
		this.sideList = new ArrayList<Double>(); //Generated from coordList
		
		this.xCoord = 0.0;
		this.yCoord = 0.0;
		
		for(int i = 0; i< this.coordList.size();i++) {
			Coord p1,p2,p3;
			if(i == 0){ //for the first element in coordList
				p1 = this.coordList.get(this.coordList.size()-1);
				p2 = this.coordList.get(i);
				p3 = this.coordList.get(i+1);
			}
			else if(i == this.coordList.size() - 1){ //for the last element in coordList
				p1 = this.coordList.get(i-1);
				p2 = this.coordList.get(i);
				p3 = this.coordList.get(0);
			}
			else { //for every other element in coordList
				p1 = this.coordList.get(i-1);
				p2 = this.coordList.get(i);
				p3 = this.coordList.get(i+1);				
			}	
			
			double s12 = getLength(p1,p2);
			double s23 = getLength(p2,p3);
			double s13 = getLength(p1,p3);
			
			this.sideList.add(s23);
			this.angleList.add(getAngle(s12,s23,s13));
			
			sumX += coordList.get(i).x;
			sumY += coordList.get(i).y;
		}
		
		this.xCoord = sumX/(this.coordList.size());
		this.yCoord = sumY/(this.coordList.size());
	}
	
	/**
	 * Set the center of the shape to a given set of coordinates
	 * @param x, the x coordinate
	 * @param y, the y coordinate
	 */
	public void setCenter(double x, double y) {
		this.xCoord = x;
		this.yCoord = y;
	}
	
	/**
	 * Returns the value of the center of the shape
	 * @return Coord(x,y), where x and y give the coordinates of the center
	 */
	public List<Double> getCenter() {
		List<Double> coords = new ArrayList<Double>();
		coords.add(this.xCoord);
		coords.add(this.yCoord);
		return(coords);
	}
	/**
	 * Rotates the entire shape clockwise to a given angle, in degrees. There is no change to 
	 * angleList and sideList, since they remain unchanged. The coordinates, however, are all
	 * changed based on the new rotation angle
	 * 
	 * @param ang, the angle in degrees to which to set the shape to
	 */
	public void rotateTo(double ang) {
		this.rotateAngle = ang;
		for(Coord c : this.coordList) {
//			double tempX = (c.x - this.xCoord)*Math.cos(Math.toRadians(ang)) + (c.y - this.yCoord)*Math.sin(Math.toRadians(ang));
//			double tempY = -1*(c.x - this.xCoord)*Math.sin(Math.toRadians(ang)) + (c.y - this.yCoord)*Math.cos(Math.toRadians(ang));
			double tempX = this.xCoord + (c.x - this.xCoord)*Math.cos(Math.toRadians(ang)) - (c.y - this.yCoord)*Math.sin(Math.toRadians(ang));
			double tempY = this.yCoord + (c.x - this.xCoord)*Math.sin(Math.toRadians(ang)) + (c.y - this.yCoord)*Math.cos(Math.toRadians(ang));
			
			c.x = tempX;
			c.y = tempY;
		}
	}
	
	/**
	 * Returns the angle to which the shape has been rotated. 0.0 by default
	 * @return double angle
	 */
	public double getAngle() {
		return this.rotateAngle;
	}
	
	public void translateCoord(int index, Coord xy) {
		if(this.coordList.contains(xy)) {
			System.out.println("Coordinate overlap. Please enter another coordinate");
		}
		else {
			this.coordList.set(index, xy);
			this.calcAttr();
		}
				
	}
	
	/**
	 * Translates the entire shape by a given x and y value. This is different from setCenter
	 * in that it adds the coordinates to the current values instead of setting them to the values
	 * provided
	 * 
	 * @param _x, the x value
	 * @param _y, the y value
	 */
	public void translateShape(double _x, double _y) {
		this.xCoord += _x;
		this.yCoord += _y;
		for(Coord c : this.coordList) {
			c.x += _x;
			c.y += _y;
		}
	}
	
	/**
	 * Compares 2 shapes based on their coordinates. It doesn't matter if the shape has been defined
	 * using the general shape constructor or a constructor that extends shape, as long as the 
	 * coordinates are equal, within an acceptable margin of error.
	 * 
	 * The coordinates do not need to start at the same point, but they do need to be in order.
	 * For example, (0,0) (0,1) (1,1) equals (0,1) (1,1) (0,0) but doesn't equal (0,1) (0,0) (1,1)
	 * Of course, a smarter version of this comparison is possible, but this is good enough for our 
	 * needs
	 * 
	 * @return boolean value depending on whether the program deems them to be equal or not
	 */
	public boolean equals(Object s) {
		if(s instanceof Shape) {
			
			if(this.coordList.size() != ((Shape) s).coordList.size()){
				System.out.println("Different number of points");
				return false;
			}
			// We can now conclude that the two coordLists have the same number of coords
			else if(!(((Shape) s).coordListContains(this.coordList.get(0)))){
				System.out.println("Can't find a point which matches first coordinate");
				return false;
			}
			else {
				//Checks if they all have the same coordinates in sequence, even if the first element declared
				//in a shape is not the same as the first element declared in the other shape.
				int sIndex = 0;
				for(int i = 0; i<((Shape) s).coordList.size();i++) {
					if (((Shape) s).coordList.get(i).equals(this.coordList.get(0))){
						sIndex = i;
					}
				}
				int thisIndex = 0;
				//We start from wherever the two sets have a matching coord
				for(; sIndex < (((Shape) s).coordList.size()); sIndex++, thisIndex++) {
					if(!(((Shape) s).coordList.get(sIndex).equals(this.coordList.get(thisIndex)))) {
						System.out.println("Co-ordinate "+sIndex+" in s is not equal to "+thisIndex+" in this");
						System.out.println("("+((Shape) s).coordList.get(sIndex).x +","+((Shape) s).coordList.get(sIndex).y+")");
						System.out.println("("+this.coordList.get(thisIndex).x+","+this.coordList.get(thisIndex).x+")");
						return false;
					}
				}
				
				//Once we have reached the end of s.coordList, we wrap around
				if(thisIndex < this.coordList.size()-1){
					for(sIndex = 0; sIndex < ((Shape) s).coordList.indexOf(this.coordList.get(thisIndex)); sIndex++,thisIndex++) {
						if(!(((Shape) s).coordList.get(sIndex).equals(this.coordList.get(thisIndex)))) {
							System.out.println("Co-ordinate "+sIndex+" in s is not equal to "+thisIndex+" in this");
							System.out.println("("+((Shape) s).coordList.get(sIndex).x +","+((Shape) s).coordList.get(sIndex).y+")");
							System.out.println("("+this.coordList.get(thisIndex).x+","+this.coordList.get(thisIndex).x+")");
							return false;
							}
				}
				
				//If it survives, then the two shapes have exactly the same coordinates in sequence, although it may 
				//not be that the first element of the two coordLists are the same. So we should return true
				}
			}
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Returns the perimeter of the shape, calculated based off the sideList
	 * @return double perimeter
	 */
	public double getPerimeter() {
		double sum = 0;
		for(double s : this.sideList) {
			sum += s;
		}
		return sum;
	}
	
	/**
	 * Checks if the coordinate list of the shape contains, within an acceptable margin of 
	 * error, a given value xy
	 * @param xy, the coordinate to be checked
	 * @return boolean value based on whether the element is found
	 */
	public boolean coordListContains(Coord xy){
		for (Coord a : this.coordList) {
			if(Math.abs(xy.x - a.x)<= EPS && Math.abs(xy.y - a.y)<= EPS){
				return true;
			}
		}
		return false;
	}
	/**
	 * Checks if the angle list of the shape contains, within an acceptable margin of 
	 * error, a given value ang, in degrees
	 * @param ang
	 * @return boolean value based on whether the element is found
	 */
	public boolean angleListContains(double ang){
		for (double a : this.angleList) {
			if(Math.abs(ang - a)<= EPS){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns a string with information about the type of shape and the coordinates of the shape
	 * @return String of info
	 */
	public String toString(){
		String shapeTypeString = "";
		String coordListString = "";
		switch (type) {
        case EQUILATERAL_TRIANGLE:  
        	shapeTypeString = "an Equilateral Triangle";
        	break;
        case RIGHT_ANGLED_ISOSCELES_TRIANGLE:  
        	shapeTypeString = "a Right-Angled Isosceles Triangle";
        	break;
        case ISOSCELES_TRIANGLE:  
        	shapeTypeString = "an Isosceles Triangle";
        	break;
        case RIGHT_ANGLED_TRIANGLE:  
        	shapeTypeString = "a Right-Angled Triangle";
        	break;
        case TRIANGLE:  
        	shapeTypeString = "a Triangle";
        	break;
        case SQUARE:  
        	shapeTypeString = "a Square";
        	break;
        case RHOMBUS:  
        	shapeTypeString = "a Rhombus";
        	break;
        case RECTANGLE: 
        	shapeTypeString = "a Rectangle";
        	break;
        case PARALLELOGRAM: 
        	shapeTypeString = "a Parallelogram";
        	break;
        case TRAPEZOID: 
        	shapeTypeString = "a Trapezoid";
        	break;
        case QUADRILATERAL: 
        	shapeTypeString = "a Quadrilateral";
        	break;
        case POLYGON: 
        	shapeTypeString = "a Regular Polygon";
        	break;
        default: 
        	shapeTypeString = "a Shape";
        	break;
        	}
		for(Coord c : this.coordList) {
			coordListString = coordListString.concat("("+c.x+","+c.y+") ");
		}
		return ("This is "+shapeTypeString+" with the following coordinates: "+coordListString);
	}
	
}