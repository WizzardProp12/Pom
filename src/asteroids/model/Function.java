package asteroids.model;

import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;

import asteroids.part3.programs.SourceLocation;

public class Function {
	private String functionName;
	private Statement body;
	private SourceLocation sourceLocation;
	
	public Function(String functionName, Statement body, SourceLocation sourceLocation){
		this.functionName = functionName;
		this.body = body;
		this.sourceLocation = sourceLocation;
	}
	
	
	public void evaluate(ArrayList<Expression> argumentList){
		body.execute(argumentList);
	}
		
	protected ArrayList<Expression> actualArgs = new ArrayList<Expression>();

	
	public ArrayList<Expression> getactualArgs(){
		return actualArgs;
	}
	
	public String getFunctionName(){
		return functionName;
	}
	
	

}
