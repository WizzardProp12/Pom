package asteroids.model.program.expressions;

import asteroids.model.program.Program;
import asteroids.part3.programs.SourceLocation;

public class SqrtExpression extends Expression<Double>{
	

	public SqrtExpression(Expression<? extends Double > e, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.e = e;
	}

	private Expression<? extends Double> e;
	
	@Override
	public Double evaluate() {
		return Math.sqrt(e.evaluate());
		
	}
	
	@Override
	public void setProgram(Program program){
		super.setProgram(program);
		e.setProgram(program);
		
	}

}
