package Tests;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import asteroids.model.Bullet;
import asteroids.model.Collision;
import asteroids.model.CollisionType;
import asteroids.model.Entity;
import asteroids.model.Position;
import asteroids.model.Ship;
import asteroids.model.World;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;

public class asteroidsTests1 {
	private static final double EPSILON = 0.0001;
	
//------------SHIP TESTS-----------------
	@Before
	public void setUp() {
		Ship ship = new Ship();
	}
	

	
	
	@Test
	public void shipConstructor1Test() throws IllegalArgumentException{
		World world = new World(100,100);
		Ship ship = new Ship(10,20,30,40,10,0,5E15,world);
		assertEquals(10, ship.getXCoord(), EPSILON);
		assertEquals(20, ship.getYCoord(),EPSILON);
		assertEquals(30, ship.getXVelocity(),EPSILON);
		assertEquals(40, ship.getYVelocity(),EPSILON);
		assertEquals(10, ship.getRadius(),EPSILON);
		assertEquals(0, ship.getOrientation(),EPSILON);
		assertEquals(5E15,ship.getMass(),EPSILON);
		assert ship.getWorld() == world;
		assert ship.getNbBullets() == 0; 
		
		
	}
	
	@Test
	public void shipConstructor2Test() throws IllegalArgumentException{
		Ship ship = new Ship(15,25,300,45,10,0,5E15);
		assertEquals(15, ship.getXCoord(), EPSILON);
		assertEquals(25, ship.getYCoord(),EPSILON);
		assertEquals(300, ship.getXVelocity(),EPSILON);
		assertEquals(45, ship.getYVelocity(),EPSILON);
		assertEquals(10, ship.getRadius(),EPSILON);
		assertEquals(0, ship.getOrientation(),EPSILON);
		assertEquals(5E15,ship.getMass(),EPSILON);
		assert ship.getWorld() == null;
		assert ship.getNbBullets() == 0;
		
		
	}
	
	@Test
	public void shipConstructor3Test() throws IllegalArgumentException{
		Ship ship = new Ship(15,25,200,45,10,0);
		assertEquals(15, ship.getXCoord(), EPSILON);
		assertEquals(25, ship.getYCoord(),EPSILON);
		assertEquals(200, ship.getXVelocity(),EPSILON);
		assertEquals(45, ship.getYVelocity(),EPSILON);
		assertEquals(10, ship.getRadius(),EPSILON);
		assertEquals(0, ship.getOrientation(),EPSILON);
		assert ship.getWorld() == null;
		assert ship.getNbBullets() == 0;
		
	}
	
	@Test
	public void shipConstructor4Test() throws IllegalArgumentException{
		Ship ship = new Ship(85,25,200,35,10);
		assertEquals(85, ship.getXCoord(), EPSILON);
		assertEquals(25, ship.getYCoord(),EPSILON);
		assertEquals(200, ship.getXVelocity(),EPSILON);
		assertEquals(35, ship.getYVelocity(),EPSILON);
		assertEquals(10, ship.getRadius(),EPSILON);
		assertEquals(0, ship.getOrientation(),EPSILON);
		assert ship.getWorld() == null;
		assert ship.getNbBullets() == 0;


	}
	
	@Test
	public void shipConstructor5Test() throws IllegalArgumentException{
		Ship ship = new Ship(20,45,200,35);
		assertEquals(20, ship.getXCoord(), EPSILON);
		assertEquals(45, ship.getYCoord(),EPSILON);
		assertEquals(200, ship.getXVelocity(),EPSILON);
		assertEquals(35, ship.getYVelocity(),EPSILON);
		assertEquals(10, ship.getRadius(),EPSILON);
		assertEquals(0, ship.getOrientation(),EPSILON);
		assert ship.getWorld() == null;
		assert ship.getNbBullets() == 0;


	}
	
	@Test
	public void shipConstructor6Test() throws IllegalArgumentException{
		Ship ship = new Ship(20,45);
		assertEquals(20, ship.getXCoord(), EPSILON);
		assertEquals(45, ship.getYCoord(),EPSILON);
		assertEquals(0, ship.getXVelocity(),EPSILON);
		assertEquals(0, ship.getYVelocity(),EPSILON);
		assertEquals(10, ship.getRadius(),EPSILON);
		assertEquals(0, ship.getOrientation(),EPSILON);
		assert ship.getWorld() == null;
		assert ship.getNbBullets() == 0;
	}
	
	@Test
	public void shipConstructor7Test() throws IllegalArgumentException{
		Ship ship = new Ship();
		System.out.println(ship.getMass());
		assertEquals(0, ship.getXCoord(), EPSILON);
		assertEquals(0, ship.getYCoord(),EPSILON);
		assertEquals(0, ship.getXVelocity(),EPSILON);
		assertEquals(0, ship.getYVelocity(),EPSILON);
		assertEquals(10, ship.getRadius(),EPSILON);
		assertEquals(0, ship.getOrientation(),EPSILON);
		assert ship.getWorld() == null;
		assert ship.getNbBullets() == 0;
	}
	
	
	@Test
	public void shipEqualsTest2(){
		Ship ship1 = new Ship(10,20,30,40,10,0);
		Ship ship2 = new Ship();
		assert !ship1.equals(ship2);
	}
	
	@Test
	public void getMinShipRadiusTest(){
		assertEquals(Ship.MIN_RADIUS, Ship.getMinRadius(),EPSILON);
	}
	
	@Test
	public void getDefaultRadiusTest(){
		assertEquals(Ship.MIN_RADIUS, Ship.getMinRadius(),EPSILON);
	}
	
	@Test
	public void canHaveAsShipRadius(){
		Ship ship = new Ship();
		assertTrue(ship.canHaveAsRadius(Ship.MIN_RADIUS + 5));
		assertFalse(ship.canHaveAsRadius(Ship.MIN_RADIUS - 5));
	}
	
	
	@Test
	public void getMinOrientationTest(){
		Ship ship = new Ship();
		assertEquals(0,ship.getMinOrientation(),EPSILON);
	}
	
