package asteroids.model.program.expressions;

import asteroids.part3.programs.SourceLocation;

public class ReadParameterExpression extends Expression<Object>{
	
	private String parameterName;
	private SourceLocation sourceLocation;

	public ReadParameterExpression(String parameterName, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.parameterName = parameterName;
		
	}

	@Override
	public Object evaluate() {
		return getProgram().getParameterMap().get(parameterName);
		
	}

}
