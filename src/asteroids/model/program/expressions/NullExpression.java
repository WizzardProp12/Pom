package asteroids.model.program.expressions;

import asteroids.part3.programs.SourceLocation;

public class NullExpression extends Expression<Double>{

	public NullExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public Double evaluate() {
		return null;
	}

}