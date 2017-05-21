package asteroids.model.program.statements;

import asteroids.model.program.Program;
import asteroids.model.program.expressions.Expression;
import asteroids.part3.programs.SourceLocation;

public class PrintStatement extends Statement{
	
	public PrintStatement(Expression<? extends Double> e, SourceLocation sourceLocation){
		super(sourceLocation);
		this.e = e;
	}

	private Expression<? extends Double> e;
	
	@Override
	public void execute() {
		System.out.println("printing, program is null is" + (getProgram() == null) );
		getProgram().addNewOutputLine(e.evaluate());
	}
	
	@Override
	public void setProgram(Program program){
		super.setProgram(program);
		e.setProgram(program);
		System.out.println("var assigned");
	}
	
}