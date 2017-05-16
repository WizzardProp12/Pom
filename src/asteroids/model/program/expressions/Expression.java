package asteroids.model.program.expressions;
import asteroids.model.program.Program;
import asteroids.part3.programs.SourceLocation;

public abstract class Expression<T> {
	
	// CONSTRUCTOR
	
	public Expression(SourceLocation sourceLocation){
		this.sourceLocation = sourceLocation;
	}
	
	// SOURCELOCATION

	private SourceLocation sourceLocation;
	
	public SourceLocation getSourceLocation() { return this.sourceLocation; }
	
	// PROGRAM

	private Program program;
	
	public Program getProgram(){ return this.program; }
	
	public void setProgram(Program program) { this.program = program; }
	
	// EVALUATE
	
	public abstract T evaluate();
}
