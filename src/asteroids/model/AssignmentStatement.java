package asteroids.model;

import asteroids.part3.programs.SourceLocation;


public class AssignmentStatement extends Statement{
	private String variableName;
	private Expression<? extends Double> value;
	
	public AssignmentStatement(String variableName, Expression value, SourceLocation sourceLocation){
		super(sourceLocation);
		this.variableName = variableName;
		this.value = value;
		}

	@Override
	public void execute() {
		getProgram().variableMap.put(variableName,value.evaluate());
	}
	
	

}
