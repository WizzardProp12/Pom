package asteroids.model.environment;

import asteroids.model.entities.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import be.kuleuven.cs.som.annotate.*;

public class World {
	
	// CONSTRUCTORS
	
	/**
	 * Initialise a new world with the given height and the given width.
	 * @param width
	 * 		  The width of this new world.
	 * 		| new getHeight() == height
	 * @param height
	 * 		  The height of this new world.
	 * 		| new getWidth() == width
	 */
	public World(double height, double width){
		this.setWidth(width);
		this.setHeight(height);
	}
	
	/**
	 * Initialise a new world with default height and default width.
	 * @post The width is the default value
	 * 	   | new getWidth() == World.DEFAULT_WIDTH
	 * @post The height is the default value
	 * 	   | new getHeight() == World.DEFAULT_HEIGHT
	 */
	public World(){
		this.setWidth(World.DEFAULT_WIDTH);
		this.setHeight(World.DEFAULT_HEIGHT);
	}
	
	
	// SIZE (total)
	
	/**
	 * The maximum width of a world.
	 */
	static final double MAX_WIDTH = Double.MAX_VALUE;
	
	/**
	 * The maximum height of a world.
	 */
	static final double MAX_HEIGHT = Double.MAX_VALUE;
	
	/**
	 * The default width of a world.
	 */
	static final double DEFAULT_WIDTH = 0;
	
	/**
	 * The default height of a world.
	 */
	static final double DEFAULT_HEIGHT = 0;
	
	
	/**
	 * The width of the world.
	 */
	private double width;
	
	/**
	 * Return the width of the world.
	 * @see implementation
	 */
	@Basic
	public double getWidth(){
		return this.width;
	}
	
	/**
	 * Set the width of the world.
	 * @post see implementation...
	 * 	   | 0 <= new getWidth() <= World.MAX_HEIGHT
	 */
	@Model
	private void setWidth(double width){
		if (width < 0)
			this.width = World.DEFAULT_WIDTH;
		else if (width > World.MAX_WIDTH)
			this.width = World.MAX_WIDTH;
		else
			this.width = width;
	}
	
	
	/**
	 * The height of the world.
	 */
	private double height;
	
	/**
	 * Return the height of the world.
	 * @see implementation
	 */
	@Basic
	public double getHeight(){
		return this.height;
	}
	
	/**
	 * Set the height of the world.
	 * @post see implementation...
	 * 	   | 0 <= new getHeight() <= World.MAX_HEIGHT
	 */
	@Model
	private void setHeight(double height){
		if (height < 0)
			this.height = World.DEFAULT_HEIGHT;
		else if (height > World.MAX_HEIGHT)
			this.height = World.MAX_HEIGHT;
		else
			this.height = height;
	}
	
	
	// COORDINATE CHECKERS
	
	/**
	 * Return if the given x coordinate is within the worlds borders.
	 * @see implementation
	 */
	@Model
	public boolean isValidXCoord(double x) {
		if (0 <= x && x <= getWidth())
			return true;
		return false;
	}
	
	/**
	 * Return if the given y coordinate is within the worlds borders.
	 * @see implementation
	 */
	@Model
	public boolean isValidYCoord(double y) {
		if (0 <= y && y <= getHeight())
			return true;
		return false;
	}
	
	/** Return whether the given position array is within the bounds of the prime object.
	 * 
	 * @param xCoord
	 * 		  The x coordinate of the position.
	 * @param yCoord
	 * 		  The y coordinate of the position.
	 * @return see implementation...
	 */
	public boolean ContainsPosition(double[] position) {
		if (isValidXCoord(position[0]) && isValidYCoord(position[1]))
			return true;
		return false;
	}
	
	/** Return whether the given Position is within the bounds of the prime object.
	 * @param Position
	 * 		  The position to be checked.
	 * @return see implementation...
	 */
	public boolean ContainsPosition(Position position) {
		if (isValidXCoord(position.getXCoord()) && isValidYCoord(position.getYCoord()))
			return true;
		return false;
	}
	
	// ENTITIES - GETTERS
	
	/**
	 * A HashMap storing all the Entities with their Positions as keys.
	 */
	HashMap<Position, Entity> entityMap = new HashMap<Position, Entity>();
	
	/**
	 * Return the HashSet storing all the Entities with their Positions
	 */
	public HashMap<Position, Entity> getEntityMap() {
		return new HashMap<Position, Entity>(entityMap);
	}
	
	/**
	 * Return an HashSet of the Entities contained by the prime object.
	 */
	public HashSet<Entity> getEntities() {
		return new HashSet<Entity>(getEntityMap().values());
	}
	
		public HashSet<Entity> getAllShips(){
		HashSet<Entity> Entities = new HashSet<Entity>(getEntityMap().values());
		HashSet<Entity> allShips = new HashSet<Entity>();
		for (Entity entity : Entities){
			if (entity instanceof Ship)
				allShips.add(entity);
		}
		return allShips;
	}
	
	public HashSet<Entity> getAllBullets(){
		HashSet<Entity> Entities = new HashSet<Entity>(getEntityMap().values());
		HashSet<Entity> allBullets = new HashSet<Entity>();
		for (Entity entity : Entities){
			if (entity instanceof Ship)
				allBullets.add(entity);
		}
		return allBullets;
	}
	
	
	/**
	 * Return a HashSet of all the occupied positions of the prime object.
	 */
	 public HashSet<Position> getPositions() {
		 return new HashSet<Position>(getEntityMap().keySet());
		 
	 }
	
	// ENTITIES - ADDING AND REMOVING (defensively)
	
