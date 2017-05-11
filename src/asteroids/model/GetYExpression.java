package asteroids.model;

import asteroids.part3.programs.SourceLocation;

public class GetYExpression extends Expression<Double>{
	
	private SourceLocation sourceLocation;
	private Expression<? extends Entity> e;

	public GetYExpression(Expression<? extends Entity> e,SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public Double evaluate() {
		return e.evaluate().getPosition().getYCoord();
	}

}