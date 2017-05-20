package asteroids.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * @author Wouter Cams and Stijn Bruggeman
 * 
 * Studies:
 * Wouter Cams: 2e Bachelor Ingenieurswetenschappen
 * Hoofdrichting Elektrotechniek, nevenrichting computerwetenschappen
 * Stijn Bruggeman: 2e Bachelor Ingenieurswetenschappen
 * Hoofdrichting computerwetenschappen, nevenrichting elektrotechniek
 * 
 * @version  0.1
 * 
 * A link to the GitHub code repository:
 * https://github.com/WizzardProp12/Pom
 * 
 * A class of minor planets in an unbounded two dimensional space.
 * 
 * @invar This is a subclass of Entity and inherits all its invariants
 */
public abstract class MinorPlanet extends Entity {
	
	// CONSTRUCTORS
	
	/**
	 * Initialise a new minor planet with given coordinates, velocities,
	 * radius and world.
	 * @param xCoord
	 * 		  The x-coordinate of this new minor planet.
	 * @param yCoord
	 * 	      The y-coordinate of this new minor planet.
	 * @param xVelocity
	 *        The velocity along the x-axis of this new minor planet.
	 * @param yVelocity
	 * 	      The velocity along the y-axis of this new minor planet.
	 * @param radius
	 * 		  The radius of this new minor planet.
	 * @param world
	 * 		  The container of this new minor planet.
	 * @pre   The given coordinates must be valid.
	 * 		| canHaveAsXCoordinate(xCoord) && canHaveAsYCoordinate(yCoord)
	 * @pre   The given radius must be valid.
	 * 		| canHaveAsRadius(radius)
	 * @pre   The given world must be valid.
	 * 		| canBePlacedIn(world)
	 * @post ...
	 * 	  |  new.getXCoord() == xCoord
	 * @post ...
	 * 	  |  new.getYCoord() == yCoord
	 * @post ...
	 * 	  |  new.getXVelocity() == xVelocity
	 * @post ...
	 * 	  |  new.getYVelocity() == yVelocity
	 * @post ...
	 * 	  |  new.getRadius() >= MIN_RADIUS
	 * @post ...
	 * 	  |  new.getOrientation() == orientation
	 * @post ...
	 * 	  | new.getWorld() == world
	 * @post ...
	 * 	  | new.getMass() >= mass
	 * @throws IllegalArgumentException
	 * 		   If the given coordinates are not legal.
	 * 		 | ! canHaveAsXCoordinate(xCoord) || ! canHaveAsYCoordinate(yCoord)
	 * @throws IllegalArgumentException
	 * 		   If the given radius is not valid
	 * 		 | ! canHaveAsRadius(radius)
	 * @throws IllegalArgumentException
	 * 		   If the minor planet cannot be added to the world.
	 * 		 | ! canBePlacedIn(world)
	 */
	public MinorPlanet(double xCoord, double yCoord, double xVelocity, double yVelocity, 
			double radius, World world) throws IllegalArgumentException {		 
		super(xCoord, yCoord, xVelocity, yVelocity, radius, world);
	}
	
	/**
	 * Initialise a new minor planet with given coordinates, velocities
	 * and radius.
	 */
	public MinorPlanet(double xCoord, double yCoord, double xVelocity, double yVelocity, 
			double radius) throws IllegalArgumentException {
		this(xCoord, yCoord, xVelocity, yVelocity, radius, null);
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
	public abstract double getDensity();
	
	/**
	 * Return the mass of the Minor Planet.
	 * @return see implementation...
	 */
	@Override
	public abstract double getMass();

}
