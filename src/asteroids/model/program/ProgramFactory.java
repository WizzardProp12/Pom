package asteroids.model.program;

import java.util.List;

import asteroids.model.program.expressions.AdditionExpression;
import asteroids.model.program.expressions.AnyExpression;
import asteroids.model.program.expressions.AsteroidExpression;
import asteroids.model.program.expressions.BulletExpression;
import asteroids.model.program.expressions.ChangeSignExpression;
import asteroids.model.program.expressions.DoubleLiteralExpression;
import asteroids.model.program.expressions.EqualityExpression;
import asteroids.model.program.expressions.Expression;
import asteroids.model.program.expressions.FunctionCallExpression;
import asteroids.model.program.expressions.GetDirectionExpression;
import asteroids.model.program.expressions.GetRadiusExpression;
import asteroids.model.program.expressions.GetVxExpression;
import asteroids.model.program.expressions.GetVyExpression;
import asteroids.model.program.expressions.GetXExpression;
import asteroids.model.program.expressions.GetYExpression;
import asteroids.model.program.expressions.LessThanExpression;
import asteroids.model.program.expressions.MultiplicationExpression;
import asteroids.model.program.expressions.NotExpression;
import asteroids.model.program.expressions.NullExpression;
import asteroids.model.program.expressions.PlanetExpression;
import asteroids.model.program.expressions.PlanetoidExpression;
import asteroids.model.program.expressions.ReadParameterExpression;
import asteroids.model.program.expressions.ReadVariableExpression;
import asteroids.model.program.expressions.SelfExpression;
import asteroids.model.program.expressions.ShipExpression;
import asteroids.model.program.expressions.SqrtExpression;
import asteroids.model.program.statements.AssignmentStatement;
import asteroids.model.program.statements.BreakStatement;
import asteroids.model.program.statements.FireStatement;
import asteroids.model.program.statements.IfStatement;
import asteroids.model.program.statements.PrintStatement;
import asteroids.model.program.statements.ReturnStatement;
import asteroids.model.program.statements.SequenceStatement;
import asteroids.model.program.statements.SkipStatement;
import asteroids.model.program.statements.Statement;
import asteroids.model.program.statements.ThrustOffStatement;
import asteroids.model.program.statements.ThrustOnStatement;
import asteroids.model.program.statements.TurnStatement;
import asteroids.model.program.statements.WhileStatement;
import asteroids.part3.programs.IProgramFactory;
import asteroids.part3.programs.SourceLocation;

public class ProgramFactory implements IProgramFactory<Expression, Statement, Function, Program>{

	@Override
	public Program createProgram(List<Function> functionList, Statement main) {
		// TODO Auto-generated method stub
		return new Program(functionList, main);
	}

	@Override
	public Function createFunctionDefinition(String functionName, Statement body, SourceLocation location) {
		return new Function(functionName, body, location);
	}

	@Override
	public Statement createAssignmentStatement(String variableName, Expression value, SourceLocation location) {
		return new AssignmentStatement(variableName, value, location);
	}

	@Override
	public Statement createWhileStatement(Expression condition, Statement body, SourceLocation location) {
		return new WhileStatement(condition, body, location);
	}

	@Override
	public Statement createBreakStatement(SourceLocation location) {
		return new BreakStatement(location);
	}

	@Override
	public Statement createReturnStatement(Expression value, SourceLocation location) {
		return new ReturnStatement(value, location);
	}
	
	@Override
	public Statement createIfStatement(Expression condition, Statement ifBody, Statement elseBody, SourceLocation location) {
		return new IfStatement(condition, ifBody, elseBody,location);
	}

	@Override
	public Statement createPrintStatement(Expression value, SourceLocation location) {
		return new PrintStatement(value, location);
	}

	@Override
	public Statement createSequenceStatement(List<Statement> statements, SourceLocation location) {
		return new SequenceStatement(location, statements);
	}

