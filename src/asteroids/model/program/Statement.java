package asteroids.model.program;
import java.util.ArrayList;
import java.util.Optional;

import asteroids.part3.programs.SourceLocation;

public abstract class Statement {
	
	private SourceLocation sourceLocation;
	private Program program;
	
	public Statement(SourceLocation sourcelocation){
		this.sourceLocation = sourceLocation;
	}
	
	public abstract void execute(ArrayList<Expression>... ArgumentList);
	
	public Program getProgram(){
		return this.program;
	}
	
	public abstract double getDuration();
}
