package asteroids.model.program;

import asteroids.part3.programs.SourceLocation;

public class EqualityExpression extends Expression<Boolean>{
	
	private Expression<? extends Object> e1;
	private Expression<? extends Object> e2;
	private SourceLocation sourceLocation;

	public EqualityExpression(Expression<? extends Object > e1 , Expression<? extends Object> e2, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.e1 = e1;
		this.e2 = e2;
	}

	@Override
	public Boolean evaluate() {
		return e1.evaluate().equals(e2.evaluate());
		
	}

}
