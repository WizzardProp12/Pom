package asteroids.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
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
 * @version  0.2
 * 
 * A link to the GitHub code repository:
 * https://github.com/WizzardProp12/Pom
 * 
 * A class of ships in an unbounded two dimensional space, 
 * involving coordinates, velocities, a radius and an 
 * orientation. 
 * 
 * @invar  The orientation of each ship must be a valid.
 *         | isValidOrientation(getOrientation())
 */
public class Ship extends Entity{
	
	// CONSTRUCTORS
	
	/**
	 * Initialise a new ship with given coordinates, velocities,
	 * radius, orientation, mass and world.
	 * @param xCoord
	 * 		  The x-coordinate of this new ship.
	 * @param yCoord
	 * 	      The x-coordinate of this new ship.
	 * @param xVelocity
	 *        The velocity along the x-axis of this new ship.
	 * @param yVelocity
	 * 	      The velocity along the x-axis of this new ship.
	 * @param radius
	 * 		  The radius of this new ship.
	 * @param orientation
	 * 		  The orientation of this new ship.
	 * @param world
	 * 		  The container of this new ship.
	 * @param mass
	 * 		  The mass of this new ship.
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
	 * 	  |  new.getRadius() == MIN_RADIUS
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
	 * 		   If the entity cannot be assigned to its position.
	 * 		 | ! isValidPosition(position)
	 * @throws IllegalArgumentException
	 * 		   If the entity cannot be added to the world.
	 * 		 | ! canBePlacedIn(world)
	 */
	public Ship(double xCoord, double yCoord, double xVelocity, double yVelocity, 
			double radius, double orientation, double mass, World world) {
		super(xCoord, yCoord, xVelocity, yVelocity, radius, world);
		setOrientation(orientation);
		if (getMinimumMass() < mass)
			setMass(mass);
		else
			setMass(getMinimumMass());
	}
	
	/**
	 * Initialise a new ship with given coordinates, velocities,
	 * radius, orientation, mass and no world.
	 */
	public Ship(double xCoord, double yCoord, double xVelocity, double yVelocity, 
			double radius, double orientation, double mass) {
		this(xCoord, yCoord, xVelocity, yVelocity, radius, orientation, mass, null);
	}
	
	/**
	 * Initialise a new ship with given coordinates, velocities,
	 * radius, orientation, default mass and no world.
	 */
	public Ship(double xCoord, double yCoord, double xVelocity, double yVelocity,
			double radius, double orientation) {
		this(xCoord, yCoord, xVelocity, yVelocity, radius, orientation, 0);
	}
	
	/**
	 * Initialise a new ship with given coordinates, velocities,
	 * radius, default orientation, default mass and no world.
	 */
	public Ship(double xCoord, double yCoord, double xVelocity, double yVelocity,
			double radius) {
		this(xCoord, yCoord, xVelocity, yVelocity, radius, 0);
	}
	
	/**
	 * Initialise a new ship with given coordinates, velocities,
	 * minimum radius, default orientation, default mass and no world.
	 */
	public Ship(double xCoord, double yCoord, double xVelocity, double yVelocity) {
		this(xCoord, yCoord, xVelocity, yVelocity, MIN_RADIUS);
	}
	
	/**
	 * Initialise a new ship with given coordinates, no velocities,
	 * minimum radius, default orientation, default mass and no world.
	 */
	public Ship(double xCoord, double yCoord) {
		this(xCoord, yCoord, 0, 0);
	}
	
	/**
	 * Initialise a new ship with coordinates (0,0), no velocities,
	 * minimum radius, default orientation, default mass and no world.
	 */
	public Ship() {
		this(0, 0);
	}
	
	
	// EQUALS
	
