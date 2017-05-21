package asteroids.model.program.statements;


import asteroids.model.program.Program;
import asteroids.model.program.expressions.Expression;
import asteroids.part3.programs.SourceLocation;


public class AssignmentStatement<T> extends Statement{
	private String variableName;
	private Expression<T> value;
	
	public AssignmentStatement(String variableName, Expression<T> value,
											SourceLocation sourceLocation){
		super(sourceLocation);
		this.variableName = variableName;
		this.value = value;
		
	}

	@Override
	public void execute() {
		getProgram().setVariable(variableName, value);
	}
	
	@Override
	public void setProgram(Program program){
		super.setProgram(program);
		value.setProgram(program);
		System.out.println("assigned program to var");
		
	}
}
