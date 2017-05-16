package asteroids.model.program.expressions;

import asteroids.part3.programs.SourceLocation;

public class GetDirectionExpression extends Expression<Double>{
	
	public GetDirectionExpression(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	@Override
	public Double evaluate() {
		return getProgram().getShip().getOrientation();
	}

}
