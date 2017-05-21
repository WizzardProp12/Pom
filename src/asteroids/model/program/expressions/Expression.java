package asteroids.model.program.expressions;
import asteroids.model.program.Program;
import asteroids.model.program.statements.Statement;
import asteroids.part3.programs.SourceLocation;

public abstract class Expression<T> {
	
	// CONSTRUCTOR
	
	public Expression(SourceLocation sourceLocation){
		this.sourceLocation = sourceLocation;
	}
	
	// SOURCELOCATION

	private SourceLocation sourceLocation;
	
	public SourceLocation getSourceLocation() { return this.sourceLocation; }
	
	// STATEMENT
	
	private Statement statement;
	
	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}
	
	
	
	// PROGRAM

	protected Program program;
	
	public Program getProgram(){ return this.program; }
	
	public void setProgram(Program program) { this.program = program; }
	
	// EVALUATE
	
	public abstract T evaluate();
}