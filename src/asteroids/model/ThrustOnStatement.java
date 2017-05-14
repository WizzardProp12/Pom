package asteroids.model;

import asteroids.part3.programs.SourceLocation;


public class ThrustOnStatement extends Statement{
	
	public ThrustOnStatement(SourceLocation sourceLocation){
		super(sourceLocation);
		}

	@Override
	public void execute() {
		getProgram().getShip().thrustOn();
	}
	
	

}
