package asteroids.model;

import asteroids.part3.programs.SourceLocation;

public class ReturnStatement extends Statement {
	
	private Expression<? extends Object> value;
	
	
	public ReturnStatement(Expression<? extends Object> value, SourceLocation sourcelocation) {
		super(sourcelocation);
		this.value = value;
	}
	
	//Immediately stops execution of the function
	public void execute() throws RuntimeException{
		throw new RuntimeException((String) value.evaluate());
		}
	
	

}
