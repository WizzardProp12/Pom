package asteroids.model.program.expressions;

import java.awt.List; 
import java.util.ArrayList;

import asteroids.part3.programs.SourceLocation;

public class FunctionCallExpression extends Expression<Object>{
	
	private String functionName;
	private List actualArgs;

	public FunctionCallExpression(String functionName, List<Expression> actualArgs2, SourceLocation sourceLocation) {
		super(sourceLocation);
		this.actualArgs = actualArgs2;
		this.functionName = functionName;
		
	}
	// evaluates the function of the program with the specified functionname and the specified arguments(cfr. IProgramFactory)
	@Override
	public Object evaluate() {
		for (Expression expression : actualArgs){
			getProgram().getFunction(functionName).evaluate(actualArgs);
		}
		return null;
	}

}

