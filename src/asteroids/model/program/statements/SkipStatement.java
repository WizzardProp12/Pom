package asteroids.model.program.statements;

import asteroids.part3.programs.SourceLocation;


public class SkipStatement extends ActionStatement {
	
	public SkipStatement(SourceLocation sourceLocation){
		super(sourceLocation);
	}

	@Override
	public void execute() {
		return;
	}
}
