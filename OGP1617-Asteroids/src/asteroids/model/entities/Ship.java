package asteroids.model.entities;

import asteroids.model.environment.World;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class of ships in an unbounded twodimensional space, 
 * involving coordinates, velocities, a radius and an 
 * orientation. 
 * 
 * @invar  The orientation of each ship must be a valid orientation for a
 *         ship.
 *         | isValidOrientation(getOrientation())
 *         
 * 
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
 * A link to the GitHub code depository:
 * https://github.com/WizzardProp12/Pom
 *
 */
public class Ship extends Entity{
	
	// CONSTRUCTORS
	
	/**
	 * Initialise a new ship with given coordinates, velocities,
	 * orientation, radius and container.
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
	 * @param container
	 * 		  The container of this new ship.
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
	 * 	  | new.getWorld() == container
	 */
	public Ship(double xCoord, double yCoord, double xVelocity, double yVelocity, 
			double radius, double orientation, World world) {
		super(xCoord, yCoord, xVelocity, yVelocity, radius, world);
		setOrientation(orientation);
		setNbBullets(0);
		this.bullet = bullet;
	}
	
	/**
	 * Initialise a new ship with given coordinates, velocities,
	 * radius, orientation and no container.
	 * @see implementation...
	 */
	public Ship(double xCoord, double yCoord, double xVelocity, double yVelocity,
			double radius, double orientation) {
		this(xCoord, yCoord, xVelocity, yVelocity, radius, orientation, null);
	}
	
	/**
	 * Initialise a new ship with given coordinates, velocities,
	 * radius, default orientation and no container.
	 * @see implementation...
	 */
	public Ship(double xCoord, double yCoord, double xVelocity, double yVelocity,
			double radius) {
		this(xCoord, yCoord, xVelocity, yVelocity, radius, 0, null);
	}
	
	/**
	 * Initialise a new ship with given coordinates, velocities,
	 * the minimum radius, default orientation and no container.
	 * @see implementation...
	 */
	public Ship(double xCoord, double yCoord, double xVelocity, double yVelocity) {
		this(xCoord, yCoord, xVelocity, yVelocity, Entity.getMinRadius(), 0, null);
	}
	
	/**
	 * Initialise a new ship with given coordinates, no velocities,
	 * the minimum radius, default orientation and no container.
	 * @see implementation...
	 */
	public Ship(double xCoord, double yCoord) {
		this(xCoord, yCoord, 0, 0, Entity.getMinRadius(), 0, null);
	}
	
	/**
	 * Initialise a new ship with coordinates (0,0), no velocities,
	 * the minimum radius, default orientation and no container.
	 * @see implementation...
	 */
	public Ship() {
		this(0, 0, 0, 0, Entity.getMinRadius(), 0, null);
	}
	
	// EQUALS
	