	@Test
	public void getMaxOrientationTest(){
		Ship ship = new Ship();
		assertEquals(6.28318530717,ship.getMaxOrientation(),EPSILON);
	}
	
	
	@Test
	public void getOrientationTest1(){
		Ship ship = new Ship(10,20,30,40,10,Math.PI);
		assertEquals(Math.PI, ship.getOrientation(),EPSILON);
	}
	
	@Test
	public void getOrientationTest2(){
		Ship ship = new Ship();
		assertEquals(0, ship.getOrientation(),EPSILON);
	}
	
	@Test
	public void canHaveAsOrientationTest(){
		Ship ship = new Ship();
		assertFalse(ship.canHaveAsOrientation(-Math.PI));
		assertTrue(ship.canHaveAsOrientation(Math.PI));
		assertFalse(ship.canHaveAsOrientation(3*Math.PI));		
	}
	
	@Test
	public void setOrientationTest1(){
		Ship ship = new Ship();
		ship.setOrientation(Math.PI/2);
		assertEquals(Math.PI/2, ship.orientation,EPSILON);
	}
	
	@Test
	public void setOrientationTest2(){
		Ship ship = new Ship();
		ship.setOrientation(0);
		assertEquals(0, ship.orientation,EPSILON);
	}
	

	
	@Test
	public void turnTest1(){
		Ship ship = new Ship();
		ship.turn(Math.PI/2);
		assertEquals(Math.PI/2, ship.getOrientation(),EPSILON);
	}
	
	@Test
	public void turnTest2(){
		Ship ship = new Ship(50,50,10,20,10,Math.PI/4);
		ship.turn(Math.PI/2);
		assertEquals(3*Math.PI/4, ship.getOrientation(),EPSILON);
	}
	
	@Test
	public void getShipDensityTest(){
		Ship ship = new Ship();
		assertEquals(1.42*Math.pow(10, 12), ship.getDensity(),EPSILON);
	}
	
	@Test
	public void setMassTest(){
		Ship ship = new Ship();
		ship.setMass(5E15);
		assertEquals(5E15,ship.getMass(),EPSILON);
	}
	
	@Test
	public void getShipTotalMassTest(){
		Ship ship = new Ship(0,0,0,0,20,0); //NO BULLETS
		Bullet bullet = new Bullet(0,0,0,0,10,ship);
		double bulletMass = bullet.getMass();
		assertEquals(ship.getMass() + bulletMass
				, ship.getTotalMass(),EPSILON);
	}
	
	@Test
	public void getThrusterStateTest(){
		Ship ship = new Ship();
		assertTrue(ship.getThrusterState() == false);
	}
	
	@Test
	public void setThrusterStateTest(){
		Ship ship = new Ship();
		ship.setThrusterState(true);
		assertTrue(ship.getThrusterState() == true);
	}
	
	@Test
	public void thrustOnTest(){
		Ship ship = new Ship();
		ship.thrustOn();
		assertTrue(ship.getThrusterState() == true);
	}
	
	@Test
	public void thrustOffTest(){
		Ship ship = new Ship();
		ship.thrustOff();
		assertTrue(ship.getThrusterState() == false);
	}
	
	@Test
	public void getThrustForceTest(){
		assertEquals(Ship.THRUSTER_FORCE,Ship.getThrusterForce(),EPSILON);
	}
	

	@Test
	public void thrustTest1(){
		Ship ship = new Ship();
		ship.thrustOn();
		ship.thrust(1);
		assertEquals(ship.getAcceleration()//ERROR, MASS = 0
				, ship.getXVelocity(),EPSILON);
	}
	
	@Test
	public void thrustTest2(){
		Ship ship = new Ship();
		ship.thrust(-1);
		System.out.println(ship.getXVelocity());
		assertEquals(0// negative time => no speed added
				, ship.getXVelocity(),EPSILON);
	}
	
	@Test
	public void thrustTest3(){
		Ship ship = new Ship();
		ship.thrustOn();
		ship.thrust(1000);//thruster aan, vel = nan
		assertEquals(Ship.LIGHT_SPEED//ZOU SPEEDLIM MOETEN ZIJN?
										//LIMITSPEED WERKT NIET?
				, ship.getXVelocity(),EPSILON);
	}
	
	
	@Test
	public void getShipBulletsTest(){
		Ship ship = new Ship();
		Bullet bigBullet = new Bullet();
		ship.load(bigBullet);
	    assertFalse(ship.getBullets().isEmpty());
	    assertTrue(ship.getBullets().contains(bigBullet));

	}
	
