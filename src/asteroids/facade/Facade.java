package asteroids.facade;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import asteroids.model.*;
import asteroids.model.program.Program;
import asteroids.model.program.ProgramFactory;
import asteroids.part2.CollisionListener;
import asteroids.part3.programs.IProgramFactory;
import asteroids.util.ModelException;

public class Facade implements asteroids.part3.facade.IFacade{

	@Override
	public Ship createShip() throws ModelException {
		try {
			return new Ship();
		} catch (IllegalArgumentException e) {
			throw new ModelException(e.getMessage());
		}
	}

	@Override
	public double[] getShipPosition(Ship ship) throws ModelException {
		return ship.getPosition().toArray();
	}

	@Override
	public double[] getShipVelocity(Ship ship) throws ModelException {
		return ship.getVelocities();
	}

	@Override
	public double getShipRadius(Ship ship) throws ModelException {
		return ship.getRadius();
	}

	@Override
	public double getShipOrientation(Ship ship) throws ModelException {
		return ship.getOrientation();
	}

	@Override
	public void move(Ship ship, double dt) throws ModelException {
		try {
			ship.move(dt);
		} catch (IllegalArgumentException e) {
			throw new ModelException(e.getMessage());
		}
	}

	@Override
	public void thrust(Ship ship, double amount) throws ModelException {
		ship.thrust(amount);
	}

	@Override
	public void turn(Ship ship, double angle) throws ModelException {
		ship.turn(angle);
	}

	@Override
	public double getDistanceBetween(Ship ship1, Ship ship2) throws ModelException {
		try {
			return ship1.getDistanceBetween(ship2);
		} catch (NullPointerException e) {
			throw new ModelException(e.getMessage());
		}
	}

	@Override
	public boolean overlap(Ship ship1, Ship ship2) throws ModelException {
		try {
			return ship1.overlaps(ship2);
		} catch (NullPointerException e) {
			throw new ModelException(e.getMessage());
		}
	}

	@Override
	public double getTimeToCollision(Ship ship1, Ship ship2) throws ModelException {
		try {
			return ship1.getTimeToCollision(ship2);
		} catch (NullPointerException e) {
			throw new ModelException(e.getMessage());
		}
	}

	@Override
	public double[] getCollisionPosition(Ship ship1, Ship ship2) throws ModelException {
		try {
			return ship1.getCollisionPosition(ship2).toArray();
		} catch (NullPointerException e) {
			throw new ModelException(e.getMessage());
		}
	}

	@Override
	public Ship createShip(double x, double y, double xVelocity, double yVelocity, 
			double radius, double direction,
			double mass) throws ModelException {
		try {
			return new Ship(x, y, xVelocity, yVelocity, radius, direction, mass);
		} catch (IllegalArgumentException e) {
			throw new ModelException(e.getMessage());
		}
	}


	public void terminateShip(Ship ship) throws ModelException {
		ship.terminate();
	}

	@Override
	public boolean isTerminatedShip(Ship ship) throws ModelException {
		return ship.isTerminated();
	}

	@Override
	public double getShipMass(Ship ship) throws ModelException {
		return ship.getMass();
	}

	@Override
	public World getShipWorld(Ship ship) throws ModelException {
		return ship.getWorld();
	}

	@Override
	public boolean isShipThrusterActive(Ship ship) throws ModelException {
		return ship.getThrusterState();
	}

	@Override
	public void setThrusterActive(Ship ship, boolean active) throws ModelException {
		ship.setThrusterState(active);
	}

	@Override
	public double getShipAcceleration(Ship ship) throws ModelException {
		return ship.getAcceleration();
	}

	@Override
	public Bullet createBullet(double x, double y, double xVelocity, double yVelocity, double radius)
			throws ModelException {
		try {
			return new Bullet(x, y, xVelocity, yVelocity, radius);
		} catch (IllegalArgumentException e) {
			throw new ModelException(e.getMessage());
		}
	}

	@Override
	public void terminateBullet(Bullet bullet) throws ModelException {
		bullet.terminate();
	}

	@Override
	public boolean isTerminatedBullet(Bullet bullet) throws ModelException {
		return bullet.isTerminated();
	}

	@Override
	public double[] getBulletPosition(Bullet bullet) throws ModelException {
		return bullet.getPosition().toArray();
	}

	@Override
	public double[] getBulletVelocity(Bullet bullet) throws ModelException {
		return bullet.getVelocities();
	}

	@Override
	public double getBulletRadius(Bullet bullet) throws ModelException {
		return bullet.getRadius();
	}

	@Override
	public double getBulletMass(Bullet bullet) throws ModelException {
		return bullet.getMass();
	}

	@Override
	public World getBulletWorld(Bullet bullet) throws ModelException {
		return bullet.getWorld();
	}

	@Override
	public Ship getBulletShip(Bullet bullet) throws ModelException {
		return bullet.getShip();
	}

	@Override
	public Ship getBulletSource(Bullet bullet) throws ModelException {
		return bullet.getSourceShip();
	}

	@Override
	public World createWorld(double width, double height) throws ModelException {
		return new World(width, height);
	}

