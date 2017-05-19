package asteroids.model;

import java.util.HashSet;

public class TestBestand {
	
	public static void main(String[] args) {
		World world = new World(500000,500000);
		Ship ship1 = new Ship(100,100, 0,0, 50);
		Ship ship2 = new Ship(500,500, 0,0, 100);
		world.add(ship1);
		world.add(ship2);
		HashSet<Ship> a = new HashSet<Ship>(world.getSomeEntitySet(Ship.class));
		System.out.println(a.size());
	}
}
