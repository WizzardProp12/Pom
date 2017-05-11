package asteroids.model;

import asteroids.part3.programs.SourceLocation;

public class NotExpression extends Expression<Boolean>{
	
	private Expression<? extends Boolean> e;
	private SourceLocation sourceLocation;

	public NotExpression(Expression<? extends Boolean > e , SourceLocation sourceLocation) {
		super(sourceLocation);
		
	}

	@Override
	public Boolean evaluate() {
		return !e.evaluate();
		
	}

}
