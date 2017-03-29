package asteroids.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asteroids.model.entities.Position;
import asteroids.model.entities.Ship;

public class asteroidsTests1 {
	private static final double EPSILON = 0.0001;
	
//------------SHIP TESTS-----------------
	
	@Before
	public void setUp() {
		Ship ship = new Ship();
	}
	
	@Test
	public void testPosition() throws IllegalArgumentException {
		Ship ship = new Ship();
		ship.setXCoord(-20);
		ship.setYCoord(30);

		assertEquals(-20, ship.getXCoord(), EPSILON);
		assertEquals(30, ship.getYCoord(), EPSILON);
	
	}
	
	@Test
	public void testVelocity(){
		Ship ship = new Ship();
		ship.setXVelocity(-10);
		ship.setYVelocity(30);
		
		
		assertEquals(-10, ship.getXVelocity(), EPSILON);
		assertEquals(30, ship.getYVelocity(), EPSILON);
		
	}
	
	@Test
	public void testVelocity2(){
		Ship ship = new Ship();
		ship.setXVelocity(Ship.SPEED_LIMIT);
		ship.setYVelocity(0);
		ship.thrust(10);
		
		assertEquals(Ship.SPEED_LIMIT, ship.getXVelocity(), EPSILON);
		assertEquals(0, ship.getYVelocity(), EPSILON);
		
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
		Ship ship1 = new Ship(0,0,0,0,10,0);
		Ship ship2 = new Ship(40,0,0,0,20,0);
		Ship ship3 = new Ship(0,30,0,0,10,0);
		
		assertEquals(ship1.getDistanceBetween(ship2), 10, EPSILON);
		assertEquals(ship2.getDistanceBetween(ship3), 20, EPSILON);
		assertEquals(ship3.getDistanceBetween(ship1), 10, EPSILON);
		ship2.getDistanceBetween(null); // throws an IllegalArgumentException
	
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testOverlap() throws IllegalArgumentException{
		Ship ship1 = new Ship(0,0,0,0,10,0);
		Ship ship2 = new Ship(0,30,0,0,10,0);
		Ship ship3 = new Ship(10,0,0,0,50,0);
									
		assertFalse(ship1.overlaps(ship2));
		assertTrue(ship2.overlaps(ship3));
		assertTrue(ship1.overlaps(ship3));
		
		ship3.overlaps(null); // throws an IllegalArgumentException
	
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetTimeToCollision() throws IllegalArgumentException{
		Ship ship1 = new Ship(0,0,0,0,10,0);
		Ship ship2 = new Ship(30,0,-1,0,10,0);
		Ship ship3 = new Ship(0,25,0,-0.5,10,0);
		Ship ship4 = new Ship(0,300,0,1,10,0);

		
		
		assertEquals(ship1.getTimeToEntityCollision(ship2), 10, EPSILON);
		assertEquals(ship2.getTimeToEntityCollision(ship3), 18, EPSILON);
		assertEquals(ship3.getTimeToEntityCollision(ship1), 10, EPSILON);
		assertEquals(ship3.getTimeToEntityCollision(ship4), Double.POSITIVE_INFINITY
		, EPSILON);
				
		
		
		ship3.getTimeToEntityCollision(null); // throws an IllegalArgumentException
	
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetCollisionPosition() throws IllegalArgumentException{
		Ship ship1 = new Ship(0,0,0,0,10,0);
		Ship ship2 = new Ship(30,0,-1,0,10,0);
		Ship ship3 = new Ship(0,25,0,-0.5,10,0);
		
		
		assertEquals(ship1.getEntityCollisionPosition(ship2)[0], 30, EPSILON);
		assertEquals(ship1.getEntityCollisionPosition(ship2)[1], 0, EPSILON);
		assertEquals(ship2.getEntityCollisionPosition(ship3)[0], -6, EPSILON);
		assertEquals(ship2.getEntityCollisionPosition(ship3)[1], 24, EPSILON);
		assertEquals(ship3.getEntityCollisionPosition(ship1)[0], 0, EPSILON);
		assertEquals(ship3.getEntityCollisionPosition(ship1)[1], -10, EPSILON);


		
		ship1.getEntityCollisionPosition(null); // throws an IllegalArgumentException
	}
	
	@Test(expected = IllegalStateException.class)
	public void testGetCollisionPosition2() throws IllegalStateException{
		Ship ship1 = new Ship(0,0,0,0,10,0);
		Ship ship2 = new Ship(0,0,0,0,20,0);
		System.out.println("pass");
		ship1.getEntityCollisionPosition(ship2); // throws an IllegalStateException
	}
	
	

	
}
