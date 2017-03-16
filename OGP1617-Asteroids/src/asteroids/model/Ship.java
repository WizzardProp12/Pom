package asteroids.model;

import asteroids.util.ModelException;
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
public class Ship {
	
	/**
	 * Initialize this new ship with default values for the coordinates, 
	 * velocities, orientation and radius.
	 * 
	 * @post The x-coordinate of this new ship is equal to 0.
	 * 	  |  new.getXCoord() == 0
	 * @post The y-coordinate of this new ship is equal to 0.
	 * 	  |  new.getYCoord() == 0
	 * @post The velocity along the x-axis of this new ship is equal to 0.
	 * 	  |  new.getXVelocity() == 0
	 * @post The velocity along the y-axis of this new ship is equal to 0.
	 * 	  |  new.getYVelocity() == 0
	 * @post The orientation of this new ship is equal to 0.
	 * 	  |  new.getOrientation() == 0
	 * @post The radius of this new ship is equal to the minimum radius.
	 * 	  |  new.getRadius() == MIN_RADIUS
	 */
	public Ship (){
		this.setXCoord(0);
		this.setYCoord(0);
		this.setXVelocity(0);
		this.setYVelocity(0);
		this.setOrientation(0);
		this.setRadius(MIN_RADIUS);
	}
	
	/**
	 * Initialize this new ship with given coordinates, velocities,
	 * orientation and radius.
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
	 * @post The x-coordinate of this new ship is equal to 0.
	 * 	  |  new.getXCoord() == 0
	 * @post The y-coordinate of this new ship is equal to 0.
	 * 	  |  new.getYCoord() == 0
	 * @post The velocity along the x-axis of this new ship is equal to 0.
	 * 	  |  new.getXVelocity() == 0
	 * @post The velocity along the y-axis of this new ship is equal to 0.
	 * 	  |  new.getYVelocity() == 0
	 * @post The orientation of this new ship is equal to 0.
	 * 	  |  new.getOrientation() == 0
	 * @post The radius of this new ship is equal to the minimum radius.
	 * 	  |  new.getRadius() == MIN_RADIUS
	 */
	public Ship(double xCoord, double yCoord, double xVelocity,
			double yVelocity, double radius, double orientation) {
		this.setXCoord(xCoord);
		this.setYCoord(yCoord);
		this.setXVelocity(xVelocity);
		this.setYVelocity(yVelocity);
		this.setOrientation(orientation);
		this.setRadius(radius);
	}
		
	public static void main(String[] args) {
		Ship boat = new Ship();
		boat.xCoord = 1; 
		boat.yCoord = 2;
		double[] position = boat.getPositions();
		System.out.println(position[0]);
		
	}

	/**
	 * A variable that keeps track of the position of the ship on the x-axis,
	 * expressed in km.
	 */
	private double xCoord = 0;
	
	/**
	* A variable that keeps track of the position of the ship on the y-axis,
	* expressed in km.
	*/
	public double yCoord = 0;
	
	/**
	 * A variable that keeps track of the velocity of the ship along the x-axis,
	 * expressed in km/s.
	 */
	public double xVelocity = 0;
	
	/**
	 * A variable that keeps track of the velocity of the ship along the y-axis,
	 * expressed in km/s.
	 */
	public double yVelocity = 0;
	
	/**
	 * A variable that represents the minimum radius of the ship.
	 */
	public static final double MIN_RADIUS = 10;
	
	/**
	 * A variable that represents the radius of the ship, expressed in km.
	 * This radius must be equal or larger than the minimum radius.
	 */
	public double radius = MIN_RADIUS; 
	
	/**
	 * A variable that keeps track of the orientation of the ship, in radians. This 
	 * variable must always be a value between 0 and 2*PI.
	 */
	public double orientation = 0;
	
	/**
	 * A variable that represents the speed limit which the speed of the ship
	 * shall not exceed.
	 */
	public static final double SPEED_LIMIT = 300000; 
	
	
	/**
	 * Return the position of this ship.
	 * 
	 * @return An array which consists of the position of the ship along the x-axis
	 * 		   as its first elements, and the position along the y-axis as the second
	 * 		   element.
	 * 		|  result == [xCoord,yCoord]
	 */
	public double[] getPositions () { //unbounded 2D space, has to return an array.
		double positions[] = new double[2];
		positions[0] = getXCoord();
		positions[1] = getYCoord();
		return positions;
	}
	
	/**
	 * Return the velocities of this ship.
	 * 
	 * @return An array which consists of the velocity of the ship along the x-axis
	 * 		   as its first element, and the velocity along the y-axis as the second
	 * 		   element.
	 * 		|  result == [xVelocity,yVelocity]
	 */
	public double[] getVelocities () { 
		double velocities[] = new double[2];
		velocities[0] = getXVelocity();
		velocities[1] = getYVelocity();
		return velocities;
	}
		
	
	
