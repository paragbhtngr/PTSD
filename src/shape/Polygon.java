package shape;

import java.util.*;

public class Polygon extends Shape {
	public Polygon(List<Coord> _coordList){
		super.coordList = _coordList;
		super.calcAttr();
		super.type = ShapeType.POLYGON;
	}
	
	public Polygon(int numSides, double sideLength){
		///TODO this function needs to be implemented
	}

}
