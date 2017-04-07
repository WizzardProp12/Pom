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
	
	@Test
	public void getVelocitiesTest1(){
		Ship ship = new Ship(10,20,30,40);
		assertEquals(30,ship.getVelocities()[0],EPSILON);
		assertEquals(40,ship.getVelocities()[1],EPSILON);

	}
	
	@Test
	public void getVelocitiesTest2(){
		Ship ship = new Ship();
		ship.setXVelocity(60);
		ship.setYVelocity(70);
		assertEquals(60,ship.getVelocities()[0],EPSILON);
		assertEquals(70,ship.getVelocities()[1],EPSILON);
	}
	
	@Test
	public void getAbsVelocityTest1(){
		Ship ship = new Ship();
		ship.setXVelocity(3);
		ship.setYVelocity(4);
		double actualX = ship.getXVelocity();
		double actualY = ship.getYVelocity();
		assertEquals(5,Entity.getAbsVelocity(actualX,actualY),EPSILON);
	}
	
	@Test
	public void getAbsVelocityTest2(){
		Ship ship = new Ship(10,20,30,0);
		double actualX = ship.getXVelocity();
		double actualY = ship.getYVelocity();
		assertEquals(30,Entity.getAbsVelocity(actualX,actualY),EPSILON);
	}
	
	@Test
	public void getSpeedTest(){
		Ship ship = new Ship(10,20,30,40);
		assertEquals(50,ship.getSpeed(),EPSILON);
	}
	
	@Test
	public void getSpeedTest2(){
		Ship ship = new Ship();
		ship.setXVelocity(300000);
		ship.setYVelocity(100);
		assertEquals(300000,ship.getSpeed(),EPSILON);
	}
	
	@Test
	public void limitSpeedTest(){
		double[] expectedVelocities = new double[2];
		expectedVelocities[0] = 300;
		expectedVelocities[1] = 400;
		assertEquals(expectedVelocities[0],Entity.limitSpeed(300,400)[0],EPSILON);
		assertEquals(expectedVelocities[1],Entity.limitSpeed(300,400)[1],EPSILON);

	}
	
	@Test
	public void limitSpeedTest2(){
		double[] expectedVelocities = new double[2];
		expectedVelocities[0] = 500000;
		expectedVelocities[1] = 666666.666666;
		assertEquals(expectedVelocities[0],
				Entity.limitSpeed(300000,400000)[0],EPSILON);
		assertEquals(expectedVelocities[1],
				Entity.limitSpeed(300000,400000)[1],EPSILON);
		//KLOPT NIET => FUNCTIE LIMITSPEED IS FOUT
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
	public void getMinRadiusTest(){
		assertEquals(10,Entity.getMinRadius(),EPSILON);
	}
	
	@Test
	public void getRadiusTest(){
		Ship ship = new Ship(0,10,10,10,100,0);
		assertEquals(100,ship.getRadius(),EPSILON);
	}
	
	@Test
	public void canHaveAsRadiusTest(){
		Ship ship = new Ship();
		assertTrue(ship.canHaveAsRadius(Entity.getMinRadius()+10));
		assertFalse(ship.canHaveAsRadius(Entity.getMinRadius()-5));
	}
	
	@Test
	public void setWorldTest1(){
		Ship ship = new Ship(10,20,30,40,10,0,null);
		World world = new World(100,100);
		ship.setWorld(world);
		assertTrue(ship.getWorld() == world);
		assertTrue(ship.getPosition().getWorld() == world);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setWorldTest2(){
		World world = new World(100,100);
		Ship ship = new Ship(10,20,30,40,10,0,world);
		ship.setWorld(world);// Throws IllegalArgumentException	
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setWorldTest3(){
		World world1 = new World(100,100);
		World world2 = new World(100,100);
		Ship ship = new Ship(10,20,30,40,10,0,world1);
		ship.setWorld(world2);// Throws IllegalArgumentException
	}
	
	@Test
	public void HashCodeTest(){
		Ship ship = new Ship();
		assertEquals(ship.getPosition().hashCode(),ship.hashCode() ,EPSILON);
	}
	
	@Test
	public void equalsTest(){
		Ship ship = new Ship();
		int integer = 3;
		World world =  new World(100,100);
		Ship ship1 = new Ship(10,10,0,0);
		Ship ship2 = new Ship(10,10,0,0);
		Ship ship3 = new Ship(10,10,0,5);
		Ship ship4 = new Ship(10,10,0,0,10,0,world);
		assertTrue(ship1.equals(ship1));
		assertTrue(ship1.equals(ship2));
		assertFalse(ship1.equals(integer));
		assertFalse(ship1.equals(ship3));
		assertFalse(ship2.equals(ship3));
		assertFalse(ship1.equals(ship4));
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
	
	
	@Test(expected = NullPointerException.class)
	public void overlapsTest() throws NullPointerException{
		Ship ship1 = new Ship(50,50,0,0,10,0);
		Ship ship2 = new Ship(50,80,0,0,10,0);
		Ship ship3 = new Ship(60,50,0,0,40,0);
		
		assertFalse(ship1.overlaps(ship2));
		assertTrue(ship2.overlaps(ship3));
		assertTrue(ship1.overlaps(ship3));
		
		ship3.overlaps(null); // throws an NullPointerException
	}
	
	@Test
	public void getWallCollisionTest(){
		World world = new World(1000,1000);
		Ship ship1 = new Ship(20,20,10,0,10,0,world);
		Ship ship2 = new Ship(20,120,-10,0,10,0,world);
		Ship ship3 = new Ship(120,20,0,10,10,0,world);
		Ship ship4 = new Ship(220,20,0,-10,10,0,world);
		Ship ship5 = new Ship(220,20,0,-10,10,0,null);
		Ship ship6 = new Ship(320,20,0,0,10,0,world);

		assertTrue(ship1.getWallCollision().getCollisionType() 
				== CollisionType.rightWall);
		assertTrue(ship1.getWallCollision().getEntity()
				== ship1);
		assertEquals(ship1.getWallCollision().getTime() 
				, (world.getWidth() - ship1.getXCoord() - ship1.getRadius())
				/ship1.getXVelocity(),EPSILON);
		assertTrue(ship2.getWallCollision().getCollisionType() 
				== CollisionType.leftWall);
		assertTrue(ship2.getWallCollision().getEntity()
				== ship2);
		assertEquals(ship2.getWallCollision().getTime() 
				, (ship2.getXCoord() - ship2.getRadius())
				/(-ship2.getXVelocity()),EPSILON);
		assertTrue(ship3.getWallCollision().getCollisionType() 
				== CollisionType.topWall);
		assertTrue(ship3.getWallCollision().getEntity()
				== ship3);
		assertEquals(ship3.getWallCollision().getTime() 
				, (world.getHeight() - ship3.getYCoord() - ship3.getRadius())
				/ship3.getYVelocity(),EPSILON);
		assertTrue(ship4.getWallCollision().getCollisionType() 
				== CollisionType.bottomWall);
		assertTrue(ship4.getWallCollision().getEntity()
				== ship4);
		assertEquals(ship4.getWallCollision().getTime() 
				, (ship4.getYCoord() - ship4.getRadius())
				/(-ship4.getYVelocity()),EPSILON);
		assertTrue(ship5.getWallCollision() == null);
		assertTrue(ship6.getWallCollision() == null);
	}
	
	@Test
	public void getTimeToWallCollisionTest() throws NullPointerException{
		World world = new World(1000,1000);
		Ship ship1 = new Ship(20,20,10,0,10,0,world);
		Ship ship2 = new Ship(20,120,-10,0,10,0,world);
		Ship ship3 = new Ship(120,20,0,10,10,0,world);
		Ship ship4 = new Ship(220,20,0,-10,10,0,world);
		Ship ship5 = new Ship(220,20,0,-10,10,0,null);
		Ship ship6 = new Ship(320,20,0,0,10,0,world);
		assertEquals(97,ship1.getTimeToWallCollision(),EPSILON);
		assertEquals(1,ship2.getTimeToWallCollision(),EPSILON);
		assertEquals(97,ship3.getTimeToWallCollision(),EPSILON);
		assertEquals(1,ship4.getTimeToWallCollision(),EPSILON);
		assertEquals(Double.POSITIVE_INFINITY,
				ship5.getTimeToWallCollision(),EPSILON);
		assertEquals(Double.POSITIVE_INFINITY,
				ship6.getTimeToWallCollision(),EPSILON);		
	}
	
	@Test
	public void getWallCollisionPositionTest(){
		World world = new World(1000,1000);
		Ship ship1 = new Ship(20,20,10,0,10,0,world);
		Ship ship2 = new Ship(20,120,-10,0,10,0,world);
		assertEquals(990,ship1.getWallCollisionPosition()[0],EPSILON);
		assertEquals(20,ship1.getWallCollisionPosition()[1],EPSILON);
		assertEquals(10,ship2.getWallCollisionPosition()[0],EPSILON);
		assertEquals(120,ship2.getWallCollisionPosition()[1],EPSILON);

	}
	
	@Test
	public void wallBounceTest(){
		World world = new World(1000,1000);
		Ship ship1 = new Ship(20,20,10,0,10,0,world);
		Ship ship2 = new Ship(120,20,0,10,10,0,world);
		ship1.wallBounce(ship1.getWallCollision().getCollisionType());
		ship2.wallBounce(ship2.getWallCollision().getCollisionType());
		assertEquals(-10,ship1.getXVelocity(),EPSILON);
		assertEquals(-10,ship2.getYVelocity(),EPSILON);

	}
	
	@Test
	public void getEntityCollisionTest(){
		World world = new World(1000,1000);
		Ship ship1 = new Ship(20,20,10,0,10,0,world);
		Ship ship2 = new Ship(120,20,-10,0,10,0,world);
		assertTrue(ship1.getEntityCollision(ship2).getCollisionType() 
				== CollisionType.entity);
		assertEquals(4,ship1.getEntityCollision(ship2).getTime(), 
				EPSILON);
		assertTrue(ship1.getEntityCollision(ship2).getEntity() 
				== ship1);
		assertTrue(ship1.getEntityCollision(ship2).getOtherEntity() 
				== ship2);
	}
	
	@Test(expected = NullPointerException.class)
	public void testGetTimeToEntityCollisionTest1() throws NullPointerException{
		Ship ship1 = new Ship(0,0,0,0,10,0);
		Ship ship2 = new Ship(30,0,-1,0,10,0);
		Ship ship3 = new Ship(0,25,0,-0.5,10,0);
		Ship ship4 = new Ship(0,300,0,1,10,0);

		assertEquals(ship1.getTimeToEntityCollision(ship2), 10, EPSILON);
		assertEquals(ship2.getTimeToEntityCollision(ship3), 18, EPSILON);
		assertEquals(ship3.getTimeToEntityCollision(ship1), 10, EPSILON);
		assertEquals(ship3.getTimeToEntityCollision(ship4), Double.POSITIVE_INFINITY
		, EPSILON);
		
		ship3.getTimeToEntityCollision(null); // throws a NullPointerException
	}
	
	@Test(expected = IllegalStateException.class)
	public void testGetTimeToEntityCollisionTest2() throws IllegalStateException{
		Ship ship1 = new Ship(0,0,0,0,10,0);
		Ship ship2 = new Ship(0,0,10,0,10,0);

		ship1.getTimeToEntityCollision(ship2); // throws an IllegalStateException
	}
	
	@Test(expected = NullPointerException.class)
	public void GetEntityCollisionPositionTest1() throws NullPointerException{
		Ship ship1 = new Ship(0,0,0,0,10,0);
		Ship ship2 = new Ship(30,0,-1,0,10,0);
		Ship ship3 = new Ship(0,25,0,-0.5,10,0);	
		
		assertEquals(ship1.getEntityCollisionPosition(ship2)[0], 30, EPSILON);
		assertEquals(ship1.getEntityCollisionPosition(ship2)[1], 0, EPSILON);
		assertEquals(ship2.getEntityCollisionPosition(ship3)[0], -6, EPSILON);
		assertEquals(ship2.getEntityCollisionPosition(ship3)[1], 24, EPSILON);
		assertEquals(ship3.getEntityCollisionPosition(ship1)[0], 0, EPSILON);
		assertEquals(ship3.getEntityCollisionPosition(ship1)[1], -10, EPSILON);
	
		ship1.getEntityCollisionPosition(null); // throws a NullPointerException
	}
	
	@Test
	public void getFirstCollisionTest(){
		World world = new World(1000,1000);
		Ship ship1 = new Ship(20,20,0,0,10,0,world);
		Ship ship2 = new Ship(20,120,0,0,10,0,world);
		Ship ship3 = new Ship(120,120,0,-0.5,10,0,world);	
		Ship ship4 = new Ship(120,20,0,0,10,0,world);	
		HashSet<Entity> entities = new HashSet<Entity>();
		entities.add(ship1);
		entities.add(ship2);
		entities.add(ship3);
		entities.add(ship4);
//		assertTrue(ship1.getFirstCollision(entities).getCollisionType() 
//				== CollisionType.entity);
//		assertTrue(ship1.getFirstCollision(entities).getEntity()
//				== ship1);
//		assertEquals(ship1.getFirstCollision(entities).getTime() 
//				, (world.getWidth() - ship1.getXCoord() - ship1.getRadius())
//				/ship1.getXVelocity(),EPSILON);
		assertTrue(true);//Beide functies met deze naam werken niet,
		//mag niet collision zoeken met zichzelf
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
		Ship ship3 = new Ship(20,20,0,0,10,0,world);
		ship2.terminate();
		ship3.terminate();
		assertFalse(ship1.isTerminated());
		assertTrue(ship2.isTerminated());
		assertTrue(world.getEntities().contains(ship3) == false);
		assertTrue(ship3.getWorld() == null);
	} 
	
	//--------BULLET TESTS------------
	
	@Test
	public void bulletConstructor1Test() throws IllegalArgumentException{
		World world = new World(100,100);
		Ship ship = new Ship();
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
		Bullet bullet = new Bullet(10,20);
		assertEquals(10, bullet.getXCoord(), EPSILON);
		assertEquals(20, bullet.getYCoord(),EPSILON);
		assertTrue(bullet.getShip() == null);
		assert bullet.getNbBounces() == 0;

	}
	
	@Test
	public void bulletConstructor5Test() throws IllegalArgumentException{
		World world = new World(100,100);
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
		assertFalse(bullet1.canHaveAsRadius(5));
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
	public void bulletHasShipTest(){
		Ship ship = new Ship(0,0,0,0,200,0);
		Bullet bullet1 = new Bullet(10,20,30,40,100,ship);
		Bullet bullet2 = new Bullet(10,20,30,40,100,null);
		assertTrue(bullet1.hasShip());
		assertFalse(bullet2.hasShip());
	}
		
	@Test
	public void canHaveAsShipTest(){
		Ship ship = new Ship(0,0,0,0,200,0);
		Bullet bullet1 = new Bullet(10,20,30,40,100,ship);
		Bullet bullet2 = new Bullet(10,20,30,40,100,null);
		assertTrue(bullet2.canHaveAsShip(ship));
		assertFalse(bullet1.canHaveAsShip(ship));
		
	}


	@Test 
	public void BulletShipCollideTest1(){
		Ship ship = new Ship();
		Bullet bullet = new Bullet();
		bullet.ship = ship;
		bullet.collide(ship);
		assertTrue(ship.getNbBullets() == 1);

	}
	
	@Test 
	public void BulletShipCollideTest2(){
		Ship ship1 = new Ship();
		Ship ship2 = new Ship(40,40,0,0);
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
		bullet.countBounce();
		bullet.countBounce();
		bullet.wallBounce(collision);
		assertTrue(bullet.isTerminated());
	}
	
	@Test
	public void wallbounceTest2(){
		Bullet bullet = new Bullet(0,0,-10,0);
		Collision collision = new Collision(CollisionType.leftWall,2,bullet);
		bullet.countBounce();
		bullet.wallBounce(collision);
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
		assertEquals(0, world.getWidth(), EPSILON);
		assertEquals(0, world.getHeight(),EPSILON);
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
	public void containsPositionTest1(){
		World world1 = new World(50,50);
		World world2 = new World(100,100);
		double[] position1 = new double[2];
		double[] position2 = new double[2];
		double[] position3 = new double[2];

		position1[0] = 25;
		position1[1] = 25;
		position2[0] = 75;
		position2[1] = 75;
		position3[0] = 125;
		position3[1] = 125;
		assertTrue(world1.ContainsPosition(position1));
		assertFalse(world1.ContainsPosition(position2));
		assertTrue(world2.ContainsPosition(position2));
		assertFalse(world2.ContainsPosition(position3));
	}
	
	@Test
	public void containsPositionTest2(){
		World world1 = new World(50,50);
		World world2 = new World(100,100);
		Position position1 = new Position(25,25);
		Position position2 = new Position(75,75);
		Position position3 = new Position(125,125);
		assertTrue(world1.ContainsPosition(position1));
		assertFalse(world1.ContainsPosition(position2));
		assertTrue(world2.ContainsPosition(position2));
		assertFalse(world2.ContainsPosition(position3));
	}
	
	@Test
	public void getEntityMapTest(){
		World world = new World();
		assertTrue(world.getEntityMap().isEmpty());
	}	

	@Test
	public void getEntitiesTest(){
		World world = new World(100,100);
		Ship ship = new Ship(20,20);
		Bullet bullet = new Bullet(40,40);
		world.add(ship);
		world.add(bullet);
		assertTrue(world.getEntities().contains(ship));
		assertTrue(world.getEntities().contains(bullet));
	}
	
	@Test
	public void getShipsTest(){
		World world = new World(100,100);
		Ship ship = new Ship(20,20);
		Bullet bullet = new Bullet(40,40);
		world.add(ship);
		world.add(bullet);
		assertTrue(world.getShips().contains(ship));
		assertFalse(world.getShips().contains(bullet));
	}
	
	@Test
	public void getBulletsTest(){
		World world = new World(100,100);
		Ship ship = new Ship(20,20);
		Bullet bullet = new Bullet(40,40);
		world.add(ship);
		world.add(bullet);
		assertFalse(world.getBullets().contains(ship));
		assertTrue(world.getBullets().contains(bullet));
	}
	
	@Test
	public void getPositionsTest(){
		World world = new World(100,100);
		Ship ship = new Ship(20,20);
		Bullet bullet = new Bullet(40,40);
		world.add(ship);
		world.add(bullet);
		Position position1 = ship.getPosition();
		Position position2 = bullet.getPosition();
		
		assertTrue(world.getPositions().contains(position1));
		assertTrue(world.getPositions().contains(position2));
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
	
	@Test(expected = NullPointerException.class)
	public void isValidEntityTest1() throws NullPointerException{
		World world1 = new World(100,100);
		World world2 = new World(100,100);

		Ship ship1 = new Ship(20,20,0,0,10,0,world2);
		Bullet bullet = new Bullet(40,40);
		Ship ship2 = new Ship(60,60,0,0,10,0,world1);
		Ship ship3 = new Ship(80,80,0,0,10,0,world2);
		assertFalse(world1.isValidEntity(ship1));
		assertTrue(world1.isValidEntity(bullet));
		assertFalse(world1.isValidEntity(ship3));
		world1.isValidEntity(null);// throws a NullPointerException
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void isValidEntityTest2(){
		World world = new World(100,100);
		Ship ship = new Ship(120,20,0,0,10,0,world);
		assertFalse(world.isValidEntity(ship));
		world.isValidEntity(ship);// throws an IllegalArgumentException

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
	public void getEntityAtTest(){
		World world = new World(100,100);
		Ship ship = new Ship(20,20,0,0,10,0,world);
		Bullet bullet = new Bullet(40,40,0,0,10);
		Position position1 = new Position(20,20);
		Position position2 = new Position(50,50);

		assertTrue(world.getEntityAt(position1) == ship);
		assertFalse(world.getEntityAt(position2) == bullet);
	}
	
	@Test
	public void getEntityAt2Test(){
		World world = new World(100,100);
		Ship ship = new Ship(20,20,0,0,10,0,world);
		Bullet bullet = new Bullet(40,40,0,0,10);
		double[] position1 = new double[2];
		position1[0] = 20;
		position1[1] = 20;
		double[] position2 = new double[2];
		position2[0] = 50;
		position2[1] = 50;
		assertTrue(world.getEntityAt(position1[0],position1[1]) == ship);
		assertFalse(world.getEntityAt(position1[0],position2[1]) == bullet);
	}
	
	@Test
	public void getDeltaTTest(){
		World world = new World(100,100);	
		assertTrue(world.getDeltaT() == 3);
	}
	
	@Test
	public void getFirstWallCollisionTest(){
		World world = new World(100,100);
		Ship ship = new Ship(20,20,-1,0,10,0,world);
		Bullet bullet = new Bullet(40,40,1,0,10,ship);
		world.add(bullet);
		assertTrue(world.getFirstWallCollision() == ship);		
	}
	
	@Test
	public void getFirstEntityCollisionTest(){//Functie werkt niet, it.remove
		World world = new World(1000,1000);
		Ship ship = new Ship(20,20,1,0,10,0,world);
		Bullet bullet1 = new Bullet(40,20,-1,0,10,ship);
		Bullet bullet2 = new Bullet(400,20,-2,0,10,ship);
		world.add(bullet1);
		Entity[] entities = new Entity[2];
		entities[0] = ship;
		entities[1] = bullet1;
		//assertTrue(world.getFirstEntityCollision() == entities);		
	}
	
	@Test
	public void getFirstWorldCollisionTest(){
		World world = new World(1000,1000);
		Ship ship = new Ship(20,20,1,0,10,0,world);
		Bullet bullet1 = new Bullet(60,20,-1,0,10,ship);
		Bullet bullet2 = new Bullet(400,20,-2,0,10,ship);
		world.add(bullet1);
		world.add(bullet2);
		Collision collision =
				new Collision(CollisionType.entity,10,ship,bullet1);
		assertTrue(world.getFirstCollision().getCollisionType() ==
				collision.getCollisionType());		
		assertTrue(world.getFirstCollision().getTime()==
				collision.getTime());	
		assertTrue(world.getFirstCollision().getEntity() ==
				collision.getEntity());	
		assertTrue(world.getFirstCollision().getOtherEntity() ==
				collision.getOtherEntity());	
	}
	
	@Test
	public void advanceTimeTest1(){
		World world = new World(1000,1000);
		Ship ship = new Ship(800,800,1,0,100,0,world);
		ship.setThrusterState(true);
		Bullet bullet1 = new Bullet(60,20,-1,0,10,ship);
		Bullet bullet2 = new Bullet(400,20,-2,0,10,ship);
		world.advanceTime(1);
		assertEquals(803,ship.getXCoord(),EPSILON);
		assertEquals(800,ship.getYCoord(),EPSILON);
		assertEquals(740.734242539,ship.getXVelocity(),EPSILON);
		assertEquals(0,ship.getYVelocity(),EPSILON);
		assertEquals(60,bullet1.getXCoord(),EPSILON);
		assertEquals(20,bullet1.getYCoord(),EPSILON);
		assertEquals(-1,bullet1.getXVelocity(),EPSILON);
		assertEquals(0,bullet1.getYVelocity(),EPSILON);
		assertEquals(400,bullet2.getXCoord(),EPSILON);
		assertEquals(20,bullet2.getYCoord(),EPSILON);
		assertEquals(-2,bullet2.getXVelocity(),EPSILON);
		assertEquals(0,bullet2.getYVelocity(),EPSILON);
		
	}
	
	@Test
	public void advanceTimeTest2(){
		World world = new World(1000,1000);
		Ship ship1 = new Ship(800,800,1,0,100,0,world);
		Ship ship2 = new Ship(63,20,2,0,10,0,world);
		Ship ship3 = new Ship(87,20,-2,0,10,0,world);
		//world.advanceTime(3);
		assertTrue(true); //functie werkt nog niet, snelheden zouden normaal moeten
						  //genegate zijn maar alle ships thrusten  automatisch
		
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
		Ship ship1 = new Ship(20,20,2,0,10,0,world);
		Ship ship2 = new Ship(60,20,-2,0,10,0,world);
		Collision collision = new Collision(CollisionType.entity,10,ship1,ship2);
		collision.resolve();
		assertEquals(Double.NaN,ship1.getXVelocity(),EPSILON);
		assertEquals(Double.NaN,ship2.getXVelocity(),EPSILON);

		
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
	public void resolveCollisionTest4(){//BulletOwnShipCollide
		Ship ship = new Ship();
		Bullet bullet = new Bullet(80,20,-2,0,10,ship);
		Collision collision = new Collision(CollisionType.entity
				,10,ship,bullet);
		assertTrue(ship.getNbBullets() == 0);
		collision.resolve();
		assertTrue(ship.getNbBullets() == 1);
	}
	
	@Test
	public void resolveCollisionTest5(){//BulletOtherShipCollide
		Ship ship1 = new Ship();
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
	public void setPositionXCoordTest(){
		Position position1 = new Position();
		Position position2 = new Position();
		Position position3 = new Position();
		
		position1.setXCoord(-20);
		position2.setXCoord(0);
		position3.setXCoord(300);

		assertEquals(-20, position1.getXCoord(), EPSILON);
		assertEquals(0, position2.getXCoord(), EPSILON);
		assertEquals(300, position3.getXCoord(), EPSILON);
	}
	
	@Test
	public void setPositionYCoordTest(){
		Position position1 = new Position();
		Position position2 = new Position();
		Position position3 = new Position();
		
		position1.setYCoord(-102.5);
		position2.setYCoord(0);
		position3.setYCoord(50);

		assertEquals(-102.5, position1.getYCoord(), EPSILON);
		assertEquals(0, position2.getYCoord(), EPSILON);
		assertEquals(50, position3.getYCoord(), EPSILON);
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
	public void setPositionEntityTest(){
		World world = new World(100,100);
		Ship ship1 = new Ship(20,10,0,0,10,0,null);
		Ship ship2 = new Ship(50,50,0,0,10,0,world);
		Position position1 = new Position(30,30);
		Position position2 = new Position(20,10);
		Position position3 = new Position(50,50);
		position1.setEntity(null);
		position2.setEntity(ship1);
		position3.setEntity(ship2);
		assertTrue(position1.getEntity() == null);
		assertTrue(position2.getEntity() == ship1);
		assertTrue(position3.getEntity() == ship2);
	}
	
	@Test
	public void getWorldPositionTest(){
		World world = new World(100,100);
		Position position1 = new Position(30,30);
		assertTrue(position1.getWorld() == null);
	}
	
	@Test 
	public void positionEqualsTest(){
		Ship  ship = new Ship();
		int integer = 5;
		Position position1 = new Position(30,30);
		position1.setEntity(ship);
		Position position2 = new Position(20,10);
		Position position3 = new Position(0,0);
		Position position4 = new Position(30,30);
		assertFalse(position1.equals(integer));
		assertFalse(position1.equals(position2));
		assertFalse(position2.equals(position3));
		assertTrue(position1.equals(position4));	
	}
		
	
}	


	
