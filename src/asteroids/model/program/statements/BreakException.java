package asteroids.model.program.statements;


public class BreakException extends RuntimeException {
	private Object object;

	public BreakException(Object object){
		this.object = object;
	}
}