	/**
	 * Return the speed of this ship.
	 * @return The square root of the velocity of the ship along the x-axis squared,
	 * 		   plus the velocity of the ship along the y-axis, squared. If this 
	 * 		   value exceeds the speed limit, the speed limit is returned.
	 * 		   
	 * 		|  result == 
	 * 		|	if (Math.sqrt(Math.pow(xVelocity, 2) + Math.pow(xVelocity, 2));
	 * 		|	< speedLimit)
	 * 		|	  return (Math.sqrt(Math.pow(xVelocity, 2) + Math.pow(xVelocity, 2))
	 * 		|	else
	 * 		|	  return speedLimit
	 * TODO: EVENTUEEL NEN INVAR				
	 */
	public double getSpeed() {
		
		double speed = Math.sqrt(Math.pow(getXVelocity(), 2) + Math.pow(getYVelocity(), 2));
		
		return speed;
	}
	
	/**
	 * Return the orientation of this ship.
	 * @return An angle in radians which represents the orientation of
	 * 		   the ship. This value must always be in between 0 and 2*PI.
	 * 		|  result == this.orientation
	 * TODO: invar overwegen
	 */
	@Basic
	public double getOrientation() {
		return orientation;
	}
	
	/**
	 * Check if the given orientation is a valid orientation.
	 * @return True if and only if the given orientation is
	 * 		   larger than 0 and less than 2*PI.
	 * 		|  result == (0 <= orientation) 
	 *		|          && (orientation <= 2*Math.PI)
	 */
	public boolean isValidOrientation(double orientation){
		return (0 <= orientation) && (orientation <= 2*Math.PI);
	}
	
	/**
	 * 
	 * Set the new orientation of the ship.
	 * @param newOrientation
	 * 		  The new orientation of the ship.
	 * @pre   The given orientation must be between 0 and 2*PI.
	 * 	   |  0 < newOrientation < 2*PI 
	 * @post  The orientation of this ship is equal to the given 
	 * 		  orientation
	 * 	   |  new.getOrientation() = orientation
	 * 		  
	 */
	@Model
	public void setOrientation(double newOrientation) {
		assert isValidOrientation(newOrientation);
		this.orientation = newOrientation;
	}
	
	/**
	 * Turn the ship in the direction of the given orientation.
	 * @param turnOrientation
	 * 		  The orientation to turn in.
	 * 
	 * @post   The new orientation is equal to the old orientation 
	 * 		   plus the given turnorientation.
	 *		|  new.getOrientation() == getOrientation() + turnOrientation
	 */	  
	public void turn(double turnOrientation) {
		double newOrientation = getOrientation() + turnOrientation;
		assert isValidOrientation(newOrientation);
		setOrientation(newOrientation);
	}
	
	/**
	 * Return whether or not the given radius is a valid value.
	 * @return True if and only if the given radius is larger or equal 
	 * 		   than the minimum radius.
	 * 		|  result == (radius >= MIN_RADIUS)
	 */
	public static boolean isValidRadius(double radius){
		return radius >= MIN_RADIUS;
	}
	
	/**
	 * Set the new radius of the ship.
	 * @throws IllegalArgumentException
	 * 		   The given newRadius is not a valid value for the radius.
	 * 		|  isValidRadius(newRadius)
	 */
	@Model
	public void setRadius(double newRadius) throws IllegalArgumentException{
		if (! isValidRadius(newRadius))
			throw new IllegalArgumentException();
		this.radius = newRadius;
	}
	
	/**
	 * Return the radius of this ship.
	 * @return The radius of this ship, expressed in km. This value will 
	 * 		   always be positive.
	 * 		|  result == this.radius
	 */
	@Basic
	public double getRadius() {
		return radius;
	}
	
	/**
	 * Check whether the given time is a valid time for
	 * movement of the ship
	 * @param time
	 * 		  The time to check.
	 * @return True if and only if the given time is 
	 * 		   larger or equal to zero.
	 * 		|  result == (time >= 0)
	 */
	public static boolean isValidTime(double time) {
		return time >= 0;
	}
	
	/**
	 * Change the position of the ship based on the current position, 
	 * velocity and a given time duration named time.
	 * @param   time
	 * 			The time for which the ship must be moved.
	 * @effect  The coordinate of the ship along the x-axis
	 * 			is set to the old x-coordinate plus the given
	 * 		    time multiplied by the velocity of the ship
	 * 			along the x-axis. 
	 * 	    |   setXcoord(getXcoord() + time*getxVelocity())
	 * @effect  The coordinate of the ship along the y-axis
	 * 			is set to the old y-coordinate plus the given
	 * 		    time multiplied by the velocity of the ship 
	 * 			along the y-axis.  		
	 * 		|   setYcoord(getYcoord() + time*getyVelocity())
	 * @throws IllegalArgumentException
	 * 		   The given duration time is less than zero.
	 * 		|  ! isValidTime(time)
	 */
	public void move(double time) throws IllegalArgumentException {
		if (!isValidTime(time))
			throw new IllegalArgumentException();
		double newxCoord = getXCoord() + time*getXVelocity();
		double newyCoord = getYCoord() + time*getYVelocity();
		
		setXCoord(newxCoord);
		setYCoord(newyCoord);

	}
	
