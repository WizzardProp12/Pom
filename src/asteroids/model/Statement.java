package asteroids.model;

import asteroids.part3.programs.SourceLocation;

public abstract class Statement {
	private SourceLocation sourceLocation;
	
	public Statement(SourceLocation sourcelocation){
		this.sourceLocation = sourceLocation;
	}
	
	public abstract void execute();
	//implementation of execution of the statement is dependent on the type of statement.

}
