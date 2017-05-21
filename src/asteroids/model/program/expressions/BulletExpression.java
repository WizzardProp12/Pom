package asteroids.model.program.expressions;
import asteroids.model.Bullet;
import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class BulletExpression extends Expression<Bullet> {
	
	public BulletExpression(SourceLocation sourceLocation){
		super(sourceLocation);
	}
	
	public Bullet evaluate(){
		Ship thisShip = getProgram().getShip();
		for (Bullet bullet: getProgram().getShip().getWorld().getSomeEntitySet(Bullet.class)) {
			if (bullet.getSourceShip() == thisShip) 
				return bullet;
		}
		return null;
	}
}