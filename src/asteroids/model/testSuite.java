package asteroids.model;

import static org.junit.Assert.*;
import java.util.HashSet;
import org.junit.Test;




public class testSuite {
	
	// margin of error
	private static final double EPSILON = 0.0001;
	
	
	@Test
	public void testCreateShip() throws IllegalArgumentException {
		World world = new World(100,100);
		Ship ship = new Ship(10,20,30,40,10,0,15E15,world);
		assertEquals(10, ship.getXCoord(), 		EPSILON);
		assertEquals(20, ship.getYCoord(), 		EPSILON);
		assertEquals(30, ship.getXVelocity(), 	EPSILON);
		assertEquals(40, ship.getYVelocity(), 	EPSILON);
		assertEquals(10, ship.getRadius(), 		EPSILON);
		assertEquals(0,  ship.getOrientation(),	EPSILON);
		assertEquals(15E15,ship.getMass(),		EPSILON);
		assertEquals(world, ship.getWorld());
		assertEquals(0, ship.getNbBullets());
	}
	
	@Test
	public void testCreateShipDefaultValues() throws IllegalArgumentException {
		Ship ship = new Ship();
		assertEquals(0, ship.getXCoord(), 		EPSILON);
		assertEquals(0, ship.getYCoord(), 		EPSILON);
		assertEquals(0, ship.getXVelocity(), 	EPSILON);
		assertEquals(0, ship.getYVelocity(), 	EPSILON);
		assertEquals(10, ship.getRadius(), 		EPSILON);
		assertEquals(0,  ship.getOrientation(),	EPSILON);
		assertEquals(ship.getMinimumMass(),ship.getMass(),EPSILON);
		assertEquals(null, ship.getWorld());
		assertEquals(0, ship.getNbBullets());
	}
	
	@Test
	public void testCreateShipOverlappingWorldBorders() throws IllegalArgumentException {
		World world = new World(100,100);
		try {
			new Ship(95,95,0,0,50,0,5E15,world);
			fail();
		} catch (IllegalArgumentException e) {
			return;
		}
	}
	
	@Test
	public void testCreateShipOutsideOfWorldBorders() throws IllegalArgumentException {
		World world = new World(100,100);
		try {
			new Ship(200,200,0,0,50,0,5E15,world);
			fail();
		} catch (IllegalArgumentException e) {
			return;
		}
	}
	
	@Test
	public void testCreateShipInvalidCoordinates() throws IllegalArgumentException {
		try {
			new Ship(Double.NaN, Double.NaN);
			fail();
		} catch (IllegalArgumentException e) {
			return;
		}
	}
	
	@Test
	public void testCreateShipTooBigVelocities() throws IllegalArgumentException {
		Ship ship = new Ship(0,0, 10E20, 10E15);
		assertEquals(ship.getSpeed(), ship.getSpeedLimit(), EPSILON);
	}
	
	@Test
	public void testCreateShipInvalidRadius() throws IllegalArgumentException {
		try {
			new Ship(95,95,0,0,9);
			fail();
		} catch (IllegalArgumentException e) {
			
		}
		try {
			new Ship(95,95,0,0,Double.NaN);
			fail();
		} catch (IllegalArgumentException e) {
			
		}
	}
	
	@Test
	public void testCreateShipInvalidOrientation() throws IllegalArgumentException {
		try {
			new Ship(0,0,0,0,20,9);
			fail();
		} catch (Error e) {
			return;
		}
	}
	
	@Test
	public void testCreateShipTooSmallMass() throws IllegalArgumentException {
		Ship ship = new Ship(0,0,0,0,20,0,0);
		assertEquals(ship.getMass(),ship.getMinimumMass(), EPSILON);
	}
	
	@Test
	public void testShipEquals() {
		Ship ship1 = new Ship(10,20,30,40,10,0);
		Ship ship2 = new Ship(10,20,30,40,10,0);
		assertEquals(ship1, ship2);
	}
	
