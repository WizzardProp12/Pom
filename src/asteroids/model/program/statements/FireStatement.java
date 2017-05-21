package asteroids.model.program.statements;

import asteroids.part3.programs.SourceLocation;


public class FireStatement extends ActionStatement{
	
	public FireStatement(SourceLocation sourceLocation){
		super(sourceLocation);
	}

	@Override
	public void execute() {
		if (getProgram().getTimeLeft() >= 0.2){
			getProgram().decreaseTime(getDuration());
			getProgram().getShip().fireBullet();
			System.out.println("firebullet for 0.2s");
			return;
		}
			getProgram().setTimeIsUp(true);
			return;
		
	}
	
}