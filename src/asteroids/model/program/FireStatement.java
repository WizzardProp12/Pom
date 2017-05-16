package asteroids.model.program;

import asteroids.part3.programs.SourceLocation;


public class FireStatement extends Statement{
	
	private double timeToExecute = 0.2;
	
	public FireStatement(SourceLocation sourceLocation){
		super(sourceLocation);
	}

	@Override
	public void execute() {
		getProgram().getShip().fireBullet();
		getProgram().decreaseTimeLeft(timeToExecute);
		
	}
	
	

}