	@Test
	public void testShipNotEquals() {
		Ship ship0 = new Ship(10,20,30,40,50,0,10E20);
		Ship ship1 = new Ship(90,20,30,40,50,0,10E20);
		Ship ship2 = new Ship(10,90,30,40,50,0,10E20);
		Ship ship3 = new Ship(10,20,90,40,50,0,10E20);
		Ship ship4 = new Ship(10,20,30,90,50,0,10E20);
		Ship ship5 = new Ship(10,20,30,40,90,0,10E20);
		Ship ship6 = new Ship(10,20,30,40,50,1,10E20);
		Ship ship7 = new Ship(10,20,30,40,50,0,20E20);
		Ship ship8 = new Ship(10,20,30,40,50,0,10E20);
		Bullet bullet = new Bullet(10,20,0,0,10);
		ship8.load(bullet);
		assertNotEquals(ship0,ship1);
		assertNotEquals(ship0,ship2);
		assertNotEquals(ship0,ship3);
		assertNotEquals(ship0,ship4);
		assertNotEquals(ship0,ship5);
		assertNotEquals(ship0,ship6);
		assertNotEquals(ship0,ship7);
		assertNotEquals(ship0,ship8);
	}
	
	@Test
	public void testShipRotation() throws IllegalArgumentException {
		Ship ship = new Ship(0,0,0,0,50,0);
		ship.turn(1);
		assertEquals(ship.getOrientation(), 1, EPSILON);
	}
	
	@Test
	public void testShipNegativeRotation() throws IllegalArgumentException {
		Ship ship = new Ship(0,0,0,0,50,0);
		try {
			ship.turn(-1);
			fail();
		} catch (Error e) {
			return;
		}
	}
	
	@Test
	public void testShipTooBigRotation() throws IllegalArgumentException {
		Ship ship = new Ship(0,0,0,0,50,0);
		try {
			ship.turn(10);
			fail();
		} catch (Error e) {
			return;
		}
	}
	
	@Test
	public void testShipLoadedWithBulletGetMass() throws IllegalArgumentException {
		Ship ship = new Ship(0,0,0,0,50,0,20E20);
		Bullet bullet = new Bullet(0,0,0,0,10);
		ship.load(bullet);
		assertEquals(ship.getMass(), 20E20 + bullet.getMass(), EPSILON);
	}
	
	@Test
	public void testShipThrust() throws IllegalArgumentException {
		Ship ship = new Ship(0,0,0,0,10,0,10E20);
		ship.thrustOn();
		assertEquals(ship.getAcceleration(), Ship.getThrusterForce() / 10E20, EPSILON);
		ship.thrustOff();
		assertEquals(ship.getAcceleration(), 0, EPSILON);
		ship.thrustOn();
		ship.thrust(1);
		assertEquals(ship.getSpeed(), ship.getAcceleration(), EPSILON);
	}
	
	@Test
	public void testShipCannotAccelerateAboveSpeedLimit() throws IllegalArgumentException {
		Ship ship = new Ship(0,0,Ship.LIGHT_SPEED,0);
		assertEquals(ship.getSpeed(), ship.getSpeedLimit(), EPSILON);
		ship.thrustOn();
		ship.thrust(10E10);
		assertEquals(ship.getSpeed(), ship.getSpeedLimit(), EPSILON);
	}
	
	@Test
	public void testShipNegativeDurationAcceleration() throws IllegalArgumentException {
		Ship ship = new Ship();
		ship.thrustOn();
		ship.thrust(-10);
		assertEquals(ship.getSpeed(), 0, EPSILON);
	}
	
	@Test
	public void testLimitSpeedKeepsMovementDirection() throws IllegalArgumentException {
		double[] result = new Ship().limitSpeed(10E10, 20E10);
		assertEquals(20E10 / 10E10, result[1] / result[0], EPSILON);
	}
	
	@Test
	public void testGetFuturePosition(){
		Ship ship = new Ship(10,10,20,10);
		assertEquals(210,ship.getFuturePosition(10).getXCoord(),EPSILON);
		assertEquals(110,ship.getFuturePosition(10).getYCoord(),EPSILON);
	}
	
	@Test
	public void testShipMove() throws IllegalArgumentException {
		Ship ship = new Ship(0,0,10,0);
		ship.move(10);
		assertEquals(ship.getXCoord(), 100, EPSILON);
	}
	
