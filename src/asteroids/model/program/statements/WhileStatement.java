package asteroids.model.program.statements;

import asteroids.model.program.expressions.Expression;
import asteroids.part3.programs.SourceLocation;

public class WhileStatement extends Statement{
	
	private Expression<Boolean> condition;
	private Statement body;
	
	
	public WhileStatement(Expression<Boolean> condition, Statement body, SourceLocation sourcelocation) {
		super(sourcelocation);
		this.condition = condition;
		this.body = body;
	}



	//Execution needs to stop if (currentStatement instanceof BreakStatement)
	public void execute(){
		while (condition.evaluate() == true){
			body.execute();
			
		}
	}


}
