package asteroids.model.program.statements;

import asteroids.part3.programs.SourceLocation;


public class SkipStatement extends ActionStatement {
	
	public SkipStatement(SourceLocation sourceLocation){
		super(sourceLocation);
	}

	@Override
	public void execute() {
		if (getProgram().getTimeLeft() >= 0.2){
			getProgram().decreaseTime(getDuration());
			System.out.println("skip for 0.2s");
			return;
		}
			getProgram().setTimeIsUp(true);
			return;
		
	}
}