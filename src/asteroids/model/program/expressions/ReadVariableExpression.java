package asteroids.model.program.expressions;

import asteroids.part3.programs.SourceLocation;

public class ReadVariableExpression extends Expression<Object>{
	
	private String variableName;

	public ReadVariableExpression(String variableName, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.variableName = variableName;
	}

	@Override
	public Object evaluate() {
		System.out.println("getprogram is null" + (getProgram() == null));
		return getProgram().getVariable(variableName);
	}
	
	

}
