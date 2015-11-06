package shape;

public class Circle extends Ellipse {
	
	public Circle(double _xRad) {
		super.xRad = _xRad;
		super.yRad = _xRad;
		super.type = ShapeType.CIRCLE;
	}
	
	@Override
	public double getPerimeter(){
		return (2*Math.PI*super.xRad);
	}
	
	@Override
	public double getArea(){
		return (Math.PI*(Math.pow(super.xRad, 2)));
	}

}
