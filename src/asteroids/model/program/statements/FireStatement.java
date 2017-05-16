package asteroids.model.program.statements;

import asteroids.part3.programs.SourceLocation;


public class FireStatement extends ActionStatement{
	
	public FireStatement(SourceLocation sourceLocation){
		super(sourceLocation);
	}

	@Override
	public void execute() {
		getProgram().getShip().fireBullet();
	}
}