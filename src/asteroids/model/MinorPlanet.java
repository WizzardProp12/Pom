package asteroids.model;

import be.kuleuven.cs.som.annotate.*;

public abstract class MinorPlanet extends Entity{
	
	// CONSTRUCTORS
	
	public MinorPlanet(double xCoord, double yCoord, double xVelocity, double yVelocity, double radius, World world){		 
		super(xCoord, yCoord, xVelocity, yVelocity, radius,world);
	}
	
	public MinorPlanet(double xCoord, double yCoord, double xVelocity, double yVelocity, double radius){		 
		super(xCoord, yCoord, xVelocity, yVelocity, radius);
	}
	
	// RADIUS (defensive)
	
	/**
	 * The default radius for a Ship when the radius is
	 * not specified in the constructor.
	 */
	@Basic @Raw @Immutable @Override
	public double getDefaultRadius() {
		return getMinRadius();
	}
	
	/**
	 * The minimum radius of a bullet.
	 */
	public static final double MIN_RADIUS = 5;

	/**
	 * Return the minimum radius of a MinorPlanet.
	 * @return see implementation...
	 */
	@Basic @Raw @Immutable 
	public static double getMinRadius() {
		return MIN_RADIUS;
	}

	/**
	 * Check if the given radius suits the prime object.
	 * @return see implementation...
	 */
	@Raw
	public boolean canHaveAsRadius(double radius) {
		return (radius >= getMinRadius());
	}
	
	/**
	 * Return the density of the MinorPlanet.
	 * @return see implementation...
	 */
	@Override
	public  abstract double getDensity();
	
	/**
	 * Return the mass of the Minor Planet.
	 * @return see implementation...
	 */
	@Override
	public abstract double getMass();

}
