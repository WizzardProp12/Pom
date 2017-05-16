package asteroids.model.program.expressions;
import asteroids.model.Asteroid;
import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class AsteroidExpression extends Expression<Asteroid> {
	
	private SourceLocation sourceLocation;
	private Asteroid closestAsteroid;
	private Ship thisShip = getProgram().getShip();
	private double closestDistanceSoFar = Double.POSITIVE_INFINITY;
	
	public AsteroidExpression(SourceLocation sourceLocation){
		super(sourceLocation);
	}
	public Asteroid evaluate(){
		
		for (Asteroid Asteroid: getProgram().getShip().getWorld().getAsteroidSet()){//Method getAsteroidSet to be added		
			if (thisShip.getDistanceBetweenCentres(Asteroid) <  closestDistanceSoFar || closestAsteroid == null){
				closestAsteroid = Asteroid;
				
			}
		}
		return closestAsteroid;

		
	}

}
