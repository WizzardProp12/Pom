package asteroids.model.program.expressions;

import asteroids.model.program.Program;
import asteroids.part3.programs.SourceLocation;

public class NotExpression extends Expression<Boolean> {

	public NotExpression(Expression<? extends Boolean> e , SourceLocation sourceLocation) {
		super(sourceLocation);
		this.e = e;
	}
	
	private Expression<? extends Boolean> e;

	@Override
	public Boolean evaluate() {
		return !e.evaluate();
		
	}
	
	@Override
	public void setProgram(Program program){
		super.setProgram(program);
		e.setProgram(program);
		
	}

}
