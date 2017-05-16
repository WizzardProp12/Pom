package asteroids.model.program.expressions;
import java.util.HashSet;
import java.util.Random;

import asteroids.model.Entity;
import asteroids.part3.programs.SourceLocation;

public class AnyExpression extends Expression<Entity> {
	
	
	int i = 0;	
	
	public AnyExpression(SourceLocation sourceLocation){
		super(sourceLocation);
	}
	
	public Entity evaluate(){
		HashSet<Entity> entitySet = getProgram().getShip().getWorld().getEntitySet();
		int randInt = new Random().nextInt(entitySet.size());
		return (Entity) entitySet.toArray()[randInt];
	}

}