	/**
	 * Return whether the prime object equals the argument.
	 * @param other
	 * 		  The other ship.
	 * @return see implementation...
	 */
	@Override
	public boolean equals(Object other){
		if (! (other instanceof Ship))
			return false;
		else if (super.equals(other)
				&& getOrientation() == ((Ship) other).getOrientation()
				&& getBullets() == ((Ship) other).getBullets()
				&& getNbBullets() == ((Ship) other).getNbBullets()
				&& getThrusterState() == ((Ship) other).getThrusterState())
			return true;
		else
			return false;
	}
	
	
	// RADIUS (defensive)
	
	/**
	 * The minimum radius of a bullet.
	 */
	public static final double MIN_RADIUS = 10;

	/**
	 * Return the minimum radius of a bullet.
	 */
	@Basic @Raw @Immutable
	public static double getMinRadius() {
		return MIN_RADIUS;
	}
	
	/**
	 * The default radius for a Ship when the radius is
	 * not specified in the constructor.
	 */
	@Basic @Raw @Immutable
	public double getDefaultRadius() {
		return getMinRadius();
	}
	
	/**
	 * Returns whether the given radius suits the prime object.
	 * @return ...
	 * 		 | getMinRadius() <= radius
	 */
	@Raw
	public boolean canHaveAsRadius(double radius) {
		return (getMinRadius() <= radius);
	}
	
	
	// ORIENTATION (nominally)
	
	/**
	 * The minimum orientation of the ship.
	 */
	private final double minOrientation = 0;

	/**
	 * The maximum orientation of the ship.
	 */
	private final double maxOrientation = 2*Math.PI;
	
	/**
	 * Return the minimum orientation of the ship.
	 */
	@Basic @Immutable @Raw
	public double getMinOrientation() {
		return minOrientation;
	}
	
	/**
	 * Return the maximum orientation of the ship.
	 */
	@Basic @Immutable @Raw
	public double getMaxOrientation() {
		return maxOrientation;
	}
	
	/**
	 * The orientation of the ship
	 */
	public double orientation;
	
	/**
	 * Return the orientation of this ship.
	 * @return The orientation of the ship expressed in radians.
	 * 		 | result == this.orientation
	 * @invar  The orientation is valid.
	 * 		 | canHaveAsOrientation(getOrientation())
	 */
	@Basic @Raw
	public double getOrientation() {
		return this.orientation;
	}
	
	/**
	 * Returns whether the given orientation suits the prime object.
	 * @return Whether the given orientation is between the 
	 * 		   minimum and maximum orientation.
	 * 		 | getMinOrientation() <= getOrientation() <= getMaxOrientation()
	 */
	@Raw
	public boolean canHaveAsOrientation(double orientation) {
		return (getMinOrientation() <= orientation && orientation <= getMaxOrientation());
	}
	
	/**
	 * Set the new orientation of the ship.
	 * @param newOrientation
	 * 		  The new orientation of the ship.
	 * @pre The given orientation must be valid
	 * 	  | canHaveAsOrientation(orientation)
	 * @post The new orientation of this ship is equal to the given 
	 * 		 orientation.
	 * 	   | new.getOrientation() = orientation
	 */
	public void setOrientation(double orientation) {
		assert canHaveAsOrientation(orientation);
		this.orientation = orientation;
	}
	
	/**
	 * Turn the ship a given amount of radians.
	 * @param radians
	 * 		  The radians to turn.
	 * @post  The new orientation is equal to the old orientation 
	 * 		  plus the given turnorientation.
	 *		| new.getOrientation() == getOrientation() + turnOrientation
	 * @invar The orientation is valid.
	 * 	    | canHaveAsOrientation(getOrientation())
	 */	  
	public void turn(double radians) {
		double newOrientation = getOrientation() + radians;
		assert canHaveAsOrientation(newOrientation);
		setOrientation(newOrientation);
	}
	
	
	// MASS (total)
	
	/**
	 * The density of the ship.
	 */
	private final double density = 1.42*Math.pow(10, 12);
	
