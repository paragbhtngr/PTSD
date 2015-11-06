package shape;

public class Ellipse extends Shape {
	double xRad = 0;
	double yRad = 0;
	
	public Ellipse() {
		this.xRad = 0;
		this.yRad = 0;
		super.type = ShapeType.ELLIPSE;
	}
	
	public Ellipse(double _xRad, double _yRad) {
		this.xRad = _xRad;
		this.yRad = _yRad;
		if(_xRad == _yRad){
			super.type = ShapeType.CIRCLE;
		}
		else {
			super.type = ShapeType.ELLIPSE;
		}
		
	}
	

	public double getArea() {
		return Math.PI*this.xRad*this.yRad;
	}
	
	public double getPerimeter(){
		//Using Ramanujan's Approximation
		double h = Math.pow((this.xRad - this.yRad), 2)/Math.pow((this.xRad + this.yRad), 2);
		return Math.PI*(this.xRad+this.yRad)*(1+((3*h)/(10+Math.sqrt(4-3*h))));
	}
	
	@Override
	public boolean equals(Object s) {
		if(s instanceof Ellipse) {	
			if(		Math.abs(this.xCoord - ((Ellipse) s).xCoord) <= super.EPS && 
					Math.abs(this.yCoord - ((Ellipse) s).yCoord) <= super.EPS &&
					Math.abs(this.xRad - ((Ellipse) s).xRad) <= super.EPS &&
					Math.abs(this.yRad - ((Ellipse) s).yRad) <= super.EPS ) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	@Override
	public String toString(){
		if(super.type == ShapeType.CIRCLE){
			return("This is a Circle with centre at ("+super.xCoord+","+super.yCoord+") and a radius of "+this.xRad);
		}
		else {
			return("This is an Ellipse with centre at ("+super.xCoord+","+super.yCoord+") and a length of "+this.xRad+" and a height of "+this.yRad);
		}
	}

}
