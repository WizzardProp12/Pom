package asteroids.model.program.expressions;
import asteroids.model.Bullet;
import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class BulletExpression extends Expression<Bullet> {
	
	private SourceLocation sourceLocation;
	private Ship thisShip = getProgram().getShip();
	private double closestDistanceSoFar = Double.POSITIVE_INFINITY;
	
	public BulletExpression(SourceLocation sourceLocation){
		super(sourceLocation);
	}
	public Bullet evaluate(){
		
		for (Bullet bullet: getProgram().getShip().getWorld().getBulletSet()){
			if (bullet.getSourceShip() == thisShip) 
				return bullet;

		
		}
		return null;
	}
}