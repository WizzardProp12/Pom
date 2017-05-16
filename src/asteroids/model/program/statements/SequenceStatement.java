package asteroids.model.program.statements;

import java.util.ArrayList;
import java.util.List;

import asteroids.model.program.expressions.Expression;
import asteroids.part3.programs.SourceLocation;


public class SequenceStatement extends Statement{
	
	private List<Statement> statements;
	
	public SequenceStatement(SourceLocation sourceLocation, List<Statement> statements) {
		super(sourceLocation);
		this.statements = statements;
	}
	
	@Override
	public void execute() {
		for (Statement statement : statements){
			statement.execute();
		}
		
	}

	@Override
	public void execute(ArrayList<Expression>... ArgumentList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getDuration() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}