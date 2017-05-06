package asteroids.model;


import java.util.ArrayList;
import java.util.Iterator;

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
 * A class of entities in an unbounded two dimensional space, 
 * involving coordinates, velocities, a radius and an 
 * orientation. 
 * 
 * Superclass of Ship.java and Bullet.java
 * @invar ...
 * 		| isValidXCoord(getXCoord())
 * @invar ...
 * 		| isValidYCoord(getYCoord())
 * @invar ...
 * 		| getXVelocity() <= getSpeedLimit()
 * @invar ...
 * 		| getYVelocity() <= getSpeedLimit()
 * @invar ...
 * 		| getSpeed() <= getSpeedLimit()
 * @invar ...
 * 		| canHaveAsRadius(getRadius())
 * @invar ...
 * 		| canHaveAsWorld(getWorld())
 * @invar The entity is always located in a valid world
 * 		| canBePlacedInWorld(getWorld())
 * @invar The mass of the entity is equal to or greater than the minimum mass
 * 		| getMass() >= getMinimumMass()
 */
public abstract class Entity {
	
	// CONSTRUCTORS
	
	/**
	 * Initialise a new entity with given coordinates, velocity, radius and world.
	 * @param xCoord
	 * 		  The x-coordinate of the entity.
	 * 		| getXCoord() == xCoord
	 * @param yCoord
	 * 		  The y-coordinate of the entity.
	 * 		| getYCoord() == yCoord
	 * @param xVelocity
	 * 		  The velocity along the x-axis
	 * 		| getXVelocity() == xVelocity
	 * @param yVelocity
	 * 		  The velocity along the y-axis
	 * 		| getYVelocity() == yVelocity
	 * @param radius
	 * 		  The radius of the entity.
	 * 		| getRadius() == radius
	 * @param world
	 * 		  The world in which the entity is located.
	 * 		| getWorld() == world
	 * @pre   The given coordinates must be valid.
	 * 		| canHaveAsXCoordinate(xCoord) && canHaveAsYCoordinate(yCoord)
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
	 * 		   If the entity cannot be assigned to its position.
	 * 		 || ! isValidPosition(position)
	 * @throws IllegalArgumentException
	 * 		   If the entity cannot be added to the world.
	 * 		 | ! canBePlacedIn(world)
	 */
	public Entity(double xCoord, double yCoord, double xVelocity, double yVelocity, 
			double radius, World world) throws IllegalArgumentException{
		
		// velocities (total)
		double[] velocities = limitSpeed(xVelocity, yVelocity);
		setXVelocity(velocities[0]);
		setYVelocity(velocities[1]);
		
		// position (defensive)
		setXCoord(xCoord);
		setYCoord(yCoord);
		
		// defensive
		if (canHaveAsRadius(radius))
			this.radius = radius;
		else
			this.radius = getDefaultRadius();
		
		
		if (world == null)
			this.setWorld(world);
		else
			world.add(this);
		
		nr = nr+1;
		this.nr_ = nr;
	}
	
	/**
	 * Initialise a new entity with given coordinates, velocity, radius and no world.
	 */
	public Entity(double xCoord, double yCoord, double xVelocity, double yVelocity,
			double radius) {
		this(xCoord, yCoord, xVelocity, yVelocity, radius, null);
	}
	
	/**
	 * Initialise a new entity with given coordinates, velocity, default
	 * radius and no world.
	 */
	public Entity(double xCoord, double yCoord, double xVelocity, double yVelocity) {
		this(xCoord, yCoord, xVelocity, yVelocity, 0);
	}
	
	/**
	 * Initialise a new entity with given coordinates, no velocity, default
	 * radius and no world.
	 */
	public Entity(double xCoord, double yCoord) {
		this(xCoord, yCoord, 0, 0);
	}
	
	/**
	 * Initialise a new entity with  coordinates (0, 0) , no velocity, default
	 * radius and no world.
	 */
	public Entity() {
		this(0, 0);
	}
	
	
	// tostring
	
	public static int nr = 0;
	
	private int nr_ = 0;
	
	public String toString() {
		String output = "Entity nr " + nr + " at (" 
						+ ((Integer) ((Double) getXCoord()).intValue()).toString() 
						+ "," + ((Integer) ((Double) getYCoord()).intValue()).toString() + ")"
						+ "(" + this.getClass() + ")";
		return output;
	}
	
	
	// POSITION (defensive)
	
	/**
	 * The position of the Entity.
	 */
	protected Position position = new Position();
	
