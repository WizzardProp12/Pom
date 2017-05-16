package asteroids.model.program.expressions;

import asteroids.model.Entity;
import asteroids.part3.programs.SourceLocation;

public class GetXExpression extends Expression<Double>{
	
	private SourceLocation sourceLocation;
	private Expression<? extends Entity> e;

	public GetXExpression(Expression<? extends Entity> e,SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public Double evaluate() {
		return e.evaluate().getPosition().getXCoord();
	}

}