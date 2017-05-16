package asteroids.model.program;

import java.util.HashMap;
import java.util.List;

import asteroids.model.Ship;
import asteroids.model.program.statements.Statement;

public class Program {
	
	// CONSTRUCTORS
	
	public Program(List<Function> functions,Statement main, double time) {
		this.functions = functions;	
		this.main = main;
		this.timeLeft = time;
	}
	
	public Program(List<Function> functions,Statement main) {
		this(functions, main, 0);
	}
	
	// FUNCTIONS
	
	private List<Function> functions;
	
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
	
	// MAIN
	
	private Statement main;
	
	// SHIP
	
	private Ship ship;
	
	public Ship getShip(){ return this.ship; }
	
	// TIMELEFT
	
	private double timeLeft;
	
	public double getTimeLeft() { return this.timeLeft; }
	
	public void addTime(double time) { timeLeft += time; }
	
	public void decreaseTime(double time){ timeLeft -= time; }
	
	// GLOBAL VARIABLES
	
	public HashMap<String, Object> globalVariables = new HashMap<String, Object>();

	public HashMap<String,Object> getGlobalVariables(){ return globalVariables;	}
	
	
	// EXECUTE
	
	//executing program returns list of objects?
	public List<Object> execute(double time){
		main.execute();
		return null;
	}
	
	// HOLD
	
	// method to pause the program if it doesn't have enough time left
	public void hold() {
		// TODO
	}
	
	
	
	
	
}