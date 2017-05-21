package asteroids.model.program.expressions;

import asteroids.model.program.Program;
import asteroids.part3.programs.SourceLocation;

public class EqualityExpression extends Expression<Boolean>{

	public EqualityExpression(Expression<? extends Object > e1 , Expression<? extends Object> e2, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.e1 = e1;
		this.e2 = e2;
	}
	
	private Expression<? extends Object> e1;
	private Expression<? extends Object> e2;
	
	@Override
	public Boolean evaluate() {
		return e1.evaluate().equals(e2.evaluate());
		
	}
	
	@Override
	public void setProgram(Program program){
		super.setProgram(program);
		e1.setProgram(program);
		e2.setProgram(program);

	}


}
