package asteroids.model.program;
import java.util.Random;

import asteroids.model.Entity;
import asteroids.part3.programs.SourceLocation;

public class AnyExpression extends Expression<Entity> {
	
	private SourceLocation sourceLocation;
	private int randInt = new Random().nextInt(getProgram().getShip().getWorld().getEntitySet().size());
	int i = 0;	
	
	public AnyExpression(SourceLocation sourceLocation){
		super(sourceLocation);
	}
	public Entity evaluate(){
		
		for (Entity entity: getProgram().getShip().getWorld().getEntitySet()){//Method getPlanetSet to be added		
			if (i == randInt){
				return entity;
			}
			i++;

		}
		return null;
	}

}