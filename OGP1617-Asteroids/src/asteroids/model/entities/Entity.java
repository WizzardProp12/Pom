package asteroids.model.entities;

import java.util.HashSet;
import java.util.Iterator;

import asteroids.model.environment.*;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class of entities in an unbounded two dimensional space, 
 * involving coordinates, velocities, a radius and an 
 * orientation. 
 * 
 * Superclass of Ship.java and Bullet.java
 */

public abstract class Entity {
	
	public boolean test() {
		return false;
	}
	
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
	 * 		| canHaveAsXCoordinate() && canHaveAsYCoordinate()
	 * @pre   The given radius must be valid.
	 * 		| canHaveAsRadius()
	 * @post ...
	 * 		| new.getPosition() == [xCoord, yCoord]
	 * @post ...
	 * 		| if getAbsSpeed(xVelocity, yVelocity) <= Entity.SPEED_LIMIT
	 * 		| 	then new getXVelocity == xVelocity and new getYVelocity == yVelocity
	 * @post ...
	 * 		| getSpeed() <= Entity.SPEED_LIMIT
	 * @post ...
	 * 		| new getRadius() == radius
	 * @post ...
	 * 		| new getWorld() == world
	 * 
	 */
	public Entity(double xCoord, double yCoord, double xVelocity, double yVelocity, 
			double radius, World world) {
		
		name = getName();
		
		// defensive
		if (canHaveAsRadius(radius))
			setRadius(radius);
		
		// total
		double[] velocities = limitSpeed(xVelocity, yVelocity);
		setXVelocity(velocities[0]);
		setYVelocity(velocities[1]);
		
		// defensive
		if (isValidXCoord(xCoord))
			setXCoord(xCoord);
		if (isValidYCoord(yCoord))
			setYCoord(yCoord);
		
		getPosition().setEntity(this);
		
		if (world == null)
			setWorld(world);
		else
			world.add(this);
	}
	
	/**
	 * Initialise a new entity with given coordinates, velocity, radius and no world.
	 * @see implementation...
	 */
	public Entity(double xCoord, double yCoord, double xVelocity, double yVelocity, 
			double radius) {
		this(xCoord, yCoord, xVelocity, yVelocity, radius, null);
	}
	
	/**
	 * Initialise a new entity with given coordinates, 
	 * velocity, the minimal radius and no world.
	 * @see implementation...
	 */
	public Entity(double xCoord, double yCoord, double xVelocity, double yVelocity) {
		this(xCoord, yCoord, xVelocity, yVelocity, MIN_RADIUS, null);
	}
	
	/**
	 * Initialise a new entity with given coordinates, 
	 * no velocity, the minimal radius and no world.
	 * @see implementation...
	 */
	public Entity(double xCoord, double yCoord) {
		this(xCoord, yCoord, 0, 0, MIN_RADIUS, null);
	}
	
	/**
	 * Initialise a new entity with coordinates (0,0), 
	 * no velocity, the minimal radius and no world.
	 * @see implementation...
	 */
	public Entity() {
		this(0, 0, 0, 0, MIN_RADIUS, null);
	}
	
	
	// POSITION (defensive)
	
	/**
	 * The position of the Entity.
	 */
	private Position position = new Position();
	
	
	/**
	 * Returns the x-coordinate of the prime entity.
	 * @return The x-coordinate of the prime entity.
	 * 		|  result == this.xCoord
	 */
	@Basic @Raw
	public double getXCoord() {
		return getPosition().getXCoord();
	}
	
	/**
	 * Return whether the given x-coordinate is valid in the given world.
	 * @param  xCoord
	 * 		   The x-coordinate to be checked.
	 * @param  world
	 * 		   The world for which the coordinate has to be valid.
	 * @return true if and only if the x-coordinate is valid.
	 * @throws IllegalArgumentException
	 * 		   If the xCoord argument is NaN.
	 */
	public boolean isValidXCoord(double xCoord, World world)  throws IllegalArgumentException{
		if (Double.isNaN(xCoord))
			throw new IllegalArgumentException("xCoord parameter is NaN.");
		if (world == null)
			return (0 <= xCoord);
		else
			return world.isValidXCoord(xCoord);
	}
	
