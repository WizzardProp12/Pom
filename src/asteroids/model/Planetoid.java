package asteroids.model;

public class Planetoid extends MinorPlanet {
	
	// CONSTRUCTORS
	
	public Planetoid(double xCoord, double yCoord, double xVelocity, double yVelocity, double radius, World world) {
		super(xCoord, yCoord, xVelocity, yVelocity, radius, world);
	
	}
	
	public void move(double time) throws IllegalArgumentException {
		super.move(time);
		shrink(getSpeed() * time * getShrinkingPercentage())
	}
	
	// MASS (total)
	
	private final double density = 0.917 * Math.pow(10, 12);
	
	@Basic @Immutable @Raw
	public abstract double getDensity();
	
	@Basic @Raw
	public double getMass() {
		return (4/3)*Math.PI*Math.pow(getRadius(), 3)*getDensity();
	}
	
	// SHRINKING AND DISSOLVING
	
	public static double SHRINKING_PERCENTAGE = 0.0001;
	
	public static double getShrinkingPercentage() {
		return SHRINKING_PERCENTAGE;
	}
	
	public void shrink(double percentage) {
		double newRadius = getRadius() * 0.01 * percentage;
		if (newRadius < getMinRadius()) {
			terminate();
		} else {
			setRadius(newRadius);
		}
	}
	
	public void dissolve() {
		terminate();
		
		if (getRadius() >= 30 and getWorld() != null) { // spawn two asteroids
			double radius = getRadius() / 2;
			double direction = Math.random() * 2 * Math.PI;
			double xVelocity = Math.cos(direction) * 1.5 * getSpeed();
			double yVelocity = Math.sin(direction) * 1.5 * getSpeed();
			double[] velocities = limitSpeed(xVelocity, yVelocity);
			double deltaX = Math.cos(direction) * 0.75 * getRadius();
			double deltaY = Math.sin(direction) * 0.75 * getRadius();
			double x = getXCoord();
			double y = getYCoord();

			Asteroid a1 = new Asteroid(x + deltaX, y + deltaY, velocities[0], velocities[1], radius);
			Asteroid a2 = new Asteroid(x - deltaX, y - deltaY, -velocities[0], -velocities[1], radius);
			if ( a1.canBePlacedIn(getWorld()) ) getWorld().add(a1);
			if ( a2.canBePlacedIn(getWorld()) ) getWorld().add(a2);
		}
	}
	
}
