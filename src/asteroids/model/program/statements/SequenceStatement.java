package asteroids.model.program.statements;

import java.util.List;

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
}