	/**
	 * Return whether the given x-coordinate is valid in the world of the prime object.
	 * @param  xCoord
	 * 		   The x-coordinate to be checked.
	 * @return true if and only if the x-coordinate is valid.
	 * @throws IllegalArgumentException
	 * 		   If the xCoord argument is NaN.
	 */
	public boolean isValidXCoord(double xCoord)  throws IllegalArgumentException{
		return isValidXCoord(xCoord, getWorld());
	}
	
	/**
	 * Set the x-coordinate of the entity.
	 * @param newXCoord
	 * 		  The new x-coordinate of the entity.
	 * @post  The x-coordinate of this entity is equal 
	 * 		  to the given x-coordinate.
	 * 	   |  new.getXCoord() = newXCoord
	 * @throws IllegalArgumentException
	 * 		   see isValidXCoord()
	 */
	@Model @Raw
	public void setXCoord(double newXCoord) throws IllegalArgumentException{
		if (isValidXCoord(newXCoord))
			position.setXCoord(newXCoord);
		else
			throw new IllegalArgumentException("parameter newXCoord is invalid.");
	}
	
	
	/**
	 * Returns the y-coordinate of the prime entity.
	 * @return The y-coordinate of the prime entity.
	 */
	@Basic @Raw
	public double getYCoord() {
		return getPosition().getYCoord();
	}

	/**
	 * Return whether the given y-coordinate is valid in the given world.
	 * @param  yCoord
	 * 		   The y-coordinate to be checked.
	 * @param  world
	 * 		   The world for which the coordinate has to be valid.
	 * @return true if and only if the y-coordinate is valid.
	 * @throws IllegalArgumentException
	 * 		   If the yCoord argument is NaN.
	 */
	public boolean isValidYCoord(double yCoord, World world) throws IllegalArgumentException{
		if (Double.isNaN(yCoord))
			throw new IllegalArgumentException("yCoord parameter is NaN.");
		if (world == null)
			return (0 <= yCoord);
		else
			return world.isValidYCoord(yCoord);
	}
	
	/**
	 * Return whether the given y-coordinate is valid in the world of the prime object.
	 * @param  yCoord
	 * 		   The y-coordinate to be checked.
	 * @return true if and only if the y-coordinate is valid.
	 * @throws IllegalArgumentException
	 * 		   If the yCoord argument is NaN.
	 */
	public boolean isValidYCoord(double yCoord) throws IllegalArgumentException{
		return isValidYCoord(yCoord, getWorld());
	}
	
	/**
	 * Set the y-coordinate of the entity.
	 * @param newYCoord
	 * 		  The new y-coordinate of the entity.
	 * @post  The y-coordinate of this entity is equal 
	 * 		  to the given y-coordinate.
	 * 	   |  new.getYCoord() = newYCoord
	 * @throws IllegalArgumentException
	 * 		   The given y-coordinate is NaN.
	 * 	   |   newYCoord == Double.NaN
	 * @throws IllegalArgumentException
	 * 		   The given y-coordinate is out of the world boundaries.
	 * 	   |   ! (0 <= newYCoord <= getWorld().getHeight())
	 */
	@Model @Raw
	public void setYCoord(double newYCoord) throws IllegalArgumentException{
		if (isValidYCoord(newYCoord))
			position.setYCoord(newYCoord);
		else
			throw new IllegalArgumentException("parameter newYCoord is invalid.");
	}
	
	
	/**
	 * Return the Position object of the prime object
	 * @return
	 */
	public Position getPosition() {
		return position;
	}
	
