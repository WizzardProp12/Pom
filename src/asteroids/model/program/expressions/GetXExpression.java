package asteroids.model.program.expressions;

import asteroids.model.Entity;
import asteroids.model.program.Program;
import asteroids.part3.programs.SourceLocation;

public class GetXExpression extends Expression<Double> {

	public GetXExpression(Expression<? extends Entity> e, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.e = e;
	}
	
	private Expression<? extends Entity> e;

	@Override
	public Double evaluate() {
		return e.evaluate().getPosition().getXCoord();
	}
	
	@Override
	public void setProgram(Program program){
		super.setProgram(program);
		e.setProgram(program);
		
	}

}