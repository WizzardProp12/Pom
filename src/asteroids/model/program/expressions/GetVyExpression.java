package asteroids.model.program.expressions;

import asteroids.model.Entity;
import asteroids.part3.programs.SourceLocation;

public class GetVyExpression extends Expression<Double>{

	public GetVyExpression(Expression<? extends Entity> e, SourceLocation sourceLocation) {
		super(sourceLocation);
	}
	
	private Expression<? extends Entity> e;

	@Override
	public Double evaluate() {
		return e.evaluate().getYVelocity();
	}

}