	/**
	 * Change the ship's velocity based on the current velocity,
	 * it's orientation and a given amount a. The speed after the
	 * thrust will never be greater than the speed limit.
	 * 
	 * 
	 * @param  a
	 * 		   The given amount a which determines the new velocity.
	 * @effect The velocity of the ship along the x-axis is set to
	 * 		   the old velocity plus a times the cosine of the 
	 * 		   orientation of the ship.
	 * 	    |  setXVelocity(getXVelocity + a*Math.cos(getOrientation()))
	 * @effect The velocity of the ship along the y-axis is set to
	 * 		   the old velocity plus a times the sine of the 
	 * 		   orientation of the ship.
	 * 	    |  setYVelocity(getYVelocity + a*Math.sin(getOrientation()))
	 * @post   The speed of the ship will not exceed the speed limit
	 * 		|  getSpeed() <= SPEED_LIMIT
	 */
	public void thrust(double a)  {
		if (a < 0)
			a = 0;
		
		setXVelocity(xVelocity + a*Math.cos(getOrientation()));
		setYVelocity(yVelocity + a*Math.sin(getOrientation()));

		if (this.getSpeed() > SPEED_LIMIT) {
			double absSpeed = getSpeed();
			setXVelocity((xVelocity/absSpeed)*SPEED_LIMIT);
			setYVelocity((yVelocity/absSpeed)*SPEED_LIMIT);
		}
	}
	
	/**
	 * Set the x-coordinate of the ship to the given value.
	 * @param newxCoord
	 * 		  The new x-coordinate of the ship.
	 * @post  The x-coordinate of this ship is equal 
	 * 		  to the given x-coordinate.
	 * 	   |  new.getXCoord() = newXCoord
	 * @throws IllegalArgumentException
	 * 		   The given x-coordinate is NaN.
	 * 	   |   newXCoord == Double.NaN
	 */
	@Model
	public void setXCoord(double newXCoord) throws IllegalArgumentException{
		if (Double.isNaN(newXCoord))
			throw new IllegalArgumentException("NaN is not a valid coordinate!");
		xCoord = newXCoord;
	}
	
	/**
	 * Set the y-coordinate of the ship to the given value.
	 * @param newyCoord
	 * 		  The new y-coordinate of the ship.
	 * @post  The y-coordinate of this ship is equal to
	 * 		  the given y-coordinate.
	 *     |  new.getYCoord() = newYCoord
	 * @throws IllegalArgumentException
	 * 		   The given y-coordinate is NaN.
	 * 	   |   newYCoord == Double.NaN
	 */
	@Model
	public void setYCoord(double newYCoord) {
		if (Double.isNaN(newYCoord))
			throw new IllegalArgumentException("NaN is not a valid coordinate!");
		yCoord = newYCoord;
	}
	
	/**
	 * Return the x-coordinate of this ship.
	 * @return The x-coordinate of this ship.
	 * 		|  result == this.xCoord
	 */
	@Basic
	public double getXCoord() {
		return xCoord;
	}
	
	/**
	 * Return the y-coordinate of this ship.
	 * @return The y-coordinate of this ship.
	 * 		|  result == this.yCoord
	 */
	@Basic
	public double getYCoord() {
		return yCoord;
	}
	
	
	/**
	 * Set the velocity along the x-axis of the ship to the
	 * given velocity.
	 * @param newXVelocity
	 * 		  The new velocity of this ship along the x-axis.
	 * @post  The velocity of this ship along the x-axis
	 * 	      is equal to the given x-coordinate.  
	 *     |  new.getxVelocity = newXVelocity
	 * 
	 */
	@Model
	public void setXVelocity(double newXVelocity) {
		xVelocity = newXVelocity;
	}
	
	/**
	 * Set the velocity along the y-axis of the ship to the
	 * given velocity.
	 * @param newYVelocity
	 * 		  The new velocity of this ship along the y-axis.
	 * @post  The velocity of this ship along the y-axis
	 * 	      is equal to the given y-coordinate.  
	 *     |  new.getYVelocity = newYVelocity
	 * 
	 */
	@Model
	public void setYVelocity(double newYVelocity) {
		yVelocity = newYVelocity;
	}
	
