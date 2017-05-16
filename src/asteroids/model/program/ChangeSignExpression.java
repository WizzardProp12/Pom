package asteroids.model.program;

import asteroids.part3.programs.SourceLocation;

public class ChangeSignExpression extends Expression<Double>{
	
	private Expression<? extends Double> e;
	private SourceLocation sourceLocation;

	public ChangeSignExpression(Expression<? extends Double > e , SourceLocation sourceLocation) {
		super(sourceLocation);
		this.e = e;
	}

	@Override
	public Double evaluate() {
		return -e.evaluate();
		
	}

}