	/**
	 * Return the x and y positions of the prime entity.
	 * @return An array of the x-coordinate and the y-coordinate of the entity
	 * 		 | result == [xCoord,yCoord]
	 */
	public double[] getPositionArray() {
		return getPosition().toArray();
	}
	
	/**
	 * Return whether the prime object is within the boundaries of the given world.
	 * @return true if the entity is located within the boundaries
	 * 		   of the world, or if the given argument is the null
	 * 		   pointer reference.
	 */
	public boolean withinBoundariesOf(World world) throws NullPointerException {
		if (world == null)
			throw new NullPointerException("the given argument references the null pointer.");

		double x = getXCoord();
		double y = getYCoord();
		double r = getRadius();
		double w = world.getWidth();
		double h = world.getHeight();

		// left, right, top, bottom
		if (x > 0.99*r && w - x > 0.99*r && h - y > 0.99*r && y > 0.99*r)
			return true;
		return false;
	}
	

	/**
	 * Change the position of the entity based on the current position, 
	 * velocity and a given time duration named time.
	 * @param  time
	 * 		   The time for which the entity must be moved.
	 * @effect The coordinate of the entity along the x-axis is set to 
	 * 		   the old x-coordinate plus the given time multiplied by 
	 * 		   the velocity of the entity along the x-axis. 
	 * 	     | setXcoord(getXcoord() + time*getxVelocity())
	 * @effect The coordinate of the entity along the y-axis
	 * 		   is set to the old y-coordinate plus the given
	 * 		   time multiplied by the velocity of the entity 
	 * 		   along the y-axis.  		
	 * 		 | setYcoord(getYcoord() + time*getyVelocity())
	 * @throws IllegalArgumentException
	 * 		   The given duration time is less than zero.
	 * 		 | ! isValidTime(time)
	 */
	public void move(double time) throws IllegalArgumentException {
		if (!isValidTime(time))
			throw new IllegalArgumentException("invalid argument (must be positive).");
		double newxCoord = getXCoord() + time*getXVelocity();
		double newyCoord = getYCoord() + time*getYVelocity();
		setXCoord(newxCoord);
		setYCoord(newyCoord);
	}
	
	// VELOCITIES (total)
	
	/**
	 * A static final variable that represents the speed limit.
	 */
	public static final double SPEED_LIMIT = 300000;
	
	
	/**
	 * The velocity of the entity along the x-axis.
	 */
	private double xVelocity = 0;
	
	/**
	 * Return the velocity of the prime object along the x-axis.
	 * @return The velocity of the prime object along the x-axis.
	 * 		|  result == this.xVelocity
	 */
	@Basic @Raw
	public double getXVelocity() {
		return this.xVelocity;
	}
	
	/**
	 * Set the x velocity, limiting the speed if it would exceed the speed limit.
	 * @param xVelocity
	 * 		  The xVelocity to be set.
	 * @post The absolute speed of the prime object will be kept under
	 * 		 the speed limit.
	 * 	     | getAbsVelocity(new xVelocity, new yVelocity) <= SPEED_LIMIT
	 */
	public void setXVelocity(double xVelocity) {
		yVelocity = getYVelocity();
		double[] newVelocities = Entity.limitSpeed(xVelocity, yVelocity);
		this.xVelocity = newVelocities[0];
		this.yVelocity = newVelocities[1];
	}
	
	
	/**
	 * The velocity of the entity along the y-axis.
	 */
	private double yVelocity = 0;
	
	/**
	 * Return the velocity of the prime object along the y-axis.
	 * @return The velocity of the prime object along the y-axis.
	 * 		 | result == this.yVelocity
	 */
	@Basic @Raw
	public double getYVelocity() {
		return this.yVelocity;
	}
	
