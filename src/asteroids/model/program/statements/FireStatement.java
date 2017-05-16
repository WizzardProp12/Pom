package asteroids.model.program.statements;

import asteroids.part3.programs.SourceLocation;


public class FireStatement extends ActionStatement{
	
	private double timeToExecute = 0.2;
	
	public FireStatement(SourceLocation sourceLocation){
		super(sourceLocation);
	}

	@Override
	public void execute() {
		getProgram().getShip().fireBullet();
		getProgram().decreaseTimeLeft(timeToExecute);
		
	}

	@Override
	public double getDuration() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}