	/**
	 * Return the density of the ship.
	 */
	@Basic @Raw @Immutable
	public double getDensity() {
		return density;
	}
	
	
	/**
	 * The mass of the ship.
	 */
	private double mass = getMinimumMass();
	
	/**
	 * Return the mass of the ship.
	 * @invar The mass is bigger than or equal to the minimum mass.
	 * 		| getMinimumMass() <= getMass()
	 */
	@Basic @Raw @Immutable
	public double getMass() {
		return mass;
	}
	
	/**
	 * Set the mass of the ship.
	 * @pre The given mass must be bigger than or equal to the minimum mass.
	 *    | getMinimumMass() <= mass
	 * @post The new mass is bigger than or equal to the minimum mass.
	 * 		| getMinimumMass() <= new getMass()
	 */
	public void setMass(double mass) {
		if (getMinimumMass() < mass) this.mass = mass;
	}
	
	/**
	 * Return the total mass of the ship and all its bullets.
	 * @see implementation...
	 */
	@Basic @Raw
	public double getTotalMass() {
		double shipMass = getMass();
		double bulletMass = 0;
		for(Bullet bullet : getBulletSet())
			bulletMass += bullet.getMass();
		return shipMass + bulletMass;
	}
	
	
	// ACCELERATION (total)

	/**
	 * The state of the ships thruster: true (on) or false (off).
	 */
	public boolean thrusterState = false;
	
	/**
	 * Return the state of the thruster.
	 * @return The state of the thruster.
	 * 		   The state of the thruster is true (on) or false (off).
	 */
	@Basic @Raw
	public boolean getThrusterState() {
		return thrusterState;
	}
	
	/**
	 * Set the state of the thruster.
	 * @param state
	 * 		  The state of the thruster is true (on) or false (off).
	 * @post  The given state is the new thrusterState
	 * 		| new getThrusterState == state
	 */
	public void setThrusterState(boolean state) {
		thrusterState = state;
	}
	
	/**
	 * Turn the prime objects thruster on.
	 * @post  The new thrusterState is true.
	 * 		| new getThrusterState == true
	 */
	public void thrustOn() {
		this.thrusterState = true;
	}
	
	/**
	 * Turn the prime objects thruster off.
	 * @post  The new thrusterState is true.
	 * 		| new getThrusterState == false
	 */
	public void thrustOff() {
		this.thrusterState = false;
	}
	
	
	/**
	 * The force of the thruster.
	 */
	public static final double THRUSTER_FORCE = 1.1*Math.pow(10, 21);
	
	/**
	 * Return the force of the thruster.
	 */
	public static double getThrusterForce() {
		return Ship.THRUSTER_FORCE;
	}
	
	/**
	 * Return the acceleration of the ship. (F = m*a)
	 * @invar The acceleration of the ship is not negative.
	 * 		| getAcceleration() >= 0
	 */
	@Basic
	public double getAcceleration() {
		if (getThrusterState() != true) return 0;
		double mass = getMass();
		double force = Ship.getThrusterForce();
		double acceleration = force/mass;
		if (acceleration < 0) {
			return 0;
		}
		return acceleration;
	}
	
	/**
	 * Accelerate the ship depending on
	 * the duration and the acceleration.
	 * @param time
	 * 		  How long to accelerate for.
	 * @param acceleration
	 * 		  The acceleration of the ship.
	 * @post  The absolute velocity of the ship will stay below the limit.
	 * 	    | new getSpeed() <= getSpeedLimit()
	 */
	public void thrust(double time, double acceleration) {
		if (acceleration <= 0) return;
		if (! isValidTime(time)) return;
		
		double deltaVelocity = time * acceleration;
		
		double xVelocity = getXVelocity() + deltaVelocity*Math.cos(getOrientation());
		double yVelocity = getYVelocity() + deltaVelocity*Math.sin(getOrientation());
		
		double[] velocities = limitSpeed(xVelocity, yVelocity);
		
		setXVelocity(velocities[0]);
		setYVelocity(velocities[1]);
	}
	
