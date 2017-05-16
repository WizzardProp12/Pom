package asteroids.model.program.statements;

import asteroids.model.program.expressions.Expression;
import asteroids.part3.programs.SourceLocation;


public class PrintStatement extends Statement{
	private Expression<? extends Double> e;
	public PrintStatement(Expression<? extends Double> e, SourceLocation sourceLocation){
		super(sourceLocation);
		this.e = e;
	}

	@Override
	public void execute() {
		System.out.println(e.evaluate());
		
	}
	
	

}
