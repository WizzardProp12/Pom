package asteroids.model.entities;

import be.kuleuven.cs.som.annotate.*;

public class Bullet extends Entity{
	
	// CONSTRUCTOR
	
	public Bullet(double xCoord, double yCoord, double xVelocity, double yVelocity, 
			double radius, Ship ship) {
		super(xCoord, yCoord, xVelocity, yVelocity, radius);
		if (canHaveAsShip(ship))
			setShip(ship);
	}
	
	public Bullet(double xCoord, double yCoord, double xVelocity, double yVelocity, 
			double radius) {
		this(xCoord, yCoord, xVelocity, yVelocity, radius, null);
	}
	
	public Bullet(double xCoord, double yCoord, double xVelocity, double yVelocity) {
		this(xCoord, yCoord, xVelocity, yVelocity, Entity.getMinRadius(), null);
	}
	
	public Bullet(double xCoord, double yCoord) {
		this(xCoord, yCoord, 0, 0, Entity.getMinRadius(), null);
	}
	
	public Bullet() {
		this(0, 0, 0, 0, Entity.getMinRadius(), null);
	}
	
	
	static public final double FIRE_SPEED = 250;
	
	// EQUALS
	
	/**
	 * Return whether the prime object equals the argument.
	 * @param other
	 * 		  The other bullet.
	 * @return see implementation...
	 */
	@Override
	public boolean equals(Object other){
		if (! (other instanceof Bullet))
			return false;
		else if (super.equals(other)
				&& getShip() == ((Bullet) other).getShip()
				&& getNbBounces() == ((Bullet) other).getNbBounces())
			return true;
		else
			return false;
	}
	
		
	// RADIUS (defensive)
	
	/**
	 * Check if the given radius satisfies the requirements.
	 * @see implementation...
	 */
	@Basic @Override
	public boolean canHaveAsRadius(double radius) {
		if (hasShip()) {
			double shipRadius = getShip().getRadius();
			return (radius >= getMinRadius() && radius >= 0.1*shipRadius);
		} else
			return radius >= getMinRadius();
	}
	
	// MASS (total)
	
	/**
	 * The density of a bullet.
	 */
	private static final double DENSITY = 7.8*Math.pow(10, 12);
	
	/**
	 * Return the density of the bullet
	 * @see implementation...
	 */
	@Basic @Raw @Override
	public double getDensity() {
		return Bullet.DENSITY;
	}
	
	// SHIP () nominal?
	
	/**
	 * The ship that holds or fired the bullet.
	 */
	private Ship ship;
	
	/**
	 * Return the ship that holds or fired the bullet.
	 * @see implementation...
	 */
	public Ship getShip() {
		return this.ship; 
	}
	
	/**
	 * Return whether the bullet belongs to a ship
	 * @see implementation...
	 */
	public boolean hasShip() {
		return getShip() != null;
	}
	
	/**
	 * Return whether the bullet can belong to the ship
	 * @see implementation...
	 */
	public boolean canHaveAsShip(Ship ship) {
		return (getShip() == null || getRadius() >= 0.1*ship.getRadius());
	}
	
	/**
	 * Set the ship of the bullet.
	 * @see implementation...
	 */
	private void setShip(Ship ship) {
		this.ship = ship;
	}
	
	
	// COLLISIONS
	
	/**
	 * Collide the bullet with a ship.
	 */
	public void collide (Ship ship) {
		ship.collide(this);
	}
	
	/**
	 * Collide the bullet with another bullet.
	 */
	public void collide(Bullet other) {
		terminate();
		other.terminate();
	}
	
	
	// BOUNCES ()
	
	/**
	 * The amount of times a bullet can bounce of a wall.
	 */
	private final int MAX_BOUNCES = 2;
	
	/**
	 * Return the maximum amount of bounces a bullet can make.
	 */
	public int getMaxNbBounces() {
		return MAX_BOUNCES;
	}
	
	/**
	 * The amount of times the bullet has bounced of a wall.
	 */
	private int nbBounces = 0;
	
	/**
	 * Get how much times the bullet has bounced of a wall.
	 * @see implementation...
	 */
	public int getNbBounces() {
		return this.nbBounces;
	}
	
	/**
	 * Count a bounce of the bullet.
	 */
	public void countBounce() {
		nbBounces++;
	}
	
	/**
	 * Bounce the bullet of a wall.
	 */
	public void wallBounce(Collision collision) {
		if (getNbBounces() == getMaxNbBounces()) {
			terminate();
		} else {
			super.wallBounce(collision.getCollisionType());
			countBounce();
		}
	}
}
