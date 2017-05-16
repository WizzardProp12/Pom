package asteroids.model.program.statements;


import asteroids.part3.programs.SourceLocation;


public class AssignmentStatement extends Statement{
	private String variableName;
	private Object value;
	
	public AssignmentStatement(String variableName, Object value,
											SourceLocation sourceLocation){
		super(sourceLocation);
		this.variableName = variableName;
		this.value = value;
	}

	@Override
	public void execute() {
		getProgram().setVariable(variableName, value);
	}
}
