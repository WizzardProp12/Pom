package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

public class Asteroid extends MinorPlanet{
	
	// CONSTRUCTORS

	public Asteroid(double xCoord, double yCoord, double xVelocity, double yVelocity, double radius, World world) {
		super(xCoord, yCoord, xVelocity, yVelocity, radius, world);
		
	}
	
	public Asteroid(double xCoord, double yCoord, double xVelocity, double yVelocity, double radius) {
		super(xCoord, yCoord, xVelocity, yVelocity, radius);
		
	}
	
	// MASS (total)
	
	/**
	 * The density of an Asteroid.
	 */
	private final double density = 2.65*Math.pow(10, 12);
	
	/**
	 * Return the density of the Minor Planet.
	 * @return see implementation...
	 */
	@Basic @Immutable @Raw
	public double getDensity() {
		return this.density;
	}
	/**
	 * Return the mass of the Asteroid.
	 * @return see implementation...
	 */
	@Override @Basic @Raw
	public double getMass() {
		return (4/3)*Math.PI*Math.pow(getRadius(), 3)*getDensity();
	}
	
	
}