	@Override
	public void terminateWorld(World world) throws ModelException {
		world.terminate();
	}

	@Override
	public boolean isTerminatedWorld(World world) throws ModelException {
		return world.isTerminated();
	}

	@Override
	public double[] getWorldSize(World world) throws ModelException {
		return world.getSize();
	}

	@Override
	public Set<? extends Ship> getWorldShips(World world) throws ModelException {
		return world.getSomeEntitySet(Ship.class);
	}

	@Override
	public Set<? extends Bullet> getWorldBullets(World world) throws ModelException {
		return world.getSomeEntitySet(Bullet.class);
	}

	@Override
	public void addShipToWorld(World world, Ship ship) throws ModelException {
		try {
			world.add(ship);
		} catch (NullPointerException e) {
			throw new ModelException(e.getMessage());
		} catch (IllegalArgumentException e) {
			throw new ModelException(e.getMessage());
		}
	}

	@Override
	public void removeShipFromWorld(World world, Ship ship) throws ModelException {
		try {
			world.remove(ship);
		} catch (IllegalArgumentException e) {
			throw new ModelException(e.getMessage());
		}
	}

	@Override
	public void addBulletToWorld(World world, Bullet bullet) throws ModelException {
		try {
			world.add(bullet);
		} catch (NullPointerException e) {
			throw new ModelException(e.getMessage());
		} catch (IllegalArgumentException e) {
			throw new ModelException(e.getMessage());
		}
	}

	@Override
	public void removeBulletFromWorld(World world, Bullet bullet) throws ModelException {
		try {
			world.remove(bullet);
		} catch (IllegalArgumentException e) {
			throw new ModelException(e.getMessage());
		}
	}

	@Override
	public Set<? extends Bullet> getBulletsOnShip(Ship ship) throws ModelException {
		return ship.getBulletSet();
	}

	@Override
	public int getNbBulletsOnShip(Ship ship) throws ModelException {
		return ship.getNbBullets();
	}

	@Override
	public void loadBulletOnShip(Ship ship, Bullet bullet) throws ModelException {
		try {
			ship.load(bullet);
		} catch (IllegalArgumentException e) {
			throw new ModelException(e.getMessage());
		}
	}

	@Override
	public void loadBulletsOnShip(Ship ship, Collection<Bullet> bullets) throws ModelException {
		try {
			ship.load(bullets);
		} catch (IllegalArgumentException e) {
			throw new ModelException(e.getMessage());
		}
	}

	@Override
	public void removeBulletFromShip(Ship ship, Bullet bullet) throws ModelException {
		try {
			ship.removeBullet(bullet);
		} catch (IllegalArgumentException e) {
			throw new ModelException(e.getMessage());
		}
	}

	@Override
	public void fireBullet(Ship ship) throws ModelException {
		ship.fireBullet();
	}

	@Override
	public double getTimeCollisionBoundary(Object object) throws ModelException {
		try {
			if (object instanceof Entity)
				return ((Entity) object).getTimeToCollision(((Entity) object).getWorld());
			else
				throw new ModelException("argument must be an Entity subclass");
		} catch (NullPointerException e) {
			throw new ModelException(e.getMessage()); 
		}
	}

	@Override
	public double[] getPositionCollisionBoundary(Object object) throws ModelException {
		try {
			if (object instanceof Entity)
				return ((Entity) object).getCollisionPosition(((Entity) object).getWorld()).toArray();
			else
				throw new ModelException("argument must be an Entity subclass");
		} catch (NullPointerException e) {
			throw new ModelException(e.getMessage()); 
		}
	}

	@Override
	public double getTimeCollisionEntity(Object entity1, Object entity2) throws ModelException {
		try {
			if ((entity1 instanceof Entity) && (entity2 instanceof Entity))
				return ((Entity) entity1).getTimeToCollision((Entity) entity2);
			else
				throw new ModelException("argument must be an Entity subclass");
		} catch (NullPointerException e) {
			throw new ModelException(e.getMessage()); 
		}
	}

	@Override
	public double[] getPositionCollisionEntity(Object entity1, Object entity2) throws ModelException {
		try {
			if ((entity1 instanceof Entity) && (entity2 instanceof Entity))
				return ((Entity) entity1).getCollisionPositionArray((Entity) entity2);
			else
				throw new ModelException("argument must be an Entity subclass");
		} catch (NullPointerException e) {
			throw new ModelException(e.getMessage()); 
		}
	}

	@Override
	public double getTimeNextCollision(World world) throws ModelException {
		return world.getFirstCollision().getTime();
	}

	@Override
	public double[] getPositionNextCollision(World world) throws ModelException {
		return world.getFirstCollision().getPosition().toArray();
	}

	@Override
	public void evolve(World world, double dt, CollisionListener collisionListener) throws ModelException {
		try {
			world.advanceTime(dt);
		} catch (IllegalArgumentException e) {
			throw new ModelException(e.getMessage());
		}
		
	}

	@Override
	public Object getEntityAt(World world, double x, double y) throws ModelException {
		return world.getEntityAt(x, y);
	}

