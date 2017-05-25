package asteroids.model.program.statements;

import asteroids.model.program.Program;
import asteroids.model.program.expressions.Expression;
import asteroids.part3.programs.SourceLocation;

public class WhileStatement extends Statement{
	
	private Expression<Boolean> condition;
	private Statement body;
	
	
	public WhileStatement(Expression<Boolean> condition, Statement body, 
													SourceLocation sourcelocation) {
		super(sourcelocation);
		this.condition = condition;
		this.body = body;
	}



	@Override
	public void execute(){
		try{
		while (condition.evaluate() == true){
			if (this.getStatementExecuted()) {System.out.println("while is done so return"); return;}
			System.out.println("execute while, condition is true");
			body.execute();
		}
		} catch (BreakException e){
			
		}
	}
	
	@Override
	public void setProgram(Program program){
		super.setProgram(program);
		condition.setProgram(program);
		body.setProgram(program);
	}
	


}