	@Test
	public void testShipMoveOutOfWorld() throws IllegalArgumentException {
		Ship ship = new Ship(10,10,-5,0,10);
		World world = new World(200,200);
		world.add(ship);
		try {
			ship.move(1);
			fail();
		} catch (IllegalArgumentException e) {
			return;
		}
	}
	
	@Test
	public void testShipLoadAndRemoveBullet() throws IllegalArgumentException {
		Ship ship = new Ship();
		Bullet bullet = new Bullet();
		ship.load(bullet);
	    assertFalse(ship.getBullets().isEmpty());
	    assertTrue(ship.getBullets().contains(bullet));
	    assertEquals(bullet.getShip(), ship);
	    ship.removeBullet(bullet);
	    assertTrue(ship.getBullets().isEmpty());
	    assertFalse(ship.getBullets().contains(bullet));
	    assertEquals(bullet.getShip(), null);
	}
	
	@Test 
	public void testShipFireBullet() throws IllegalArgumentException {
		Ship ship = new Ship(20,20,0,0,20);
		Bullet bullet = new Bullet(20,20,0,0,10);
		ship.load(bullet);
		World world = new World(200,200);
		world.add(ship);
		ship.fireBullet();
		assertEquals(bullet.getWorld(), world);
		assertEquals(bullet.getXCoord(), 50, EPSILON);
		assertEquals(bullet.getYCoord(), 20, EPSILON);
		assertTrue(world.getSomeEntitySet(Bullet.class).contains(bullet));
		assertEquals(bullet.getSpeed(), Bullet.getFireSpeed(), EPSILON);
	}
	
	@Test 
	public void testShipFireBulletOutsideOfWorld() throws IllegalArgumentException {
		Ship ship1 = new Ship(180,180,0,0,20);
		Bullet bullet1 = new Bullet(180,180,0,0,10);
		ship1.load(bullet1);
		World world = new World(200,200);
		world.add(ship1);
		ship1.fireBullet();
		assertTrue(bullet1.isTerminated());
		
		Ship ship2 = new Ship();
		Bullet bullet2 = new Bullet();
		ship2.load(bullet2);
		ship2.fireBullet();
		assertTrue(ship2.getBullets().contains(bullet2));
	}
	
	@Test 
	public void testShipFireBulletDirectlyOnShip() throws IllegalArgumentException {
		World world = new World(200,200);
		Ship ship1 = new Ship(20,20,0,0,15,0,20E20,world);
		Ship ship2 = new Ship(50,20,0,0,10,0,20E20,world);
		Bullet bullet = new Bullet(20,20,0,0,10);
		ship1.load(bullet);
		ship1.fireBullet();
		assertTrue(bullet.isTerminated());
		assertTrue(ship2.isTerminated());
		assertEquals(world.getEntityList().size(), 1);
	}
	
	@Test
	public void testCanHaveAsBullet() throws IllegalArgumentException {
		Ship ship = new Ship(0,0,0,0,15);
		Bullet bullet0 = new Bullet(0,0,0,0,15);
		Bullet bullet1 = new Bullet(0,0,0,0,14.99);
		Bullet bullet2 = new Bullet(0,0,0,0,1);
		Bullet bullet3 = new Bullet(20,0,0,0,2);
		assertFalse(ship.canHaveAsBullet(bullet0));
		assertTrue(ship.canHaveAsBullet(bullet1));
		assertTrue(ship.canHaveAsBullet(bullet2));
		assertFalse(ship.canHaveAsBullet(bullet3));
	}
	
	@Test
	public void testLoadCollectionOfBulletsOnShip() throws IllegalArgumentException{
	Ship ship = new Ship();
	Bullet bullet1 = new Bullet();
	Bullet bullet2 = new Bullet();
	HashSet<Bullet> bullets = new HashSet<Bullet>();
	bullets.add(bullet1);
	bullets.add(bullet2);
	ship.load(bullets);
	assertTrue(ship.getBullets().contains(bullet1));
	assertEquals(ship.getBullets().size(), 2);
	}
	
