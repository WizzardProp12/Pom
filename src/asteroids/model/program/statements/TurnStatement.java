package asteroids.model.program.statements;

import asteroids.part3.programs.SourceLocation;

public class TurnStatement extends ActionStatement {
	
	public TurnStatement(double angle, SourceLocation sourceLocation){
		super(sourceLocation);
		this.angle = angle;
	}
	
	private double angle;
	
	public double getAngle() { return this.angle; }
	
	@Override
	public void execute() {
		getProgram().getShip().turn(getAngle());
	}
}
