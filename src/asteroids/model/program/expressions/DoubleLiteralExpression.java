package asteroids.model.program.expressions;

import asteroids.part3.programs.SourceLocation;

public class DoubleLiteralExpression extends Expression<Double>{
	
	private SourceLocation sourceLocation;
	private double value;

	public DoubleLiteralExpression(double value,SourceLocation sourceLocation) {
		super(sourceLocation);
		this.value = value;
	}

	@Override
	public Double evaluate() {
		return value;
	}

}