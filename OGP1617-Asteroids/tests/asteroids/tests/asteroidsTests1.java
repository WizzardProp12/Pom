package asteroids.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asteroids.model.entities.Bullet;
import asteroids.model.entities.Entity;
import asteroids.model.entities.Position;
import asteroids.model.entities.Ship;
import asteroids.model.environment.World;
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
		Ship ship = new Ship(10,20,30,40,10,0,world);
		assertEquals(10, ship.getXCoord(), EPSILON);
		assertEquals(20, ship.getYCoord(),EPSILON);
		assertEquals(30, ship.getXVelocity(),EPSILON);
		assertEquals(40, ship.getYVelocity(),EPSILON);
		assertEquals(10, ship.getRadius(),EPSILON);
		assertEquals(0, ship.getOrientation(),EPSILON);
		assert ship.getWorld() == world;
		assert ship.getNbBullets() == 0; 
		
		
	}
	
	@Test
	public void shipConstructor2Test() throws IllegalArgumentException{
		Ship ship = new Ship(15,25,300,45,10,0);
		assertEquals(15, ship.getXCoord(), EPSILON);
		assertEquals(25, ship.getYCoord(),EPSILON);
		assertEquals(300, ship.getXVelocity(),EPSILON);
		assertEquals(45, ship.getYVelocity(),EPSILON);
		assertEquals(10, ship.getRadius(),EPSILON);
		assertEquals(0, ship.getOrientation(),EPSILON);
		assert ship.getWorld() == null;
		assert ship.getNbBullets() == 0;
		
		
	}
	
	@Test
	public void shipConstructor3Test() throws IllegalArgumentException{
		Ship ship = new Ship(15,25,200,45,10);
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
		Ship ship = new Ship(85,25,200,35);
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
	public void shipConstructor6Test() throws IllegalArgumentException{
		Ship ship = new Ship();
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
	public void shipEqualsTest1(){
		Ship ship1 = new Ship(10,20,30,40,10,0);
		Ship ship2 = new Ship(10,20,30,40,10,0);
		assert ship1.equals(ship2);
	}
	
	@Test
	public void shipEqualsTest2(){
		Ship ship1 = new Ship(10,20,30,40,10,0);
		Ship ship2 = new Ship();
		assert !ship1.equals(ship2);
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
		ship.turn(-Math.PI/2);
		assertEquals(-Math.PI/4, ship.getOrientation(),EPSILON);
	}
	
	@Test
	public void getShipDensityTest(){
		Ship ship = new Ship();
		assertEquals(1.42*Math.pow(10, 12), ship.getDensity(),EPSILON);
	}
	
	@Test
	public void getShipTotalMassTest(){
		Ship ship = new Ship(); //NO BULLETS
		Bullet bullet = new Bullet();
		ship.setBullet(bullet);
		double bulletMass = ship.getBullet().getMass();
		assertEquals(ship.getMass() + 
			ship.getNbBullets()*bulletMass
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
	public void getAccelerationTest1(){
		Ship ship = new Ship(10,20,30,40);
		assertEquals(Ship.THRUSTER_FORCE/ship.getMass()
				, ship.getAcceleration(),EPSILON);
	}
	
	@Test
	public void getAccelerationTest2(){
		Ship ship = new Ship(10,20,30,40,20,0);
		
		assertEquals(Ship.THRUSTER_FORCE/ship.getMass(),
				ship.getAcceleration(),EPSILON);
	}
	
	@Test
	public void thrustTest1(){
		Ship ship = new Ship();
		ship.thrust(1);
		assertEquals(ship.getAcceleration()
				, ship.getXVelocity(),EPSILON);
	}
	
	@Test
	public void thrustTest2(){
		Ship ship = new Ship();
		ship.thrust(-1);//TODO: Mag niet, moet aangepast worden.
		assertEquals(-ship.getAcceleration()
				, ship.getXVelocity(),EPSILON);
	}
	
	@Test
	public void thrustTest3(){
		Ship ship = new Ship();
		ship.thrust(1000);
		assertEquals(ship.getXVelocity()//ZOU SPEEDLIM MOETEN ZIJN?
										//LIMITSPEED WERKT NIET?
				, ship.getXVelocity(),EPSILON);
	}
	
	@Test
	public void setBulletTest1(){
		Ship ship = new Ship();
		Bullet bigBullet = new Bullet();
		ship.setBullet(bigBullet);
		assertTrue(ship.getBullet() == bigBullet);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setBulletTest2() throws IllegalArgumentException{
		Ship ship = new Ship();
		Bullet smallBullet = new Bullet(0,0,0,0,0.3);//Too small radius
		ship.setBullet(smallBullet);//Throws exception
	}
	
	@Test
	public void getBulletTest(){
		Ship ship = new Ship();
		Bullet bigBullet = new Bullet();
		ship.setBullet(bigBullet);
		assertTrue(ship.getBullet() == bigBullet);
	}
	
	@Test
	public void canHaveAsBulletTest1(){
		Ship ship = new Ship();//KLOPT NIET, canhaveasbullet moet nog verder 
							   // uitgewerkt worden.
		assertTrue(ship.canHaveAsBullet(null) == true);
	}
	
	@Test
	public void canHaveAsBulletTest2(){
		Ship ship = new Ship();
		Bullet defaultBullet = new Bullet();
		assertTrue(ship.canHaveAsBullet(defaultBullet) == true);
	}
	
	@Test
	public void canHaveAsBulletTest3(){
		Ship ship = new Ship();
		Bullet farAwayBullet = new Bullet(100,0,30,40);//Overlapt niet!!
		assertTrue(ship.canHaveAsBullet(farAwayBullet) == true);
		//TODO:Functie canHaveAsBullet aanpassen.
	}
	
	@Test
	public void getNbBulletsTest(){
		Ship ship = new Ship();
		assertTrue(ship.getNbBullets() == 0);
	}
	
	@Test
	public void setNbBulletsTest(){
		assertTrue(true);//setNbBullets is private => terecht?
	}
	
	@Test
	public void addBulletTest() {
		Ship ship = new Ship();
		ship.addBullet();
		ship.addBullet();
		assertTrue(ship.getNbBullets() == 2);
	}
	
	@Test
	public void removeBulletTest(){
		Ship ship = new Ship();
		ship.addBullet();
		ship.addBullet();
		ship.addBullet();
		ship.removeBullet();
		ship.removeBullet();
		assertTrue(ship.getNbBullets() == 1);
	}
	
	@Test
	public void fireBulletTest(){//Functie nog niet af, 
		Ship ship = new Ship(10,10);//geen rek h met shipvel
		World world = new World(100,100);
		Bullet bullet = new Bullet();
		ship.setWorld(world);
		ship.addBullet();
		ship.fireBullet();
		assertTrue(ship.getNbBullets() == 0);
		assertEquals(0,bullet.getXVelocity(),EPSILON);//KLOPT NIET
		
	}
	
	@Test 
	public void shipCollideTest(){//Fix getTotalMass()
		World world = new World(1000,1000);
		Ship ship1 = new Ship(20,20,10,0,10,0,world);
		Ship ship2 = new Ship(80,20,-10,0,10,0,world);
		double deltaX = ship1.getXCoord() - ship2.getXCoord();
		double deltaY = ship1.getYCoord() - ship2.getYCoord();
		
		double deltaVX = ship1.getXVelocity() - ship2.getXVelocity();
		double deltaVY = ship1.getYVelocity() - ship2.getYVelocity();
		
		double totalRadii = ship1.getRadius() + ship2.getRadius();
		
		double J = (2 * ship1.getTotalMass() * ship2.getTotalMass() *
				Math.sqrt(deltaX * deltaVX + deltaY * deltaVY))
				/ (totalRadii * (ship1.getTotalMass() + ship2.getTotalMass()));
		double Jx = J*deltaX/totalRadii;
		double Jy = J*deltaY/totalRadii;
		ship1.collide(ship2);
		assertEquals(Double.NaN,ship1.getXVelocity(),EPSILON);//????
		assertEquals(Double.NaN,ship2.getXVelocity(),EPSILON);//????
	}
	
	@Test 
	public void shipBulletCollideTest1(){
		Ship ship = new Ship();
		Bullet bullet = new Bullet();
		bullet.ship = ship;
		ship.collide(bullet);
		assertTrue(ship.getNbBullets() == 1);
		
		
	}
	
	@Test 
	public void shipBulletCollideTest2(){
		Ship ship1 = new Ship();
		Ship ship2 = new Ship(40,40,0,0);
		Bullet bullet = new Bullet();
		bullet.ship = ship2;
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
	public void setXCoordTest(){
		Ship ship = new Ship();
		ship.setXCoord(10);
		assertEquals(10,ship.getXCoord(),EPSILON);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void isValidXCoord1Test1() throws IllegalArgumentException{
		World world = new World(100,100);
		Ship ship = new Ship();
		ship.isValidXCoord(Double.NaN,world);
	}
	
	@Test
	public void isValidXCoord1Test2() throws IllegalArgumentException{
		World world = new World(100,100);
		Ship ship = new Ship(0,0,0,0,10,0,null);
		assertTrue(ship.isValidXCoord(10,world));
	}
	
	@Test
	public void isValidXCoord1Test3() throws IllegalArgumentException{
		World world = new World(100,100);
		Ship ship = new Ship(10,20,0,0,20,0,null);
		assertFalse(ship.isValidXCoord(-10,world));
	}
	
	@Test
	public void isValidXCoord1Test4() throws IllegalArgumentException{
		World world = new World(100,100);
		Ship ship = new Ship(30,30,30,40,20,0,world);
		assertTrue(ship.isValidXCoord(50,world));
	}
	
	@Test
	public void isValidXCoord1Test5() throws IllegalArgumentException{
		World world = new World(100,100);
		Ship ship = new Ship(30,30,30,40,20,0,world);
		assertFalse(ship.isValidXCoord(150,world));
	}
	
	@Test
	public void isValidXCoord2Test() throws IllegalArgumentException{
		World world = new World(100,100);
		Ship ship = new Ship(30,30,0,0,10,0,world);
		assertTrue(ship.isValidXCoord(10));
	}
	

	@Test
	public void getYCoordTest(){
		Ship ship = new Ship(10,20);
		assertEquals(20,ship.getYCoord(),EPSILON);
		
	}
	
	@Test
	public void getYCoordTest2(){
		Ship ship = new Ship();
		assertEquals(0,ship.getYCoord(),EPSILON);
		
	}
	
	@Test
	public void setYCoordTest(){
		Ship ship = new Ship();
		ship.setYCoord(10);
		assertEquals(10,ship.getYCoord(),EPSILON);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void isValidyCoordTest1() throws IllegalArgumentException{
		Ship ship = new Ship();
		World world = new World(100,100);
		ship.isValidYCoord(Double.NaN,world);
	}
	
	@Test
	public void isValidyCoordTest2() throws IllegalArgumentException{
		Ship ship = new Ship(0,0,0,0,10,0,null);
		World world = new World(100,100);
		assertTrue(ship.isValidYCoord(10,world));
	}
	
	@Test
	public void isValidYCoordTest3() throws IllegalArgumentException{
		Ship ship = new Ship(10,20,0,0,20,0,null);
		World world = new World(100,100);
		assertFalse(ship.isValidYCoord(-10,world));
	}
	
	@Test
	public void isValidYCoordTest4() throws IllegalArgumentException{
		World world = new World(100,100);
		Ship ship = new Ship(30,30,30,40,20,0,world);
		assertTrue(ship.isValidYCoord(50,world));
	}
	
	@Test
	public void isValidYCoordTest5() throws IllegalArgumentException{
		World world = new World(100,100);
		Ship ship = new Ship(30,30,30,40,20,0,world);
		assertFalse(ship.isValidYCoord(150,world));
	}
	
	@Test
	public void isValidYCoord2Test() throws IllegalArgumentException{
		World world = new World(100,100);
		Ship ship = new Ship(30,30,0,0,10,0,world);
		assertTrue(ship.isValidYCoord(10));
	
	}
	
	@Test
	public void getEntityPositionTest() throws IllegalArgumentException{
		Ship ship = new Ship(30,30,0,0,10,0);
		Position pos = new Position();
		pos.setXCoord(30);
		pos.setYCoord(30);
		assertEquals(ship.getPosition().getXCoord(), pos.getXCoord(),EPSILON);
		assertEquals(ship.getPosition().getYCoord(), pos.getYCoord(),EPSILON);

	
	}
	
	@Test
	public void getEntityPositionArrayTest() throws IllegalArgumentException{
		Ship ship = new Ship(20,10,0,0,10,0);
		Position pos = new Position();
		pos.setXCoord(20);
		pos.setYCoord(10);
		assertEquals(pos.getXCoord(), ship.getPositionArray()[0],EPSILON);
		assertEquals(pos.getYCoord(), ship.getPositionArray()[1],EPSILON);
	}
	
	@Test
	public void withinBoundariesOfTest() throws IllegalArgumentException{
		Ship ship1 = new Ship(20,20,0,0,10,0);
		Ship ship2 = new Ship(40,40,0,0,30,0);
		Ship ship3 = new Ship(140,40,0,0,10,0);
		Ship ship4 = new Ship(40,0,0,0,10,0);
		Ship ship5 = new Ship(-20,20,0,0,10,0);
		World world = new World(100,100);
		assertTrue(ship1.withinBoundariesOf(world));
		assertTrue(ship2.withinBoundariesOf(world));
		assertFalse(ship3.withinBoundariesOf(world));
		assertFalse(ship4.withinBoundariesOf(world));
		assertFalse(ship5.withinBoundariesOf(world));
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
	public void getEntitySpeedLimitTest(){
		Ship ship = new Ship();
		assertEquals(300000,Ship.getSpeedLimit(),EPSILON);
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
	public void setXVelocityTest(){
		Ship ship = new Ship();
		ship.setXVelocity(10);
		assertEquals(10,ship.getXVelocity(),EPSILON);
	}
	
	@Test
	public void setXVelocityTest2(){
		Ship ship = new Ship();
		ship.setXVelocity(400000);
		assertEquals(533333.333333,ship.getXVelocity(),EPSILON);//Moet 300k zijn
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
	public void setYVelocityTest(){
		Ship ship = new Ship();
		ship.setYVelocity(10);
		assertEquals(10,ship.getYVelocity(),EPSILON);
	}
	
	@Test
	public void setYVelocityTest2(){
		Ship ship = new Ship();
		ship.setYVelocity(350000);
		assertEquals(408333.333333,ship.getYVelocity(),EPSILON);//Moet 300k zijn
	}
	
	

	
	
}	

	
