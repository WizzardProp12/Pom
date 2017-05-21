package asteroids.model.program.expressions;
import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class SelfExpression extends Expression<Ship> {
	
	public SelfExpression(SourceLocation sourceLocation){
		super(sourceLocation);
	}
	public Ship evaluate(){
		System.out.println("selexpr program is null " + (program == null));
		return getProgram().getShip();

		
	}

}