	// TODO: Overridden function is probably unnecessary as 
	//		 the inherited super.equals() would suffice. 
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
				&& getBullet() == ((Ship) other).getBullet()
				&& getNbBullets() == ((Ship) other).getNbBullets()
				&& getThrusterState() == ((Ship) other).getThrusterState())
			return true;
		else
			return false;
	}
	
	
	// ORIENTATION (nominally)
	
	/**
	 * The orientation of the ship
	 */
	public double orientation;
	
	/**
	 * Return the orientation of this ship.
	 * @return The orientation of the ship expressed in radians.
	 * 		   This value is always be in between 0 and 2*PI.
	 * 		 | result == this.orientation
	 * @invar  The orientation is between 0 and 2*PI radians.
	 * 		 | 0 <= getOrientation() <= 2*PI
	 */
	@Basic
	public double getOrientation() {
		return this.orientation;
	}
	
	/**
	 * Set the new orientation of the ship.
	 * @param newOrientation
	 * 		  The new orientation of the ship.
	 * @pre The given orientation must be between 0 and 2*PI.
	 * 	  | 0 < newOrientation < 2*PI 
	 * @post The orientation of this ship is equal to the given 
	 * 		 orientation
	 * 	   | new.getOrientation() = orientation
	 * @invar The orientation is between 0 and 2*PI
	 * 	   | 0 <= getOrientation() <= 2*PI
	 */
	@Model
	public void setOrientation(double newOrientation) {
		this.orientation = newOrientation;
	}
	
	/**
	 * Turn the ship a given amount of radians.
	 * @param radians
	 * 		  The radians to turn.
	 * @post   The new orientation is equal to the old orientation 
	 * 		   plus the given turnorientation.
	 *		|  new.getOrientation() == getOrientation() + turnOrientation
	 */	  
	public void turn(double radians) {
		double newOrientation = getOrientation() + radians;
		setOrientation(newOrientation);
	}
	
	
	// MASS (total)
	
	/**
	 * The density of a ship
	 */
	private static final double DENSITY = 1.42*Math.pow(10, 12);
	
	/**
	 * Return the density of the ship
	 * @see implementation...
	 */
	@Basic @Raw @Override
	public double getDensity() {
		return Ship.DENSITY;
	}
	
	/**
	 * Return the total mass of the ship and all its bullets.
	 * @see implementation...
	 */
	public double getTotalMass() {
		double bulletMass = 0;
		if (getNbBullets() > 0)
			bulletMass = getBullet().getMass();
			
		double shipMass = getMass();
		double totalMass = shipMass + getNbBullets()*bulletMass;
		return totalMass;
	}
	
	
	
	// ACCELERATION (total)

	/**
	 * The state of the ships thruster.
	 * True == ON, False == OFF
	 */
	public boolean thrusterState = false;
	
	/**
	 * Return the state of the thruster.
	 * @return The state of the thruster.
	 * 		 | result == true if the thruster is on
	 * 		 | result == false if the thruster is off
	 */
	public boolean getThrusterState() {
		return thrusterState;
	}
	
	/**
	 * Set the state of the thruster
	 */
	public void setThrusterState(boolean state) {
		thrusterState = state;
	}
	
	/**
	 * The force of the thruster
	 */
	public static final double THRUSTER_FORCE = 1.1*Math.pow(10, 21);
	
	/**
	 * Turn the prime objects thruster on.
	 */
	public void thrustOn() {
		this.thrusterState = true;
	}
	
	/**
	 * Turn the prime objects thruster off.
	 */
	public void thrustOff() {
		this.thrusterState = false;
	}
	
	/**
	 * Return the acceleration of the ship. (F = m*a)
	 * @return the acceleration of the ship.
	 */
	public double getAcceleration() {
		double mass = getMass();
		double force = Ship.THRUSTER_FORCE;
		double acceleration = force/mass;
		if (acceleration < 0) {
			return 0;
		}
		return acceleration;
	}
	
	/**
	 * Accelerate the ship depending on the mass,
	 * thruster force and duration.
	 * @param time
	 * 		  How long the thruster accelerated..
	 */
	public void thrust(double time) {
		double deltaVelocity = time * getAcceleration();
		
		double xVelocity = getXVelocity() + deltaVelocity*Math.cos(getOrientation());
		double yVelocity = getYVelocity() + deltaVelocity*Math.sin(getOrientation());
		
		double[] velocities = limitSpeed(xVelocity, yVelocity);
		
		setXVelocity(velocities[0]);
		setYVelocity(velocities[1]);
	}
	
	
	
	// BULLET (defensive)

	/**
	 * The bullet type that the ship carries.
	 */
	private Bullet bullet;
	
	/**
	 * Return the bullet type of the ship.
	 * @see implementation...
	 */
	public Bullet getBullet() {
		return this.bullet;
	}
	
	// TODO: implement canHaveAsBullet()
	/**
	 * Return true if the ship can have the bullet as ammunition.
	 * @param bullet
	 * @return
	 */
	public boolean canHaveAsBullet(Bullet bullet) {
		if (bullet == null)
			return true;
		else
			return bullet.getRadius() > 0.1*getRadius();
	}
	
	/**
	 * Set the bullet type of the ship.
	 * @see implementation...
	 */
	public void setBullet(Bullet bullet) throws IllegalArgumentException {
		if (! canHaveAsBullet(bullet))
				throw new IllegalArgumentException(
						"prime object cannot have the given argument as bullet");
		else
			this.bullet = bullet;
	}
	
	
	/**
	 * The amount of bullets the ship carries.
	 */
	private int nbBullets;
	
	/**
	 * Returns the amount of bullets the ship carries.
	 * @see implementation...
	 */
	public int getNbBullets() {
		return this.nbBullets;
	}
	
	/**
	 * Set the amount of bullets the ship carries.
	 * @invar The amount of bullets is never negative.
	 * 		| getNbBulets() >= 0
	 * @throws The amount of bullets cannot be negative.
	 * 		| if (nb < 0)
	 * 		|	then throw IllegalArgumentException
	 */
	private void setNbBullets(int nb) throws IllegalArgumentException {
		if (nb < 0) {
			throw new IllegalArgumentException("nbBullets cannot be negative.");
		}
		this.nbBullets = nb;
	}
	
	/**
	 * Add 1 bullet
	 * @see implementation...
	 */
	public void addBullet() {
		setNbBullets(getNbBullets()+1);
	}
	
	/**
	 * Remove 1 bullet.
	 * @see implementation
	 */
	public void removeBullet() {
		setNbBullets(getNbBullets()-1);
	}
	
	// SHOOTING (total)
	
	/**
	 * Fires a bullet from the ship.
	 */
	public void fireBullet() {
		
		if (getWorld() == null || getNbBullets() <= 0)
			return;
		
		double bulletRadius;
		if (getBullet() == null)
			bulletRadius = Bullet.getMinRadius();
		else
			bulletRadius = getBullet().getRadius();
		
		double xCoord = getXCoord() + (getRadius()+bulletRadius) * Math.cos(getOrientation());
		double yCoord = getYCoord() + (getRadius()+bulletRadius) * Math.sin(getOrientation());
		
		double xVelocity = getXVelocity() + Bullet.FIRE_SPEED * Math.cos(getOrientation());
		double yVelocity = getYVelocity() + Bullet.FIRE_SPEED * Math.sin(getOrientation());
		
		double[] velocities = limitSpeed(xVelocity, yVelocity);
		
		Bullet bullet = new Bullet(xCoord, yCoord, velocities[0], velocities[1],
									bulletRadius, this);
		
		if (getWorld().isValidEntity(bullet))
			getWorld().add(bullet);

		removeBullet();
	}
		
	// COLLISIONS
	
	/**
	
	
	/**
	 * Collide the ship with another. The ships bounce of
	 * eachother and the new velocities are calculated
	 * based on the mass and velocities of the ships.
	 */
	public void collide(Ship other) {
		double deltaX = getXCoord() - other.getXCoord();
		double deltaY = getYCoord() - other.getYCoord();
		
		double deltaVX = getXVelocity() - other.getXVelocity();
		double deltaVY = getYVelocity() - other.getYVelocity();
		
		double totalRadii = getRadius() + other.getRadius();
		
		double J = (2 * getTotalMass() * other.getTotalMass() *
									Math.sqrt(deltaX * deltaVX + deltaY * deltaVY))
					/ (totalRadii * (getTotalMass() + other.getTotalMass()));
		double Jx = J*deltaX/totalRadii;
		double Jy = J*deltaY/totalRadii;
		
		double[] velocities1 = limitSpeed(getXVelocity() + Jx/getTotalMass(), 
											getYVelocity() + Jy/getTotalMass());
		double[] velocities2 = limitSpeed(other.getXVelocity() - Jx/other.getTotalMass(),
											other.getYVelocity() - Jy/other.getTotalMass());
		
		setXVelocity(velocities1[0]);
		setYVelocity(velocities1[1]);
		other.setXVelocity(velocities2[0]);
		other.setYVelocity(velocities2[1]);
	}
	
	/**
	 * Collide the ship with a bullet.
	 */
	public void collide(Bullet bullet) {
		if (bullet.getShip() == this) {
			addBullet();
		} else {
			terminate();
		}
		bullet.terminate();
	}
	
	
	
}