	/**
	 * Set the y velocity, limiting the speed if it would exceed the speed limit.
	 * @param yVelocity
	 * 		  The yVelocity to be set.
	 * @post The absolute speed of the prime object will be kept under
	 * 		 the speed limit.
	 * 	     | getAbsVelocity(new xVelocity, new yVelocity) <= SPEED_LIMIT
	 */
	public void setYVelocity(double yVelocity) {
		xVelocity = getXVelocity();
		double[] newVelocities = Entity.limitSpeed(xVelocity, yVelocity);
		this.xVelocity = newVelocities[0];
		this.yVelocity = newVelocities[1];
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
	public static double getAbsVelocity(double xVelocity, double yVelocity) {
		return Math.sqrt(Math.pow(xVelocity, 2) + Math.pow(yVelocity, 2));
	}
	
	/**
	 * Return the speed of the prime object.
	 * @return The absolute value of the x and y velocity.
	 * 		|  result == 
	 * 		|	if (getAbsVelocity(xVelocity, yVelocity) < speedLimit)
	 * 		|	  return getAbsVelocity(xVelocity, yVelocity)
	 * 		|	else
	 * 		|	  return speedLimit
	 * @invar The speed is lower than the speed limit.
	 * 		| getSpeed() <= entity.SPEED_LIMIT
	 */
	public double getSpeed() {
		double absSpeed = getAbsVelocity(getXVelocity(), getYVelocity());
		if (absSpeed > SPEED_LIMIT)
			return SPEED_LIMIT;
		return absSpeed;
	}
	
	/**
	 * Return the x and y velocities that respect the speed limit but
	 * also maintain the direction of the entity.
	 * @param xVelocity
	 * 		  The x velocity of the entity.
	 * @param yVelocity
	 * 		  The y velocity of the entity.
	 * @return An array of the scaled down velocities.
	 * @post new getAbsVelocity() <= SPEED_LIMIT
	 */
	public static double[] limitSpeed(double xVelocity, double yVelocity) {
		double newVelocities[] = new double[2];
		double absVelocity = getAbsVelocity(xVelocity, yVelocity);
		if (absVelocity > SPEED_LIMIT) {
			newVelocities[0] = xVelocity*(absVelocity/SPEED_LIMIT);
			newVelocities[1] = yVelocity*(absVelocity/SPEED_LIMIT);
			return newVelocities;
		}
		newVelocities[0] = xVelocity;
		newVelocities[1] = yVelocity;
		return newVelocities;
	}
	
	
	// RADIUS (defensive)
	
	/**
	 * The minimum radius of the entity.
	 */
	public static final double MIN_RADIUS = 10;
	
	
	/**
	 * The radius of the entity.
	 */
	private double radius;
	
	/**
	 * Return the radius of the prime object.
	 * @return The radius of the prime object.
	 * @invar The radius has a minimum value.
	 * 		| getRadius() >= MIN_RADIUS
	 */
	public double getRadius() {
		return this.radius;
	}
	
	/**
	 * Check if the given radius satisfies the requirements.
	 * @see implementation...
	 */
	@Basic
	public boolean canHaveAsRadius(double radius) {
		return (radius >= MIN_RADIUS); 
	}
	
	/**
	 * Sets the radius of the entity.
	 * @param radius
	 * @throws IllegalArgumentException
	 * 		   The given radius is too small.
	 * 		 | radius < MIN_RADIUS
	 */
	@Basic @Raw
	protected void setRadius(double radius) throws IllegalArgumentException {
		if (! canHaveAsRadius(radius)) {
			throw new IllegalArgumentException("Radius is too small.");
		}
		this.radius = radius;
	}
	
	
	// WORLD - ADDING AND REMOVING (defensively)

	/**
	 * The variable referencing the container of the entity.
	 */
	private World world;
	
	/**
	 * Return the container in which the entity is located.
	 * @see implementation...
	 */
	public World getWorld() {
		return this.world;
	}
	
	/**
	 * Set the container of the entity.
	 * @throws IllegalArgumentException
	 * 		   If the prime object is not present in the given world.
	 * 		 | ! world.contains(this)
	 */
	public void setWorld(World newWorld) throws IllegalArgumentException {
		// check if the entity references a current world
		if (getWorld() == null) {
			this.world = newWorld;
			getPosition().setWorld(newWorld);
			return;
		}
		
		// check if new world contains the prime object
		if (! (newWorld == null || newWorld.contains(this)))
			throw new IllegalArgumentException("given argument must contain the prime object");
		
		// check if the current world no longer contains the prime object
		if (getWorld() != null && getWorld().contains(this)) {
			throw new IllegalArgumentException("prime object is already in a world");
		}
		
		this.world = newWorld;
		getPosition().setWorld(newWorld);
	}
	
	// WORLD - SEARCHING (total)
	
	/**
	 * Returns the hashCode of the entity. 
	 * The hashCode is based on the coordinates of the 
	 * entity and 32 numbers long.
	 * @return An int between 0 and 
	 */
	@Override
    public int hashCode() {
		Position position = getPosition();
        return position.hashCode();
    }
	
	/**
	 * Return whether the prime object equals the argument.
	 * @param other
	 * 		  The other entity.
	 * @return see implementation...
	 */
	public boolean equals(Object other){
		if (! (other instanceof Entity))
			return false;
		else if (getXCoord() == ((Entity) other).getXCoord() 
				&& getYCoord() == ((Entity) other).getYCoord()
				&& getXVelocity() == ((Entity) other).getXVelocity() 
				&& getYVelocity() == ((Entity) other).getYVelocity()
				&& getRadius() == ((Entity) other).getRadius()
				&& getWorld() == ((Entity) other).getWorld())
			return true;
		else
			return false;
	}
	
	/**
	 * Return a string representing the Entity.
	 * @Return ...
	 * 		 | result == name + hashCode
	 */
	public String toString() {
		String hashCode = String.valueOf(hashCode());
		return name + " (hc:" + hashCode + ")";
	}
	
	// NAME
	
	/**
	 * The name of the Entity.
	 */
	public String name;
	
	/**
	 * The list of name prefixes.
	 */
	private String[] prefixes = {"zwarte", "witte", "blauwe", "gele", 
	                                 "groene", "rode", "oranje", "paarse"};
	
	/**
	 * The list of name suffixes.
	 */
	private String[] suffixes = {"tijger", "haai", "gekko", "kikker", 
	                             "zebra", "kolibri", "gorilla", "sprinkhaan"};
	
	/**
	 * Returns a generated name.
	 */
	private String getName() {
		int x = (int)(Math.random() * prefixes.length-1);
		int y = (int)(Math.random() * suffixes.length-1);
		return prefixes[x] + " " + suffixes[y];
	}
	
	// MASS (total)
	
	/**
	 * The density of the entity, the default value is
	 * overwritten in the subclasses.
	 */
	private final double DENSITY = 0;
	
	/**
	 * Return the density of the entity
	 * @see implementation...
	 */
	public double getDensity() {
		return this.DENSITY;
	}
	
	
	/**
	 * Return the mass of the entity.
	 * @return (4/3)*PI*(radius^3)*DENSITY
	 */
	public double getMass() {
		double mass = (4/3)*Math.PI*Math.pow(getRadius(), 3)*getDensity();
		return mass;
	}
	
	
	// COLLISIONS (defensive)
	
	/**
	 * Check whether the given time is valid.
	 * @see implementation...
	 */
	@Basic @Raw
	public static boolean isValidTime(double time) {
		return time >= 0;
	}
	
	// distance and overlap
	
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
	 * Return whether this entity significantly overlaps with the other entity
	 * @param other
	 * 		  The other entity.
	 * @return see implementation...
	 * @throws NullPointerException
	 * 		   The given argument references a null pointer.
	 *         | other == null
	 */
	public boolean overlaps(Entity other) throws NullPointerException {
		if (other == null)
			throw new NullPointerException("argument references null pointer.");
		double totalRadii = getRadius() - other.getRadius();
		return getDistanceBetweenCentres(other) <= 0.99*totalRadii;
	}
	
	// wallcollisions (total)

	/**
	 * Return the first collision of the entity with a wall
	 * that will occur.
	 * @return the type of wall collision. (String)
	 * 		   "horizontal", "vertical" or "none"
	 */
	public Collision getWallCollision() {
		if (getWorld() == null || (getXVelocity() == 0 && getYVelocity() == 0))
			return null;
		
		CollisionType type;
		double time;
		
		double timeToRightCollision = (getXVelocity() > 0) 
									? (getWorld().getWidth() - getXCoord() - getRadius()) / getXVelocity()
									: Double.POSITIVE_INFINITY;
		time = timeToRightCollision;
		type = CollisionType.rightWall;
		
		double timeToLeftCollision = (getXVelocity() < 0)
									? (getXCoord() - getRadius()) / -getXVelocity()
									: Double.POSITIVE_INFINITY;
		if (time > timeToLeftCollision) {
			time = timeToLeftCollision;
			type = CollisionType.leftWall;
		}
		double timeToTopCollision = (getYVelocity() > 0)
									? (getWorld().getHeight() - getYCoord() - getRadius()) / getYVelocity()
									: Double.POSITIVE_INFINITY;
		if (time > timeToTopCollision) {
			time = timeToTopCollision;
			type = CollisionType.topWall;
		}
		double timeToBottomCollision = (getYVelocity() < 0)
									? (getYCoord() - getRadius()) / -getYVelocity()
									: Double.POSITIVE_INFINITY;
		if (time > timeToBottomCollision) {
			time = timeToBottomCollision;
			type = CollisionType.bottomWall;
		}
		
		return new Collision(type, time, this);
	}
	
	/**
	 * Return the duration before the prime object will collide with a world border.
	 * @throws NullPointerException
	 * 		   If the prime object has no reference to a world.
	 */
	public double getTimeToWallCollision() {
		Collision collision = getWallCollision();
		if (collision == null)
			return Double.POSITIVE_INFINITY;
		else
			return collision.getTime();
	}

	/**
	 * Bounce the entity of a horizontal or vertical wall.
	 * @effect If the entity bounces with a vertical wall, negate the x velocity
	 * 		 | new getXVelocity() == -getXVelocity()
	 * @effect If the entity bounces with a horizontal wall, negate the y velocity
	 * 		 | new getYVelocity() == -getYVelocity()
	 */
	public void wallBounce(CollisionType type) {
		if (type == CollisionType.bottomWall || type == CollisionType.topWall)
			setYVelocity(-getYVelocity());
		else if (type == CollisionType.leftWall || type == CollisionType.rightWall)
			setXVelocity(-getXVelocity());
	}
	
	// entitycollisions (total)
	
	/**
	 * Return the collision of the entity with another entity
	 * @param other
	 * 		| A reference to the other entity.
	 */
	public Collision getEntityCollision(Entity other) {
		double time = getTimeToEntityCollision(other);
		return new Collision(CollisionType.entity, time, this, other);
	}
	
	/**
	 * Return the time it will take for this entity to collide with the other entity.
	 * @param other
	 * 		  The other entity.
	 * @return The time it will take before the entities collide,
	 * 		   if and only if the entities collide.
	 * 		   Else, infinity is returned.
	 * @throws IllegalStateException
	 * 		   This entity already overlaps with the other entity.
	 * 		 | overlap(other) == True
	 * @throws NullPointerException
	 * 		   The given argument references a null pointer.
	 *         | other == null
	 */
	public double getTimeToEntityCollision(Entity other) 
			throws IllegalStateException, NullPointerException {
		if (this.overlaps(other) == true)
			throw new IllegalStateException("The entities overlap.");
		if (other == null)
			throw new NullPointerException("argument references null pointer.");
		
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
	 * Return the position where the entities will collide.
	 * @param other
	 * 		 The other entity.
	 * @return The position where the entities will collide,
	 * 		   if and only if the time to collision is less than infinity.
	 * 		   Else, null is returned.
	 * 	    |  If (this.getTimeToCollision(other) == Double.POSITIVE_INFINITY)
	 * 		|	 result == null
	 * 		|  else
	 * 		|     result == [collisionX,collisionY] where
	 * 		|
	 * 		|     collisionX = other.getXCoord() + this.getTimeToCollision(other)
	 * 		|	  other.getXvelocity() + (other.getXCoord() - this.getXCoord())
	 * 		|	  * other.getRadius() / (other.getRadius() + this.getRadius())
	 * 		|
	 * 		|     collisionY = other.getYCoord() + this.getTimeToCollision(other)
	 * 		|	  other.getYvelocity() + (other.getYCoord() - this.getYCoord())
	 * 		|     * other.getRadius() / (other.getRadius() + this.getRadius())
	 * @throws IllegalStateException
	 * 		   This entity overlaps with the other entity.
	 * 		|  overlap(other) 
	 * @throws NullPointerException
	 * 		   The given argument references a null pointer.
	 *         | other == null
	 */
	public double[] getEntityCollisionPosition(Entity other) 
			throws IllegalStateException, NullPointerException {
		if (this.overlaps(other) == true)
			throw new IllegalStateException("The entities overlap.");
		if (other == null)
			throw new NullPointerException("argument references null pointer.");
		
		double deltaT = getTimeToEntityCollision(other);
		
		if (deltaT == Double.POSITIVE_INFINITY)
			return null;
		
		double thisxCoord = getXCoord() + deltaT*getXVelocity();
		double thisyCoord = getYCoord() + deltaT*getYVelocity();
		
		double otherxCoord = other.getXCoord() + deltaT*other.getXVelocity();
		double otheryCoord = other.getYCoord() + deltaT*other.getYVelocity();
		
		double deltaX = otherxCoord - thisxCoord;
		double deltaY = otheryCoord - thisyCoord;
		
		double sigma = other.getRadius() + this.getRadius();
		
		double collisionX = otherxCoord + deltaX * (other.getRadius()/sigma);
		double collisionY = otheryCoord + deltaY * (other.getRadius()/sigma);
		
		double collisionPosition[] = new double[2];
		collisionPosition[0] = collisionX;
		collisionPosition[1] = collisionY;
		return collisionPosition;
	}
	
	// both
	
	/**
	 * Return the first collision that will occur for the prime entity
	 * for the given entities and the walls of the world.
	 * @param entities
	 * 		| The entities to check.
	 * @return The first collision that will occur (with a wall or another entity).
	 */
	public Collision getFirstCollision(HashSet<Entity> entities) {
		// wall
		Collision collision = getWallCollision();
		
		// entities
		for (Iterator<Entity> i = entities.iterator(); i.hasNext();) {
			Entity other = i.next();
			if (other != null && getWorld() == other.getWorld()) {
				Collision currentCollision = getEntityCollision(other);
				if (currentCollision == null || collision.getTime() > currentCollision.getTime())
					collision = currentCollision;
			}
		}
		return collision;
	}
	
	/**
	 * Return the first collision that will occur for the prime entity
	 * in that entities world.
	 * @return The first collision that will occur (with a wall or another entity).
	 */
	public Collision getFirstCollision() {
		if (getWorld() == null)
			return null;
		else {
			HashSet<Entity> entities = getWorld().getEntities();
			return getFirstCollision(entities);
		}
	}
	
	
	
	
	/**
	 * Destroy the entity
	 */
	public void destroy() {
		if (getWorld() != null) {
			getWorld().remove(this);
		}
	}
}
