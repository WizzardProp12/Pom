package asteroids.model.program;

import asteroids.part3.programs.SourceLocation;

public class ReadVariableExpression extends Expression<Object>{
	
	private String variableName;

	public ReadVariableExpression(String variableName, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.variableName = variableName;
	}

	@Override
	public Object evaluate() {
		return getProgram().getVariableMap().get(variableName);
	}

}
