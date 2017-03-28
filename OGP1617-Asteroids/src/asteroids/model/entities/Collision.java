package asteroids.model.entities;

public class Collision {
	
	// CONSTRUCTORS
	
	/**
	 * Initialise a new Collision with the given type, time, entity and other.
	 * @param type
	 * 		  The type of collision.
	 * @param time
	 * 		  The amount of time before the collision will occur.
	 * @param entity
	 * 		  The entity that collides with the other entity or wall.
	 * @param other
	 * 		  If the entity collied with another entity, this is that entity.
	 * @pre   The given time must be valid.
	 * 		| isValidTime(time)
	 * @pre   The given other entity can only be null if the the collisionType is not CollisionType.Entity
	 * 		| if (type == CollisionType.Entity)
	 * 		|		then other != null
	 * @post  ...
	 * 		| new getCollisionType() == type
	 * @post  ...
	 * 		| new getTime() == time
	 * @post  ...
	 * 		| new getEntity() == entity
	 * @post  ...
	 * 		| new getOtherEntity() == other
	 */
	public Collision(CollisionType type, double time, Entity entity, Entity other) {
		if (type == CollisionType.entity || other == null)
			throw new IllegalArgumentException("other entity cannot reference null if the collision is of the Entity type.");
		setCollisionType(type);
		setTime(time);
		setEntity(entity);
		if (type != CollisionType.entity)
			setOtherEntity(null);
		else
			setOtherEntity(other);
	}
	
	/**
	 * Initialise a new Collision with the given type, time and entity.
	 * @param type
	 * 		  The type of collision.
	 * @param time
	 * 		  The amount of time before the collision will occur.
	 * @param entity
	 * 		  The entity that collides with the other entity or wall.
	 * @pre   The given time must be valid.
	 * 		| isValidTime(time)
	 * @post  ...
	 * 		| new getCollisionType() == type
	 * @post  ...
	 * 		| new getTime() == time
	 * @post  ...
	 * 		| new getEntity() == entity
	 * @post  ...
	 * 		| new getOtherEntity() == null
	 */
	public Collision(CollisionType type, double time, Entity entity) {
		this(type, time, entity, null);
	}
	
	
	// COLLISIONTYPE
	
	/**
	 * The CollisionType of the Collision.
	 */
	private CollisionType collisionType;
	
	/**
	 * Return the CollisionType of the Collision.
	 * @return Entity, TopWall, BottomWall, LeftWall or RightWall
	 */
	public CollisionType getCollisionType() {
		return collisionType;
	}
	
	/**
	 * Set the CollisionType of the Collision.
	 */
	private void setCollisionType(CollisionType type) {
		collisionType = type;
	}
	
	
	// TIME
	
	/**
	 * The amount of time left before the collision occurs.
	 */
	private double time;
	
	/**
	 * Get the amount of time left before the collision.
	 * @return see implementation...
	 */
	public double getTime() {
		return time;
	}
	
	/**
	 * Return whether the time left before the collision is valid.
	 * @return see implementation...
	 */
	public boolean isValidTime(double time) {
		return (time >= 0);
	}
	
	/**
	 * Set the amount of time left before the collision.
	 */
	private void setTime(double time) throws IllegalArgumentException {
		if (! isValidTime(time))
			throw new IllegalArgumentException("illegal argument (time), must be positive.");
		else
			this.time = time;
	}
	
	
	// MAIN ENTITY
	
	/**
	 * The main entity of the collision
	 */
	private Entity entity;
	
	/**
	 * Return the main entity of the collision.
	 */
	public Entity getEntity() {
		return entity;
	}
	
	/**
	 * Set the main entity of the collision.
	 */
	private void setEntity(Entity entity) {
		this.entity = entity;
	}
	
	
	// OTHER ENTITY
	
	/**
	 * The other entity of the collision
	 */
	private Entity other;
	
	/**
	 * Return the other entity of the collision, if one exists.
	 * @return The other entity
	 * 		 | if collisionType = Entity
	 * 		 | 		return other
	 * 		 | else
	 * 		 |		return null
	 */
	public Entity getOtherEntity() {
		return other;
	}
	
	/**
	 * Set the other entity of the collision.
	 */
	private void setOtherEntity(Entity other) {
		this.other = other;
	}

	
	// RESOLVE COLLISION
	
	public void resolve() {
		
		// Entity - Entity collision
		if (getCollisionType() == CollisionType.entity) {
			if (getEntity() instanceof Ship)
				((Ship) getEntity()).collide(getOtherEntity());
			else if (getEntity() instanceof Bullet)
				((Bullet) getEntity()).collide(getOtherEntity());
		// Entity - Wall collision
		} else {
			getEntity().wallBounce(getCollisionType());
		}
		
	}

	
	
	
}
