package asteroids.model.program.statements;

import asteroids.model.program.expressions.Expression;
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