	@Test
	public void testCreateBullet() throws IllegalArgumentException {
		Bullet bullet = new Bullet(10,20,30,40,15);
		assertEquals(10, bullet.getXCoord(), 	EPSILON);
		assertEquals(20, bullet.getYCoord(), 	EPSILON);
		assertEquals(30, bullet.getXVelocity(), EPSILON);
		assertEquals(40, bullet.getYVelocity(), EPSILON);
		assertEquals(15, bullet.getRadius(), 	EPSILON);
	}
	
	@Test
	public void testCreateBulletDefaultValues() throws IllegalArgumentException {
		Bullet bullet = new Bullet();
		assertEquals(0, bullet.getXCoord(), 	EPSILON);
		assertEquals(0, bullet.getYCoord(), 	EPSILON);
		assertEquals(0, bullet.getXVelocity(),  EPSILON);
		assertEquals(0, bullet.getYVelocity(), 	EPSILON);
		assertEquals(1, bullet.getRadius(), 	EPSILON);
	}
	
	@Test
	public void testShipBounce() throws IllegalArgumentException {
		Ship ship = new Ship(0,0,10,10);
		ship.bounce(CollisionType.verticalWall);
		ship.bounce(CollisionType.horizontalWall);
		assertEquals(-10,ship.getXVelocity(),EPSILON);
		assertEquals(-10,ship.getYVelocity(),EPSILON);
		ship.bounce(CollisionType.verticalWall);
		ship.bounce(CollisionType.horizontalWall);
		assertEquals(10,ship.getXVelocity(),EPSILON);
		assertEquals(10,ship.getYVelocity(),EPSILON);
	}
	
	@Test
	public void testBulletBounce() throws IllegalArgumentException {
		Bullet bullet = new Bullet(0,0,10,5);
		bullet.bounce(CollisionType.horizontalWall);
		assertEquals(bullet.getYVelocity(), -5, EPSILON);
		bullet.bounce(CollisionType.horizontalWall);
		assertEquals(bullet.getYVelocity(), 5, EPSILON);
		bullet.bounce(CollisionType.horizontalWall);
		assertTrue(bullet.isTerminated());
	}
	
	@Test
	public void testOverlap() throws IllegalArgumentException {
		Ship ship = new Ship(0,0,0,0,10);
		Ship ship0 = new Ship(20,0,0,0,10);
		Ship ship1 = new Ship(19.5,0,0,0,10);
		Ship ship2 = new Ship(20,0,0,0,10.5);
		assertFalse(ship.overlaps(ship0));
		assertTrue(ship.overlaps(ship1));
		assertTrue(ship.overlaps(ship2));
	}
	
	@Test
	public void testCompleteOverlap() throws IllegalArgumentException {
		Ship ship = new Ship(0,0,0,0,15);
		Ship ship0 = new Ship(0,0,0,0,15);
		Ship ship1 = new Ship(0,0,0,0,14.9999);
		assertFalse(ship.completelyOverlaps(ship0));
		assertTrue(ship.completelyOverlaps(ship1));
	}
	
	@Test
	public void testGetTimeToWallCollision() throws IllegalArgumentException {
		World world = new World(1000,1000);
		Ship ship1 = new Ship(20,20,10,0,10,0,5E15,world);
		Ship ship2 = new Ship(20,120,-10,0,10,0,5E15,world);
		Ship ship3 = new Ship(120,20,0,10,10,0,5E15,world);
		Ship ship4 = new Ship(220,20,0,-10,10,0,5E15,world);
		Ship ship5 = new Ship(220,20,0,-10,10,0,5E15,null);
		Ship ship6 = new Ship(320,20,0,0,10,0,5E15,world);
		assertEquals(ship1.getTimeToBorderCollision(),97,EPSILON);
		assertEquals(ship2.getTimeToBorderCollision(),1,EPSILON);
		assertEquals(ship3.getTimeToBorderCollision(),97,EPSILON);
		assertEquals(ship4.getTimeToBorderCollision(),1,EPSILON);
		assertEquals(ship5.getTimeToBorderCollision(),Double.POSITIVE_INFINITY,EPSILON);
		assertEquals(ship6.getTimeToBorderCollision(),Double.POSITIVE_INFINITY,EPSILON);	
	}
	
