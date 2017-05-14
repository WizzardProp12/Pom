package asteroids.model;
import asteroids.part3.programs.SourceLocation;



public abstract class Statement {
	private SourceLocation sourceLocation;
	private Program program;
	
	public Statement(SourceLocation sourcelocation){
		this.sourceLocation = sourceLocation;
	}
	
	public Program getProgram(){
		return this.program;
	}
	
	public abstract void execute();
	//implementation of execution of the statement is dependent on the type of statement.

}
