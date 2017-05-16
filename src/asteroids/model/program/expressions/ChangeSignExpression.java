package asteroids.model.program.expressions;

import asteroids.part3.programs.SourceLocation;

public class ChangeSignExpression extends Expression<Double>{
	
	public ChangeSignExpression(Expression<? extends Double> e, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.e = e;
	}

	private Expression<? extends Double> e;
	
	@Override
	public Double evaluate() {
		return -e.evaluate();
	}

}
