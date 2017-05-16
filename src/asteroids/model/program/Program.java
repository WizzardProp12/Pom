package asteroids.model.program;

import java.util.HashMap;
import java.util.List;

import asteroids.model.Ship;

public class Program {

	private List<Function> functions;
	private Statement main;
	private Ship ship;
	private double timeLeft;

	
	public Program(List<Function> functions,Statement main){
		this.functions = functions;	
		this.main = main;
	}
	
	//executing program returns list of objects?
	public List<Object> execute(double time){
		main.execute();
		return null;
	}
	
	//Gets the ship that runs this program.
	public Ship getShip(){
		return this.ship;
	}
	
	public double getTimeLeft() {
		return this.timeLeft;
	}
	
	public void decreaseTimeLeft(double time){
		timeLeft -= time;
	}
	
	// method to pause the program if it doesn't have enough time left
	public void hold() {
		// TODO
	}
	
	protected HashMap<String, Object> variableMap = new HashMap<String, Object>();

	
	public HashMap<String,Object> getVariableMap(){
		return variableMap;
	}
	
	protected HashMap<String, Object> parameterMap = new HashMap<String, Object>();
	
	public HashMap<String,Object> getParameterMap(){
		return parameterMap;
	}
	
	public Function getFunction(String functionName){
		for (Function function : functions){
			if (function.getFunctionName() == functionName)
				return function;
		}
		return null;
	}
	
}