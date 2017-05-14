package asteroids.model;
import asteroids.part3.programs.SourceLocation;

public class SelfExpression extends Expression<Ship> {
	
	private SourceLocation sourceLocation;
	
	public SelfExpression(SourceLocation sourceLocation){
		super(sourceLocation);
	}
	public Ship evaluate(){
		return getProgram().getShip();

		
	}

}