	@Override
	public Set<? extends Object> getEntities(World world) throws ModelException {
		return world.getEntitySet();
	}

	@Override
	public int getNbStudentsInTeam() {
		return 2;
	}

	@Override
	public Set<? extends Asteroid> getWorldAsteroids(World world) throws ModelException {
		return (Set<? extends Asteroid>) world.getSomeEntitySet(Asteroid.class);
	}

	@Override
	public void addAsteroidToWorld(World world, Asteroid asteroid) throws ModelException {
		try {
			world.add(asteroid);
		} catch (NullPointerException e) {
			throw new ModelException(e.getMessage());
		} catch (IllegalArgumentException e) {
			throw new ModelException(e.getMessage());
		}
	}

	@Override
	public void removeAsteroidFromWorld(World world, Asteroid asteroid) throws ModelException {
		try {
			world.remove(asteroid);
		} catch (IllegalArgumentException e) {
			throw new ModelException(e.getMessage());
		}
	}

	@Override
	public Set<? extends Planetoid> getWorldPlanetoids(World world) throws ModelException {
		return (Set<? extends Planetoid>) world.getSomeEntitySet(Planetoid.class);

	}

	@Override
	public void addPlanetoidToWorld(World world, Planetoid planetoid) throws ModelException {
		try {
			world.add(planetoid);
		} catch (NullPointerException e) {
			throw new ModelException(e.getMessage());
		} catch (IllegalArgumentException e) {
			throw new ModelException(e.getMessage());
		}
	}

	@Override
	public void removePlanetoidFromWorld(World world, Planetoid planetoid) throws ModelException {
		try {
			world.remove(planetoid);
		} catch (IllegalArgumentException e) {
			throw new ModelException(e.getMessage());
		}
	}

	@Override
	public Asteroid createAsteroid(double x, double y, double xVelocity, double yVelocity, double radius) throws ModelException{
		try {
			return new Asteroid(x, y, xVelocity, yVelocity, radius);
		} catch (IllegalArgumentException e) {
			throw new ModelException(e.getMessage());
		}
	}

	@Override
	public void terminateAsteroid(Asteroid asteroid) throws ModelException {
		asteroid.terminate();
	}

	@Override
	public boolean isTerminatedAsteroid(Asteroid asteroid) throws ModelException {
		return asteroid.isTerminated();
	}

	@Override
	public double[] getAsteroidPosition(Asteroid asteroid) throws ModelException {
		return asteroid.getPosition().toArray();
	}

	@Override
	public double[] getAsteroidVelocity(Asteroid asteroid) throws ModelException {
		return asteroid.getVelocities();
	}

	@Override
	public double getAsteroidRadius(Asteroid asteroid) throws ModelException {
		return asteroid.getRadius();
	}

	@Override
	public double getAsteroidMass(Asteroid asteroid) throws ModelException {
		return asteroid.getMass();
	}

	@Override
	public World getAsteroidWorld(Asteroid asteroid) throws ModelException {
		return asteroid.getWorld();
	}

	@Override
	public Planetoid createPlanetoid(double x, double y, double xVelocity, double yVelocity, double radius,
			double totalTraveledDistance) throws ModelException {
		try {
			return new Planetoid(x, y, xVelocity, yVelocity, radius, totalTraveledDistance);
		} catch (IllegalArgumentException e) {
			throw new ModelException(e.getMessage());
		}
	}

	@Override
	public void terminatePlanetoid(Planetoid planetoid) throws ModelException {
		planetoid.terminate();
	}

	@Override
	public boolean isTerminatedPlanetoid(Planetoid planetoid) throws ModelException {
		return planetoid.isTerminated();
	}

	@Override
	public double[] getPlanetoidPosition(Planetoid planetoid) throws ModelException {
		return planetoid.getPosition().toArray();
	}

	@Override
	public double[] getPlanetoidVelocity(Planetoid planetoid) throws ModelException {
		return planetoid.getVelocities();
		}

	@Override
	public double getPlanetoidRadius(Planetoid planetoid) throws ModelException {
		return planetoid.getRadius();
		}

	@Override
	public double getPlanetoidMass(Planetoid planetoid) throws ModelException {
		return planetoid.getMass();
	}

	@Override
	public double getPlanetoidTotalTraveledDistance(Planetoid planetoid) throws ModelException {
		return planetoid.getTotalTraveledDistance();
	}
	
	@Override
	public World getPlanetoidWorld(Planetoid planetoid) throws ModelException {
		return planetoid.getWorld();
	}

	@Override
	public Program getShipProgram(Ship ship) throws ModelException {
		return ship.getProgram();
	}

	@Override
	public void loadProgramOnShip(Ship ship, Program program) throws ModelException {
		ship.setProgram(program);
	}

	@Override
	public List<Object> executeProgram(Ship ship, double dt) throws ModelException {
		return ship.getProgram().execute(dt);
	}

	@Override
	public IProgramFactory<?, ?, ?, ? extends Program> createProgramFactory() throws ModelException {
		return new ProgramFactory();
	}
}

