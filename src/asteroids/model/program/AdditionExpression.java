package asteroids.model.program;
import asteroids.part3.programs.SourceLocation;

public class AdditionExpression extends Expression<Double> {


	private Expression<? extends Double> e1;
	private Expression<? extends Double> e2;
	private SourceLocation sourceLocation;

	public AdditionExpression(Expression<? extends Double > e1 , Expression<? extends Double> e2, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.e1 = e1;
		this.e2 = e2;
	}
	
	@Override
	public Double evaluate(){
		return e1.evaluate() + e2.evaluate();
	}

}
