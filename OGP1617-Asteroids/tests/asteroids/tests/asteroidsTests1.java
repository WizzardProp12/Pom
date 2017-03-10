package asteroids.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asteroids.model.Ship;
import asteroids.model.Ship;

public class asteroidsTests1 {
	private static final double EPSILON = 0.0001;
	
	@Before
	public void setUp() {
		Ship ship = new Ship();
	}
	
	@Test
	public void testPosition() throws IllegalArgumentException {
		Ship ship = new Ship();
		ship.setXCoord(-20);
		ship.setYCoord(30);
			
		double positions[] = ship.getPositions();

		assertEquals(-20, positions[0], EPSILON);
		assertEquals(30, positions[1], EPSILON);
	
	}
	
	@Test
	public void testVelocity(){
		Ship ship = new Ship();
		ship.setXVelocity(-10);
		ship.setYVelocity(30);
		
		double velocities[] = ship.getVelocities();
		
		assertEquals(-10, velocities[0], EPSILON);
		assertEquals(30, velocities[1], EPSILON);
		
	}
	
	@Test
	public void testVelocity2(){
		Ship ship = new Ship();
		ship.setXVelocity(Ship.SPEED_LIMIT);
		ship.setYVelocity(0);
		ship.thrust(10);
		double velocities[] = ship.getVelocities();
		
		assertEquals(Ship.SPEED_LIMIT, velocities[0], EPSILON);
		assertEquals(0, velocities[1], EPSILON);
		
	}
	
	@Test
	public void testSpeed() {
		Ship ship = new Ship();
		ship.setOrientation(0);

		ship.thrust(-100);
		assertEquals(0, ship.getSpeed(), EPSILON);
		
		ship.thrust(50);
		assertEquals(50, ship.getSpeed(), EPSILON);
		
		ship.thrust(Ship.SPEED_LIMIT * 2);
		assertEquals(Ship.SPEED_LIMIT, ship.getSpeed(), EPSILON);
	}
	
	@Test
	public void testOrientation() {
		Ship ship = new Ship();
		ship.setOrientation(0);
		
		ship.turn(1);
		assertEquals(ship.getOrientation(), 1, EPSILON);
		
		ship.turn(4);
		assertEquals(ship.getOrientation(), 5, EPSILON);
		
		ship.turn(-5);
		assertEquals(ship.getOrientation(), 0, EPSILON);
	}
	
	@Test
	public void testRadius() {
		assert ! Ship.isValidRadius(-5);
		assert ! Ship.isValidRadius(-5);
		assert ! Ship.isValidRadius(0);
		assert ! Ship.isValidRadius(9.99999);
		assert Ship.isValidRadius(10);
		assert Ship.isValidRadius(100);
		assert Ship.isValidRadius(1000);
	}
	
	@Test
	public void testTime() {
		assert ! Ship.isValidTime(-5);
		assert Ship.isValidTime(0);
		assert Ship.isValidTime(5);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMove() throws IllegalArgumentException{
		Ship ship = new Ship();
		System.out.println(ship.getXCoord());
		ship.thrust(1);
		ship.move(1);
		assertEquals(ship.getXCoord(), 1, EPSILON);
		assertEquals(ship.getYCoord(), 0, EPSILON);
		
		ship.move(-1); // throws an IllegalArgumentException
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetDistanceBetween() throws IllegalArgumentException{
		Ship ship1 = new Ship(0,0,0,0,5,0);
		Ship ship2 = new Ship(0,20,0,0,5,0);
		Ship ship3 = new Ship(10,0,0,0,2,0);
		assertEquals(ship1.getDistanceBetween(ship2), 10, EPSILON);
		assertEquals(ship2.getDistanceBetween(ship1), 10, EPSILON);
		assertEquals(ship3.getDistanceBetween(ship1), 4, EPSILON);
		ship2.getDistanceBetween(null); // throws an IllegalArgumentException
	
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testOverlap() throws IllegalArgumentException{
		Ship ship1 = new Ship(0,0,0,0,5,0);
		Ship ship2 = new Ship(0,20,0,0,5,0);
		Ship ship3 = new Ship(10,0,0,0,50,0);
									
		assertTrue(ship1.overlap(ship3));
		assertTrue(ship2.overlap(ship3));
		assertFalse(ship2.overlap(ship1));
		
		ship3.overlap(null); // throws an IllegalArgumentException
	
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetTimeToCollision() throws IllegalArgumentException{
		Ship ship1 = new Ship(0,0,0,0,5,0);
		System.out.println(ship1.getXCoord());
		Ship ship2 = new Ship(0,20,-1,0,5,0);
		Ship ship3 = new Ship(10,0,0,-0.5,5,0);
		
		
		assertEquals(ship1.getTimeToCollision(ship2), 10, EPSILON);
		assertEquals(ship2.getTimeToCollision(ship3), 10, EPSILON);
		assertEquals(ship3.getTimeToCollision(ship1), 10, EPSILON);
									
		
		
		ship3.getTimeToCollision(null); // throws an IllegalArgumentException
	
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetCollisionPosition() throws IllegalArgumentException{
		Ship ship1 = new Ship(0,0,0,0,5,0);
		System.out.println(ship1.getXCoord());
		Ship ship2 = new Ship(0,20,-1,0,5,0);

		
		assertEquals(ship1.getCollisionPosition(ship2)[0], 10, EPSILON);
		assertEquals(ship2.getCollisionPosition(ship1)[1], 0, EPSILON);
									
		
		
		ship2.getTimeToCollision(null); // throws an IllegalArgumentException
	}

	
}
