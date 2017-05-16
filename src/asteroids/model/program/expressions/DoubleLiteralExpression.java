package asteroids.model.program.expressions;

import asteroids.part3.programs.SourceLocation;

public class DoubleLiteralExpression extends Expression<Double>{
	

	public DoubleLiteralExpression(double value, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.value = value;
	}

	private double value;
	
	@Override
	public Double evaluate() {
		return value;
	}

}