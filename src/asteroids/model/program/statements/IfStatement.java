package asteroids.model.program.statements;

import asteroids.model.program.Program;
import asteroids.model.program.expressions.Expression;
import asteroids.part3.programs.SourceLocation;

public class IfStatement extends Statement{
	
	public IfStatement(Expression<Boolean> condition, 
			Statement ifStatement, Statement elseStatement, SourceLocation sourcelocation) {
		super(sourcelocation);
		this.condition = condition;
		this.ifStatement = ifStatement;
		this.elseStatement = elseStatement;
	}
	
	private Expression<Boolean> condition;
	private Statement ifStatement;
	private Statement elseStatement;
	
	public void execute(){
		if (condition.evaluate() == true){
			System.out.println("start executing if");
			ifStatement.execute();
			
			if (ifStatement.getProgram().isTimeIsUp()) {System.out.println("time of if is up"); return;}
		}
		else if (elseStatement != null)
			elseStatement.execute();
			if (ifStatement.getProgram().isTimeIsUp()) {return;}

		return;
	}
	
	@Override
	public void setProgram(Program program){
		super.setProgram(program);
		condition.setProgram(program);
		ifStatement.setProgram(program);
		//Elsebody is optional!!!!!!
		
		if (elseStatement != null) {elseStatement.setProgram(program);}


	}
}