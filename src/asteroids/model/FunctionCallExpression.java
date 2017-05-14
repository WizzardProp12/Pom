package asteroids.model;

import java.util.List;

import asteroids.part3.programs.SourceLocation;

public class FunctionCallExpression extends Expression<Object>{
	
	private String functionName;
	private SourceLocation sourceLocation;

	public ReadParameterExpression(String functionName, List<Expression> actualArgs, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.parameterName = parameterName;
		
	}

	@Override
	public Object evaluate() {
		return getProgram().getParameterMap().get(parameterName);
		
	}

}
