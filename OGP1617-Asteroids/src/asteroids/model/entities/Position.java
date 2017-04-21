package asteroids.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Raw;

/**
 * A class representing the position of an Entity.
 * @invar isValidXCoord(getXCoord)
 * @invar isValidYCoord(getYCoord)
 */
public class Position {
	
	// CONSTRUCTOR
	
	/**
	 * Initialise a new Position with given coordinates.
	 * @throws IllegalArgumentException
	 * 		   If the x coordinate is NaN.
	 * 	     | xCoord == Double.NaN
	 * @throws IllegalArgumentException
	 * 		   If the y coordinate is NaN.
	 * 	     | yCoord == Double.NaN
	 */
	public Position(double xCoord, double yCoord) throws IllegalArgumentException {
		if (xCoord == Double.NaN) throw new IllegalArgumentException(
				"given xCoord is NaN");
		if (yCoord == Double.NaN) throw new IllegalArgumentException(
				"given yCoord is NaN");
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
	
	/**
	 * The x coordinate of the position.
	 */
	private double xCoord;
	
	/**
	 * The y coordinate of the position.
	 */
	private double yCoord;
	
	/**
	 * Return the x coordinate of the position.
	 * @pre The x coordinate is a number
	 * 	  | ! xCoord == Double.NaN
	 */
	@Basic @Raw
	public double getXCoord() {
		return xCoord;
	}
	
	/**
	 * Return the y coordinate of the position.
	 * @pre The y coordinate is a number
	 * 	  | ! yCoord == Double.NaN
	 */
	@Basic @Raw
	public double getYCoord() {
		return yCoord;
	}
	
	/**
	 * Set the x-coordinate of the position.
	 * @post ...
	 * 	   | new getXCoord() == xCoord
	 */
	protected void setXCoord(double xCoord) throws IllegalArgumentException {
		this.xCoord = xCoord;
	}
	
	/**
	 * Set the y-coordinate of the position.
	 * @post ...
	 * 	   | new getYCoord() == yCoord
	 */
	protected void setYCoord(double yCoord) throws IllegalArgumentException {
		this.yCoord = yCoord;
	}
	
	/**
	 * Return the coordinates of this position in an array.
	 */
	public double[] toArray() {
		double position[] = new double[2];
		position[0] = getXCoord();
		position[1] = getYCoord();
		return position;
	}
	
	// ENTITY
	
	/**
	 * The entity assigned to this position.
	 */
	private Entity entity;
	
	/**
	 * Return the entity assigned to this position.
	 * @invar This position is valid for the entity (if it is not null)
	 * 		| getEntity() == null || getEntity().canHaveAsPosition(this)
	 */
	@Basic
	public Entity getEntity() {
		return entity;
	}
	
	/**
	 * Set a new entity reference for this position.
	 * @pre    The previous entity must not reference this position.
	 * 		 | getEntity() == null || newEntity().getPosition() != this
	 * @pre    The given entity must reference this position.
	 * 		 | newEntity.getPosition() == this
	 * @post   The position references the given entity.
	 * 		 | getEntity() == newEntity
	 * @throws IllegalArgumentException
	 * 		   If the current entity still references this position
	 * 		 | getEntity() != null && getEntity().getPosition() == this
	 * @throws IllegalArgumentException
	 * 		   If the new entity does not reference this position
	 * 		 | newEntity != null && newEntity.getPosition() != this
	 */
	protected void setEntity(Entity newEntity) throws IllegalArgumentException{
		if (getEntity() != null && getEntity().getPosition() == this)
			throw new IllegalArgumentException(
					"the position is still referenced by its current entity");
		if (newEntity != null && newEntity.getPosition() != this)
			throw new IllegalArgumentException(
					"the given entity must reference this position");
		entity = newEntity;
	}
	
	
	// OVERRIDES
	
	/**
	 * Return whether the given argument equals the prime object.
	 */
	@Override
	public boolean equals(Object other){
		if (! (other instanceof Position))
			return false;
		else return hashCode() == other.hashCode();
	}
	
	/**
	 * Return the hashCode corresponding to the position.
	 * @return see implementation...
	 */
	@Override
	public int hashCode() {
		Integer xValue = (int) Math.round(getXCoord()/4);
		Integer yValue = (int) Math.round(getYCoord()/4);
        return Integer.parseInt(xValue.toString() + yValue.toString());
    }
	
	/**
	 * Return a string representing the Position.
	 */
	public String toString() {
		return "(" + String.valueOf(getXCoord()) 
						+ ", " + String.valueOf(getYCoord()) + ")";
	}
}
