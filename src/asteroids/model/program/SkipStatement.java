package asteroids.model.program;

import asteroids.part3.programs.SourceLocation;


public class SkipStatement extends Statement{
	private Expression<? extends Double> e;
	private double timeToExecute = 0.2;
	
	public SkipStatement(SourceLocation sourceLocation){
		super(sourceLocation);
	}

	@Override
	public void execute() {
		getProgram().decreaseTimeLeft(timeToExecute);
		
	}
	
	

}
