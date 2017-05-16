package asteroids.model.program;
import asteroids.part3.programs.SourceLocation;

public abstract class Expression<T> {
	
	private SourceLocation sourceLocation;
	private Program program;
	
	public Expression(SourceLocation sourceLocation){
		this.sourceLocation = sourceLocation;
	}
	
	public abstract T evaluate();
	
	public Program getProgram(){
		return this.program;
	}
}
