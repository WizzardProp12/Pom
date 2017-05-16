package asteroids.model.program.expressions;
import asteroids.model.Planetoid;
import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class PlanetoidExpression extends Expression<Planetoid> {
	
	private SourceLocation sourceLocation;
	private Planetoid closestPlanetoid;
	private Ship thisShip = getProgram().getShip();
	private double closestDistanceSoFar = Double.POSITIVE_INFINITY;
	
	public PlanetoidExpression(SourceLocation sourceLocation){
		super(sourceLocation);
	}
	public Planetoid evaluate(){
		
		for (Planetoid Planetoid: getProgram().getShip().getWorld().getPlanetoidSet()){//Method getPlanetoidSet to be added		
			if (thisShip.getDistanceBetweenCentres(Planetoid) <  closestDistanceSoFar || closestPlanetoid == null){
				closestPlanetoid = Planetoid;
				
			}
		}
		return closestPlanetoid;

		
	}

}