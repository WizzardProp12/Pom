package asteroids.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class of ships in an unbounded twodimensional space, 
 * involving coordinates, velocities, a radius and an 
 * orientation. 
 * @author Wouter Cams and Stijn Bruggeman
 *
 */
public class Ship {
	
	private static final Exception IllegalTimeException = null;

	public static void main(String[] args) {
		Ship boat = new Ship();
		boat.xCoord = 1;
		boat.yCoord = 2;
		double[] position = boat.getPosition();
		System.out.println(position);
		
	}

	/**
	 * A variable that keeps track of the position of the ship on the x-axis,
	 * expressed in km.
	 */
	private double xCoord;
	
	/**
	* A variable that keeps track of the position of the ship on the y-axis,
	* expressed in km.
	*/
	public double yCoord;
	
	/**
	 * A variable that keeps track of the velocity of the ship along the x-axis,
	 * expressed in km/s.
	 */
	public double xVelocity;
	
	/**
	 * A variable that keeps track of the velocity of the ship along the y-axis,
	 * expressed in km/s.
	 */
	public double yVelocity;
	
	/**
	 * A variable that represents the minimum radius of the ship.
	 */
	private final double MIN_RADIUS = 10;
	
	/**
	 * A variable that represents the radius of the ship, expressed in km.
	 */
	public double RADIUS; // never changes during program execution.
	
	/**
	 * A variable that keeps track of the orientation of the ship, in radians. This 
	 * variable must always be a value between 0 and 2*PI.
	 */
	public double orientation;
	