	/**
	 * Return the velocity of this ship along the x-axis.
	 * @return The velocity of this ship along the x-axis.
	 * 		|  result == this.xVelocity
	 */
	@Basic
	public double getXVelocity() {
		return xVelocity;
	}
	
	/**
	 * Return the velocity of this ship along the y-axis.
	 * @return The velocity of this ship along the y-axis.
	 * 		|  result == this.yVelocity
	 */
	@Basic
	public double getYVelocity() {
		return yVelocity;
	}
	
	
	/**
	 * Return the distance between this ship and the other ship.
	 * @param other
	 * 		  The other ship which is needed to calculate the
	 * 		  the distance between this ship and the other ship.
	 * @return The distance between this ship and the other ship.		
	 * 		|  result == Math.sqrt(Math.pow(this.xCoord - other.xCoord, 2) + Math.pow(this.yCoord - other.yCoord, 2))
	 *		|  - (this.RADIUS + other.RADIUS)
	 * @throws IllegalArgumentException
	 * 		   The other ship is not effective.
	 * 		|  other == null
	 */
	public double getDistanceBetween(Ship other) throws IllegalArgumentException {
		if (other == null)
			throw new IllegalArgumentException("The other ship is not effective!");
		return Math.sqrt(Math.pow(this.xCoord - other.xCoord, 2) + Math.pow(this.yCoord - other.yCoord, 2))
			- (this.radius + other.radius);
	}
	
	/**
	 * Return whether this ship overlaps with the other ship.
	 * @param other
	 * 		  The other ship which is used to check if
	 * 		  this ship overlaps with it.
	 * @return True if and only if the distance between the
	 * 		   ships is less or equal to 0.
	 * 		|  result ==  (getDistanceBetween(other) <= 0)
	 * @throws IllegalArgumentException
	 * 		  The other ship is not effective.
	 * 		|  other == null;
	 */
	public boolean overlap(Ship other) throws IllegalArgumentException{
		if (other == null)
			throw new IllegalArgumentException("The other ship is not effective!");
		return getDistanceBetween(other) <= 0;
	}
	
	/**
	 * Return the time it will take for this ship to collide with the other ship.
	 * @param other
	 * 		  The other ship which is used to calculate the time it will
	 * 		  take to collide with this ship.
	 * @return The time it will take for this ship to collide with the 
	 * 		   other ship, if they collide.
	 * 		|  result == -(deltaV*deltaR + Math.sqrt(d))/Math.pow(deltaV,2)
	 * @throws IllegalStateException
	 * 		   This ship overlaps with the other ship.
	 * 		|  overlap(other) 
	 * @throws IllegalArgumentException
	 * 		  The other ship is not effective.
	 * 		|  other == null
	 */
	public double getTimeToCollision(Ship other) throws IllegalStateException, IllegalArgumentException{
		if (this.overlap(other) == true)
			throw new IllegalStateException("The ships overlap!");
		if (other == null)
			throw new IllegalArgumentException("The other ship is not effective!");
		
		double deltarX = other.getXCoord() - this.getXCoord();
		double deltarY = other.getYCoord() - this.getYCoord();
		double sigma = other.getRadius() + this.getRadius();
		double deltavX = other.getXVelocity() - this.getXVelocity();
		double deltavY = other.getYVelocity() - this.getYVelocity();
		
		double d = Math.pow(deltarX * deltavX + deltarY * deltavY, 2) 
				   - ( Math.pow(deltavX, 2) + Math.pow(deltavY, 2))* 
				     (Math.pow(deltarX, 2) + Math.pow(deltarY, 2) - Math.pow(sigma, 2));
		
		if ((deltavX * deltarX + deltavY * deltarY >= 0) || (d <= 0) )
			return Double.POSITIVE_INFINITY;
		
		double time = -(deltavX * deltarX + deltavY * deltarY + Math.sqrt(d))
						/ (Math.pow(deltavX, 2) + Math.pow(deltavY, 2));
		return time;
	}
	
	/**
	 * Return the position where this ship and the other ship will collide.
	 * @param other
	 * 		 The other ship which is used to calculate the position where
	 * 		 it will collide with this ship.
	 * @return The position where this ship and the other ship will collide,
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
	 * 		   This ship overlaps with the other ship.
	 * 		|  overlap(other) 
	 * @throws IllegalArgumentException
	 * 		   The other ship is not effective.
	 * 		|  other == null
	 */
	public double[] getCollisionPosition(Ship other) throws IllegalStateException, IllegalArgumentException{
		if (this.overlap(other) == true)
			throw new IllegalStateException("The ships overlap!");
		if (other == null)
			throw new IllegalArgumentException("The other ship is not effective!");
		
		double deltaT = getTimeToCollision(other);
		
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
	
	
}