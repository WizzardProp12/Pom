package asteroids.model.program.statements;

import asteroids.part3.programs.SourceLocation;

public abstract class ActionStatement extends Statement{
	
	public ActionStatement(SourceLocation sourceLocation) {
		super(sourceLocation);
	}

	private double duration = 0.2;

	public double getDuration() { return this.duration; }
	
}