	/**
	 * Return the Position object of the prime object
	 * @invar The returned position is always valid
	 * 		| isValidPosition(getPosition())
	 */
	public Position getPosition() {
		return position;
	}
	
	/**
	 * Returns the x-coordinate of the prime entity.
	 * @invar The returned xCoord is always valid.
	 * 		| isValidPosition(getXCoord(), validYCoord)
	 * @return The x-coordinate of the prime entity.
	 * 		|  result == getPosition().getXCoord()
	 */
	@Basic @Raw
	public double getXCoord() {
		return getPosition().getXCoord();
	}
	
	/**
	 * Returns the y-coordinate of the prime entity.
	 * @invar The returned yCoord is always valid.
	 * 		| isValidPosition(validXCoord, getYCoord())
	 * @return The y-coordinate of the prime entity.
	 * 		|  result == getPosition().getYCoord();
	 */
	@Basic @Raw
	public double getYCoord() {
		return getPosition().getYCoord();
	}
	
	
	/**
	 * Return whether the given position would be valid for the prime object.
	 * @return see implementation...
	 */
	@Raw
	public boolean isValidPosition(Position position) {
		if (getWorld() == null) return true;
		else return isWithinBoundariesOf(getWorld(), position);
	}
	
	/**
	 * Return whether the prime object can take the given position.
	 * This method is like isValidPosition() but also takes in account
	 * other entities.
	 * @return see implementation...
	 */
	@Raw
	public boolean canTakePosition(Position position) {
		if (! isValidPosition(position))
			return false;
		return (getWorld() == null || ! wouldOverLapAt(position, getWorld().getEntityList()));
	}
	
	
	/**
	 * Set the position of the entity.
	 * @post The new position is valid.
	 * 	   | isValidPosition(position)
	 * @throws IllegalArgumentException
	 * 		   If the given position is not valid
	 * 		 | ! isValidPosition(position)
	 */
	@Model @Raw
	protected void setPosition(Position position) throws IllegalArgumentException {
		if (! canTakePosition(position)) throw new IllegalArgumentException(
				"invalid position, see canTakePosition()");
		this.position = position;
	}
	
	/**
	 * Set the x-coordinate of the entity.
	 * @param newXCoord
	 * 		  The new x-coordinate of the entity.
	 * @post  The x-coordinate of this entity is equal 
	 * 		  to the given x-coordinate.
	 * 	    | new.getXCoord() = newXCoord
	 * @throws IllegalArgumentException
	 * 		   If the given x-coordinate is NaN.
	 * 	    |  newXCoord == Double.NaN
	 * @throws IllegalArgumentException
	 * 		   If the given x-coordinate would result in a position the entity cannot take
	 * 		|  ! canTakePosition(new Position(xCoord, getYCoord())
	 */
	@Model @Raw
	protected void setXCoord(double xCoord) throws IllegalArgumentException {
		if (! canTakePosition(new Position(xCoord, getYCoord()))) throw new 
		IllegalArgumentException("given xCoord would result in an illegal position, "
								+ "see canTakePosition()");
		getPosition().setXCoord(xCoord);
	}
	
	/**
	 * Set the y-coordinate of the entity.
	 * @param newYCoord
	 * 		  The new y-coordinate of the entity.
	 * @post  The y-coordinate of this entity is equal 
	 * 		  to the given y-coordinate.
	 * 	    | new.getYCoord() = newYCoord
	 * @throws IllegalArgumentException
	 * 		   If the given y-coordinate is NaN.
	 * 	    |  newYCoord == Double.NaN
	 * @throws IllegalArgumentException
	 * 		   If the given y-coordinate would result in a position the entity cannot take
	 * 		|  ! canTakePosition(new Position(getXCoord(), yCoord)
	 */
	@Model @Raw
	protected void setYCoord(double yCoord) throws IllegalArgumentException {
		if (! canTakePosition(new Position(getXCoord(), yCoord))) throw new 
		IllegalArgumentException("given yCoord would result in an illegal position, "
								+ "see canTakePosition()");
		getPosition().setYCoord(yCoord);
	}
	
	
	/**
	 * Return whether the prime object would be within the given worlds 
	 * boundaries if it would be at the given position in that world.
	 * @return see implementation...
	 */
	public boolean isWithinBoundariesOf(World world, Position position) {
		if (world == null)
			return true;
		
		double x = position.getXCoord();
		double y = position.getYCoord();
		double r = getRadius();
		double w = world.getWidth();
		double h = world.getHeight();
		
		// left, right, top, bottom
		return (0.99*r < x && x + 0.99*r < w && 0.99*r < y && y + 0.99*r < h);
	}
	