	@Test
	public void testGetTimeToEntityCollision() throws IllegalArgumentException, NullPointerException {
		Ship ship1 = new Ship(0,0,0,0,10,0);
		Ship ship2 = new Ship(30,0,-1,0,10,0);
		Ship ship3 = new Ship(0,25,0,-0.5,10,0);
		Ship ship4 = new Ship(0,300,0,1,10,0);
		assertEquals(ship1.getTimeToCollision(ship2),10,EPSILON);
		assertEquals(ship2.getTimeToCollision(ship3),18,EPSILON);
		assertEquals(ship3.getTimeToCollision(ship1),10,EPSILON);
		assertEquals(ship3.getTimeToCollision(ship4),Double.POSITIVE_INFINITY,EPSILON);
	}
	
	@Test 
	public void testShipTerminate() throws IllegalArgumentException {
		World world = new World(1000,1000);
		Ship ship1 = new Ship();
		Ship ship2 = new Ship();
		Ship ship3 = new Ship(20,20,0,0,10,0,5E15,world);
		ship2.terminate();
		ship3.terminate();
		assertFalse(ship1.isTerminated());
		assertTrue(ship2.isTerminated());
		assertFalse(world.getEntityList().contains(ship3));
		assertNull(ship3.getWorld());
	} 
	
	@Test
	public void testWorldIsValidXCoord() throws IllegalArgumentException {
		World world1 = new World(100,100);
		World world2 = new World(50,50);
		assertTrue(world1.isValidXCoord(100));
		assertFalse(world1.isValidXCoord(101));
		assertTrue(world2.isValidXCoord(25));
		assertFalse(world2.isValidXCoord(75));
	}
	
	@Test
	public void testWorldIsValidYCoord() throws IllegalArgumentException {
		World world1 = new World(100,100);
		World world2 = new World(50,50);
		assertTrue(world1.isValidYCoord(100));
		assertFalse(world1.isValidYCoord(101));
		assertTrue(world2.isValidYCoord(25));
		assertFalse(world2.isValidYCoord(75));
	}
	
	@Test
	public void testWorldGetSomeEntitySet() throws IllegalArgumentException {
		World world = new World(1000,1000);
		Ship ship = new Ship(20,20,0,0,10,0,0,world);
		Bullet bullet = new Bullet(80,80,0,0,10);
		world.add(bullet);
		assertTrue(world.getSomeEntitySet(Ship.class).contains(ship));
		assertFalse(world.getSomeEntitySet(Ship.class).contains(bullet));
		assertTrue(world.getSomeEntitySet(Bullet.class).contains(bullet));
		assertFalse(world.getSomeEntitySet(Bullet.class).contains(ship));
		assertTrue(world.getSomeEntitySet(Entity.class).contains(ship));
		assertTrue(world.getSomeEntitySet(Entity.class).contains(bullet));
	}

	@Test
	public void testWorldContains() throws IllegalArgumentException {
		World world = new World(100,100);
		Ship ship1 = new Ship(20,20);
		Bullet bullet1 = new Bullet(40,40);
		Ship ship2 = new Ship(60,60);
		Bullet bullet2 = new Bullet(80,80);
		world.add(ship1);
		world.add(bullet1);
		assertTrue(world.contains(ship1));
		assertFalse(world.contains(ship2));
		assertTrue(world.contains(bullet1));
		assertFalse(world.contains(bullet2));
	}
	
	@Test
	public void testWorldAdd() throws IllegalArgumentException {
		World world = new World(100,100);
		Ship ship = new Ship(20,20,0,0,10,0);
		assertFalse(world.contains(ship));
		assertFalse(ship.getWorld() == world);
		world.add(ship);
		assertTrue(world.contains(ship));
		assertTrue(ship.getWorld() == world);
	}
	
	@Test
	public void testAddEntityToMultipleWorld() throws IllegalArgumentException {
		World world1 = new World(200,200);
		World world2 = new World(200,200);
		Ship ship = new Ship(50,50);
		world1.add(ship);
		try {
			world2.add(ship);
			fail();
		} catch (IllegalArgumentException e) {
			return;
		}
	}
	