	/**
	 * Accelerate the ship depending on the mass of the ship,
	 * the duration and the force of the thruster.
	 * @param time
	 * 		  How long the thruster accelerated..
	 * @post The absolute velocity of the ship will stay below the limit.
	 * 	   | new getSpeed() <= getSpeedLimit()
	 */
	public void thrust(double time) {
		thrust(time, getAcceleration());
	}
	
	
	// BULLET (defensive)
	
	/**
	 * The listArray of bullets that are contained by the ship.
	 */
	private ArrayList<Bullet> bulletList = new ArrayList<Bullet>();
	
	/**
	 * Return a list of all the bullets contained by the ship.
	 */
	@Basic
	public ArrayList<Bullet> getBullets() {
		return new ArrayList<Bullet>(bulletList);
	}
	
	/**
	 * Return the list of all the bullets as a HashSet.
	 */
	public HashSet<Bullet> getBulletSet() {
		return new HashSet<Bullet>(getBullets());
	}
	
	/**
	 * Return the bullet stored at the given index
	 */
	@Basic
	public Bullet getBulletAtIndex(int index) {
		return getBullets().get(index);
	}
	
	/**
	 * Return the amount of bullets that are loaded on the ship
	 * @invar The amount of bullets is not negative.
	 *      | 0 <= getNbBullets()
	 */
	public int getNbBullets() {
		return getBullets().size();
	}
	
	/**
	 * Return true if the ship can use the bullet as ammunition.
	 * @param bullet
	 * @return Whether the ship can use the bullet as ammunition.
	 * 		 | bullet != null && bullet.getRadius() < getRadius()
	 */
	public boolean canHaveAsBullet(Bullet bullet) {
		return (bullet != null && bullet.getRadius() < getRadius());
	}
	
	/**
	 * Load the given bullet on the ship if it fits the ship.
	 * A bullet can be loaded multiple times on a ship
	 * @param bullet
	 * 	      The bullet to be loaded on the ship.
	 * @throws IllegalArgumentException
	 * 		   If the given bullet is already loaded on the ship
	 * 		 | bullet.getShip() == this
	 * @throws IllegalArgumentException
	 * 		   If the given bullet can't be loaded on the ship
	 * 		 | ! canHaveAsBullet(bullet)
	 */
	public void load(Bullet bullet) throws IllegalArgumentException {
		if (canHaveAsBullet(bullet) && bullet.canHaveAsShip(this)) {
			if (bullet.getShip() == this) throw new IllegalArgumentException(
											"the bullet is already loaded on this ship");
			else if (bullet.getShip() != null) throw new IllegalArgumentException(
											"the bullet is already loaded on another ship");
			bulletList.add(bullet);
			bullet.setShip(this);
		} else
			throw new IllegalArgumentException(
					"the given bullet can't be loaded on the ship");
	}
	
	
	/**
	 * Load the given bullets on the ship
	 * @throws IllegalArgumentException
	 * 		   If the given bullet can't be loaded on the ship
	 * 		 | !canHaveAsBullet(bullet)
	 */
	public void load(Collection<Bullet> bullets) {
		for (Bullet bullet : bullets)
			load(bullet);
	}
	
	/**
	 * Remove the given bullet from the ship.
	 * @pre    The given bullet must be on the ship.
	 * 		 | bullet.getShip() == this
	 * @post   The given bullet is no longer on the ship
	 *       | bullet.getShip() != this
	 * @throws IllegalArgumentException
	 * 		   If the given bullet is not loaded on the ship.
	 */
	public void removeBullet(Bullet bullet) throws IllegalArgumentException {
		if (bullet.getShip() != this)
			throw new IllegalArgumentException(
					"the bullet given bullet is not loaded on the ship");
		else
			bulletList.remove(bullet);
	}
	