	/**
	 * Return whether the prime object would be within the given worlds 
	 * boundaries if it would be at its current position in that world.
	 * @return see implementation...
	 */
	public boolean isWithinBoundariesOf(World world) {
		double x = getXCoord();
		double y = getYCoord();
		return isWithinBoundariesOf(world, new Position(x, y));
	}
	
	/**
	 * Return with which border the entity is currently overlapping
	 * @result == 0 (if none)
	 * 			  1 (if left)
	 * 			  2 (if top)
	 * 			  3 (if right)
	 * 			  4 (if bottom)
	 */
	public int getOverLappingBorder(World world) {
		double left   = getXCoord();
		double top    = world.getHeight() - getYCoord();
		double right  = world.getWidth() - getXCoord();
		double bottom = getYCoord();
		double[] distances = {left, top, right, bottom};
		
		double min = distances[0];
		for (double distance : distances)
			if (distance < min) min = distance;
		
		if (0.99*getRadius() < min) return 0;
		else if (min == left) 	return 1;
		else if (min == top) 	return 2;
		else if (min == right)  return 3;
		else if (min == bottom) return 4;
		else return 0;
	}
	
	
	/**
	 * Check whether the given time is valid.
	 * @see implementation...
	 */
	@Basic @Immutable @Raw
	public static boolean isValidTime(double time) {
		return (0 <= time && !Double.isNaN(time) && !Double.isInfinite(time));
	}
	
	/**
	 * Return the coordinates the entity would be at after moving forward for
	 * the given amount of seconds.
	 * @throws IllegalArgumentException
	 * 		   The given duration time is invalid.
	 * 		 | ! isValidTime(time)
	 */
	public double[] getFutureCoordinates(double time) throws IllegalArgumentException {
		if (!Entity.isValidTime(time))
			throw new IllegalArgumentException("the given time ("+ time +") is invalid");
		
		double xCoord = getXCoord() + time*getXVelocity();
		double yCoord = getYCoord() + time*getYVelocity();
		return new double[] {xCoord, yCoord};
	}
	
	/**
	 * Return the position the entity would be at after moving forward for
	 * the given amount of seconds.
	 * @throws IllegalArgumentException
	 * 		   The given duration time is invalid.
	 * 		 | ! isValidTime(time)
	 */
	public Position getFuturePosition(double time) throws IllegalArgumentException {
		double[] newCoords = getFutureCoordinates(time);
		return new Position(newCoords[0], newCoords[1]);
	}
	
	/**
	 * Change the position of the entity based on the current position, 
	 * velocity and a given time duration.
	 * @param  time
	 * 		   The time for which the entity must be moved.
	 * @effect The entity is moved to it's future position
	 * 		 | new.getPosition() == old.getFuturePosition(time)
	 * @throws IllegalArgumentException
	 * 		   The given duration time is invalid.
	 * 		 | ! isValidTime(time)
	 * @throws IllegalArgumentException
	 * 		   The given duration would place the entity outside of its world boundaries
	 * 		 | ! isValidPosition(getFuturePosition(time))
	 */
	public void move(double time) throws IllegalArgumentException {
		Position futurePosition = getFuturePosition(time);
		if (! isWithinBoundariesOf(getWorld(), futurePosition))
			throw new IllegalArgumentException(
					"moving the entity for the given time would place "
					+ "it outside of the world boundaries");
		else {
			getPosition().setXCoord(futurePosition.getXCoord());
			getPosition().setYCoord(futurePosition.getYCoord());
		}
	}
	
	
	// VELOCITIES (total)
	
	/**
	 * The velocity of the entity along the x-axis.
	 */
	private double xVelocity = 0;
	
	/**
	 * The velocity of the entity along the y-axis.
	 */
	private double yVelocity = 0;
	
	/**
	 * Return the velocity of the prime object along the x-axis.
	 * @post The x velocity of the prime object is under the speed limit.
	 * 	     | getAbsVelocity(getXVelocity(), 0) <= SPEED_LIMIT
	 * @return The velocity of the prime object along the x-axis.
	 * 		|  result == this.xVelocity
	 */
	@Basic @Raw
	public double getXVelocity() {
		return this.xVelocity;
	}
	
	/**
	 * Return the velocity of the prime object along the y-axis.
	 * @post The y velocity of the prime object is under the speed limit.
	 * 	     | getAbsVelocity(0, getYVelocity()) <= SPEED_LIMIT
	 * @return The velocity of the prime object along the y-axis.
	 * 		 | result == this.yVelocity
	 */
	@Basic @Raw
	public double getYVelocity() {
		return this.yVelocity;
	}
	
