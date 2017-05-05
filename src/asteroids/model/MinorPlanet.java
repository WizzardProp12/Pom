package asteroids.model;

public abstract class MinorPlanet extends Entity{
	
	public MinorPlanet(double xCoord, double yCoord, double xVelocity, double yVelocity, double radius, World world){		 
		super(xCoord, yCoord, xVelocity, yVelocity, radius,world);
	}
	

	@Override
	public double getDefaultRadius() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean canHaveAsRadius(double radius) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getDensity() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	double getMass() {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
