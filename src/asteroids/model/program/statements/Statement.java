package asteroids.model.program.statements;
import java.util.ArrayList;

import asteroids.model.program.Program;
import asteroids.model.program.expressions.Expression;
import asteroids.part3.programs.SourceLocation;

public abstract class Statement {
	
	public Statement(SourceLocation sourceLocation){
		this.sourceLocation = sourceLocation;
	}

	// SOURCELOCATION

	private SourceLocation sourceLocation;
	
	public SourceLocation getSourceLocation() { return this.sourceLocation; }
	
	// PROGRAM

	protected Program program;
	
	public Program getProgram(){ return this.program; }
	
	public void setProgram(Program program) { this.program = program; }
	
	// EXECUTE
	
	public abstract void execute();
	
	public void execute(ArrayList<Expression> argumentList){
		execute();
	}
	
	private boolean statementExecuted = true;

	public boolean getStatementExecuted() {
		return statementExecuted;
	}

	public void setStatementExecuted(boolean isExecuted) {
		this.statementExecuted = isExecuted;
	}
	
}