	@Test
	public void getShipBulletSetTest(){
		Ship ship = new Ship();
		Bullet bigBullet = new Bullet();
		ship.load(bigBullet);
	    assertFalse(ship.getBulletSet().isEmpty());
	    assertTrue(ship.getBulletSet().contains(bigBullet));

	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void getBulletAtIndexTest() throws IndexOutOfBoundsException{
		Ship ship = new Ship();
		Bullet bigBullet = new Bullet();
		Bullet smallBullet = new Bullet();

		ship.load(bigBullet);
		ship.load(smallBullet);

	    assertTrue(ship.getBulletAtIndex(0) == bigBullet);
	    assertTrue(ship.getBulletAtIndex(1) == smallBullet);
	    assertTrue(ship.getBulletAtIndex(2) == smallBullet);// exception

	    

	}
	
	@Test
	public void getNbShipBulletsTest(){

		Ship ship = new Ship();
		Bullet bigBullet = new Bullet();
		Bullet smallBullet = new Bullet();

		ship.load(bigBullet);
		ship.load(smallBullet);

	  
		assertTrue(ship.getNbBullets() == 2);	    

	}
	
	
	@Test
	public void canHaveAsBulletTest1(){
		Ship ship = new Ship();
		assertTrue(ship.canHaveAsBullet(null) == false);
	}
	
	@Test
	public void canHaveAsBulletTest2(){
		Ship ship = new Ship();
		Bullet defaultBullet = new Bullet();
		assertTrue(ship.canHaveAsBullet(defaultBullet));
	}
	
	@Test
	public void loadTest(){
	Ship ship = new Ship();
	Bullet bullet = new Bullet();
	ship.load(bullet);
	assertTrue(ship.getBullets().contains(bullet));
			
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void loadTest2() throws IllegalArgumentException{
	Ship ship1 = new Ship();
	Ship ship2 = new Ship();

	Bullet bullet = new Bullet(0,0,0,0,10,ship2);
	ship1.load(bullet);
	assertTrue(ship1.getBullets().contains(bullet));//throws exception
			
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void loadTest3() throws IllegalArgumentException{
	Ship ship1 = new Ship();
	Ship ship2 = new Ship();

	Bullet bullet = new Bullet(0,0,0,0,10,ship1);
	ship1.load(bullet);
	assertTrue(ship1.getBullets().contains(bullet));//throws exception
			
	}
	
	@Test
	public void collectionLoadTest() throws IllegalArgumentException{
	Ship ship1 = new Ship();

	Bullet bullet1 = new Bullet();
	Bullet bullet2 = new Bullet();
	HashSet<Bullet> bullets = new HashSet<Bullet>();
	bullets.add(bullet1);
	ship1.load(bullets);
	assertTrue(ship1.getBullets().contains(bullet1));
			
	}
	
	@Test
	public void removeShipBulletTest(){
		Ship ship = new Ship();

		Bullet bullet = new Bullet();
		ship.load(bullet);
		assertTrue(ship.getBullets().contains(bullet));
		ship.removeBullet(bullet);
		assertFalse(ship.getBullets().contains(bullet));	
	}
	
	@Test
	public void removeShipBulletAtTest(){
		Ship ship = new Ship();

		Bullet bullet = new Bullet();
		ship.load(bullet);
		assertTrue(ship.getBullets().contains(bullet));
		ship.removeBulletAt(0);
		assertFalse(ship.getBullets().contains(bullet));	
	}
	

	

	
	@Test
	public void fireBulletTest(){
		World world = new World(100,100);
		Ship ship = new Ship(10,10,0,0,10,0,5E15,world);
		Bullet bullet = new Bullet();
		ship.load(bullet);
		ship.fireBullet();
		assertTrue(ship.getNbBullets() == 0);
		assertEquals(250,bullet.getXVelocity(),EPSILON);
		
	}
	
	@Test 
	public void shipCollideTest(){
		World world = new World(1000,1000);
		Ship ship1 = new Ship(20,20,10,0,10,0,5E15,world);
		Ship ship2 = new Ship(80,20,-10,0,10,0,5E15,world);
		ship1.collide(ship2);
		assertEquals(190,ship1.getXVelocity(),EPSILON);
		assertEquals(-190,ship2.getXVelocity(),EPSILON);// Functie werkt nog niet
	}

	@Test 
	public void shipBulletCollideTest(){
		Ship ship1 = new Ship();
		Ship ship2 = new Ship(40,40,0,0,20);
		Bullet bullet = new Bullet(0,0,0,0,10,ship2);
		ship1.collide(bullet);
		assertTrue(ship1.isTerminated());
		assertTrue(bullet.isTerminated());
	}
	
//------------ENTITY TESTS-----------------

//NO CONSTRUCTOR TESTS BECAUSE ENTITY IS ABSTRACT CLASS
//   => CANNOT INITIATE ENTITY => WE WILL USE SHIP TO TEST ENTITY

	@Test
	public void getXCoordTest(){
		Ship ship = new Ship(10,20);
		assertEquals(10,ship.getXCoord(),EPSILON);
		
	}
	
	@Test
	public void getXCoordTest2(){
		Ship ship = new Ship();
		assertEquals(0,ship.getXCoord(),EPSILON);
		
	}

	
	@Test
	public void getEntityPositionTest() throws IllegalArgumentException{
		Ship ship = new Ship(30,30,0,0,10,0);
		Position pos = new Position(30,30);
		assertEquals(ship.getPosition().getXCoord(), pos.getXCoord(),EPSILON);
		assertEquals(ship.getPosition().getYCoord(), pos.getYCoord(),EPSILON);

	
	}
	
	@Test
	public void isValidPositionTest() throws IllegalArgumentException{
		Position pos = new Position(30,30);
		World world = new World();
		Ship ship1 = new Ship();
		Ship ship2 = new Ship(20,20);
		assertTrue(ship1.isValidPosition(pos));
		assertTrue(ship2.isValidPosition(pos));

	
	}
	
	

	
	@Test
	public void withinBoundariesOfTest() throws IllegalArgumentException{
		Ship ship1 = new Ship(20,20,0,0,10,0);
		Ship ship2 = new Ship(40,40,0,0,30,0);
		Ship ship3 = new Ship(140,40,0,0,10,0);
		Ship ship4 = new Ship(40,0,0,0,10,0);
		Ship ship5 = new Ship(-20,20,0,0,10,0);
		World world = new World(100,100);
		assertTrue(ship1.isWithinBoundariesOf(world));
		assertTrue(ship2.isWithinBoundariesOf(world));
		assertFalse(ship3.isWithinBoundariesOf(world));
		assertFalse(ship4.isWithinBoundariesOf(world));
		assertFalse(ship5.isWithinBoundariesOf(world));
	}
	
	
	
	@Test(expected = IllegalArgumentException.class)
	public void moveTest() throws IllegalArgumentException{
		Ship ship1 = new Ship(20,20,20,0,10,0);
		Ship ship2 = new Ship(40,40,0,10,30,Math.PI);
		ship1.move(10);
		ship2.move(20);
		assertEquals(220,ship1.getXCoord(),EPSILON);
		assertEquals(20,ship1.getYCoord(),EPSILON);
		assertEquals(40,ship2.getXCoord(),EPSILON);
		assertEquals(240,ship2.getYCoord(),EPSILON);
		ship1.move(-1);//Throws IllegalArgumentException
	}
	
	
	@Test
	public void getXVelocityTest(){
		Ship ship = new Ship(10,20,10,20);
		assertEquals(10,ship.getXVelocity(),EPSILON);
		
	}
	
	@Test
	public void getXVelocityTest2(){
		Ship ship = new Ship();
		assertEquals(0,ship.getXVelocity(),EPSILON);
		
	}
	
	
	@Test
	public void getYVelocityTest(){
		Ship ship = new Ship(10,20,20,10);
		assertEquals(10,ship.getYVelocity(),EPSILON);
		
	}
	
	@Test
	public void getYVelocityTest2(){
		Ship ship = new Ship();
		assertEquals(0,ship.getYVelocity(),EPSILON);
		
	}
	
	@Test
	public void getVelocitiesTest1(){
		Ship ship = new Ship(10,20,30,40);
		assertEquals(30,ship.getVelocities()[0],EPSILON);
		assertEquals(40,ship.getVelocities()[1],EPSILON);

	}
	
	
	@Test
	public void getAbsVelocityTest(){
		Ship ship = new Ship(10,20,30,0);
		double actualX = ship.getXVelocity();
		double actualY = ship.getYVelocity();
		assertEquals(30,Entity.getAbsSpeed(actualX,actualY),EPSILON);
	}
	
	@Test
	public void getSpeedTest(){
		Ship ship = new Ship(10,20,30,40);
		assertEquals(50,ship.getSpeed(),EPSILON);
	}
	
	
	@Test
	public void limitSpeedTest1(){
		Ship ship = new Ship();
		double[] expectedVelocities = new double[2];
		expectedVelocities[0] = 300;
		expectedVelocities[1] = 400;
		assertEquals(expectedVelocities[0],ship.limitSpeed(300,400)[0],EPSILON);
		assertEquals(expectedVelocities[1],ship.limitSpeed(300,400)[1],EPSILON);

	}
	
	@Test
	public void limitSpeedTest2(){
		Ship ship = new Ship();
		double[] expectedVelocities = new double[2];
		expectedVelocities[0] = 180000;
		expectedVelocities[1] = 240000;
		assertEquals(expectedVelocities[0],ship.limitSpeed(300000,400000)[0],EPSILON);
		assertEquals(expectedVelocities[1],ship.limitSpeed(300000,400000)[1],EPSILON);

	}
	
	@Test
	public void getFuturePositionTest1(){
		Ship ship = new Ship(10,10,20,10);
		assertEquals(210,ship.getFuturePosition(10).getXCoord(),EPSILON);
		assertEquals(110,ship.getFuturePosition(10).getYCoord(),EPSILON);

	}
	
	@Test
	public void getFuturePositionTest2(){
		Ship ship = new Ship(10,10,-20,-10);
		assertEquals(-190,ship.getFuturePosition(10).getXCoord(),EPSILON);
		assertEquals(-90,ship.getFuturePosition(10).getYCoord(),EPSILON);

	}
	
	@Test
	public void getFutureCoordinatesTest(){
		Ship ship = new Ship(10,10,20,10);
		assertEquals(210,ship.getFutureCoordinates(10)[0],EPSILON);
		assertEquals(110,ship.getFutureCoordinates(10)[1],EPSILON);

	}
	
	@Test
	public void getFutureCoordinatesTest2(){
		Ship ship = new Ship(10,10,-20,-10);
		assertEquals(-190,ship.getFutureCoordinates(10)[0],EPSILON);
		assertEquals(-90,ship.getFutureCoordinates(10)[1],EPSILON);
	}
	
	@Test
	public void getDefaultTest(){
		Ship ship = new Ship();
		assertEquals(10,ship.getDefaultRadius(),EPSILON);
	}
	
	@Test
	public void getRadiusTest(){
		Ship ship = new Ship(0,10,10,10,100,0);
		assertEquals(100,ship.getRadius(),EPSILON);
	}
	
	
	@Test
	public void setWorldTest1(){
		World world = new World(100,100);
		Ship ship = new Ship(10,20,30,40,10,0,5E15,world);
		assertTrue(ship.getWorld() == world);
	}

	
	@Test
	public void getDensityTest(){
		Ship ship = new Ship();
		Bullet bullet = new Bullet();
		assertEquals(1.42*Math.pow(10, 12),ship.getDensity(),EPSILON);
		assertEquals(7.8*Math.pow(10, 12),bullet.getDensity(),EPSILON);
	}
	
	@Test
	public void getMassTest(){
		Ship ship = new Ship();
		assertEquals((4/3)*Math.PI*Math.pow(ship.getRadius(), 3)*ship.getDensity()
				,ship.getMass(),EPSILON);
	}
	
	@Test
	public void collideTest(){
		Ship ship1 = new Ship();
		Ship ship2 = new Ship();
		Bullet bullet1 = new Bullet();
		Bullet bullet2 = new Bullet();
		int integer = 5;
		ship1.collide(ship2); //Valid collision
		bullet1.collide(bullet2); //Valid collision
		ship1.collide(bullet2); //Valid collision
	}
	
	@Test
	public void isValidTimeTest(){
		Ship ship1 = new Ship();
		assertTrue(Entity.isValidTime(5));
		assertTrue(Entity.isValidTime(0));
		assertFalse(Entity.isValidTime(-2));
	}
	
		
	@Test(expected = NullPointerException.class)
	public void testGetDistanceBetweenCentres() throws NullPointerException{
		Ship ship1 = new Ship(0,0,0,0,10,0);
		Ship ship2 = new Ship(40,0,0,0,20,0);
		Ship ship3 = new Ship(0,30,0,0,10,0);
		
		assertEquals(ship1.getDistanceBetweenCentres(ship2), 40, EPSILON);
		assertEquals(ship2.getDistanceBetweenCentres(ship3), 50, EPSILON);
		assertEquals(ship3.getDistanceBetweenCentres(ship1), 30, EPSILON);
		ship2.getDistanceBetweenCentres(null); // throws a NullPointerException
	}
	
	@Test(expected = NullPointerException.class)
	public void testGetDistanceBetween() throws NullPointerException{
		Ship ship1 = new Ship(0,0,0,0,10,0);
		Ship ship2 = new Ship(40,0,0,0,20,0);
		Ship ship3 = new Ship(0,30,0,0,10,0);
		
		assertEquals(ship1.getDistanceBetween(ship2), 10, EPSILON);
		assertEquals(ship2.getDistanceBetween(ship3), 20, EPSILON);
		assertEquals(ship3.getDistanceBetween(ship1), 10, EPSILON);
		ship2.getDistanceBetween(null); // throws a NullPointerException
	}
	
	
	@Test
	public void overlapsTest(){
		Ship ship1 = new Ship(50,50,0,0,10,0);
		Ship ship2 = new Ship(50,80,0,0,10,0);
		Ship ship3 = new Ship(60,50,0,0,40,0);
		
		assertFalse(ship1.overlaps(ship2));
		assertTrue(ship2.overlaps(ship3));
		assertTrue(ship1.overlaps(ship3));
		
			}
	
	@Test
	public void getTimeToWallCollisionTest() throws NullPointerException{
		World world = new World(1000,1000);
		Ship ship1 = new Ship(20,20,10,0,10,0,5E15,world);
		Ship ship2 = new Ship(20,120,-10,0,10,0,5E15,world);
		Ship ship3 = new Ship(120,20,0,10,10,0,5E15,world);
		Ship ship4 = new Ship(220,20,0,-10,10,0,5E15,world);
		Ship ship5 = new Ship(220,20,0,-10,10,0,5E15,null);
		Ship ship6 = new Ship(320,20,0,0,10,0,5E15,world);
		assertEquals(97.01,ship1.getTimeToCollision(world),EPSILON);
		assertEquals(Double.POSITIVE_INFINITY,ship2.getTimeToCollision(world),EPSILON);
		assertEquals(97.01,ship3.getTimeToCollision(world),EPSILON);
		assertEquals(Double.POSITIVE_INFINITY,ship4.getTimeToCollision(world),EPSILON);
		assertEquals(Double.POSITIVE_INFINITY,
				ship5.getTimeToCollision(world),EPSILON);
		assertEquals(Double.POSITIVE_INFINITY,
				ship6.getTimeToCollision(world),EPSILON);		
	}
	
	
	@Test
	public void wallBounceTest(){
		World world = new World(1000,1000);
		Ship ship1 = new Ship(20,20,10,0,10,0,5E15,world);
		Ship ship2 = new Ship(120,20,0,10,10,0,5E15,world);
		ship1.wallBounce(CollisionType.rightWall);
		ship2.wallBounce(CollisionType.topWall);
		assertEquals(-10,ship1.getXVelocity(),EPSILON);
		assertEquals(-10,ship2.getYVelocity(),EPSILON);
	}
	
	
	@Test
	public void testGetTimeToCollisionTest1() throws NullPointerException{
		Ship ship1 = new Ship(0,0,0,0,10,0);
		Ship ship2 = new Ship(30,0,-1,0,10,0);
		Ship ship3 = new Ship(0,25,0,-0.5,10,0);
		Ship ship4 = new Ship(0,300,0,1,10,0);

		assertEquals(ship1.getTimeToCollision(ship2), 10, EPSILON);
		assertEquals(ship2.getTimeToCollision(ship3), 18, EPSILON);
		assertEquals(ship3.getTimeToCollision(ship1), 10, EPSILON);
		assertEquals(ship3.getTimeToCollision(ship4), Double.POSITIVE_INFINITY
		, EPSILON);
		
	}
	

	
	@Test 
	public void isTerminatedTest(){
		Ship ship1 = new Ship();
		Ship ship2 = new Ship();
		ship2.terminate();
		assertFalse(ship1.isTerminated());
		assertTrue(ship2.isTerminated());
	} 
	
	@Test 
	public void TerminateTest(){
		World world = new World(1000,1000);
		Ship ship1 = new Ship();
		Ship ship2 = new Ship();
		Ship ship3 = new Ship(20,20,0,0,10,0,5E15,world);
		ship2.terminate();
		ship3.terminate();
		assertFalse(ship1.isTerminated());
		assertTrue(ship2.isTerminated());
		assertTrue(world.getEntityList().contains(ship3) == false);
		assertTrue(ship3.getWorld() == null);
	} 
	
	//--------BULLET TESTS------------
	
	@Test
	public void bulletConstructor1Test() throws IllegalArgumentException{
		World world = new World(100,100);
		Ship ship = new Ship(10,20,30,40,20);
		Bullet bullet = new Bullet(10,20,30,40,10,ship);
		assertEquals(10, bullet.getXCoord(), EPSILON);
		assertEquals(20, bullet.getYCoord(),EPSILON);
		assertEquals(30, bullet.getXVelocity(),EPSILON);
		assertEquals(40, bullet.getYVelocity(),EPSILON);
		assertEquals(10, bullet.getRadius(),EPSILON);
		assertTrue(bullet.getShip() == ship);
		assert bullet.getNbBounces() == 0;

		
		
	}
	
	@Test
	public void bulletConstructor2Test() throws IllegalArgumentException{
		World world = new World(100,100);
		Ship ship = new Ship(10,20,30,40,20);
		Bullet bullet = new Bullet(10,20,30,40,10);
		assertEquals(10, bullet.getXCoord(), EPSILON);
		assertEquals(20, bullet.getYCoord(),EPSILON);
		assertEquals(30, bullet.getXVelocity(),EPSILON);
		assertEquals(40, bullet.getYVelocity(),EPSILON);
		assertEquals(10, bullet.getRadius(),EPSILON);
		assertTrue(bullet.getShip() == null);
		assert bullet.getNbBounces() == 0;	
		
	}
	
	@Test
	public void bulletConstructor3Test() throws IllegalArgumentException{
		World world = new World(100,100);
		Ship ship = new Ship(10,20,30,40,20);
		Bullet bullet = new Bullet(10,20,30,40);
		assertEquals(10, bullet.getXCoord(), EPSILON);
		assertEquals(20, bullet.getYCoord(),EPSILON);
		assertEquals(30, bullet.getXVelocity(),EPSILON);
		assertEquals(40, bullet.getYVelocity(),EPSILON);
		assertTrue(bullet.getShip() == null);
		assert bullet.getNbBounces() == 0;
	}
	
	@Test
	public void bulletConstructor4Test() throws IllegalArgumentException{
		World world = new World(100,100);
		Ship ship = new Ship(10,20,30,40,20);

		Bullet bullet = new Bullet(10,20);
		assertEquals(10, bullet.getXCoord(), EPSILON);
		assertEquals(20, bullet.getYCoord(),EPSILON);
		assertTrue(bullet.getShip() == null);
		assert bullet.getNbBounces() == 0;

	}
	
	@Test
	public void bulletConstructor5Test() throws IllegalArgumentException{
		World world = new World(100,100);
		Ship ship = new Ship(10,20,30,40,20);

		Bullet bullet = new Bullet();
		assertEquals(0, bullet.getXCoord(), EPSILON);
		assertEquals(0, bullet.getYCoord(),EPSILON);
		assertEquals(0, bullet.getXVelocity(),EPSILON);
		assertEquals(0, bullet.getYVelocity(),EPSILON);
		assertTrue(bullet.getShip() == null);
		assert bullet.getNbBounces() == 0;
	}
	
	@Test
	public void bulletEqualsTest1(){
		Bullet bullet1 = new Bullet(10,20,30,40,10,null);
		Bullet bullet2 = new Bullet(10,20,30,40,10,null);
		assert bullet1.equals(bullet2);
	}
	
	@Test
	public void bulletEqualsTest2(){
		Bullet bullet1 = new Bullet(10,20,30,40,10,null);
		Bullet bullet2 = new Bullet();
		assert !bullet1.equals(bullet2);
		
	}
	
	@Test
	public void canHaveAsBulletRadiusTest(){
		Ship ship = new Ship(0,0,0,0,200,0);
		Bullet bullet1 = new Bullet(10,20,30,40,10,null);
		Bullet bullet2 = new Bullet(10,20,30,40,100,ship);
		Bullet bullet3 = new Bullet(10,20,30,40,10,ship);
		assertTrue(bullet1.canHaveAsRadius(10));
		assertFalse(bullet1.canHaveAsRadius(0.5));
		assertTrue(bullet2.canHaveAsRadius(100));
		assertTrue(bullet3.canHaveAsRadius(10));

	}
	
	@Test
	public void getBulletDensityTest(){
		Bullet bullet1 = new Bullet();
		assertEquals( 7.8*Math.pow(10, 12),bullet1.getDensity(),EPSILON);

	}
	
	@Test
	public void getBulletShipTest(){
		Ship ship = new Ship(0,0,0,0,200,0);
		Bullet bullet = new Bullet(10,20,30,40,100,ship);
		assertTrue(bullet.getShip() == ship);

	}

		
	@Test
	public void canHaveAsShipTest(){
		Ship ship = new Ship(0,0,0,0,200,0);
		Bullet bullet1 = new Bullet(10,20,30,40,100,ship);
		Bullet bullet2 = new Bullet(10,20,30,40,100,null);
		assertTrue(bullet2.canHaveAsShip(ship));
		
	}


	@Test 
	public void BulletShipCollideTest1(){
		Ship ship = new Ship(10,20,30,40,20);
		Bullet bullet = new Bullet(0,0,0,0,10,ship);
		bullet.collide(ship);
		assertTrue(ship.getNbBullets() == 1);

	}
	
	@Test 
	public void BulletShipCollideTest2(){
		Ship ship1 = new Ship();
		Ship ship2 = new Ship(10,20,30,40,20);
		Bullet bullet = new Bullet(0,0,10,0,10,ship2);
		bullet.collide(ship1);
		assertTrue(ship1.isTerminated());
		assertTrue(bullet.isTerminated());
	}
	
	@Test 
	public void bulletBulletCollideTest(){
		Bullet bullet1 = new Bullet(0,0,10,0,10);
		Bullet bullet2 = new Bullet(0,0,10,0,10);
		bullet1.collide(bullet2);
		assertTrue(bullet1.isTerminated());
		assertTrue(bullet2.isTerminated());
	}
	
	@Test
	public void getMaxNbBouncesTest(){
		Bullet bullet = new Bullet();
		assertTrue(bullet.getMaxNbBounces() == 2);
	}
	
	@Test
	public void getNbBouncesTest(){
		Bullet bullet = new Bullet();
		assertTrue(bullet.getNbBounces() == 0);
	}
	
	@Test
	public void countBounceTest(){
		Bullet bullet = new Bullet();
		assertTrue(bullet.getNbBounces() == 0);
		bullet.countBounce();
		assertTrue(bullet.getNbBounces() == 1);
		bullet.countBounce();
		assertTrue(bullet.getNbBounces() == 2);

	}
	
	@Test
	public void wallbounceTest1(){
		Bullet bullet = new Bullet();
		Collision collision = new Collision(CollisionType.leftWall,2,bullet);
		bullet.wallBounce(CollisionType.bottomWall);;
		bullet.wallBounce(CollisionType.bottomWall);;
		bullet.wallBounce(CollisionType.bottomWall);;
		assertTrue(bullet.isTerminated());
	}
	
	@Test
	public void wallbounceTest2(){
		Bullet bullet = new Bullet(0,0,-10,0);
		Collision collision = new Collision(CollisionType.leftWall,2,bullet);
		bullet.countBounce();
		bullet.wallBounce(CollisionType.leftWall);
		assertTrue(bullet.getNbBounces() == 2);
		assertEquals(10,bullet.getXVelocity(),EPSILON);
	}
	
//------------WORLD TESTS-------------
	
	@Test
	public void worldConstructor1Test() throws IllegalArgumentException{
		World world = new World(100,100);
		assertEquals(100, world.getWidth(), EPSILON);
		assertEquals(100, world.getHeight(),EPSILON);

	}
	
	@Test
	public void worldConstructor2Test() throws IllegalArgumentException{
		World world = new World();
		assertEquals(1.7976931348623157E308, world.getWidth(), EPSILON);
		assertEquals(1.7976931348623157E308, world.getHeight(),EPSILON);
	}
	
	@Test
	public void getWidthTest() throws IllegalArgumentException{
		World world1 = new World(100,100);
		World world2 = new World(50,50);
		assertEquals(100, world1.getWidth(), EPSILON);
		assertEquals(50, world2.getWidth(), EPSILON);

	}
	
	@Test
	public void getHeightTest() throws IllegalArgumentException{
		World world1 = new World(100,100);
		World world2 = new World(50,50);
		assertEquals(100, world1.getWidth(), EPSILON);
		assertEquals(50, world2.getWidth(), EPSILON);
		
	}
	
	@Test
	public void getSizeTest(){
		World world1 = new World(100,100);
		World world2 = new World(50,50);
		assertEquals(100, world1.getSize()[0], EPSILON);
		assertEquals(100, world1.getSize()[1], EPSILON);
		assertEquals(50, world2.getSize()[0], EPSILON);
		assertEquals(50, world2.getSize()[1], EPSILON);

	}
	
	@Test
	public void isValidXCoordTest(){
		World world1 = new World(100,100);
		World world2 = new World(50,50);
		assertTrue(world1.isValidXCoord(75));
		assertFalse(world1.isValidXCoord(125));
		assertTrue(world2.isValidXCoord(25));
		assertFalse(world2.isValidXCoord(75));
	}
	
	@Test
	public void isValidYCoordTest(){
		World world1 = new World(100,100);
		World world2 = new World(50,50);
		assertTrue(world1.isValidYCoord(75));
		assertFalse(world1.isValidYCoord(125));
		assertTrue(world2.isValidYCoord(25));
		assertFalse(world2.isValidYCoord(75));
	}
	


	@Test
	public void getEntitiesTest(){
		World world = new World(100,100);
		Ship ship = new Ship(20,20);
		Bullet bullet = new Bullet(40,40);
		world.add(ship);
		world.add(bullet);
		assertTrue(world.getEntitySet().contains(ship));
		assertTrue(world.getEntitySet().contains(bullet));
	}
	
	@Test
	public void getShipsTest(){
		World world = new World(100,100);
		Ship ship = new Ship(20,20);
		Bullet bullet = new Bullet(40,40);
		world.add(ship);
		world.add(bullet);
		assertTrue(world.getShipSet().contains(ship));
		assertFalse(world.getShipSet().contains(bullet));
	}
	
	@Test
	public void getBulletsTest(){
		World world = new World(100,100);
		Ship ship = new Ship(20,20);
		Bullet bullet = new Bullet(40,40);
		world.add(ship);
		world.add(bullet);
		assertFalse(world.getBulletSet().contains(ship));
		assertTrue(world.getBulletSet().contains(bullet));
	}

	
	@Test
	public void containsTest(){
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
	public void worldEntityAddTest(){
		World world = new World(100,100);
		Ship ship = new Ship(20,20,0,0,10,0);
		assertFalse(world.contains(ship));
		assertFalse(ship.getWorld() == world);
		world.add(ship);
		assertTrue(world.contains(ship));
		assertTrue(ship.getWorld() == world);

	}
	
	@Test(expected = IllegalArgumentException.class)
	public void worldEntityRemoveTest(){
		World world = new World(100,100);
		Ship ship = new Ship(20,20,0,0,10,0);
		world.add(ship);
		assertTrue(world.contains(ship));
		assertTrue(ship.getWorld() == world);
		world.remove(ship);
		assertFalse(world.contains(ship));
		assertFalse(ship.getWorld() == world);
		world.remove(ship);//Throws an IllegalArgumentException
	}
	

	@Test
	public void getFirstWallCollisionTest(){
		World world = new World(100,100);
		Ship ship = new Ship(20,20,-1,0,15,0,5E15,world);
		Bullet bullet = new Bullet(40,40,1,0,10,ship);
		assertTrue(world.getFirstWallCollision().getCollisionType() !=
				CollisionType.entity);		
	}
	
	
	@Test
	public void getFirstWorldCollisionTest(){
		World world = new World(1000,1000);
		Ship ship = new Ship(20,20,1,0,15,0,5E15,world);
		Bullet bullet1 = new Bullet(60,20,-1,0,10);
		Bullet bullet2 = new Bullet(400,20,-2,0,10);
		world.add(bullet1);
		world.add(bullet2);
		Collision collision =
				new Collision(CollisionType.entity,10,ship,bullet1);
		assertTrue(world.getFirstCollision().getCollisionType() ==
				collision.getCollisionType());
	}
	
	@Test
	public void advanceTimeTest(){
		World world = new World(1000,1000);
		Ship ship = new Ship(800,800,1,0,100,0,5E15,world);
		ship.setThrusterState(true);
		Bullet bullet1 = new Bullet(60,20,-1,0,10,ship);
		Bullet bullet2 = new Bullet(400,20,-2,0,10,ship);
		world.advanceTime(1);
		assertEquals(801,ship.getXCoord(),EPSILON);
		assertEquals(800,ship.getYCoord(),EPSILON);
		assertEquals(247.578080846,ship.getXVelocity(),EPSILON);
		assertEquals(0,ship.getYVelocity(),EPSILON);
		assertEquals(801,bullet1.getXCoord(),EPSILON);
		assertEquals(800,bullet1.getYCoord(),EPSILON);
		assertEquals(-1,bullet1.getXVelocity(),EPSILON);
		assertEquals(0,bullet1.getYVelocity(),EPSILON);
		assertEquals(801,bullet2.getXCoord(),EPSILON);
		assertEquals(800,bullet2.getYCoord(),EPSILON);
		assertEquals(-2,bullet2.getXVelocity(),EPSILON);
		assertEquals(0,bullet2.getYVelocity(),EPSILON);
		
	}
	
	
	
	@Test
	public void worldisTerminatedTest(){
		World world = new World();
		assertFalse(world.isTerminated());
	}
	
	@Test
	public void worldTerminateTest(){
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
		assertFalse(ship.getWorld() == world);
		assertFalse(bullet.getWorld() == world);
		assertTrue(world.isTerminated());
	}
	
//-----------COLLISION TESTS--------------
	
	@Test
	public void collisionConstructor1Test1(){
		Ship ship1 = new Ship();
		Ship ship2 = new Ship();

		Collision collision = new Collision(CollisionType.entity,10,ship1,ship2);
		assertTrue(collision.getCollisionType() == CollisionType.entity);
		assertTrue(collision.getTime() == 10);
		assertTrue(collision.getEntity() == ship1);
		assertTrue(collision.getOtherEntity() == ship2);

	}
	
	@Test(expected = IllegalArgumentException.class)
	public void collisionConstructor1Test2(){
		Ship ship1 = new Ship();
		Ship ship2 = new Ship();

		Collision collision = new Collision(CollisionType.entity,10,ship1,null);
		//throws an IllegalArgumentException

	}
	
	@Test
	public void collisionConstructor2Test(){
		Ship ship = new Ship();

		Collision collision = new Collision(CollisionType.topWall,10,ship);
		assertTrue(collision.getCollisionType() == CollisionType.topWall);
		assertTrue(collision.getTime() == 10);
		assertTrue(collision.getEntity() == ship);
	}
	
	@Test
	public void getCollisionTypeTest(){
		Ship ship1 = new Ship();
		Ship ship2 = new Ship();

		Collision collision1 = new Collision(CollisionType.entity,10,ship1,ship2);
		Collision collision2 = new Collision(CollisionType.leftWall,10,ship1);

		assertTrue(collision1.getCollisionType() == CollisionType.entity);
		assertTrue(collision2.getCollisionType() == CollisionType.leftWall);
	}
	
	@Test
	public void getCollisionTimeTest(){
		Ship ship1 = new Ship();
		Ship ship2 = new Ship();

		Collision collision1 = new Collision(CollisionType.entity,10,ship1,ship2);
		Collision collision2 = new Collision(CollisionType.leftWall,20,ship1);

		assertTrue(collision1.getTime() == 10);
		assertTrue(collision2.getTime() == 20);
	}
	
	@Test
	public void isValidCollisionTimeTest(){
		Ship ship1 = new Ship();
		Ship ship2 = new Ship();
		Collision collision = new Collision(CollisionType.entity,10,ship1,ship2);

		assertTrue(collision.isValidTime(5));
		assertFalse(collision.isValidTime(-5));

	}
	
	@Test
	public void getCollisionEntity(){
		Ship ship1 = new Ship();
		Ship ship2 = new Ship();
		Collision collision1 = new Collision(CollisionType.entity,10,ship1,ship2);
		Collision collision2 = new Collision(CollisionType.rightWall,10,ship1);

		assertTrue(collision1.getEntity() == ship1);
		assertTrue(collision2.getEntity() == ship1);


		

	}
	
	@Test
	public void getCollisionOtherEntity(){
		Ship ship1 = new Ship();
		Ship ship2 = new Ship();
		Collision collision1 = new Collision(CollisionType.entity,10,ship1,ship2);
		Collision collision2 = new Collision(CollisionType.rightWall,10,ship1);

		assertTrue(collision1.getOtherEntity() == ship2);
		assertTrue(collision2.getOtherEntity() == null);

	}
	
	@Test
	public void resolveCollisionTest1(){//2 ships collide
		World world = new World(100,100);
		Ship ship1 = new Ship(20,20,2,0,10,0,5E15,world);
		Ship ship2 = new Ship(60,20,-2,0,10,0,5E15,world);
		Collision collision = new Collision(CollisionType.entity,10,ship1,ship2);
		collision.resolve();
		assertEquals(18,ship1.getXVelocity(),EPSILON);
		assertEquals(-18,ship2.getXVelocity(),EPSILON);

		
	}
	
	@Test
	public void resolveCollisionTest2(){//2 bullets collide
		World world = new World(100,100);
		Bullet bullet1 = new Bullet(20,20,2,0,10);
		Bullet bullet2 = new Bullet(80,20,-2,0,10);
		Collision collision = new Collision(CollisionType.entity,
				10,bullet1,bullet2);
		collision.resolve();
		assertTrue(bullet1.isTerminated());
		assertTrue(bullet2.isTerminated());
		
	}
	
	@Test
	public void resolveCollisionTest3(){//Wallcollision
		Bullet bullet = new Bullet(80,20,-2,0,10);
		Collision collision = new Collision(CollisionType.leftWall
				,10,bullet);
		assertTrue(bullet.getNbBounces() == 0);
		collision.resolve();
		assertTrue(bullet.getNbBounces() == 1);
	}

	
	@Test
	public void resolveCollisionTest4(){//BulletOtherShipCollide
		Ship ship1 = new Ship(0,0,0,0,20,0);
		Ship ship2 = new Ship();
		Bullet bullet = new Bullet(80,20,-2,0,10,ship1);
		Collision collision = new Collision(CollisionType.entity
				,10,ship2,bullet);
		collision.resolve();
		assertTrue(ship2.isTerminated());
		assertTrue(bullet.isTerminated());
	}
	
//--------POSITION TESTS----------
	
	
	@Test
	public void positionConstructor1Test(){
		Position position = new Position(10,20);

		assertEquals(10, position.getXCoord(), EPSILON);
		assertEquals(20, position.getYCoord(),EPSILON);
		
	}
	
	@Test
	public void positionConstructor2Test(){
		Position position = new Position(0,0);
		assertEquals(0, position.getXCoord(), EPSILON);
		assertEquals(0, position.getYCoord(),EPSILON);
	}
	
	@Test
	public void getPositionXCoordTest(){
		Position position1 = new Position(0,0);
		Position position2 = new Position(-20,-10);
		Position position3 = new Position(30,-15);

		assertEquals(0, position1.getXCoord(), EPSILON);
		assertEquals(-20, position2.getXCoord(), EPSILON);
		assertEquals(30, position3.getXCoord(), EPSILON);
	}
	
	@Test
	public void getPositionYCoordTest(){
		Position position1 = new Position(0,0);
		Position position2 = new Position(-20,-10);
		Position position3 = new Position(30,-15);

		assertEquals(0, position1.getYCoord(),EPSILON);
		assertEquals(-10, position2.getYCoord(),EPSILON);
		assertEquals(-15, position3.getYCoord(),EPSILON);
	}
		
	@Test
	public void positionToArrayTest(){
		Position position1 = new Position(0,0);
		Position position2 = new Position(-20,-10);
		Position position3 = new Position(30,-15);

		
		assertEquals(0, position1.toArray()[0], EPSILON);
		assertEquals(0, position1.toArray()[1], EPSILON);
		assertEquals(-20, position2.toArray()[0], EPSILON);
		assertEquals(-10, position2.toArray()[1], EPSILON);
		assertEquals(30, position3.toArray()[0], EPSILON);
		assertEquals(-15, position3.toArray()[1], EPSILON);
	}
	
	@Test
	public void getPositionEntityTest(){
		Position position = new Position(30,-15);
		assertTrue(position.getEntity() == null);
	}
	
	

	
	@Test 
	public void positionEqualsTest(){
		Ship ship = new Ship();
		int integer = 5;
		Position position1 = new Position(30,30);
		Position position2 = new Position(20,10);
		Position position3 = new Position(0,0);
		Position position4 = new Position(30,30);
		assertFalse(position1.equals(integer));
		assertFalse(position1.equals(position2));
		assertFalse(position2.equals(position3));
		assertTrue(position1.equals(position4));	
	}
		
	
}	


	
