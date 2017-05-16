package asteroids.model.program.statements;

import asteroids.part3.programs.SourceLocation;


public class ThrustOnStatement extends ActionStatement {
	
	public ThrustOnStatement(SourceLocation sourceLocation){
		super(sourceLocation);
	}

	@Override
	public void execute() {
		getProgram().getShip().thrustOn();
	}
}
