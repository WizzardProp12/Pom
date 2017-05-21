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
		if (getProgram().getTimeLeft() >= 0.2){
			getProgram().decreaseTime(getDuration());
			getProgram().getShip().turn(getAngle());
			System.out.println("turn for 0.2s");
			return;
		}
			getProgram().setTimeIsUp(true);
			return;
		
	}
}