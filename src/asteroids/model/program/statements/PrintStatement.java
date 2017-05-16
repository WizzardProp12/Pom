package asteroids.model.program.statements;

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
		getProgram().addNewOutputLine(e.toString());
	}
}
