package asteroids.model;

import asteroids.part3.programs.SourceLocation;

public class Function {
	private String functionName;
	private Statement body;
	private SourceLocation sourceLocation;
	
	public Function(String functionName, Statement body, SourceLocation sourceLocation){
		this.functionName = functionName;
		this.body = body;
		this.sourceLocation = sourceLocation;
	}
		
	public void evaluate(){
		body.execute();
	}
		
	
	
	
	
	

}
