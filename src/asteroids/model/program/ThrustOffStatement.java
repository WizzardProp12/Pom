package asteroids.model.program;

import asteroids.part3.programs.SourceLocation;


public class ThrustOffStatement extends Statement{
	
	public ThrustOffStatement(SourceLocation sourceLocation){
		super(sourceLocation);
		}

	@Override
	public void execute() {
		getProgram().getShip().thrustOff();
	}
	
	

}
