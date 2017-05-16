package asteroids.model.program.expressions;

import asteroids.part3.programs.SourceLocation;

public class NotExpression extends Expression<Boolean> {

	public NotExpression(Expression<? extends Boolean> e , SourceLocation sourceLocation) {
		super(sourceLocation);
	}
	
	private Expression<? extends Boolean> e;

	@Override
	public Boolean evaluate() {
		return !e.evaluate();
		
	}

}