	@Test
	public void testWorldTerminate(){
		World world = new World(100,100);
		Ship ship = new Ship(20,20);
		Bullet bullet  = new Bullet(40,40);
		world.add(ship);
		world.add(bullet);
		assertTrue(world.contains(ship));
		assertTrue(world.contains(bullet));
		assertTrue(ship.getWorld() == world);
		assertTrue(bullet.getWorld() == world);
		world.terminate();
		assertFalse(world.contains(ship));
		assertFalse(world.contains(bullet));
		assertNull(ship.getWorld());
		assertNull(bullet.getWorld());
		assertTrue(world.isTerminated());
		assertFalse(ship.isTerminated());
		assertFalse(bullet.isTerminated());
	}
	
	@Test
	public void testPlanetoidShrinkDissolve() {
		Planetoid planetoid = new Planetoid(0,0,0,0,50,0);
		planetoid.increaseTravelledDistance(100);
		planetoid.updateCurrentRadius();
		assertEquals(planetoid.getRadius(), 50 - 100 * Planetoid.getShrinkingPercentage() * 0.01, EPSILON);
		planetoid.increaseTravelledDistance(10E10);
		assertFalse(planetoid.isTerminated());
		planetoid.updateCurrentRadius();
		assertTrue(planetoid.isTerminated());
	}
	
	@Test
	public void testPlanetoidDieSpawnAsteroids() {
		World world = new World(1000,1000);
		Planetoid planetoid = new Planetoid(500,500,0,0,50,0,world);
		assertEquals(world.getSomeEntitySet(Planetoid.class).size(),1);
		assertEquals(world.getSomeEntitySet(Asteroid.class).size(),0);
		planetoid.die();
		assertEquals(world.getSomeEntitySet(Planetoid.class).size(),0);
		assertEquals(world.getSomeEntitySet(Asteroid.class).size(),2);
	}
	
	@Test
	public void testPlanetoidDieSpawnAsteroidsOverlappingWorldBorders() {
		World world = new World(100,100);
		Planetoid planetoid = new Planetoid(50,50,0,0,50,0,world);
		assertEquals(world.getSomeEntitySet(Planetoid.class).size(),1);
		assertEquals(world.getSomeEntitySet(Asteroid.class).size(),0);
		planetoid.die();
		assertEquals(world.getSomeEntitySet(Planetoid.class).size(),0);
		assertEquals(world.getSomeEntitySet(Asteroid.class).size(),0);
	}
	
	@Test
	public void testResolveCollisionShipBullet() {
		World world = new World(1000,1000);
		Ship ship = new Ship(50,50,10,0);
		Bullet bullet = new Bullet(250,50,-10,0);
		world.add(ship);
		world.add(bullet);
		Collision firstCollision = world.getFirstCollision();
		assertEquals(firstCollision.getCollisionType(), CollisionType.entity);
		world.advanceTime(firstCollision.getTime()-0.1);
		assertEquals(world.getEntityList().size(),2);
		world.advanceTime(0.1);
		assertEquals(world.getEntityList().size(),0);
	}
	
	@Test
	public void testResolveCollisionShipFiredBullet() {
		World world = new World(1000,1000);
		Ship ship = new Ship(50,50,10,0);
		Bullet bullet = new Bullet(250,50,-10,0);
		world.add(ship);
		world.add(bullet);
		bullet.setSourceShip(ship);
		Collision firstCollision = world.getFirstCollision();
		assertEquals(firstCollision.getCollisionType(), CollisionType.entity);
		world.advanceTime(firstCollision.getTime()-0.1);
		assertEquals(world.getEntityList().size(),2);
		world.advanceTime(0.1);
		assertEquals(world.getEntityList().size(),1);
		assertTrue(world.getEntityList().contains(ship));
	}
	
	@Test
	public void testResolveCollisionShipAsteroid() {
		World world = new World(1000,1000);
		Ship ship = new Ship(50,50,10,0);
		Asteroid asteroid = new Asteroid(250,50,-10,0,10);
		world.add(ship);
		world.add(asteroid);
		Collision firstCollision = world.getFirstCollision();
		assertEquals(firstCollision.getCollisionType(), CollisionType.entity);
		world.advanceTime(firstCollision.getTime()-0.1);
		assertEquals(world.getEntityList().size(),2);
		world.advanceTime(0.1);
		assertEquals(world.getEntityList().size(),1);
		assertTrue(world.getEntityList().contains(asteroid));
	}
}
