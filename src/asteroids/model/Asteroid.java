package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * @author Wouter Cams and Stijn Bruggeman
 * 
 * Studies:
 * Wouter Cams: 2e Bachelor Ingenieurswetenschappen
 * Hoofdrichting Elektrotechniek, nevenrichting computerwetenschappen
 * Stijn Bruggeman: 2e Bachelor Ingenieurswetenschappen
 * Hoofdrichting computerwetenschappen, nevenrichting elektrotechniek
 * 
 * @version  0.2
 * 
 * A link to the GitHub code repository:
 * https://github.com/WizzardProp12/Pom
 * 
 * A class of asteroids in an unbounded two dimensional space.
 * 
 * @invar This is a subclass of MinorPlanet and inherits all its invariants
 */
public class Asteroid extends MinorPlanet {
	
	// CONSTRUCTORS
	
	/**
	 * Initialise a new asteroid with given coordinates, velocity, radius and world.
	 * @param xCoord
	 * 		  The x-coordinate of the asteroid.
	 * 		| getXCoord() == xCoord
	 * @param yCoord
	 * 		  The y-coordinate of the asteroid.
	 * 		| getYCoord() == yCoord
	 * @param xVelocity
	 * 		  The velocity along the x-axis
	 * 		| getXVelocity() == xVelocity
	 * @param yVelocity
	 * 		  The velocity along the y-axis
	 * 		| getYVelocity() == yVelocity
	 * @param radius
	 * 		  The radius of the asteroid.
	 * 		| getRadius() == radius
	 * @param world
	 * 		  The world in which the asteroid is located.
	 * 		| getWorld() == world
	 * @pre   The given coordinates must be valid.
	 * 		| isValidXCoord(xCoord) && isValidYCoord(yCoord)
	 * @pre   The given radius must be valid.
	 * 		| canHaveAsRadius(radius)
	 * @pre   The given world must be valid.
	 * 		| canBePlacedIn(world)
	 * @post ...
	 * 		| new.getPosition() == [xCoord, yCoord]
	 * @post ...
	 * 		| if getAbsSpeed(xVelocity, yVelocity) <= Entity.SPEED_LIMIT
	 * 		| 	then new getXVelocity == xVelocity and new getYVelocity == yVelocity
	 * @post ...
	 * 		| getSpeed() <= Entity.SPEED_LIMIT
	 * @post ...
	 * 		| new getRadius() >= getMinRadius()
	 * @post ...
	 * 		| new getWorld() == world
	 * @throws IllegalArgumentException
	 * 		   If the given coordinates are not legal.
	 * 		 | ! canHaveAsXCoordinate(xCoord) || ! canHaveAsYCoordinate(yCoord)
	 * @throws IllegalArgumentException
	 * 		   If the given radius is not valid
	 * 		 | ! canHaveAsRadius(radius)
	 * @throws IllegalArgumentException
	 * 		   If the asteroid cannot be added to the world.
	 * 		 | ! canBePlacedIn(world)
	 */
	public Asteroid(double xCoord, double yCoord, double xVelocity, double yVelocity, 
			double radius, World world) throws IllegalArgumentException {
		super(xCoord, yCoord, xVelocity, yVelocity, radius, world);
	}
	
	/**
	 * Initialise a new asteroid with given coordinates, velocity, radius and world.
	 */
	public Asteroid(double xCoord, double yCoord, double xVelocity, double yVelocity, 
			double radius) throws IllegalArgumentException {
		this(xCoord, yCoord, xVelocity, yVelocity, radius, null);
		
	}
	
	// MASS (total)
	
	/**
	 * The density of an asteroid.
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
		return (4/3.)*Math.PI*Math.pow(getRadius(), 3)*getDensity();
	}
}
