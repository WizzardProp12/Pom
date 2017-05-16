package asteroids.model.program.statements;

import java.util.ArrayList;

import asteroids.model.program.expressions.Expression;
import asteroids.part3.programs.SourceLocation;


public class AssignmentStatement extends Statement{
	private String variableName;
	private Expression<? extends Double> value;
	
	public AssignmentStatement(String variableName, Expression value, SourceLocation sourceLocation){
		super(sourceLocation);
		this.variableName = variableName;
		this.value = value;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getDuration() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
