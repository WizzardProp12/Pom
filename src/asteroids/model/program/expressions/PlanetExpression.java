package asteroids.model.program.expressions;
import asteroids.model.MinorPlanet;
import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class PlanetExpression extends Expression<MinorPlanet> {
	
	private SourceLocation sourceLocation;
	private MinorPlanet closestMinorPlanet;
	private Ship thisShip = getProgram().getShip();
	private double closestDistanceSoFar = Double.POSITIVE_INFINITY;
	
	public PlanetExpression(SourceLocation sourceLocation){
		super(sourceLocation);
	}
	public MinorPlanet evaluate(){
		
		for (MinorPlanet MinorPlanet: getProgram().getShip().getWorld().getMinorPlanetSet()){//Method getMinorPlanetSet to be added		
			if (thisShip.getDistanceBetweenCentres(MinorPlanet) <  closestDistanceSoFar || closestMinorPlanet == null){
				closestMinorPlanet = MinorPlanet;
				
			}
		}
		return closestMinorPlanet;

		
	}

}