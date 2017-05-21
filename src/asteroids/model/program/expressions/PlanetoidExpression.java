package asteroids.model.program.expressions;
import asteroids.model.Planetoid;
import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class PlanetoidExpression extends Expression<Planetoid> {
	
	private SourceLocation sourceLocation;
	private Planetoid closestPlanetoid;
	private double closestDistanceSoFar = Double.POSITIVE_INFINITY;
	
	public PlanetoidExpression(SourceLocation sourceLocation){
		super(sourceLocation);
	}
	public Planetoid evaluate(){
		
		for (Planetoid Planetoid: getProgram().getShip().getWorld().getSomeEntitySet(Planetoid.class)){
			if (getProgram().getShip().getDistanceBetweenCentres(Planetoid) <  closestDistanceSoFar || closestPlanetoid == null){
				closestPlanetoid = Planetoid;
				
			}
		}
		return closestPlanetoid;

		
	}

}