	/**
	 * Set the x velocity, limiting the speed if it would exceed the speed limit.
	 * @param xVelocity
	 * 		  The xVelocity to be set.
	 * @post The absolute speed of the prime object will be kept under
	 * 		 the speed limit.
	 * 	     | getAbsVelocity(new xVelocity, new yVelocity) <= SPEED_LIMIT
	 */
	protected void setXVelocity(double xVelocity) {
		yVelocity = getYVelocity();
		double[] newVelocities = limitSpeed(xVelocity, yVelocity);
		this.xVelocity = newVelocities[0];
		this.yVelocity = newVelocities[1];
	}
	
	/**
	 * Set the y velocity, limiting the speed if it would exceed the speed limit.
	 * @param yVelocity
	 * 		  The yVelocity to be set.
	 * @post The absolute speed of the prime object will be kept under
	 * 		 the speed limit.
	 * 	     | getAbsVelocity(new xVelocity, new yVelocity) <= SPEED_LIMIT
	 */
	protected void setYVelocity(double yVelocity) {
		xVelocity = getXVelocity();
		double[] newVelocities = limitSpeed(xVelocity, yVelocity);
		this.xVelocity = newVelocities[0];
		this.yVelocity = newVelocities[1];
	}
	
	
	// ABSOLUTE VELOCITY (total)
	
	/**
	 * Return an array of the x and y velocities of the entity.
	 * @post The absolute speed of the prime object will be kept under
	 * 		 the speed limit.
	 * 	     | getAbsVelocity(result[0], result[1]) <= SPEED_LIMIT
	 * @return An array of the x and y velocity
	 * 		 | result[0] == getXVelocity()
	 * 		 | result[1] == getYVelocity()
	 */
	@Basic @Raw
	public double[] getVelocities() {
		return new double[] {getXVelocity(), getYVelocity()};
	}
	
	/**
	 * Returns the absolute velocity using the formula of the vector length
	 * on the (xVelocity, yVelocity) vector.
	 * @param xVelocity
	 * 		  The x velocity
	 * @param yVelocity
	 * 		  The y velocity
	 * @return The absolute velocity
	 * 		  | result == Math.sqrt(Math.pow(xVelocity, 2) + Math.pow(xVelocity, 2)
	 */
	public static double getAbsSpeed(double xVelocity, double yVelocity) {
		return Math.sqrt(Math.pow(xVelocity, 2) + Math.pow(yVelocity, 2));
	}
	
	/**
	 * Return the speed of the prime object.
	 * @invar The absolute speed is lower than or equal to the speed limit
	 * 		| getSpeed() <= getSpeedLimit()
	 */
	@Basic @Raw
	public double getSpeed() {
		double absSpeed = Entity.getAbsSpeed(getXVelocity(), getYVelocity());
		if (absSpeed > getSpeedLimit())
			return getSpeedLimit();
		return absSpeed;
	}
	
	/**
	 * Return the x and y velocities that respect the speed limit but
	 * also maintain the direction of the entity.
	 * @param xVelocity
	 * 		  The x velocity of the entity.
	 * @param yVelocity
	 * 		  The y velocity of the entity.
	 * @post  ...
	 * 		| getAbsVelocity(result[0], result[1]) <= getSpeedLimiet
	 * @return An array of the scaled down velocities.
	 */
	public double[] limitSpeed(double xVelocity, double yVelocity) {
		double newVelocities[] = new double[2];
		double absVelocity = Entity.getAbsSpeed(xVelocity, yVelocity);
		if (absVelocity > this.getSpeedLimit()) {
			newVelocities[0] = xVelocity*(getSpeedLimit()/absVelocity);
			newVelocities[1] = yVelocity*(getSpeedLimit()/absVelocity);
			return newVelocities;
		}
		newVelocities[0] = xVelocity;
		newVelocities[1] = yVelocity;
		return newVelocities;
	}
	
	
	// SPEED LIMIT (total)
	
	/**
	 * A static variable representing the speed limit.
	 * This is used as the upper bound for the speed limits.
	 */
	public static double LIGHT_SPEED = 300000;
	
	/**
	 * Return the light speed.
	 */
	@Basic @Raw @Immutable
	public static double getLightSpeed() {
		return Entity.LIGHT_SPEED;
	}
	
	/**
	 * A that represents the speed limit of the Entity.
	 */
	private double speedLimit = Entity.getLightSpeed();
	
