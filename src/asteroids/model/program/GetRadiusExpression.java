package asteroids.model.program;

import asteroids.model.Entity;
import asteroids.part3.programs.SourceLocation;

public class GetRadiusExpression extends Expression<Double>{
	
	private SourceLocation sourceLocation;
	private Expression<? extends Entity> e;

	public GetRadiusExpression(Expression<? extends Entity> e,SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public Double evaluate() {
		return e.evaluate().getRadius();
		
	}

}