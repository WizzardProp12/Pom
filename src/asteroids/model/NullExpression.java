package asteroids.model;

import asteroids.part3.programs.SourceLocation;

public class NullExpression extends Expression<Double>{
	
	private SourceLocation sourceLocation;

	public NullExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public Double evaluate() {
		return null;
	}

}