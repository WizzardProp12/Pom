package asteroids.model;

import java.util.HashMap;
import java.util.List;

public class Program {
	
	private List<Function> functionList;
	private Statement main;
	private Ship ship;
	private double timeLeft;

	
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
	
	public void decreaseTimeLeft(double time){
		timeLeft -= time;
		
	}
	
	protected HashMap<String, Object> variableMap = new HashMap<String, Object>();

	
	public HashMap<String,Object> getVariableMap(){
		return variableMap;
	}
	
	protected HashMap<String, Object> parameterMap = new HashMap<String, Object>();

	
	public HashMap<String,Object> getParameterMap(){
		return parameterMap;
	
	
	
	
}