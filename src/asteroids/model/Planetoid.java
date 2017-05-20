package asteroids.model;

import be.kuleuven.cs.som.annotate.*;

public class Planetoid extends MinorPlanet {
	
	// CONSTRUCTORS
	
	/**
	 * Initialise a new planetoid with given coordinates, velocity, radius,
	 * travelled distance and world.
	 * @param xCoord
	 * 		  The x-coordinate of the planetoid.
	 * 		| getXCoord() == xCoord
	 * @param yCoord
	 * 		  The y-coordinate of the planetoid.
	 * 		| getYCoord() == yCoord
	 * @param xVelocity
	 * 		  The velocity along the x-axis
	 * 		| getXVelocity() == xVelocity
	 * @param yVelocity
	 * 		  The velocity along the y-axis
	 * 		| getYVelocity() == yVelocity
	 * @param radius
	 * 		  The radius of the planetoid.
	 * 		| getRadius() == radius
	 * @param travelledDistance
	 * 		  The distance the planetoid has travelled.
	 * 		| getTravelledDistance() == travelledDistance
	 * @param world
	 * 		  The world in which the planetoid is located.
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
	 * 		   If the planetoid cannot be added to the world.
	 * 		 | ! canBePlacedIn(world)
	 */
	public Planetoid(double xCoord, double yCoord, double xVelocity, double yVelocity, 
			double radius, double travelledDistance, World world) {
		super(xCoord, yCoord, xVelocity, yVelocity, radius, world);
		this.originalRadius = radius;
		setTravelledDistance(travelledDistance);
		updateCurrentRadius();
	}
	
	/**
	 * Initialise a new planetoid with given coordinates, velocity, radius
	 * and travelled distance.
	 */
	public Planetoid(double xCoord, double yCoord, double xVelocity, double yVelocity, 
			double radius, double travelledDistance) {
		this(xCoord, yCoord, xVelocity, yVelocity, radius, travelledDistance, null);
	}
	
	/**
	 * Initialise a new planetoid with given coordinates, velocity and radius.
	 */
	public Planetoid(double xCoord, double yCoord, double xVelocity, double yVelocity, 
			double radius) {
		this(xCoord, yCoord, xVelocity, yVelocity, radius, 0, null);
	}
	
	
	// TRAVELLED DISTANCE
	
	/**
	 * Variable that keeps track of the total distance travelled by the planetoid.
	 */
	private double travelledDistance;
	
	/**
	 * Get the total distance travelled by the planetoid.
	 * @return see implementation...
	 */
	@Basic @Raw
	public double getTravelledDistance(){
		return travelledDistance;
	}
	
	/**
	 * Set the total distance travelled by the planetoid.
	 * @return see implementation...
	 * 
	 */
	protected void setTravelledDistance(double distance) {
		this.travelledDistance = distance;
	}
	
	/**
	 * Increase the travelled distance by the given amount.
	 * @post ...
	 * 	   | new getTravelledDistance() == old getTravelledDistance() + distance
	 */
	protected void increaseTravelledDistance(double distance) {
		setTravelledDistance(getTravelledDistance() + distance);
	}
	
	
	// SHRINKING AND DISSOLVING
	
	/**
	 * How much percentage the planetoid shrinks per travelled km.
	 */
	public static final double SHRINKING_PERCENTAGE = 0.0001;
	
	/**
	 * Return how much percentage the planetoid shrinks per travelled km.
	 */
	@Basic @Raw @Immutable
	public static double getShrinkingPercentage() {
		return SHRINKING_PERCENTAGE;
	}
	
	/**
	 * Overwritten function of the Entity.die() function to implement the
	 * spawning of 2 asteroids if the radius of the planetoid is >= 30.
	 * @post The planetoid will be terminated
	 * 	   | isTerminated() == True
	 */
	@Override
	public void die() {
		super.die();
		
		if (getRadius() >= 30 && getWorld() != null) { // spawn two asteroids
			double radius = getRadius() / 2;
			double direction = Math.random() * 2 * Math.PI;
			double xVelocity = Math.cos(direction) * 1.5 * getSpeed();
			double yVelocity = Math.sin(direction) * 1.5 * getSpeed();
			double[] velocities = limitSpeed(xVelocity, yVelocity);
			double deltaX = Math.cos(direction) * 0.75 * getRadius();
			double deltaY = Math.sin(direction) * 0.75 * getRadius();
			double x = getXCoord();
			double y = getYCoord();

			Asteroid a1 = new Asteroid(x + deltaX, y + deltaY, velocities[0], velocities[1], radius);
			Asteroid a2 = new Asteroid(x - deltaX, y - deltaY, -velocities[0], -velocities[1], radius);
			if ( a1.canBePlacedIn(getWorld()) ) getWorld().add(a1);
			if ( a2.canBePlacedIn(getWorld()) ) getWorld().add(a2);
		}
	}
		
	
	
	// RADIUS (defensive)
	/**
	 * The minimum radius of a planetoid.
	 */
	public final static double MIN_RADIUS = 5;
	
	/**
	 * Return the minimum radius of a planetoid.
	 * @see implementation...
	 */
	@Basic @Raw @Immutable
	public static double GET_MIN_RADIUS() { return MIN_RADIUS; }
	
	/**
	 * Check whether the planetoid can have the given radius.
	 */
	@Basic @Raw
	public boolean canHaveAsRadius(double radius) {
		return radius >= Planetoid.GET_MIN_RADIUS();
	}
	
	
	/**
	 * The original radius with which the planetoid was initialised.
	 */
	private final double originalRadius;
	
	/**
	 * Return the original radius with which the planetoid was initialised.
	 */
	@Basic @Raw @Immutable
	public double getOriginalRadius() { return this.originalRadius; }
	
	/**
	 * Return the current radius of the planetoid.
	 * This is the original radius, shrunk by an amount depending on the
	 * shrinking percentage and travelled distance
	 * @invar  The current radius is always smaller than or equal to the original radius
	 * 		 | getCurrentRadius() <= getOriginalRadius()
	 * @return The current radius
	 * 		 | see implementation...
	 */
	@Basic @Raw
	public double getCurrentRadius() {
		return getOriginalRadius() 
				- getShrinkingPercentage() * 0.01 * getTravelledDistance();
	}
	
	/**
	 * Update the radius of the planetoid depending on how much it has
	 * shrunk. If the planetoid has shrunk below its minimum radius, it dies.
	 */
	public void updateCurrentRadius() {
		double currentRadius = getCurrentRadius();
		if (currentRadius < GET_MIN_RADIUS())
			die();
		else
			setRadius(currentRadius);
	}
	
	
	// MASS (total)
	
	/**
	 * The density of a planetoid.
	 */
	private final double density = 0.917 * Math.pow(10, 12);
	
	/**
	 * Returns the density of a planetoid
	 * @return see implementation....
	 */
	@Basic @Raw @Immutable
	public double getDensity() {
		return this.density;
	}
	
	/**
	 * Returns the mass of a planetoid.
	 * @return see implementation...
	 */
	@Basic @Raw
	public double getMass() {
		return (4/3.)*Math.PI*Math.pow(getRadius(), 3)*getDensity();
	}
	
	
	// MOVING
	
	/**
	 * Move the planetoid for the given amount of time and update its
	 * radius afterwards.
	 */
	@Override
	public void move(double time) throws IllegalArgumentException {
		super.move(time);
		double travelledDistance = time * getSpeed();
		increaseTravelledDistance(travelledDistance);
		updateCurrentRadius();
	}
}
