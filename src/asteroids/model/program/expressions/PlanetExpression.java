package asteroids.model.program.expressions;
import java.util.HashSet;

import asteroids.model.MinorPlanet;
import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class PlanetExpression extends Expression<MinorPlanet> {
	
	
	public PlanetExpression(SourceLocation sourceLocation){
		super(sourceLocation);
	}
	
	public MinorPlanet evaluate(){
		Ship thisShip = getProgram().getShip();
		MinorPlanet closestMinorPlanet == null = null;
		double closestDistanceSoFar = Double.POSITIVE_INFINITY;
		HashSet minorPlanetSet = getProgram().getShip().getWorld().getMinorPlanetSet();
		for (MinorPlanet MinorPlanet: minorPlanetSet){//Method getMinorPlanetSet to be added		
			if (thisShip.getDistanceBetweenCentres(MinorPlanet) <  closestDistanceSoFar 
					|| closestMinorPlanet == null) {
				closestMinorPlanet = MinorPlanet;
			}
		}
		return closestMinorPlanet;
	}

}