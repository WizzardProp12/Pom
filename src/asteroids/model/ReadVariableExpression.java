package asteroids.model;

import asteroids.part3.programs.SourceLocation;

public class ReadVariableExpression extends Expression<Object>{
	
	private String variableName;
	private SourceLocation sourceLocation;

	public ReadVariableExpression(String variableName, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.variableName = variableName;
	}

	@Override
	public Object evaluate() {
		return getProgram().getVariableMap().get(variableName);
		
	}

}
