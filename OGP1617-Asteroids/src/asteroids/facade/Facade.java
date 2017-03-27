package asteroids.facade;

import asteroids.model.entities.Ship;
import asteroids.part1.facade.IFacade;
import asteroids.util.ModelException;

public class Facade implements IFacade, asteroids.part2.facade.IFacade {

	@Override
	public Ship createShip() throws ModelException {
		return createShip(0,0,0,0,Ship.MIN_RADIUS,Math.PI/2);
		
	}

	@Override
	public Ship createShip(double x, double y, double xVelocity, double yVelocity, double radius, double orientation)
			throws ModelException {
		Ship ship = new Ship();
		ship.setXCoord(x);
		ship.setYCoord(y);
		ship.setXVelocity(xVelocity);
		ship.setYVelocity(yVelocity);
		ship.setRadius(radius);
		ship.setOrientation(orientation);
		return ship;
	}

	@Override
	public double[] getShipPosition(Ship ship) throws ModelException {
		return ship.getPositions();
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
		ship.move(dt);
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
		return ship1.getDistanceBetween(ship2);
	}

	@Override
	public boolean overlap(Ship ship1, Ship ship2) throws ModelException {
		return ship1.overlap(ship2);
	}

	@Override
	public double getTimeToCollision(Ship ship1, Ship ship2) throws ModelException {
		return ship1.getTimeToCollision(ship2);
	}

	@Override
	public double[] getCollisionPosition(Ship ship1, Ship ship2) throws ModelException {
		return ship1.getCollisionPosition(ship2);
	}

}
