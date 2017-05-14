package asteroids.model;

import asteroids.part3.programs.SourceLocation;

public class LessThanExpression extends Expression<Boolean>{
	
	private Expression<? extends Double> e1;
	private Expression<? extends Double> e2;
	private SourceLocation sourceLocation;

	public LessThanExpression(Expression<? extends Double> e1 , Expression<? extends Double> e2, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.e1 = e1;
		this.e2 = e2;
	}

	@Override
	public Boolean evaluate() {
		return e1.evaluate() < e2.evaluate();
		
	}

}
