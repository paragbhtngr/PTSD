package shape;

import java.util.List;

/**
 * Triangle class extending the shape class.
 * Has methods for creating, comparing and modifying triangles
 * @author Parag Bhatnagar
 */
public class Triangle extends Shape {
	public Triangle(List<Coord> _coordList) {
		super.coordList = _coordList;
		super.calcAttr();
		triangleType();
		
	}
	/**
	 * Returns the type of any valid triangle
	 * @return ShapeType enum with the kind of triangle
	 */
	private void triangleType() {
		double sa = super.sideList.get(0);
		double sb = super.sideList.get(1);
		double sc = super.sideList.get(2);
		if(Math.abs(sa-sb) <= super.EPS && Math.abs(sb-sc) <= super.EPS) {
			//all 3 sides are equal
			super.type = ShapeType.EQUILATERAL_TRIANGLE;
		}
		else if(Math.abs(sa-sb) <= super.EPS || Math.abs(sb-sc) <= super.EPS || Math.abs(sa-sc) <= super.EPS) {
			//2 sides are equal
			if(super.angleListContains(90.0)){
				super.type = ShapeType.RIGHT_ANGLED_ISOSCELES_TRIANGLE;
			}
			else {
				super.type = ShapeType.ISOSCELES_TRIANGLE;
			}
		}
		else if(super.angleListContains(90.0)) {
			super.type = ShapeType.RIGHT_ANGLED_TRIANGLE;
		}
		else {
			super.type = ShapeType.TRIANGLE;
		}
	}
	
	/**
	 * Creates a new Triangle object with 3 sides as input
	 * @param s1 Side 1, starting at (0,0) and extending right along the x axis
	 * @param s2 Side 2
	 * @param s3 Side 3
	 * @return a Triangle object with sides s1, s2 and s3
	 * @throws NotATriangle
	 */
	public Triangle(double s1, double s2, double s3) throws NotATriangle{
		super.sideList.add(s1);
		super.sideList.add(s2);
		super.sideList.add(s3);
		
		super.angleList.add(getAngle(s3,s1,s2));
		super.angleList.add(getAngle(s1,s2,s3));
		super.angleList.add(getAngle(s2,s3,s1));
		
		Coord xy1 = new Coord(0.0,0.0);
		Coord xy2 = new Coord(0.0,0.0);
		Coord xy3 = new Coord(0.0,0.0);
		xy2.x = s1;
		xy3.x = xy2.x - s3*Math.cos(Math.toRadians(super.angleList.get(1)));
		xy3.y = s3*Math.sin(Math.toRadians(super.angleList.get(1)));
		
		//Checks if the 3 lengths actually form a proper triangle
		double s3Actual = Math.sqrt(Math.pow(xy3.x, 2)+Math.pow(xy3.y, 2));
		if(Math.abs(s3 - s3Actual)<= super.EPS) {
			throw new NotATriangle();
		}
		
		
	}
	
	//Methods to generate triangles by coordinates
	/**
	 * 
	 * @param _coordList, a list of coordinates
	 * @return new Triangle object of type Equilateral triangle
	 * @throws InvalidCoordinatesForThisTriangleType
	 */ 
	public Triangle equilateralTriangle(List<Coord> _coordList) throws InvalidCoordinatesForThisTriangleType{
		Triangle x = new Triangle(_coordList);
		if(x.type != ShapeType.EQUILATERAL_TRIANGLE) {
			throw new InvalidCoordinatesForThisTriangleType();
		}
		else return x;
	}
	/**
	 * 
	 * @param _coordList, a list of coordinates
	 * @return new Triangle object of type Right Angled Isosceles triangle
	 * @throws InvalidCoordinatesForThisTriangleType
	 */	
	public Triangle rightAngledIsoscelesTriangle(List<Coord> _coordList) throws InvalidCoordinatesForThisTriangleType{
		Triangle x = new Triangle(_coordList);
		if(x.type != ShapeType.RIGHT_ANGLED_ISOSCELES_TRIANGLE) {
			throw new InvalidCoordinatesForThisTriangleType();
		}
		else return x;
	}
	
	/**
	 * 
	 * @param _coordList, a list of coordinates
	 * @return new Triangle object of type Isosceles triangle
	 * @throws InvalidCoordinatesForThisTriangleType
	 */
	public Triangle isoscelesTriangle(List<Coord> _coordList) throws InvalidCoordinatesForThisTriangleType{
		Triangle x = new Triangle(_coordList);
		if(x.type != ShapeType.ISOSCELES_TRIANGLE) {
			throw new InvalidCoordinatesForThisTriangleType();
		}
		else return x;
	}
	
	/**
	 * 
	 * @param _coordList, a list of coordinates
	 * @return new Triangle object of type Right Angled triangle
	 * @throws InvalidCoordinatesForThisTriangleType
	 */
	public Triangle rightAngledTriangle(List<Coord> _coordList) throws InvalidCoordinatesForThisTriangleType{
		Triangle x = new Triangle(_coordList);
		if(x.type != ShapeType.RIGHT_ANGLED_TRIANGLE) {
			throw new InvalidCoordinatesForThisTriangleType();
		}
		else return x;
	}
	
	/**'
	 * Returns the area of the triangle
	 * @return double area
	 */
	public double getArea(){
		//Using Heron's formula
		double sa = super.sideList.get(0);
		double sb = super.sideList.get(1);
		double sc = super.sideList.get(2);
		
		double s = super.getPerimeter()/2;
		return Math.sqrt(s*(s-sa)*(s-sb)*(s-sc));
	}

}
