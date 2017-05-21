package asteroids.model.program.statements;

import asteroids.part3.programs.SourceLocation;


public class ThrustOffStatement extends ActionStatement {
	
	public ThrustOffStatement(SourceLocation sourceLocation){
		super(sourceLocation);
	}

	@Override
	public void execute() {
		if (getProgram().getTimeLeft() >= 0.2){
			getProgram().decreaseTime(getDuration());
			System.out.println("thrustoff for 0.2s");
			getProgram().getShip().thrustOff();
			return;
		}
			getProgram().setTimeIsUp(true);
			return;
		
	}
}