	/**
	 * Remove the bullet at the given index.
	 * @param  index
	 * 		   The index of the to be removed bullet
	 * @pre    The given index must be in range of the bullet list.
	 * 		 | (index >= 0 || index < getBullets().size())
	 * @effect Removes the bullet at the given index from the ship
	 * 		 | bulletList.remove(getBulletAtIndex(index))
	 * @throws IndexOutOfBoundsException 
	 * 		   If the index out is of range
	 * 		 | (index < 0 || index >= getBullets().size())
	 */
	public void removeBulletAt(int index) throws IndexOutOfBoundsException {
		bulletList.remove(index);
	}
	
	// FIRING (total)
	
	/**
	 * Fires a bullet from the ship.
	 * The first bullet from the bulletList is fired.
	 * @post The amount of bullets is decremented by one.
	 * 	   | new getNbBullets() = old getNbBullets() - 1
	 */
	public void fireBullet() {
		// does nothing if the ship has no bullets
		if (getNbBullets() <= 0)
			return;
		
		// get and remove the bullet from the bullet list.
		Bullet bullet = getBulletAtIndex(0);
		removeBulletAt(0);
		bullet.setSourceShip(this);
		
		// if the ship isn't in a world, the bullet is destroyed
		if (getWorld() == null) {
			bullet.terminate();
			return;
		}
		
		// get the position offset of the fired bullet
		double xOffset = 1.5 * (getRadius()+bullet.getRadius())
							 * Math.cos(getOrientation());
		double yOffset = 1.5 * (getRadius()+bullet.getRadius())
							 * Math.sin(getOrientation());
		
		// get the position of the fired bullet
		double xCoord = getXCoord() + xOffset;
		double yCoord = getYCoord() + yOffset;
		
		// set the new position of the bullet
		bullet.setPosition(new Position(xCoord, yCoord));
		
		// check if the bullet overlaps with any entity in the world
		for(Entity other : getWorld().getEntityList())
			if (bullet.overlaps(other) && this != other) {
				bullet.collide(other);
				return;
			}
		
		// if the bullet would be placed (partially) outside the ships world, it's destroyed
		if (! bullet.isWithinBoundariesOf(getWorld(), new Position(xCoord, yCoord))) {
			bullet.terminate();
			return;
		}
		
		// add the bullet to the world
		getWorld().add(bullet);
		
		// get and set the velocity of the fired bullet
		double xVelocity = Bullet.getFireSpeed() * Math.cos(getOrientation());
		double yVelocity = Bullet.getFireSpeed() * Math.sin(getOrientation());
		bullet.setXVelocity(xVelocity);
		bullet.setYVelocity(yVelocity);
	}
		
	// COLLISIONS RESOLVING (total)
	
	/**
	 * Collide the ship with the given entity.
	 */
	@Override
	public void collide(Entity other) {
		if (other instanceof Ship) collide((Ship) other);
		if (other instanceof Bullet) collide((Bullet) other);
	}
	
	/**
	 * Collide the ship with another. The ships bounce of
	 * eachother and the new velocities are calculated
	 * based on the mass and velocities of the ships.
	 */
	public void collide(Ship other) {
		
		double totalMass = getTotalMass() + other.getTotalMass();
		double mass = getTotalMass();
		double otherMass = other.getTotalMass();
		
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
	 * Collide the ship with a bullet.
	 * @post If the bullet was fired by the ship
 	 *			then it is loaded back on the ship
 	 *		 else
 	 *   	 	then the ship and bullet are terminated
	 */
	public void collide(Bullet bullet) {
		if (bullet.getSourceShip() == this) {
			bullet.getWorld().remove(bullet);
			load(bullet);
		} else {
			terminate();
			bullet.terminate();
		}
	}
	
	
	// TERMINATION
	
	/**
	 * Terminate the ship and all its bullets.
	 */
	@Override @Raw
	public void terminate() {
		for(Bullet bullet : getBullets())
			bullet.terminate();
		super.terminate();
	}
}