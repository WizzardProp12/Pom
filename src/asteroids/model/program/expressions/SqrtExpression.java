package asteroids.model.program.expressions;

import asteroids.part3.programs.SourceLocation;

public class SqrtExpression extends Expression<Double>{
	
	private Expression<? extends Double> e;
	private SourceLocation sourceLocation;

	public SqrtExpression(Expression<? extends Double > e , SourceLocation sourceLocation) {
		super(sourceLocation);
		this.e = e;
	}

	@Override
	public Double evaluate() {
		return Math.sqrt(e.evaluate());
		
	}

}
