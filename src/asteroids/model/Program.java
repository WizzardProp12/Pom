package asteroids.model;

import java.util.List;

public class Program {
	
	private List<Function> functionList;
	private Statement main;
	private Ship ship;

	
	public Program(List<Function> functions,Statement main){
		this.main = main;
		this.functionList = functionList;	
}
	
	public void execute(double time){
		main.execute();
	}
	
	//Sets the ship that runs this program.
	private void setShip(Ship ship){
		this.ship = ship;
	}
	
	//Gets the ship that runs this program.
	public Ship getShip(){
		return this.ship;
		
	}
	
	
	
	
}