	/**
	 * Return the speed limit of the prime object.
	 */
	@Basic @Immutable @Raw
	public double getSpeedLimit() {
		return speedLimit;
	}
	
	/**
	 * Check whether the given speed limit is valid.
	 * @return true if the speed limit is smaller than the speed of light.
	 * 		 | (0 < speedLimit || speedLimit <= Entity.getLightSpeed())
	 */
	@Raw
	public boolean canHaveAsSpeedLimit(double speedLimit) {
		return (0 < speedLimit || speedLimit <= Entity.getLightSpeed());
	}
	
	/**
	 * Set the speed limit of the prime object if the given speed limit is valid.
	 * @post The speed limit is valid
	 * 	   | canHaveAsSpeedLimit(speedLimit)
	 */
	public void setSpeedLimit(double speedLimit) {
		if (canHaveAsSpeedLimit(speedLimit))
			this.speedLimit = speedLimit;
	}
	
	
	// RADIUS (defensive)
	
	/**
	 * The radius of the entity.
	 */
	private double radius;
	
	/**
	 * Return the radius of the prime object.
	 * @invar The radius is valid.
	 * 		| canHaveAsRadius(getRadius()) == true
	 */
	@Basic @Immutable @Raw
	public double getRadius() {
		return this.radius;
	}
	
	/**
	 * Check if the given radius suits the prime object.
	 * @return see implementation...
	 */
	@Raw
	public abstract boolean canHaveAsRadius(double radius);
	
	/**
	 * Set the given radius.
	 */
	@Raw
	public void setRadius(double radius) throws IllegalArgumentException {
		if (! canHaveAsRadius(radius)) throw new IllegalArgumentException(
				"The given radius is not valid");
		this.radius = radius;
	}
	
	/**
	 * Return the default radius of the prime object.
	 * This abstract method must be overwritten in the subclasses.
	 */
	@Basic @Immutable @Raw
	public abstract double getDefaultRadius();
	
	
	// WORLD - ADDING AND REMOVING (defensively)
	

	/**
	 * The world in which the entity is located.
	 */
	private World world;
	
	/**
	 * Return the world in which the entity is located.
	 * @invar The world contains the entity
	 * 		 | getWorld().contains()
	 * @invar The entity is located within the world boundaries.
	 * 		 | isWithinBoundaries(getWorld())
	 */
	@Basic
	public World getWorld() {
		return world;
	}
	
	/**
	 * Returns whether the entity can change world.
	 */
	@Basic
	public boolean canChangeWorld() {
		if (getWorld() == null) return true;
		else {
			System.out.println("canChangeWorld()");
			return ! getWorld().contains(this);
		}
	}
	
	/**
	 * Returns whether the entity can be placed in the given world.
	 * @return ...
	 * 		 | (isWithinBoundariesOf(world) && ! overlaps(world.getEntitySet()) )
	 */
	public boolean canBePlacedIn(World world) {
		if (world == null) return true;
		return (isWithinBoundariesOf(world) && !overlaps(world.getEntityList()));
	}
	
	/**
	 * Set the container of the entity.
	 * @post   ...
	 * 	     | getWorld() == world
	 * @throws IllegalArgumentException
	 * 		   If the entity is still referenced by its current world.
	 * 		 | ! canChangeWorld()
	 * @throws IllegalArgumentException
	 * 		   If the entity can't be placed in the given world
	 * 		 | ! canBePlacedIn(world)
	 * @throws IllegalArgumentException
	 *		   If the the entity is not referenced by the given world.
	 *		 | world != null && ! world.contains(this)
	 */
	protected void setWorld(World world) throws IllegalArgumentException {
		if (! canChangeWorld()) throw new IllegalArgumentException(
				"entity is still referenced by current world");
		if (! canBePlacedIn(world)) throw new IllegalArgumentException(
				"entity can't be placed in the given world");
		if (world != null && ! world.contains(this)) throw new IllegalArgumentException(
				"the entity is not referenced by the given world");
		this.world = world;
	}
	
	
	// WORLD - SEARCHING (total)
	
	
	/**
	 * Return whether the prime object equals the argument.
	 * @param other
	 * 		  The other entity.
	 * @return see implementation...
	 */
	@Override @Basic
	public boolean equals(Object other){
		if (! (other instanceof Entity))
			return false;
		else if (getXCoord() == ((Entity) other).getXCoord() 
				&& getYCoord() == ((Entity) other).getYCoord()
				&& getXVelocity() == ((Entity) other).getXVelocity() 
				&& getYVelocity() == ((Entity) other).getYVelocity()
				&& getRadius() == ((Entity) other).getRadius())
			return true;
		else
			return false;
	}
	
	
	// MASS (total)
	
