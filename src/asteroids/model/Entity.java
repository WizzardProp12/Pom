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
	 * 		   If the entity cannot be added to the world.
	 * 		 | ! canBePlacedIn(world)
	 */
	public Entity(double xCoord, double yCoord, double xVelocity, double yVelocity, 
			double radius, World world) throws IllegalArgumentException {
		
		// velocities (total)
		double[] velocities = limitSpeed(xVelocity, yVelocity);
		setXVelocity(velocities[0]);
		setYVelocity(velocities[1]);
		
		// position (defensive)
		setXCoord(xCoord);
		setYCoord(yCoord);
		
		// radius (defensive)
		if (canHaveAsRadius(radius)) this.radius = radius;
		else throw new IllegalArgumentException("invalid radius");
		
		// world
		if (world == null) this.setWorld(world);
		else world.add(this);
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
	public Position getPosition() { return position; }
	
	/**
	 * Returns the x-coordinate of the prime entity.
	 * @invar The returned xCoord is always valid.
	 * 		| isValidPosition(getXCoord(), validYCoord)
	 * @return The x-coordinate of the prime entity.
	 * 		|  result == getPosition().getXCoord()
	 */
	@Basic @Raw
	public double getXCoord() {	return getPosition().getXCoord(); }
	
	/**
	 * Returns the y-coordinate of the prime entity.
	 * @invar The returned yCoord is always valid.
	 * 		| isValidPosition(validXCoord, getYCoord())
	 * @return The y-coordinate of the prime entity.
	 * 		|  result == getPosition().getYCoord();
	 */
	@Basic @Raw
	public double getYCoord() { return getPosition().getYCoord(); }
	
	/**
	 * Return whether the given xCoord is valid.
	 * @param  xCoord
	 * 		   The x-coordinate to check.
	 * @return see implementation...
	 */
	@Basic @Raw @Model
	public boolean isValidXCoord(double xCoord) { return ! Double.isNaN(xCoord); }
	
	/**
	 * Return whether the given yCoord is valid.
	 * @param  yCoord
	 * 		   The y-coordinate to check.
	 * @return see implementation...
	 */
	@Basic @Raw @Model
	public boolean isValidYCoord(double yCoord) { return ! Double.isNaN(yCoord); }
	
	/**
	 * Return whether the given position would be valid for the prime object.
	 * @param  position
	 * 		   The position to check.
	 * @return see implementation...
	 */
	@Raw
	public boolean isValidPosition(Position position) {
		if (getWorld() == null) return true;
		else return isValidXCoord(position.getXCoord())
				&& isValidYCoord(position.getYCoord())
				&& isWithinBoundariesOf(getWorld(), position);
	}
	
	/**
	 * Return whether the prime object can take the given position.
	 * This method is like isValidPosition() but also takes in account
	 * other entities.
	 * @param  position
	 * 		   The position to check.
	 * @return see implementation...
	 */
	@Raw
	public boolean canTakePosition(Position position) {
		if (! isValidPosition(position)) return false;
		else return getWorld() == null 
					|| ! wouldOverLapAt(position, getWorld().getEntityList());
	}
	
	/**
	 * Set the position of the entity.
	 * @param  position
	 * 		   The new position
	 * @post   The new position is valid.
	 * 	     | isValidPosition(position)
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
	 * @param  newXCoord
	 * 		   The new x-coordinate of the entity.
	 * @post   The x-coordinate of this entity is equal 
	 * 		   to the given x-coordinate.
	 * 	     | new.getXCoord() = newXCoord
	 * @throws IllegalArgumentException
	 * 		   If the given x-coordinate is not valid.
	 * 	     | ! isValidXCoord(xCoord)
	 * @throws IllegalArgumentException
	 * 		   If the given x-coordinate would result in a position the entity cannot take
	 * 		 | ! canTakePosition(new Position(xCoord, getYCoord())
	 */
	@Raw
	protected void setXCoord(double xCoord) throws IllegalArgumentException {
		if (! isValidXCoord(xCoord)) throw new IllegalArgumentException(
				"given xCoord is invalid, see isValidXCoord()");
		if (! canTakePosition(new Position(xCoord, getYCoord()))) throw new 
		IllegalArgumentException("given xCoord would result in an illegal position, "
								+ "see canTakePosition()");
		getPosition().setXCoord(xCoord);
	}
	
	/**
	 * Set the y-coordinate of the entity.
	 * @param  newYCoord
	 * 		   The new y-coordinate of the entity.
	 * @post   The y-coordinate of this entity is equal 
	 * 		   to the given y-coordinate.
	 * 	     | new.getYCoord() = newYCoord
	 * @throws IllegalArgumentException
	 * 		   If the given y-coordinate is not valid.
	 * 	     | ! isValidYCoord(yCoord)
	 * @throws IllegalArgumentException
	 * 		   If the given y-coordinate would result in a position the entity cannot take
	 * 		 | ! canTakePosition(new Position(getXCoord(), yCoord)
	 */
	@Raw
	protected void setYCoord(double yCoord) throws IllegalArgumentException {
		if (! isValidYCoord(yCoord)) throw new IllegalArgumentException(
				"given yCoord is invalid, see isValidYCoord()");
		if (! canTakePosition(new Position(getXCoord(), yCoord))) throw new 
		IllegalArgumentException("given yCoord would result in an illegal position, "
								+ "see canTakePosition()");
		getPosition().setYCoord(yCoord);
	}
	
	
	/**
	 * Return whether the prime object would be within the given worlds 
	 * boundaries if it would be at the given position in that world.
	 * @param  world
	 * 		   The world for which to check the given position.
	 * @param  position
	 * 		   The position to check.
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
	 * @param  world
	 * 		   The world for which to check the entities position.
	 * @return see implementation...
	 */
	public boolean isWithinBoundariesOf(World world) {
		return isWithinBoundariesOf(world, new Position(getXCoord(), getYCoord()));
	}
	
	/**
	 * Return with which border the entity is currently overlapping
	 * @param  world
	 * 		   The world which borders have to be checked.
	 * @return 0 (if none)
	 * 		   1 (if left)
	 * 		   2 (if top)
	 * 		   3 (if right)
	 * 		   4 (if bottom)
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
	 * @param  time
	 * 		   The time to be checked for validity.
	 * @return Whether the given time is valid
	 * 		 | see implementation...
	 */
	@Basic @Raw
	public static boolean isValidTime(double time) {
		return (0 <= time && !Double.isNaN(time) && !Double.isInfinite(time));
	}
	
	/**
	 * Return the coordinates the entity would be at after moving forward for
	 * the given amount of seconds.
	 * @param  time
	 * 		   For how much seconds in the future the coordinates have to be calculated.
	 * @throws IllegalArgumentException
	 * 		   The given duration time is invalid.
	 * 		 | ! isValidTime(time)
	 */
	@Raw
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
	 * @param  time
	 * 		   For how much seconds in the future the position has to be calculated.
	 * @throws IllegalArgumentException
	 * 		   The given duration time is invalid.
	 * 		 | ! isValidTime(time)
	 */
	@Raw
	public Position getFuturePosition(double time) throws IllegalArgumentException {
		double[] newCoords = getFutureCoordinates(time);
		return new Position(newCoords[0], newCoords[1]);
	}
	
	/**
	 * Change the position of the entity based on the current position, 
	 * velocity and a given time duration.
	 * @param  time
	 * 		   For how much seconds the entity has to be moved.
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
	 * @post   The x velocity of the prime object is under the speed limit.
	 * 	     | getAbsVelocity(getXVelocity(), 0) <= SPEED_LIMIT
	 * @return The velocity of the prime object along the x-axis.
	 * 		 | result == this.xVelocity
	 */
	@Basic @Raw
	public double getXVelocity() {
		return this.xVelocity;
	}
	
	/**
	 * Return the velocity of the prime object along the y-axis.
	 * @post   The y velocity of the prime object is under the speed limit.
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
	 * @post  The absolute speed of the prime object will be kept under
	 * 		  the speed limit.
	 * 	    | getAbsVelocity(new getXVelocity(), new getYVelocity()) <= SPEED_LIMIT
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
	 * @post  The absolute speed of the prime object will be kept under
	 * 		  the speed limit.
	 * 	    | getAbsVelocity(new getXVelocity(), new getYVelocity()) <= SPEED_LIMIT
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
	 * @invar  The absolute speed is kept under the speed limit.
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
	 * @param  xVelocity
	 * 		   The x velocity
	 * @param  yVelocity
	 * 		   The y velocity
	 * @return The absolute velocity
	 * 		 | result == Math.sqrt(Math.pow(xVelocity, 2) + Math.pow(xVelocity, 2)
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
	 * @param  xVelocity
	 * 		   The x-velocity to limit.
	 * @param  yVelocity
	 * 		   The y-velocity to limit.
	 * @post   The absolute speed of the result does not exceed the speed limit
	 * 		 | getAbsVelocity(result[0], result[1]) <= getSpeedLimit()
	 * @return An array of the scaled down velocities.
	 * 		 | see implementation...
	 */
	public double[] limitSpeed(double xVelocity, double yVelocity) {
		if (Double.isNaN(xVelocity)) return limitSpeed(0,yVelocity);
		if (Double.isNaN(yVelocity)) return limitSpeed(xVelocity,0);
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
	 * @param  speedLimit
	 * 		   The speed limit to check for validity.
	 * @return true if the speed limit is smaller than the speed of light.
	 * 		 | 0 < speedLimit || speedLimit <= Entity.getLightSpeed()
	 */
	@Raw
	public boolean canHaveAsSpeedLimit(double speedLimit) {
		return 0 < speedLimit || speedLimit <= Entity.getLightSpeed();
	}
	
	/**
	 * Set the speed limit of the prime object if the given speed limit is valid.
	 * @param speedLimit
	 * 		  The speed limit to be set.
	 * @post  The speed limit is valid
	 * 	    | canHaveAsSpeedLimit(speedLimit)
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
	 * @invar  The returned radius is valid.
	 * 		 | canHaveAsRadius(getRadius()) == true
	 * @return The radius of the entity
	 */
	@Basic @Immutable @Raw
	public double getRadius() {
		return this.radius;
	}
	
	/**
	 * Check if the given radius suits the prime object.
	 * @param  radius
	 * 		   The radius to check.
	 * @return see implementation in subclasses...
	 */
	@Raw
	public abstract boolean canHaveAsRadius(double radius);
	
	/**
	 * Set the given radius.
	 * @param  radius
	 * 		   The radius to be set
	 * @post   The given radius is the new radius of the entity.
	 * 		 | new getRadius() == radius
	 * @throws IllegalArgumentException
	 * 		   If the given radius is not valid for the entity.
	 * 		 | ! canHaveAsRadius(radius)
	 */
	@Raw
	public void setRadius(double radius) throws IllegalArgumentException {
		if (! canHaveAsRadius(radius)) throw new IllegalArgumentException(
				"The given radius is not valid, see canHaveAsRadius()");
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
	 * @invar  The world contains the entity or is null.
	 * 		 | getWorld() == null|| getWorld().contains()
	 * @invar  The entity is located within the world boundaries.
	 * 		 | isWithinBoundaries(getWorld())
	 * @return The world in which the entity is located.
	 */
	@Basic
	public World getWorld() {
		return world;
	}
	
	/**
	 * Returns whether the entity can change world.
	 * @return Whether the entity can change world.
	 * 		 | getWorld() == null || ! getWorld().contains(this)
	 */
	@Basic
	public boolean canChangeWorld() {
		return getWorld() == null || ! getWorld().contains(this);
	}
	
	/**
	 * Returns whether the entity can be placed in the given world.
	 * @param  world
	 * 		   The world to check.
	 * @return Whether the entity can be placed in the given world.
	 * 		 | isWithinBoundariesOf(world) && ! overlaps(world.getEntitySet())
	 */
	public boolean canBePlacedIn(World world) {
		if (world == null) return true;
		return (isWithinBoundariesOf(world) && !overlaps(world.getEntityList()));
	}
	
	/**
	 * Set the container of the entity.
	 * @param  world
	 * 		   The world to set as container for the entity.
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
	public void setWorld(World world) throws IllegalArgumentException {
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
	 * @param  other
	 * 		   The entity to check for equality.
	 * @return Whether the given object is equal to the prime object.
	 * 		 | see implementation...
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
	 * @return The minimum mass of the entity.
	 * 		 | (4/3.) * Math.PI * Math.pow(getRadius(), 3) * getDensity()
	 */
	@Basic @Immutable @Raw
	public double getMinimumMass() {
		return (4/3.) * Math.PI * Math.pow(getRadius(), 3) * getDensity();
	}
	
	/**
	 * Return the mass of the entity.
	 */
	@Basic @Raw @Immutable
	abstract double getMass();
	
	
	// COLLISION PREDICTION (total)
	
	/**
	 * Return the distance between the centres of two entities.
	 * @param  other
	 * 		   The entity for which to check the distance.
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
	 * @param  other
	 * 		   The entity for which to check the distance.
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
	 * Returns whether this entity fully overlaps with the other entity
	 * @param  other
	 * 		   The entity for which to check whether it completely overlaps.
	 * @return True if the entities completely overlap.
	 * 		 | getDistanceBetweenCentres(other) <= Math.abs(getRadius() - other.getRadius())
	 */
	public boolean completelyOverlaps(Entity other) {
		if (other == null) return false;
		return getDistanceBetweenCentres(other) 
				<= Math.abs(getRadius() - other.getRadius());
	}
	
	/**
	 * Return whether this entity significantly overlaps with the other entity.
	 * Important: this function ignores the entities worlds.
	 * @param other
	 * 		   The entity for which to check whether it overlaps.
	 * @return True if the entities overlap
	 * 		 | getDistanceBetweenCentres(other) <= 0.99*(getRadius() + other.getRadius())
	 */
	public boolean overlaps(Entity other) {
		if (other == null) return false;
		double totalRadii = getRadius() + other.getRadius();
		return getDistanceBetweenCentres(other) <= 0.99*totalRadii;
	}
	
	/**
	 * Return whether the entity overlaps with one or more of the
	 * entities in the given array.
	 * @param otherEntities
	 * 		  An ArrayList of entities.
	 */
	public boolean overlaps(ArrayList<Entity> otherEntities) {
		for (Entity other : otherEntities){
			if (this != other && overlaps(other))
				return true;
		}
		return false;
	}
	
	/**
	 * Return whether this entity, if placed at the given position, 
	 * would overlap the given entity.
	 * @param position
	 * 		  The position at which the prime entity would be placed.
	 * @param other
	 * 		  The other entity.
	 */
	public boolean wouldOverLapAt(Position position, Entity other) {
		if (position == null || other == null) return false;
		double deltaX = position.getXCoord() - other.getXCoord();
		double deltaY = position.getYCoord() - other.getYCoord();
		double distBetweenCenters = Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
		return distBetweenCenters < 0.99 * (getRadius() + other.getRadius());
	}
	
	/**
	 * Return whether this entity, if placed at the given position,
	 * would overlap any of the entities of the given ArrayList.
	 * @param position
	 * 		  The position at which the prime entity would be placed.
	 * @param otherEntities
	 * 		  The other entities.
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
	 * @param  other
	 * 		   The other entity.
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
	 * one of the given world boundaries.
	 * @param world
	 * 		  The world which borders are checked.
	 */
	public double getTimeToBorderCollision(World world) {
		if (world == null || getSpeed() == 0) return Double.POSITIVE_INFINITY;
		
		return Math.min(getTimeToHorizontalCollision(world), 
						getTimeToVerticalCollision(world));
	}
	
	/**
	 * Return how long it will take before the entity collides with
	 * one of its world boundaries.
	 * @return ...
	 * 		 | getTimeToBorderCollision(getWorld())
	 */
	public double getTimeToBorderCollision() {
		return getTimeToBorderCollision(getWorld());
	}
	
	/**
	 * Return how long it will take before the entity collides with a
	 * horizontal border.
	 * @param  world
	 * 		   The world which horizontal boundaries are checked.
	 * @return The time it will take to collide with a horizontal border.
	 * @throws IllegalArgumentException
	 * 		   If the entity is not within the given worlds boundaries
	 * 		 | ! isWithinBoundariesOf(world)
	 */
	public double getTimeToHorizontalCollision(World world) 
			throws IllegalArgumentException {
		if (! isWithinBoundariesOf(world)) throw new IllegalArgumentException(
				"the entity is not within the world boundaries");
		
		if (getXVelocity() > 0) {
			return (world.getWidth() - getPosition().getXCoord() - getRadius())
						/ getXVelocity();
		} else if (getXVelocity() < 0) {
			return (getPosition().getXCoord() - getRadius())
						/ -getXVelocity();
		} else { return Double.POSITIVE_INFINITY; }
	}
	
	/**
	 * Return how long it will take before the entity collides with a
	 * horizontal border.
	 * @param  world
	 * 		   The world which vertical boundaries are checked.
	 * @return The time it will take to collide with a vertical border.
	 * @throws IllegalArgumentException
	 * 		   If the entity is not within the given worlds boundaries
	 * 		 | ! isWithinBoundariesOf(world)
	 */
	public double getTimeToVerticalCollision(World world) 
			throws IllegalArgumentException {
		if (! isWithinBoundariesOf(world)) throw new IllegalArgumentException(
				"the entity is not within the world boundaries");
		if (getYVelocity() > 0) {
			return (world.getHeight() - getPosition().getYCoord() - getRadius())
						/ getYVelocity();
		} else if (getYVelocity() < 0) {
			return (getPosition().getYCoord() - getRadius())
						/ -getYVelocity();
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
	 * @param  world
	 * 		   The world where to border collision has to be calculated.
	 * @throws NullPointerException
	 * 		   If the given argument references the null pointer (thrown by .overlaps())
	 * 		 | world == null
	 */
	public Collision getBorderCollision(World world) {
		if (world == null || getSpeed() == 0) return null;
		
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
	public Position getCollisionPosition(Entity other) throws NullPointerException {
		double deltaT = getTimeToCollision(other);
		if (deltaT == Double.POSITIVE_INFINITY) return null;
		
		double[] coords = getFutureCoordinates(deltaT);
		double[] otherCoords = other.getFutureCoordinates(deltaT);
		
		double deltaX = otherCoords[0] - coords[0];
		double deltaY = otherCoords[1] - coords[1];
		
		double sigma = this.getRadius() + other.getRadius();
		
		double collisionX = coords[0] + deltaX * (getRadius()/sigma);
		double collisionY = coords[1] + deltaY * (getRadius()/sigma);
		
		return new Position(collisionX, collisionY);
	}
	
	/**
	 * Return the position where the entities will collide as an array.
	 * @param  other
	 * 		   The other entity.
	 * @return The coordinates where the entities will collide if they collide.
	 * @throws NullPointerException
	 * 		   The given argument references a null pointer.
	 *       | other == null
	 */
	public double[] getCollisionPositionArray(Entity other) 
								throws NullPointerException {
		Position pos = getCollisionPosition(other);
		if (pos == null) return null;
		else return pos.toArray();
	}
	
	/**
	 * Return the position where the entity will hit the world boundary.
	 * @param  world
	 * 		   The world where to border collision position has to be calculated.
	 * @throws NullPointerException
	 * 		   The given argument references a null pointer.
	 *       | world == null
	 */
	public Position getBorderCollisionPosition(World world) 
			throws NullPointerException {
		
		Collision collision = getBorderCollision(world);
		
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
	 * Return the position where the entity will hit the world boundary
	 * as an array
	 * @param  world
	 * 		   The world where to border collision position array has to be calculated.
	 * @throws NullPointerException
	 * 		   The given argument references a null pointer.
	 *       | world == null
	 */
	public double[] getBorderCollisionPositionArray(World world) 
			throws NullPointerException {
		Position position = getBorderCollisionPosition(world);
		if (position == null) return null;
		else return position.toArray();
	}
	
	
	/**
	 * Return the first collision that will occur between the prime
	 * object and one of the entities of the given ArrayList.
	 * @param entities
	 * 		  A list of other entities
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
	 * @param  world
	 * 		   The world where the first collision (border or entity) has to be calculated.
	 */
	public Collision getFirstCollision(World world) {
		if (world == null) return null;
		else {
			Collision entityCollision = getFirstCollision(world.getEntityList());
			Collision worldCollision = getBorderCollision(world);
			if (entityCollision.getTime() < worldCollision.getTime()) return entityCollision;
			else return worldCollision;
		}
	}
	
	
	// COLLISION RESOLVING (total)
	
	/**
	 * Collide the entity with another entity.
	 * @param other
	 * 		  The other entity.
	 */
	public void bounce(Entity other) {
		double mass = getMass();
		double otherMass = other.getMass();
		double totalMass = mass + otherMass;
		
		double deltaX = other.getXCoord() - getXCoord();
		double deltaY = other.getYCoord() - getYCoord();
		
		double deltaVX = other.getXVelocity() - getXVelocity();
		double deltaVY = other.getYVelocity() - getYVelocity();
		
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
	 * @param  type
	 * 		   The type of border collision.
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
	 * When an entity is killed it dies
	 * @post The entity is terminated.
	 * 	   | new isTerminated()
	 */
	@Raw
	public void die() {
		this.terminate();
	}
	
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
	 * 	   | new getWorld() == null
	 * @post The entity is terminated.
	 * 	   | new isTerminated()
	 */
	@Raw
	public void terminate() {
		if (getWorld() != null)
			getWorld().remove(this);
		isTerminated = true;
	}
}
