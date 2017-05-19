package asteroids.model;

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
 * A class of collision objects 
 * A collision object stores the involved entities,
 * the collision type and the time until the collision.
 * @invar isValidtime(time)
 * @invar getEntity() != null
 */
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
	 * 		  The other entity involved in the collision, this can be the null pointer
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
		if (type == CollisionType.entity && other == null)
			throw new IllegalArgumentException("other entity cannot reference null if the collision is of the Entity type.");
		this.collisionType = type;
		this.time = time;
		this.entity = entity;
		this.other = other;
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
	private final CollisionType collisionType;
	
	/**
	 * Return the CollisionType of the Collision.
	 * @return entity, horizontalWall or verticalWall
	 */
	@Basic @Raw @Immutable
	public CollisionType getCollisionType() {
		return collisionType;
	}
	
	// TIME
	
	/**
	 * The amount of time left before the collision occurs.
	 */
	private final double time;
	
	/**
	 * Get the amount of time left before the collision.
	 * @invar The time is valid
	 * 	    | isValidTime(getTime())
	 */
	@Basic @Raw @Immutable
	public double getTime() {
		return time;
	}
	
	// MAIN ENTITY
	
	/**
	 * The main entity of the collision
	 */
	private final Entity entity;
	
	/**
	 * Return the main entity of the collision.
	 */
	@Basic @Raw @Immutable
	public Entity getEntity() {
		return entity;
	}
	
	
	// OTHER ENTITY
	
	/**
	 * The other entity of the collision
	 */
	private final Entity other;
	
	/**
	 * Return the other entity of the collision, if one exists.
	 */
	@Basic @Raw @Immutable
	public Entity getOther() {
		return other;
	}

	
	// COLLISION POSITION
	
	/**
	 * Return the position of the collision
	 * @return see implementation...
	 */
	public Position getPosition() {
		if (getCollisionType() == CollisionType.entity)
			return getEntity().getCollisionPosition(getOther());
		else
			return getEntity().getCollisionPosition(getEntity().getWorld());
	}
	
	
	// RESOLVE COLLISION
	
	/**
	 * Resolve the collision by examining all possible situations.
	 */
	public void resolve() {
		// Entity - Wall
		if (getCollisionType() == CollisionType.horizontalWall) {
			getEntity().bounce(CollisionType.horizontalWall);
			return;
		} else if (getCollisionType() == CollisionType.verticalWall) {
			getEntity().bounce(CollisionType.verticalWall);
			return;
		}
		
		// Ship - Entity
		if (getEntity() instanceof Ship || getOther() instanceof Ship) {
			Ship ship = getEntity() instanceof Ship ? (Ship) getEntity()
													: (Ship) getOther();
			Entity other = getEntity() instanceof Ship ? getOther()
														 : getEntity();
			if (ship instanceof Ship && other instanceof Bullet)
			// Ship - Bullet fired by Ship
			if (other instanceof Bullet && ((Bullet) other).getSourceShip() == ship) {
				ship.load((Bullet) other);
				return;
			// Ship - Asteroid
			} else if (other instanceof Asteroid) {
				ship.die();
				return;
			// Ship - Planetoid
			} else if (other instanceof Planetoid) {
				ship.teleport();
				return;
			}
		// Bullet - Entity
		} 
		if (getEntity() instanceof Bullet || getOther() instanceof Bullet) {
			getEntity().die();
			getOther().die();
			return;
		}
		
		getEntity().bounce(getOther());
	}
}
