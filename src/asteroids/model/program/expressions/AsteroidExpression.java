package asteroids.model.program.expressions;
import asteroids.model.Asteroid;
import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class AsteroidExpression extends Expression<Asteroid> {
	
	private SourceLocation sourceLocation;
	private Asteroid closestAsteroid;
	private double closestDistanceSoFar = Double.POSITIVE_INFINITY;
	
	public AsteroidExpression(SourceLocation sourceLocation){
		super(sourceLocation);
	}
	public Asteroid evaluate(){
		
		for (Asteroid Asteroid: getProgram().getShip().getWorld().getSomeEntitySet(Asteroid.class)){	
			if (getProgram().getShip().getDistanceBetweenCentres(Asteroid) <  closestDistanceSoFar || closestAsteroid == null){
				closestAsteroid = Asteroid;
				
			}
		}
		return closestAsteroid;

		
	}

}