	/**
	 * Return whether the given Entity is present in the prime object.
	 * @return see implementation...
	 */
	 public boolean contains(Entity entity) {
		 return getEntities().contains(entity);
	 }
	 
	/**
	 * Return whether the given Entity is valid for the prime object.
	 * @param entity
	 * 		  The Entity to be checked.
	 * @return see implementation...
	 * @throws NullPointerException
	 * 		   If the argument references the null pointer.
	 */
	public boolean isValidEntity(Entity entity) 
			throws NullPointerException, IllegalArgumentException {
		if (entity == null)
			throw new NullPointerException("given argument references the null pointer.");
		if (! entity.withinBoundariesOf(this))
			throw new IllegalArgumentException("given argument not inside world boundaries.");
		for(Entity other : getEntities())
			if (entity.overlaps(other))
				throw new IllegalArgumentException("given argument overlaps other entity.");
		if (entity.getWorld() == null || entity.getWorld() == this)
			return true;
		return false;
	}
	
	/**
	 * Add the given Entity to the prime object.
	 * @param entity
	 * 		  The Entity to be added.
	 * @throws IllegalArgumentException
	 * 		   If the given Entity is not valid.
	 */
	public void add(Entity entity) throws NullPointerException, IllegalArgumentException {
		if (isValidEntity(entity)) {
			// add to world
			entityMap.put(entity.getPosition(), entity);
			// set entity world
			entity.setWorld(this);
		} else
			throw new IllegalArgumentException("given parameter is an invalid entity.");
	}
	
	/**
	 * Remove the given entity from the world.
	 * @throws IllegalArgumentException
	 * 		   If the given entity was not located in the world.
	 */
	public void remove(Entity entity) throws IllegalArgumentException {
		if (! contains(entity))
			throw new IllegalArgumentException("given entity is not in world.");
		entityMap.remove(entity.getPosition());
		entity.setWorld(null);
	}
	
	// ENTITIES - SEARCHING (total)
	
	/**
	 * Return the Entity at the given position. If there
	 * is no Entity, the null pointer is returned.
	 * @param position
	 * 		  The position to be checked
	 * @return If there is an Entity at the given position
	 * 		   		then return that Entity.
	 * 		   Otherwise, return the null pointer reference
	 * @post The Entity is within the bounds of the prime object.
	 * 	   | isValidEntity()
	 */
	public Entity getEntityAt(Position position) {
		Entity foundEntity = getEntityMap().get(position);
		return foundEntity;
	}
	
	/**
	 * Return the Entity at the given coordinates. If there
	 * is no Entity, the null pointer is returned.
	 * @param xCoord
	 * 		  The x coordinate to be checked
	 * @param yCoord
	 * 		  The y coordinate to be checked
	 * @return If there is an Entity at the given coordinates
	 * 				then return that Entity
	 * 		   Otherwise, return the null pointer reference
	 * @post The Entity is within the bounds of the prime object.
	 * 	   | isValidEntity()
	 */
	public Entity getEntityAt(double xCoord, double yCoord) {
		return getEntityAt(new Position(xCoord, yCoord));
	}
	
	// 

	/**
	 * Return a string representing the World.
	 * @Return ...
	 * 		 | result == name + hashCode
	 */
	public String toString() {
		String size = "width:"+String.valueOf(getWidth()) 
						+ " height:"+String.valueOf(getHeight());
		return size;
	}

	
	// ADVANCING TIME (defensive)
	
	/**
	 * Time interval of world evolutions.
	 */
	public static final double DELTA_T = 3;
	
	/**
	 * Return the Entity that is going to collide with a wall first.
	 * @return
	 */
	public Entity getFirstWallCollision() {
		
		Double minTime = Double.POSITIVE_INFINITY;
		Entity collisionEntity = null;

		HashSet<Entity> entities = getEntities();
		
		// iterate through every Entity
		Iterator<Entity> iterator = entities.iterator();
		while(iterator.hasNext()) {
			Entity currentEntity = iterator.next();
			double collisionTime = currentEntity.getTimeToWallCollision();
			if (collisionTime < minTime) {
				minTime = collisionTime;
				collisionEntity = currentEntity;
			}
		}
		return collisionEntity;
	}
	
	/**
	 * Return the collision between 2 Entities that will happen first.
	 */
	public Entity[] getFirstEntityCollision() {
		
		Double minTime = Double.POSITIVE_INFINITY;
		Entity[] collisionEntities = {null, null};
		
		HashSet<Entity> entities = getEntities();
		
		// iterate through every Entity
		Iterator<Entity> iterator = entities.iterator();
		while(iterator.hasNext()) {
			Entity currentEntity = iterator.next();
			
			// remove Entity from the set (to avoid double collision checks)
			entities.remove(currentEntity);
			
			// new iterator to check for collisions
			Iterator<Entity> nestedIterator = entities.iterator();
			while(nestedIterator.hasNext()) {
				Entity otherEntity = nestedIterator.next();
				
				double time = currentEntity.getTimeToEntityCollision(otherEntity);
				if (time < minTime) {
					minTime = time;
					collisionEntities[0] = currentEntity;
					collisionEntities[1] = otherEntity;
				}
			}
		}
		return collisionEntities;
	}

	public void advanceTime() {
		
		// 1. Predict the first collision
		Entity[] entityCollision = getFirstEntityCollision();
		double timeToEntityCollision 
					= entityCollision[0].getTimeToEntityCollision(entityCollision[1]);
		
		Entity wallCollision = getFirstWallCollision();
		double timeToWallCollision = wallCollision.getTimeToWallCollision();
		
		if (timeToEntityCollision < World.DELTA_T || timeToWallCollision < World.DELTA_T) {
			// 2.
			// 3.
			// 4.
		}
		// 5.
		
	}
}
