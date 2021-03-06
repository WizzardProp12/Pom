package asteroids.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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
 * @version  0.1
 * 
 * A link to the GitHub code repository:
 * https://github.com/WizzardProp12/Pom
 * 
 * A bound space with x and y coordinates representing a two dimensional world.
 * A world can contain entities, these are added and removed with methods or
 * by entity interactions.
 *
 * @invar ...
 * 		| canHaveAsWidth(getWidth())
 * @invar ...
 * 		| canHaveAsHeight(getHeight())
 * @invar Every entity in the world has valid coordinates
 * 		| for (Entity entity : getEntityList())
 * 		|		isValidPosition(entity.getPosition())
 * @invar Every entity in the world can be contained by the world
 * 		| for (Entity entity : getEntityList())
 * 		|		canContain(entity)
 */
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
	 * @post  The width is valid.
	 * 		| new canHaveAsWidth(new getWidth())
	 * @post  The height is valid.
	 * 		| new canHaveAsHeight(new getHeight())
	 */
	public World(double width, double height){
		if (canHaveAsWidth(width))
			this.width = width;
		else
			this.width = World.getDefaultWidth();
		if (canHaveAsHeight(height))
			this.height = height;
		else
			this.height = World.getDefaultHeight();
	}
	
	/**
	 * Initialise a new world with default height and default width.
	 * @effect ...
	 * 		 | World(World.getDefaultWidth(), World.getDefaultHeight())
	 * @post The width is the default value
	 * 	   | new getWidth() == World.getDefaultWidth()
	 * @post The height is the default value
	 * 	   | new getHeight() == World.getDefaultHeight()
	 */
	public World(){
		this(World.getDefaultWidth(), World.getDefaultHeight());
	}
	
	// SIZE (total)
	
	/**
	 * The minimum width of a world object.
	 */
	static final double MIN_WIDTH = 0;
	
	/**
	 * The maximum width of a world object.
	 */
	static final double MAX_WIDTH = Double.MAX_VALUE;
	
	/**
	 * The minimum height of a world object.
	 */
	static final double MIN_HEIGHT = 0;
	
	/**
	 * The maximum height of a world object.
	 */
	static final double MAX_HEIGHT = Double.MAX_VALUE;
	
	/**
	 * A getter for the minimum width of a world object.
	 */
	@Basic @Immutable @Raw
	static double getMinWidth() {
		return World.MIN_WIDTH;
	}
	
	/**
	 * A getter for the maximum width of a world object.
	 */
	@Basic @Immutable @Raw
	static double getMaxWidth() {
		return World.MAX_WIDTH;
	}
	
	/**
	 * A getter for the minimum height of a world object.
	 */
	@Basic @Immutable @Raw
	static double getMinHeight() {
		return World.MIN_HEIGHT;
	}
	
	/**
	 * A getter for the maximum height of a world object.
	 */
	@Basic @Immutable @Raw
	static double getMaxHeight() {
		return World.MAX_HEIGHT;
	}
	
	
	/**
	 * The default width of a world object.
	 */
	static final double DEFAULT_WIDTH = getMaxWidth();
	
	/**
	 * The default height of a world object.
	 */
	static final double DEFAULT_HEIGHT = getMaxHeight();
	
	/**
	 * A getter for the default width of a world object.
	 */
	@Basic @Immutable @Raw
	static double getDefaultWidth() {
		return World.DEFAULT_WIDTH;
	}
	
	/**
	 * A getter for the default height of a world object.
	 */
	@Basic @Immutable @Raw
	static double getDefaultHeight() {
		return World.DEFAULT_HEIGHT;
	}
	
	
	/**
	 * The width of the world.
	 */
	private final double width;
	
	/**
	 * The height of the world.
	 */
	private final double height;
	
	/**
	 * Return the width of the world.
	 * @invar The width of the world is valid
	 * 		| canHaveAsWidth(getWidth())
	 */
	@Basic @Immutable @Raw
	public double getWidth(){
		return this.width;
	}
	
	/**
	 * Return the height of the world.
	 * @invar The height of the world is valid
	 * 		| canHaveAsHeight(getHeight())
	 */
	@Basic @Immutable @Raw
	public double getHeight(){
		return this.height;
	}
	
	/**
	 * Return whether the given width suits the world.
	 * @param  width
	 * 		   The width to check
	 * @return ...
	 * 		 | World.getMinWidth() <= width <= World.getMaxWidth()
	 */
	@Raw @Model
	public boolean canHaveAsWidth(double width) {
		return ((!Double.isNaN(width)) 
				&& World.getMinWidth() <= width && width <= World.getMaxWidth());
	}
	
	/**
	 * Return whether the given height suits the world.
	 * @param  height
	 * 		   The height to check
	 * @return ...
	 * 		 | World.getMinHeight() <= height <= World.getMaxHeight()
	 */
	@Raw @Model
	public boolean canHaveAsHeight(double height) {
		return ((!Double.isNaN(height)) 
				&& World.getMinHeight() <= height && height <= World.getMaxHeight());
	}
	
	/**
	 * Return the size of the world.
	 * @invar The size of the world is valid
	 * 	    | canHaveAsWidth(result[0]) && canHaveAsHeight(result[1])
	 */
	@Raw @Model @Basic @Immutable
	public double[] getSize() {
		double[] size = {getWidth(), getHeight()};
		return size;
	}
	
	
	// COORDINATE CHECKERS
	
	/**
	 * Return if the given x coordinate is valid and within the world borders.
	 * @param  xCoord
	 * 		   The x-coordinate to check
	 * @return Whether the given coordinate is a number and within boundaries.
	 * 		 | ! Double.isNaN(xCoord) && 0 <= xCoord && xCoord <= getWidth()
	 */
	@Raw
	public boolean isValidXCoord(double xCoord) {
		return ! Double.isNaN(xCoord) && 0 <= xCoord && xCoord <= getWidth();
	}
	
	/**
	 * Return if the given y coordinate is valid and within the world borders.
	 * @param  yCoord
	 * 		   The y-coordinate to check
	 * @return Whether the given coordinate is a number and within boundaries.
	 * 		   Double.isNaN(yCoord) && 0 <= yCoord && yCoord <= getHeight()
	 */
	@Raw
	public boolean isValidYCoord(double yCoord) {
		return ! Double.isNaN(yCoord) && 0 <= yCoord && yCoord <= getHeight();
	}
	
	/** 
	 * Return whether the given position array is within the world borders.
	 * @param xCoord
	 * 		  The x coordinate of the position.
	 * @param yCoord
	 * 		  The y coordinate of the position.
	 * @return Whether the given position is valid.
	 * 		 | isValidXCoord(position[0]) && isValidYCoord(position[1]
	 */
	@Raw
	public boolean isValidPosition(double[] position) {
		return (isValidXCoord(position[0]) && isValidYCoord(position[1]));
	}
	
	/** 
	 * Return whether the given Position is within the bounds of the prime object.
	 * @param position
	 * 		  The position to be checked.
	 * @return ...
	 * 		 | isValidPosition(position.toArray())
	 */
	@Raw
	public boolean isValidPosition(Position position) {
		return isValidPosition(position.toArray());
	}
	
	// ENTITIES - GETTERS
	
	/**
	 * A set storing all the Entities in the world.
	 */
	private Set<Entity> entitySet = new HashSet<Entity>();
	
	/**
	 * Return a HashSet of the Entities contained by the prime object.
	 * @invar All the entities in the result are contained by the world.
	 * 		| for (Entity entity : getEntitySet())
	 * 		|		world.contains(entity)
	 */
	@Basic
	public HashSet<Entity> getEntitySet() {
		return new HashSet<Entity>(entitySet);
	}
	
	/**
	 * Return an ArrayList of the Entities contained by the prime object.
	 * @invar All the entities in the result are contained by the world.
	 * 		| for (Entity entity : getEntityList())
	 * 		|		world.contains(entity)
	 */
	@Basic
	public ArrayList<Entity> getEntityList() {
		return new ArrayList<Entity>(entitySet);
	}
	
	/**
	 * Return a set of the specified subclass of entity.
	 * @param cls
	 * 		  The class type of entities to return (e.g. Ship.class)
	 * @invar All the Entities of the specified class in the result are contained by the world.
	 * 		| for (Entity someEntity : getSomeEntityList())
	 * 		|		world.contains(someEntity)
	 */
	@Basic
	public <T> Set<T> getSomeEntitySet(Class<T> cls){
		Set<T> someEntitySet = new HashSet<>();
		for (Entity entity : getEntitySet()){
			if (cls.isInstance(entity))
				someEntitySet.add(cls.cast(entity));
		}
		return someEntitySet;
	}
	
	// ENTITIES - ADDING AND REMOVING (defensively)
	
	/**
	* Return whether the given Entity is present in the prime object.
	* @param  entity
	* 		  The entity.
	* @return Whether the world contains the given entity.
	* 		| getEntitySet().contains(entity)
	*/
	public boolean contains(Entity entity) {
		 return getEntitySet().contains(entity);
	}
	
	/**
	 * Return whether the prime object can contain the given entity.
	 * @param  entity
	 * 		   The entity.
	 * @return Whether the given entity is within the boundaries of this world.
	 * 		 | entity.isWithinBoundariesOf(this)
	 */
	public boolean canContain(Entity entity) {
		return entity.isWithinBoundariesOf(this);
	}
	
	
	/**
	 * Add the given entity to the prime object.
	 * @param  entity
	 * 		   The entity to be added.
	 * @pre    The given entity must not already be located in this world
	 * 		 | entity.getWorld() != this
	 * @pre    The given entity must be able to change its world.
	 * 		 | entity.canChangeWorld()
	 * @pre    The given entity must be able to be placed in the world
	 * 		 | entity.canBePlacedIn(this)
	 * @post   The world contains the given entity.
	 * 		 | contains(entity)
	 * @post   The entity references the world.
	 * 		 | entity.getWorld() == this
	 * @throws IllegalArgumentException
	 * 		   If the given entity references the null pointer.
	 * @throws IllegalArgumentException
	 * 		   If the given entity is already located in this world.
	 * @throws IllegalArgumentException
	 * 		   If the given entity is still referenced by another world.
	 * @throws IllegalArgumentException
	 * 		   If the given entity can't be placed in the world.
	 */
	public void add(Entity entity) throws NullPointerException, IllegalArgumentException {
		
		if (entity == null) throw new NullPointerException(
				"cannot add nullpointer to world");
		if (entity.getWorld() == this) throw new IllegalArgumentException(
				"entity is already located in this world");
		if (! entity.canChangeWorld()) throw new IllegalArgumentException(
				"entity is still referenced by another world");
		if (! entity.canBePlacedIn(this)) throw new IllegalArgumentException(
				"entity can't be placed in this world");
		
		entitySet.add(entity);
		entity.setWorld(this);
	}
	
	/**
	 * Remove the given entity from the world.
	 * @param  entity
	 * 		   The entity to be removed from the world.
	 * @pre    The world must contain the given entity
	 * 		 | contains(entity)
	 * @post   The world doesn't contain the given entity
	 * 		 | ! contains(entity)
	 * @post   The given entity has the null pointer as world reference
	 * 		 | entity.getWorld() == null
	 * @throws IllegalArgumentException
	 * 		   If the given entity is not located in the world.
	 * 		 | ! contains(entity)
	 */
	@Raw
	public void remove(Entity entity) throws IllegalArgumentException {
		if (! contains(entity)) throw new IllegalArgumentException(
				"given entity is not in this world.");
		entitySet.remove(entity);
		entity.setWorld(null);
	}
	
	// ENTITIES - SEARCHING (total)
	
	/**
	 * Return the Entity at the given position. If there
	 * is no Entity, the null pointer is returned.
	 * @param  position
	 * 		   The position to be checked.
	 * @return The entity at the given position or if there is none, the null pointer.
	 * 		 | result == null || result.getPosition() == position
	 */
	@Basic
	public Entity getEntityAt(Position position) {
		for (Entity entity : getEntityList()) {
			if (entity.getPosition().equals(position))
				return entity;
		}
		return null;
	}
	
	/**
	 * Return the Entity at the given coordinates. If there
	 * is no Entity, the null pointer is returned.
	 * @param xCoord
	 * 		  The x coordinate to be checked
	 * @param yCoord
	 * 		  The y coordinate to be checked
	 * @return The entity at the given coordinates or if there is none, the null pointer.
	 * 		| (result == null || result.getPosition() == new Position(xCoord, yCoord))
	 */
	@Basic
	public Entity getEntityAt(double xCoord, double yCoord) {
		if (!isValidXCoord(xCoord) || !isValidYCoord(yCoord))
			return null;
		return getEntityAt(new Position(xCoord, yCoord));
	}
	
	// ENTITIES - COLLISIONS (defensive)
	
	/**
	 * Return the entity collision that is going to occur first
	 * @return The first entity collision that occurs
	 * 		 | result == null 
	 * 		 |   || (result.getCollisionType() == entity && result.getOtherEntity() != null)
	 */
	public Collision getFirstEntityCollision() {
		Collision firstCollision = null;
		
		ArrayList<Entity> entities = getEntityList();
		Iterator<Entity> iterator = entities.iterator();
		while(iterator.hasNext()) {
			Entity currentEntity = iterator.next();
			iterator.remove(); // remove Entity from the set (to avoid double collision checks)
			Collision currentCollision = currentEntity.getFirstCollision(entities);
			if (firstCollision == null || 
					(currentCollision != null && firstCollision.getTime() > currentCollision.getTime()))
				firstCollision = currentCollision;
		}
		return firstCollision;
	}
	
	/**
	 * Return the border collision that is going to occur first.
	 * @return The first border collision that occurs
	 *       | result == null
	 *       |   || result.getCollisionType() == leftWall
	 * 		 | 	 || result.getCollisionType() == rightWall
	 * 		 |   || result.getCollisionType() == topWall
	 * 		 |   || result.getCollisionType() == bottomWall
	 */
	public Collision getFirstBorderCollision() {
		Collision firstCollision = null;
		for(Entity entity : getEntityList()) {
			Collision currentCollision = entity.getBorderCollision(this);
			if (firstCollision == null || firstCollision.getTime() > currentCollision.getTime())
				firstCollision = currentCollision;
		}
		return firstCollision;
	}
	
	/**
	 * Return the collision that is going to occur first
	 * @return The first collision that is going to occur
	 * 		 | result == getFirstEntityCollision() || result == getFirstWallCollision()
	 */
	public Collision getFirstCollision() {
		Collision entityCollision = getFirstEntityCollision();
		Collision wallCollision = getFirstBorderCollision();
		if (entityCollision == null) return wallCollision;
		if (wallCollision == null) return entityCollision;
		if (entityCollision.getTime() < wallCollision.getTime())
			return entityCollision;
		else return wallCollision;
	}
	
	
	// ADVANCING TIME (defensive)
	
	/**
	 * Time interval of world evolutions.
	 */
	public final double DELTA_T = 0.01;
	
	/**
	 * Return the time interval of world evolutions.
	 */
	@Basic @Immutable @Raw
	public double getDeltaT() {
		return DELTA_T;
	}
	
	/**
	 * Advance the time.
	 */
	public void advanceTime(double time) {
		
		
		Collision firstCollision = getFirstCollision();
		if (firstCollision != null && firstCollision.getTime() <= time) {
			
			// Advance all entities up to the point of the first collision
			for(Entity entity : getEntityList())
				entity.move(firstCollision.getTime());
			
			// Resolve the collision
			firstCollision.resolve();
			
			// Update velocities
			for(Entity ship : getSomeEntitySet(Ship.class))
				((Ship) ship).thrust(firstCollision.getTime());
			
			// Advance time for the remaining time
			advanceTime(time - firstCollision.getTime());
			
		} else {
			// Advance all entities
			for(Entity entity : getEntityList())
				entity.move(time);
			
			// Update velocities
			for(Entity ship : getSomeEntitySet(Ship.class))
				((Ship) ship).thrust(time);
		}
	}
	
	/**
	 * Advance the time getDeltaT seconds.
	 */
	public void advanceTime() {
		advanceTime(getDeltaT());
	}
	
	
	// TERMINATION
	
	/** 
	 * A private variable storing whether the world is terminated.
	 */
	private boolean isTerminated = false;
	
	/**
	 * Returns whether the world is terminated.
	 */
	@Basic
	public boolean isTerminated() {
		return isTerminated;
	}
	
	/**
	 * Terminate the world by removing all it's entities.
	 */
	public void terminate() {
		for (Entity entity : getEntityList()) {
			remove(entity);
		}
		isTerminated = true;
	}
}
