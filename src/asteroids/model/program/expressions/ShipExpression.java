package asteroids.model.program.expressions;
import asteroids.model.Ship;
import asteroids.part3.programs.SourceLocation;

public class ShipExpression extends Expression<Ship> {
	
	private Ship closestShip;
	private Ship thisShip = getProgram().getShip();
	private double closestDistanceSoFar = Double.POSITIVE_INFINITY;
	
	public ShipExpression(SourceLocation sourceLocation){
		super(sourceLocation);
	}
	public Ship evaluate(){
		
		for (Ship ship: getProgram().getShip().getWorld().getShipSet()){
			if (ship != getProgram().getShip()){
				if (thisShip.getDistanceBetweenCentres(ship) <  closestDistanceSoFar || closestShip == null){
					closestShip = ship;
				}
			}
		}
		return closestShip;

		
	}

}
