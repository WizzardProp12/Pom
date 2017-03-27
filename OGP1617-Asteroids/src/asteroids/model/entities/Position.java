package asteroids.model.entities;

import asteroids.model.environment.World;

/**
 * A class representing the position of an Entity.
 */
public class Position {
	
	// CONSTRUCTOR
	
	/**
	 * Initialise a new Position with given x and y coordinates.
	 */
	public Position(double xCoord, double yCoord) {
		setXCoord(xCoord);
		setYCoord(yCoord);
	}
	
	/**
	 * Initialise a new Position with x and y coordinates 0.
	 */
	public Position() {
		this(0, 0);
	}
	
	// COORDINATES
	
	private double xCoord;
	
	private double yCoord;
	
	public double getXCoord() {
		return xCoord;
	}
	
	public double getYCoord() {
		return yCoord;
	}
	
	public void setXCoord(double newXCoord) {
		xCoord = newXCoord;
	}
	
	public void setYCoord(double newYCoord) {
		yCoord = newYCoord;
	}
	
	public double[] toArray() {
		double position[] = new double[2];
		position[0] = getXCoord();
		position[1] = getYCoord();
		return position;
	}
	
	// ENTITY
	
	private Entity entity;
	
	public Entity getEntity() {
		return entity;
	}
	
	public void setEntity(Entity newEntity) {
		entity = newEntity;
		if (newEntity != null && newEntity.getWorld() != null)
			setWorld(newEntity.getWorld());
	}
	
	// WORLD
	
	/**
	 * The world in which the position is located. If no world is 
	 * assigned to the position, this is the null pointer reference.
	 */
	private World world;
	
	/**
	 * Returns a reference to the positions world, if any.
	 * @return If the position is located in a world
	 * 				then return the positions world
	 * 		   Else return the null pointer reference
	 */
	public World getWorld() {
		return world;
	}
	
	/**
	 * Set the world of the position if and only if the position has no
	 * entity or the entity is in the given world
	 */
	protected void setWorld(World newWorld) throws IllegalArgumentException{
		if (getEntity() == null	|| getEntity().getWorld() == newWorld)
			world = newWorld;
		else
			throw new IllegalArgumentException(
					"associated entity is connected to another world");
	}
	
	
	// OVERRIDES
	
	/**
	 * Return whether the given argument equals the prime object.
	 * @return true if and only if the coordinates of the Positions are the same.
	 */
	@Override
	public boolean equals(Object other){
		if (! (other instanceof Position))
			return false;
		else if (getXCoord() == ((Position) other).getXCoord()
				&& getYCoord() == ((Position) other).getYCoord())
			return true;
		else
			return false;
	}
	
	/**
	 * Return the hashCode corresponding to the position.
	 * @return see implementation...
	 */
	@Override
	public int hashCode() {
		Integer max = (int) Math.floor(Math.sqrt(Integer.MAX_VALUE));
		double xCoord = getXCoord();
		double yCoord = getYCoord();
		Integer xValue;
		Integer yValue;
		
		if (getWorld() != null && getWorld().getWidth() >= max)
			xValue = (int) Math.round(xCoord * (max/getWorld().getWidth()));
		else
			xValue = (int) Math.round(xCoord);
		
		if (getWorld() != null && getWorld().getHeight() >= max)
			yValue = (int) Math.round(yCoord * (max/getWorld().getHeight()));
		else
			yValue = (int) Math.round(yCoord);
		
		String stringCode = xValue.toString() + yValue.toString();
		
		int intCode = Integer.parseInt(stringCode);
        return intCode;
    }
	
	/**
	 * Return a string representing the Position.
	 */
	public String toString() {
		return "Position at (x:" + String.valueOf(getXCoord()) 
						+ ", y:" + String.valueOf(getYCoord()) + ")";
	}
}
