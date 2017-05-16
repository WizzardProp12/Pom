package asteroids.model.program.statements;
import java.util.ArrayList;
import java.util.Optional;

import asteroids.model.program.Program;
import asteroids.model.program.expressions.Expression;
import asteroids.part3.programs.SourceLocation;

public abstract class Statement {
	
	private SourceLocation sourceLocation;
	private Program program;
	
	public Statement(SourceLocation sourcelocation){
		this.sourceLocation = sourceLocation;
	}
	
	public SourceLocation getSourceLocation() {
		return this.sourceLocation;
	}
	
	public abstract void execute(ArrayList<Expression>... ArgumentList);
	
	public Program getProgram(){
		return this.program;
	}
	
	public abstract double getDuration();
}
