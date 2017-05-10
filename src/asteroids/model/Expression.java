package asteroids.model;
import asteroids.part3.programs.SourceLocation;

public abstract class Expression<T> {
	
	private SourceLocation SourceLocation;
	private Program program;
	
	public Expression(SourceLocation SourceLocation){
		this.SourceLocation = SourceLocation;
	}
	
	public abstract T evaluate();
	
	//Sets the program which this expression is part of.
	private void setProgram(){
		this.program = program;
	}
	
	//Return the program which this expression is part of.
	public Program getProgram(){
		return this.program;
	}
	
	
	

}