	/**
	 * Return the density of the entity
	 */
	@Basic @Immutable @Raw
	public abstract double getDensity();
	
	/**
	 * Return the minimum mass of the entity.
	 */
	@Basic @Immutable @Raw
	public double getMinimumMass() {
		return (4/3)*Math.PI*Math.pow(getRadius(), 3)*getDensity();
	}
	
	/**
	 * Return the mass of the entity.
	 */
	@Basic @Raw @Immutable
	abstract double getMass();
	
	
	// COLLISION PREDICTION (defensive)
	
	/**
	 * Return the distance between the centres of two entities.
	 * @param other
	 * 		  The other entity.
	 * @return The distance between the centres of two entities.
	 * 		 | see implementation...
	 * @throws NullPointerException
	 * 		   The given argument references a null pointer.
	 * 		 | other == null
	 */
	public double getDistanceBetweenCentres(Entity other) throws NullPointerException {
		if (other == null)
			throw new NullPointerException("argument references null pointer.");
		double deltaX = getXCoord() - other.getXCoord();
		double deltaY = getYCoord() - other.getYCoord();
		return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
	}
	
	/**
	 * Return the distance between this entity and the other entity.
	 * @param other
	 * 		  The other entity.
	 * @return The distance between this entity and the other entity.
	 * 		 | see implementation...
	 * @throws NullPointerException
	 * 		   The given argument references a null pointer.
	 * 		 | other == null
	 */
	public double getDistanceBetween(Entity other) throws NullPointerException {
		if (other == null)
			throw new NullPointerException("argument references null pointer.");
		return getDistanceBetweenCentres(other) - getRadius() - other.getRadius();
	}
	
	
	/**
	 * Return whether this entity significantly overlaps with the other entity.
	 * Important: this function ignores the entities worlds.
	 * @param other
	 * 		  The other entity.
	 * @throws NullPointerException
	 * 		   The given argument references a null pointer.
	 *       | other == null
	 * @return True if the entities overlap
	 * 		 | getDistanceBetweenCentres(other) <= 0.99*(getRadius() + other.getRadius())
	 */
	public boolean overlaps(Entity other) throws NullPointerException {
		if (other == null) return false;
		double totalRadii = getRadius() + other.getRadius();
		return getDistanceBetweenCentres(other) <= 0.99*totalRadii;
	}
	
	/**
	 * Return whether the entity overlaps with one or more of the
	 * entities in the given array.
	 */
	public boolean overlaps(ArrayList<Entity> otherEntities) {
		for (Entity other : otherEntities){
			if (this != other && overlaps(other))
				return true;
		}
		return false;
	}
	
	/**
	 * Return whether this entity would overlap the given entity
	 * if it was placed at the given position.
	 */
	public boolean wouldOverLapAt(Position position, Entity other) {
		if (position == null || other == null) return false;
		double deltaX = position.getXCoord() - other.getXCoord();
		double deltaY = position.getYCoord() - other.getYCoord();
		double distBetweenCenters = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
		return distBetweenCenters < 0.99 * (getRadius() + other.getRadius());
	}
	
	/**
	 * Return whether this entity would overlap any of the given entities
	 * if it was placed at the given position.
	 */
	public boolean wouldOverLapAt(Position position, ArrayList<Entity> otherEntities) {
		for (Entity other : otherEntities){
			if (this != other && wouldOverLapAt(position, other))
				return true;
		}
		return false;
	}
	
	
	/**
	 * Return the time it will take for this entity to collide with the other entity.
	 * @param other
	 * 		  The other entity.
	 * @return The time it will take before the entities collide,
	 * 		   if and only if the entities collide.
	 * 		   Else, infinity is returned.
	 * @throws NullPointerException
	 * 		   If the given argument references the null pointer (thrown by .overlaps())
	 * 		 | other == null
	 */
	public double getTimeToCollision(Entity other) throws NullPointerException {
		if (this.overlaps(other) == true) return 0;
		
		double deltaRX = other.getXCoord() - this.getXCoord();
		double deltaRY = other.getYCoord() - this.getYCoord();
		double sigma = other.getRadius() + this.getRadius();
		double deltaVX = other.getXVelocity() - this.getXVelocity();
		double deltaVY = other.getYVelocity() - this.getYVelocity();
		
		double d = Math.pow(deltaRX * deltaVX + deltaRY * deltaVY, 2) 
				   - (Math.pow(deltaVX, 2) + Math.pow(deltaVY, 2))
				       * (Math.pow(deltaRX, 2) + Math.pow(deltaRY, 2) 
				   - Math.pow(sigma, 2));
		
		if ((deltaVX * deltaRX + deltaVY * deltaRY >= 0) || (d <= 0) )
			return Double.POSITIVE_INFINITY;
		
		double time = - (deltaVX * deltaRX + deltaVY * deltaRY + Math.sqrt(d))
						/ (Math.pow(deltaVX, 2) + Math.pow(deltaVY, 2));
		return time;
	}
	
	
	/**
	 * Return how long it will take before the entity collides with
	 * one of the world boundaries.
	 * @throws NullPointerException
	 * 		   If the given argument references the null pointer
	 * 		 | world == null
	 */
	public double getTimeToCollision(World world) throws NullPointerException {
		if (world == null) throw new NullPointerException(
				"given argument references the null pointer");
		if (getSpeed() == 0) return Double.POSITIVE_INFINITY;
		
		return Math.min(getTimeToHorizontalCollision(world), 
						getTimeToVerticalCollision(world));
	}
	