	/**
	 * A variable that represents the speed limit which the speed of the ship
	 * shall not exceed.
	 */
	private final double SPEED_LIMIT = 300000; // speed limit can vary from ship to ship.
	
	
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
		positions[0] = this.xCoord;
		positions[1] = this.yCoord;
		return positions;
	}
	
	/**
	 * Return the velocities of this ship.
	 * 
	 * @return An array which consists of the velocity of the ship along the x-axis
	 * 		   as its first element, and the velocity along the y-axis as the second
	 * 		   element.
	 * 		|  result == [this.xVelocity,this.yVelocity]
	 */
	public double[] getVelocities () { 
		double velocities[] = new double[2];
		velocities[0] = this.getXVelocity();
		velocities[1] = this.getYVelocity();
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
	 * 				
	 */
	public double getSpeed() {
		
		double speed = Math.sqrt(Math.pow(xVelocity, 2) + Math.pow(xVelocity, 2));
		//The if class might be unnecessary because the thrust  can never cause
		// a velocity higher than the SPEED_LIMIT.
		 
		if (speed < SPEED_LIMIT)
			return speed;
		else
			return SPEED_LIMIT;
	}
	
	/**
	 * Return the orientation of this ship.
	 * @return An angle in radians which represents the orientation of
	 * 		   the ship. This value must always be in between 0 and 2*PI.
	 * 		|  result == this.orientation
	 * 
	 */
	public double getOrientation() {
		return orientation;
	}
	
	/**
	 *nominally
	 * Set the new orientation of the ship.
	 * @param newOrientation
	 * 		  The new orientation of the ship.
	 * @pre   The given orientation must be between 0 and 2*PI.
	 * 	   |  0 < newOrientation < 2*PI 
	 * @post  The orientation of this ship is equal to the given 
	 * 		  orientation
	 * 	   |  new.getOrientation = orientation
	 * 		  
	 */
	public void setOrientation(double newOrientation) {
		this.orientation = newOrientation;
	}
	
	/**
	 * Turn the ship in the direction of the given orientation.
	 * @param turnOrientation
	 */
	public void turn(double turnOrientation) {
		setOrientation(getOrientation() + turnOrientation);
	}
	
	/**
	 * Set the new radius of the ship
	 */
	public void setRadius(double newRadius){
		this.RADIUS = newRadius;
	}
	/**
	 * Return the radius of this ship.
	 * @return The radius of this ship, expressed in km. This value will 
	 * 		   always be positive.
	 * 		|  result == this.radius
	 */
	public double getRadius() {
		return RADIUS;
	}
	
	/**
	 * Check whether the given time is a valid time for
	 * movement of the ship
	 * @param time
	 * 		  The time to check.
	 * @return True if and only if the given time is 
	 * 		   larger or equal to zero.
	 * 		|  result == time >= 0
	 */
	public static boolean isValidTime(double time) {
		return time >= 0;
	}
	
	/**
	 * DEFENSIEF
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
		double newxCoord = getXCoord() + time*getYVelocity();
		double newyCoord = getYCoord() + time*getYVelocity();
		
		setXCoord(newxCoord);
		setYCoord(newyCoord);

	}
	
	/**
	 * TOTALLY
	 * Change the ship's velocity based on the current velocity,
	 * it's orientation and a given amount a.
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
	 */
	public void thrust(double a)  {
		if (a < 0)
			a = 0;
		setXVelocity(xVelocity + a*Math.cos(getOrientation()));
		setYVelocity(yVelocity + a*Math.sin(getOrientation()));
		
	}
	
	/**
	 * UNBOUNDED 2D SPACE => NO INVALID COORDS POSSIBLE??? DEFENSIEF
	 * @param newxCoord
	 * 		  The new x-coordinate of the ship.
	 * @post  The x-coordinate of this ship is equal 
	 * 		  to the given x-coordinate.
	 * 	   |  new.getXCoord = newXCoord
	 */
	public void setXCoord(double newXCoord) {
		xCoord = newXCoord;
	}
	
	/**
	 * UNBOUNDED 2D SPACE => NO INVALID COORDS POSSIBLE??? DEFENSIEF
	 * @param newyCoord
	 * 		  The new y-coordinate of the ship.
	 * @post  The y-coordinate of this ship is equal to
	 * 		  the given y-coordinate.
	 *     |  new.getYCoord = newYCoord
	 */
	public void setYCoord(double newYCoord) {
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
	 * TOTAL
	 * @param newXVelocity
	 * 		  The new velocity of this ship along the x-axis.
	 * @post  The velocity of this ship along the x-axis
	 * 	      is equal to the given x-coordinate.  
	 *     |  new.getxVelocity = newXVelocity
	 * 
	 */
	public void setXVelocity(double newXVelocity) {
		xVelocity = newXVelocity;
	}
	
	/**
	 * TOTAL
	 * @param newYVelocity
	 * 		  The new velocity of this ship along the y-axis.
	 * @post  The velocity of this ship along the y-axis
	 * 	      is equal to the given y-coordinate.  
	 *     |  new.getYVelocity = newYVelocity
	 * 
	 */
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
	 * DEFENSIVE
	 * @param other
	 * 		  The other ship which is needed to calculate the
	 * 		  the distance between this ship and the other ship.
	 * 		  
	 * @return The distance between this ship and the other ship.		
	 * 		|  result == Math.sqrt(Math.pow(this.xCoord - other.xCoord, 2) + Math.pow(this.yCoord - other.yCoord, 2))
	 *		|  - (this.RADIUS + other.RADIUS)
	 * @throws IllegalArgumentException
	 * 		   The other ship is not effective.
	 * 		|  other == null
	 * 			  
	 * 		   
	 */
	public double getDistanceBetween(Ship other) throws IllegalArgumentException {
		if (other == null)
			throw new IllegalArgumentException("The other ship is not effective!");
		return Math.sqrt(Math.pow(this.xCoord - other.xCoord, 2) + Math.pow(this.yCoord - other.yCoord, 2))
			- (this.RADIUS + other.RADIUS);
	}
	
	/**
	 * 
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
	 * 
	 * @param other
	 * 		  The other ship which is used to calculate the time it will
	 * 		  take to collide with this ship.
	 * @return The time it will take for this ship to collide with the 
	 * 		   other ship.
	 * 		|  result == -(deltaV*deltaR + Math.sqrt(d))/Math.pow(deltaV,2)
	 * 
	 * @throws IllegalStateException
	 * 		   This ship overlaps with the other ship.
	 * 		|  overlap(other) 
	 * @throws IllegalArgumentException
	 * 		  The other ship is not effective.
	 * 		|  other == null
	 * 
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
	 * 
	 * @param other
	 * 		 The other ship which is used to calculate the position where
	 * 		 it will collide with this ship.
	 * @return The position where this ship and the other ship will collide,
	 * 		   if and only if the time to collision is less than infinity.
	 * 		   Else, null is returned.
	 * 	    |  If (this.getTimeToCollision(other) < Double.POSITIVE_INFINITY)
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
	 * 
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