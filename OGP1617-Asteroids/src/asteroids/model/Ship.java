package asteroids.model;
// Class where the asked methods should be worked out in.
public class Ship {
	
	public static void main(String[] args) {
		Ship boat = new Ship();
		boat.x = 1;
		boat.y = 2;
		double[] position = boat.getPosition();
		System.out.println(position);
		
	}

	/**
	 * A variable that keeps track of the position of the ship on the x-axis,
	 * expressed in km.
	 */
	private double x;
	
	/**
	* A variable that keeps track of the position of the ship on the y-axis,
	* expressed in km.
	*/
	private double y;
	
	/**
	 * A variable that keeps track of the velocity of the ship along the x-axis,
	 * expressed in km/s.
	 */
	private double xVelocity;
	
	/**
	 * A variable that keeps track of the velocity of the ship along the y-axis,
	 * expressed in km/s.
	 */
	private double yVelocity;
	
	/**
	 * A variable that represents the radius of the ship, expressed in km.
	 */
	private final double radius = 10; // never changes during program execution.
	
	/**
	 * A variable that keeps track of the orientation of the ship, in radians. This 
	 * variable must always be a value between 0 and 2*PI.
	 * 
	 */
	private double orientation;
	
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
		position[0] = this.x;
		position[1] = this.y;
		return position;
	}
	
	/**
	 * A variable that represents the speed limit which the speed of the ship
	 * shall not exceed.
	 * 
	 */
	
	private final double speedLimit = 300000; // speed limit can vary 
											  // from ship to ship.
	/**
	 * A variable that represents the minimum radius of the sip.
	 */
	private final double minRadius = 10;
	
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
		if (speed < speedLimit)
			return speed;
		else
			return speedLimit;
	
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
	 * Return the radius of this ship.
	 * @return The radius of this ship, expressed in km. This value will 
	 * 		   always be positive.
	 */
	public double getRadius() {
		return radius;
	}
}