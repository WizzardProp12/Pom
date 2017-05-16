package asteroids.model.program;

import asteroids.part3.programs.SourceLocation;

public class BreakStatement extends Statement {
	
	
	
	public BreakStatement(SourceLocation sourcelocation) {
		super(sourcelocation);
	}

	//executing the break statement means exiting the loop (== return;)
	public void execute(){
		return;
			
		}
	


}
