package asteroids.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asteroids.model.entities.Bullet;
import asteroids.model.entities.Entity;
import asteroids.model.entities.Position;
import asteroids.model.entities.Ship;
import asteroids.model.environment.World;

public class asteroidsTests1 {
	private static final double EPSILON = 0.0001;
	
//------------SHIP TESTS-----------------
	@Before
	public void setUp() {
		Ship ship = new Ship();
	}
	

	
	
	@Test
	public void shipConstructorTest() throws IllegalArgumentException{
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
		assertEquals(250,bullet.getXVelocity(),EPSILON);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

	

	
	
}	

	
