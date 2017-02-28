package asteroids.model;
// Class where the asked methods should be worked out in.
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
	public double xCoord;
	
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
	 * A variable that represents the minimum radius of the sip.
	 */
	private final double MIN_RADIUS = 10;
	
	/**
	 * A variable that represents the radius of the ship, expressed in km.
	 */
	public final double RADIUS; // never changes during program execution.
	
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
	 * 		|  result == [x,y]
	 */
	public double[] getPosition () { //unbounded 2D space, has to return an array.
		double position[] = new double[2];
		position[0] = this.xCoord;
		position[1] = this.yCoord;
		return position;
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
	 */
	public void setOrientation(double newOrientation) {
		this.orientation = newOrientation;
	}
	
	/**
	 * Return the radius of this ship.
	 * @return The radius of this ship, expressed in km. This value will 
	 * 		   always be positive.
	 */
	public double getRadius() {
		return RADIUS;
	}
	
	/**
	 * Change the position of the ship based on the current position, 
	 * velocity and a given time duration delta t.
	 * 
	 * @return
	 */
	public void move(double time) throws IllegalArgumentException {
		if (time < 0) 
			throw new IllegalArgumentException();
		double newxCoord = getxCoord() + time*getxVelocity();
		double newyCoord = getyCoord() + time*getyVelocity();
		
		setXCoord(newxCoord);
		setYCoord(newyCoord);

	}
	
	/**
	 * Change the ship's velocity based on the current velocity,
	 * it's orientation and a given amount a.
	 * @param  a
	 * 		   The given amount a which determines the new velocity.
	 * @throws IllegalArgumentException
	 * 		   The value a is negative
	 * 		|  a < 0
	 */
	public void thrust(double a) throws IllegalArgumentException {
		if (a<0)
			throw new IllegalArgumentException();
		
		setxVelocity(xVelocity + a*Math.cos(getOrientation()));
		setyVelocity(yVelocity + a*Math.sin(getOrientation()));
		
	}
	
	private void setXCoord(double newxCoord) {
		xCoord = newxCoord;
	}
	
	private void setYCoord(double newyCoord) {
		yCoord = newyCoord;
	}
	
	public double getxCoord() {
		return xCoord;
	}
	
	public double getyCoord() {
		return yCoord;
	}
	
	
	
	private void setxVelocity(double newxVelocity) {
		xVelocity = newxVelocity;
	}
	
	private void setyVelocity(double newyVelocity) {
		yVelocity = newyVelocity;
	}
	
	public double getxVelocity() {
		return xVelocity;
	}
	
	public double getyVelocity() {
		return yVelocity;
	}
	
	
	/**
	 * ook defensief he jongens
	 * @param other
	 * @return The distance between this ship and the other ship.
	 * 		   
	 */
	public double getDistanceBetween(Ship other) {
		return Math.sqrt(Math.pow(this.xCoord - other.xCoord, 2) + Math.pow(this.yCoord - other.yCoord, 2))
			- (this.RADIUS + other.RADIUS);
	}
	
	public boolean overlap(Ship other) {
		return getDistanceBetween(other) <= 0;
	}
	
	public double getTimeToCollision(Ship other) {
		double deltarX = other.getxCoord() - this.getxCoord();
		double deltarY = other.getyCoord() - this.getyCoord();
		double sigma = other.getRadius() + this.getRadius();
		double deltavX = other.getxVelocity() - this.getxVelocity();
		double deltavY = other.getyVelocity() - this.getyVelocity();
		
		double d = Math.pow(deltarX * deltavX + deltarY * deltavY, 2) 
				   - ( Math.pow(deltavX, 2) + Math.pow(deltavY, 2))* 
				     (Math.pow(deltarX, 2) + Math.pow(deltarY, 2) - Math.pow(sigma, 2));
		
		if ((deltavX * deltarX + deltavY * deltarY >= 0) || (d <= 0) )
			return Double.POSITIVE_INFINITY;
		
		double time = -(deltavX * deltarX + deltavY * deltarY + Math.sqrt(d))
						/ (Math.pow(deltavX, 2) + Math.pow(deltavY, 2));
		return time;
	}
	
	public double[] getCollisionPosition(Ship other) {
		double deltaT = getTimeToCollision(other);
		
		double thisxCoord = getxCoord() + deltaT*getxVelocity();
		double thisyCoord = getyCoord() + deltaT*getyVelocity();
		
		double otherxCoord = other.getxCoord() + deltaT*other.getxVelocity();
		double otheryCoord = other.getyCoord() + deltaT*other.getyVelocity();
		
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