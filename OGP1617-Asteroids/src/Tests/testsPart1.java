package Tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import asteroids.facade.Facade;
import asteroids.model.Ship;
import asteroids.util.ModelException;

public class testsPart1 {
	
	private static final double EPSILON = 0.0001;
	
	
	// TEST 1
	// TEST 2 - "Commit"
	
	@Before
	public void setUp() {
		Ship ship = new Ship();
	}
		
	public void testPosition() throws IllegalArgumentException {
		Ship ship = new Ship();
		ship.setXCoord(20);
		ship.setYCoord(30);
			
		double positions[] = ship.getPositions();
		System.out.println(positions[0]);
		System.out.println(positions[1]);

		assertEquals(20, positions[0], EPSILON);
		assertEquals(30, positions[1], EPSILON);
	
	}
}
