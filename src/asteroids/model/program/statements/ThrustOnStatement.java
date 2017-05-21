package asteroids.model.program.statements;

import asteroids.part3.programs.SourceLocation;


public class ThrustOnStatement extends ActionStatement {
	
	public ThrustOnStatement(SourceLocation sourceLocation){
		super(sourceLocation);
	}

	@Override
	public void execute() {
		if (getProgram().getTimeLeft() >= 0.2){
			getProgram().decreaseTime(getDuration());
			getProgram().getShip().thrustOn();
			System.out.println("thrust for 0.2s");
			return;
		}
			getProgram().setTimeIsUp(true);
			return;
		
	}
}