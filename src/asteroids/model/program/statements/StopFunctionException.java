package asteroids.model.program.statements;


public class StopFunctionException extends RuntimeException {
	private Object object;

	public StopFunctionException(Object object){
		this.object = object;
	}
}