	public double getTimeToHorizontalCollision(World world) throws NullPointerException {
		if (getXVelocity() > 0) {
			return (world.getWidth() - getPosition().getXCoord() - getRadius())
						/ getXVelocity();
		} else if (getXVelocity() < 0) {
			return (getPosition().getXCoord() - getRadius())
						/ -getXVelocity();
		} else { return Double.POSITIVE_INFINITY; }
	}
	
	public double getTimeToVerticalCollision(World world) throws NullPointerException {
		if (getYVelocity() > 0) {
			return (world.getHeight() - getPosition().getYCoord() - getRadius())
						/ getYVelocity();
		} else if (getYVelocity() < 0) {
			return (getPosition().getYCoord() - getRadius())
						/ getYVelocity();
		} else { return Double.POSITIVE_INFINITY; }
	}
	
	
	/**
	 * Return the collision of the entity with another entity. Returns
	 * the null pointer if no collision occurs.
	 * @param other
	 * 		| A reference to the other entity.
	 * @throws NullPointerException
	 * 		   If the given argument references the null pointer (thrown by .overlaps())
	 * 		 | other == null
	 */
	public Collision getCollision(Entity other) throws NullPointerException {
		double time = getTimeToCollision(other);
		if (Double.isInfinite(time)) return null;
		else return new Collision(CollisionType.entity, time, this, other);
	}
	
	/**
	 * Return the first collision the entity will have with the boundaries of 
	 * the given world. Returns the null pointer if no collision occurs.
	 * @throws NullPointerException
	 * 		   If the given argument references the null pointer (thrown by .overlaps())
	 * 		 | world == null
	 */
	public Collision getCollision(World world) throws NullPointerException {
		// world references the null pointer
		if (world == null) throw new NullPointerException(
				"the given argument references the null pointer");

		// entity is not moving
		if (getSpeed() == 0) return null;
		
		double horizontal_time = getTimeToHorizontalCollision(world);
		double vertical_time = getTimeToVerticalCollision(world);
		
		if (horizontal_time < vertical_time) {
			return new Collision(CollisionType.verticalWall, horizontal_time, this);
		} else {
			return new Collision(CollisionType.horizontalWall, vertical_time, this);
		}
	}
	
	
	/**
	 * Return the position where the entities will collide.
	 * @param  other
	 * 		   The other entity.
	 * @return The position where the entities will collide if they collide.
	 * @throws NullPointerException
	 * 		   The given argument references a null pointer.
	 *       | other == null
	 */
	public Position getCollisionPosition(Entity other) throws  NullPointerException {
		double deltaT = getTimeToCollision(other);
		if (deltaT == Double.POSITIVE_INFINITY) return null;
		
		double[] coords = getFutureCoordinates(deltaT);
		double[] otherCoords = getFutureCoordinates(deltaT);
		
		double deltaX = otherCoords[0] - coords[0];
		double deltaY = otherCoords[1] - coords[1];
		
		double sigma = this.getRadius() + other.getRadius();
		
		double collisionX = otherCoords[0] + deltaX * (other.getRadius()/sigma);
		double collisionY = otherCoords[1] + deltaY * (other.getRadius()/sigma);
		
		return new Position(collisionX, collisionY);
	}
	
