package asteroids.model.program.expressions;

import asteroids.model.Entity;
import asteroids.part3.programs.SourceLocation;

public class GetVxExpression extends Expression<Double>{
	
	public GetVxExpression(Expression<? extends Entity> e, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.e = e;
	}

	private Expression<? extends Entity> e;

	@Override
	public Double evaluate() {
		return e.evaluate().getXVelocity();
	}

}