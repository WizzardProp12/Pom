package asteroids.model.program;

import asteroids.part3.programs.SourceLocation;


public class TurnStatement extends Statement{
	private Expression<? extends Double> angle;
	
	public TurnStatement(SourceLocation sourceLocation, Expression<? extends Double> angle){
		super(sourceLocation);
		this.angle = angle;
	}

	@Override
	public void execute() {
		getProgram().getShip().turn(angle);
		
	}
	
	

}
