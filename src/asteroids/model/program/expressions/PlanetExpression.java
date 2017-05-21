package asteroids.model.program.expressions;

import asteroids.model.MinorPlanet;
import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class PlanetExpression extends Expression<MinorPlanet> {
	
	
	public PlanetExpression(SourceLocation sourceLocation){
		super(sourceLocation);
	}
	
	public MinorPlanet evaluate(){
		MinorPlanet closestMinorPlanet = null;
		double closestDistanceSoFar = Double.POSITIVE_INFINITY;
		for (MinorPlanet MinorPlanet: getProgram().getShip().getWorld().getSomeEntitySet(MinorPlanet.class)){
			if (getProgram().getShip().getDistanceBetweenCentres(MinorPlanet) <  closestDistanceSoFar 
					|| closestMinorPlanet == null) {
				closestMinorPlanet = MinorPlanet;
			}
		}
		return closestMinorPlanet;
	}

}