	/**
	 * Return the position where the entity will hit the world boundary.
	 * @throws NullPointerException
	 * 		   The given argument references a null pointer.
	 *       | world == null
	 */
	public Position getCollisionPosition(World world) throws  NullPointerException {
		
		Collision collision = getCollision(world);
		if (collision == null) return null;
		
		double[] futurePosition = getFutureCoordinates(collision.getTime());
		
		switch (collision.getCollisionType()) {
			case verticalWall:
				if (getXVelocity() > 0) {
					return new Position(getWorld().getWidth(), futurePosition[1]);
				} else {
					return new Position(0, futurePosition[1]);
				}
			case horizontalWall:
				if (getYVelocity() > 0) {
					return new Position(futurePosition[0], getWorld().getHeight());
				} else {
					return new Position(futurePosition[0], 0);
				}
			default:
				return null;
		}
	}
	
	
	/**
	 * Return the first collision that will occur between the prime
	 * object and one of the entities of the given ArrayList.
	 */
	public Collision getFirstCollision(ArrayList<Entity> entities) {
		Collision firstCollision = null;
		
		for (Iterator<Entity> i = entities.iterator(); i.hasNext();) {
			Entity other = i.next();
			
			if (this != other && other != null) {
				Collision currentCollision = getCollision(other);
				if (firstCollision == null 
						|| (currentCollision != null 
							&& firstCollision.getTime() > currentCollision.getTime()))
					firstCollision = currentCollision;
			}
		}
		return firstCollision;
	}
	
	/**
	 * Return the first collision the entity will experience in the given world.
	 * The collision can be with a border or any other entity in the given world.
	 */
	public Collision getFirstCollision(World world) {
		if (world == null) return null;
		else {
			Collision entityCollision = getFirstCollision(world.getEntityList());
			Collision worldCollision = getCollision(world);
			if (entityCollision.getTime() < worldCollision.getTime()) return entityCollision;
			else return worldCollision;
		}
	}
	
	
	// COLLISION RESOLVING (total)
	
	/**
	 * Collide the entity with another entity.
	 */
	public void bounce(Entity other) {
		double mass = getMass();
		double otherMass = other.getMass();
		double totalMass = mass + otherMass;
		
		double deltaX = getXCoord() - other.getXCoord();
		double deltaY = getYCoord() - other.getYCoord();
		
		double deltaVX = getXVelocity() - other.getXVelocity();
		double deltaVY = getYVelocity() - other.getYVelocity();
		
		double totalRadii = getRadius() + other.getRadius();
		
		double J = (2 * mass * otherMass
						* (deltaX * deltaVX + deltaY * deltaVY))
					/ (totalRadii * totalMass);
		double Jx = (J*deltaX)/totalRadii;
		double Jy = (J*deltaY)/totalRadii;
	
		double[] velocities1 = {getXVelocity() + Jx/mass, getYVelocity() + Jy/mass};
		double[] velocities2 = {other.getXVelocity() - Jx/otherMass, 
													other.getYVelocity() - Jy/otherMass};
		
		velocities1 = limitSpeed(velocities1[0], velocities1[1]);
		velocities2 = limitSpeed(velocities2[0], velocities2[1]);
		
		setXVelocity(velocities1[0]);
		setYVelocity(velocities1[1]);
		other.setXVelocity(velocities2[0]);
		other.setYVelocity(velocities2[1]);
	}
	
	/**
	 * Bounce the entity of a horizontal or vertical wall.
	 * @effect If the entity bounces with a vertical wall, negate the x velocity
	 * 		 | new getXVelocity() == -getXVelocity()
	 * @effect If the entity bounces with a horizontal wall, negate the y velocity
	 * 		 | new getYVelocity() == -getYVelocity()
	 * @throws IllegalArgumentException
	 * 		   If the given collisiontype is not horizontalWall or verticalWall
	 */
	public void bounce(CollisionType type) {
		if (type == CollisionType.horizontalWall)
			setYVelocity(-getYVelocity());
		else if (type == CollisionType.verticalWall)
			setXVelocity(-getXVelocity());
		else throw new IllegalArgumentException(
				"The given collisiontype must be horizontalWall or verticalWall");
	}
	
	
	// TERMINATION
	
	/** 
	 * A private variable storing whether the entity is terminated.
	 */
	private boolean isTerminated = false;
	
	/**
	 * Returns whether the entity is terminated.
	 */
	@Basic @Raw
	public boolean isTerminated() {
		return isTerminated;
	}
	
	/**
	 * Terminate the entity by removing it from its current world.
	 * @post The entity isn't located in any world
	 * 	   | getWorld() == null
	 */
	@Raw
	public void terminate() {
		System.out.println("terminate");
		if (getWorld() != null)
			getWorld().remove(this);
		isTerminated = true;
	}
}