	@Override
	public Expression createReadVariableExpression(String variableName, SourceLocation location) {
		return new ReadVariableExpression(variableName, location);
	}

	@Override
	public Expression createReadParameterExpression(String parameterName, SourceLocation location) {
		return new ReadParameterExpression(parameterName, location);
	}

	@Override
	public Expression createFunctionCallExpression(String functionName, List<Expression> actualArgs, SourceLocation sourceLocation) {
		return new FunctionCallExpression(functionName, actualArgs, sourceLocation);
	}
	
	@Override
	public Expression createChangeSignExpression(Expression e, SourceLocation location) {
		return new ChangeSignExpression(e, location);
	}

	@Override
	public Expression createNotExpression(Expression e, SourceLocation location) {
		return new NotExpression(e, location);
	}

	@Override
	public Expression createDoubleLiteralExpression(double value, SourceLocation location) {
		return new DoubleLiteralExpression(value, location);
	}

	@Override
	public Expression createNullExpression(SourceLocation location) {
		return new NullExpression(location);
	}

	@Override
	public Expression createSelfExpression(SourceLocation location) {
		return new SelfExpression(location);
	}

	@Override
	public Expression createShipExpression(SourceLocation location) {
		return new ShipExpression(location);
	}

	@Override
	public Expression createAsteroidExpression(SourceLocation location) {
		return new AsteroidExpression(location);
	}

	@Override
	public Expression createPlanetoidExpression(SourceLocation location) {
		return new PlanetoidExpression(location);
	}

	@Override
	public Expression createBulletExpression(SourceLocation location) {
		return new BulletExpression(location);
	}

	@Override
	public Expression createPlanetExpression(SourceLocation location) {
		return new PlanetExpression(location);
	}

	@Override
	public Expression createAnyExpression(SourceLocation location) {
		return new AnyExpression(location);
	}

	@Override
	public Expression createGetXExpression(Expression e, SourceLocation location) {
		return new GetXExpression(e, location);
	}

	@Override
	public Expression createGetYExpression(Expression e, SourceLocation location) {
		return new GetYExpression(e,location);
	}

	@Override
	public Expression createGetVXExpression(Expression e, SourceLocation location) {
		return new GetVxExpression(e, location);
	}

	@Override
	public Expression createGetVYExpression(Expression e, SourceLocation location) {
		return new GetVyExpression(e, location);
	}

	@Override
	public Expression createGetRadiusExpression(Expression e, SourceLocation location) {
		return new GetRadiusExpression(e, location);
	}

	@Override
	public Expression createLessThanExpression(Expression e1, Expression e2, SourceLocation location) {
		return new LessThanExpression(e1, e2, location);
	}

	@Override
	public Expression createEqualityExpression(Expression e1, Expression e2, SourceLocation location) {
		return new EqualityExpression(e1, e2, location);
	}

	@Override
	public Expression createAdditionExpression(Expression e1, Expression e2, SourceLocation location) {
		return new AdditionExpression(e1,e2,location);
	}

	@Override
	public Expression createMultiplicationExpression(Expression e1, Expression e2, SourceLocation location) {
		return new MultiplicationExpression(e1, e2, location);
	}

	@Override
	public Expression createSqrtExpression(Expression e, SourceLocation location) {
		return new SqrtExpression(e, location);
	}

	@Override
	public Expression createGetDirectionExpression(SourceLocation location) {
		return new GetDirectionExpression(location);
	}

	@Override
	public Statement createThrustOnStatement(SourceLocation location) {
		return new ThrustOnStatement(location);
	}

	@Override
	public Statement createThrustOffStatement(SourceLocation location) {
		return new ThrustOffStatement(location);
	}

	@Override
	public Statement createFireStatement(SourceLocation location) {
		return new FireStatement(location);
	}

	@Override
	public Statement createTurnStatement(Expression angle, SourceLocation location) {
		return new TurnStatement(location, angle);
	}

	@Override
	public Statement createSkipStatement(SourceLocation location) {
		return new SkipStatement(location);
	}




}