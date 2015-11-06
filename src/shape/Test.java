package shape;

import java.util.*;

public class Test {

	public static void main(String[] args) {
		Coord p1 = new Coord(0,0);
		Coord p2 = new Coord(1,0);
		Coord p3 = new Coord(1,1);
		Coord p4 = new Coord(0,1);
		
		
		
		List<Coord> pList = Arrays.asList(p1,p2,p3,p4);
		List<Coord> pList2 = Arrays.asList(p3,p4,p1,p2);
		List<Coord> pList3 = Arrays.asList(p1,p2,p4);
		
 		Quadrilateral sq1 = new Quadrilateral(pList); //A Square defined by Quadrilateral
 		Shape sq2 = new Shape(pList); //A Square defined by the general method Shape
		Shape sq3 = new Shape(pList2); //A Square identical to the prev two but with coordinates in different order
		Triangle tr1 = new Triangle(pList3); //A Right-angled Isosceles triangle defined by Triangle 
		Shape tr2 = new Shape(pList3); //The same triangle defined by Shape method
		Ellipse cc1 = new Ellipse(1,1);
		Circle cc2 = new Circle(1);
		
		
		System.out.println(sq1);
		System.out.println(sq2);
		System.out.println(sq3);
		
		System.out.println();
		System.out.println(sq1.sideList);
		System.out.println(sq1.angleList);
		System.out.println("Perimeter of square: "+ sq1.getPerimeter());
		System.out.println("Area of square: "+ sq1.getArea());
		System.out.println("Center of square: "+ sq1.xCoord+","+sq1.yCoord);
		
		System.out.println();
		System.out.println(tr1);
		System.out.println(tr2);
		
		System.out.println();
		System.out.println(tr1.sideList);
		System.out.println(tr1.angleList);
		System.out.println("Perimeter of triangle: "+ tr1.getPerimeter());
		System.out.println("Area of triangle: "+ tr1.getArea());
		System.out.println("Center of triangle: "+ tr1.xCoord+","+tr1.yCoord);
		
		System.out.println();
		System.out.println(cc1);
		System.out.println(cc2);
		System.out.println("Perimeter of Ellipse: "+ cc1.getPerimeter());
		System.out.println("Area of Ellipse: "+ cc1.getArea());
		System.out.println("Center of Ellipse: "+ cc1.xCoord+","+cc1.yCoord);
		
		System.out.println();
		System.out.println(sq1.equals(sq2)); //compares them despite them being of different classes
		System.out.println(sq1.equals(sq3));
		System.out.println(tr1.equals(tr2));
		System.out.println(cc1.equals(cc2));
		
		System.out.println();
		System.out.println(sq1.equals(tr1)); //false, with reason why it's false
		
		sq2.translateCoord(0, new Coord(0.5, 0.5));
		System.out.println(sq2);
		System.out.println(sq2.sideList);
		System.out.println(sq2.angleList);
		System.out.println("Center of square: "+ sq2.xCoord+","+sq2.yCoord);
		System.out.println(sq2.equals(sq3)); //false
		
		System.out.println();
		sq3.rotateTo(45);
		System.out.println(sq3);
		sq3.calcAttr();
		System.out.println(sq3.sideList);
		System.out.println(sq3.angleList);

	}

}
