package shape;

import java.util.List;

public class Quadrilateral extends Shape {
	public Quadrilateral(List<Coord> _coordList){
		super.coordList = _coordList;
		super.calcAttr();
		
		double sa = super.sideList.get(0);
		double sb = super.sideList.get(1);
		double sc = super.sideList.get(2);
		double sd = super.sideList.get(3);
		
		double v0 = super.angleList.get(0);
		double v1 = super.angleList.get(1);
		double v2 = super.angleList.get(2);
		double v3 = super.angleList.get(3);
		
		if(Math.abs(sa-sc) <= super.EPS && Math.abs(sb-sd) <= super.EPS && Math.abs(sa-sb) <= super.EPS){
			if(Math.abs(v0-90.0) <= super.EPS && Math.abs(v2-90.0) <= super.EPS){
				super.type = ShapeType.SQUARE;
			}
			else {
				super.type = ShapeType.RHOMBUS;
			}
		}
		else if(Math.abs(sa-sc) <= super.EPS || Math.abs(sb-sd) <= super.EPS){
			if(Math.abs(v0-90.0) <= super.EPS && Math.abs(v2-90.0) <= super.EPS){
				super.type = ShapeType.RECTANGLE;
			}
			else {
				super.type = ShapeType.PARALLELOGRAM;
			}
		}
		else if(Math.abs(v0+v1-180.0) <= super.EPS || Math.abs(v1+v2-180.0) <= super.EPS){
			super.type = ShapeType.TRAPEZOID;
		}
		else{
			super.type = ShapeType.QUADRILATERAL;	
		}
	}
	
	public double getArea(){
		//Using Bretschneider's formula
		double a = super.sideList.get(0);
		double b = super.sideList.get(1);
		double c = super.sideList.get(2);
		double d = super.sideList.get(3);
				
		double s = (a+b+c+d)/2; 
		
		double theta = Math.toRadians(super.angleList.get(0)+super.angleList.get(2));
		return ((s-a)*(s-b)*(s-c)*(s-d) - a*b*c*d*(Math.pow((Math.cos(theta/2)), 2)));
	}

}
