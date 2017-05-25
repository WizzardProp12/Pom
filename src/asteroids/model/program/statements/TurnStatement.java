package asteroids.model.program.statements;

import asteroids.model.program.Program;
import asteroids.model.program.expressions.Expression;
import asteroids.part3.programs.SourceLocation;

public class TurnStatement extends ActionStatement {
	
	public TurnStatement(SourceLocation location, Expression angle2){
		super(location);
		this.angle = angle;
	}
	
	private Expression angle;
	
	public Expression getAngle() { return this.angle; }
	
	@Override
	public void execute() {
		if (getProgram().getTimeLeft() >= 0.2){
			getProgram().decreaseTime(getDuration());
			getProgram().getShip().turn((double) getAngle().evaluate());
			System.out.println("turn for 0.2s");
			return;
		}
			getProgram().setTimeIsUp(true);
			return;
		
	}
	
	@Override
	public void setProgram(Program program){
		super.setProgram(program);
		angle.setProgram(program);
	}
}