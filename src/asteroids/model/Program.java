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
	protected HashMap<Integer, Object> parameterMap = new HashMap<Integer, Object>();

	
	public HashMap<String,Object> getVariableMap(){
		return variableMap;
	}
	
	public HashMap<Integer,Object> getParameterMap(){
		return parameterMap;
	
	
	
	
}