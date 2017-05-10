package asteroids.model;

import java.util.List;

import asteroids.part3.programs.IProgramFactory;
import asteroids.part3.programs.SourceLocation;

public class ProgramFactory implements IProgramFactory<Expression, Statement, Function, Program>{

	@Override
	public Program createProgram(List<Function> functionList, Statement main) {
		// TODO Auto-generated method stub
		return new Program(functionList,main);
	}

	@Override
	public F createFunctionDefinition(String functionName, S body, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public S createAssignmentStatement(String variableName, E value, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public S createWhileStatement(E condition, S body, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public S createBreakStatement(SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public S createReturnStatement(E value, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public S createIfStatement(E condition, S ifBody, S elseBody, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public S createPrintStatement(E value, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public S createSequenceStatement(List<S> statements, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createReadVariableExpression(String variableName, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createReadParameterExpression(String parameterName, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createFunctionCallExpression(String functionName, List<E> actualArgs, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createChangeSignExpression(E expression, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createNotExpression(E expression, SourceLocation sourceLocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createDoubleLiteralExpression(double value, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createNullExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createSelfExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createShipExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createAsteroidExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createPlanetoidExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createBulletExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createPlanetExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createAnyExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createGetXExpression(E e, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createGetYExpression(E e, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createGetVXExpression(E e, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createGetVYExpression(E e, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createGetRadiusExpression(E e, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E createLessThanExpression(Expression e1, Expression e2, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createEqualityExpression(E e1, E e2, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createAdditionExpression(Expression e1, Expression e2, SourceLocation location) {
		// TODO Auto-generated method stub
		return new AdditionExpression(e1,e2,location);
	}

	@Override
	public Expression createMultiplicationExpression(Expression e1, Expression e2, SourceLocation location) {
		return new MultiplicationExpression(e1, e2, location);
	}

	@Override
	public E createSqrtExpression(E e, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Expression createGetDirectionExpression(SourceLocation location) {
		// TODO Auto-generated method stub
		return new GetDirectionExpression(location);
	}

	@Override
	public S createThrustOnStatement(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public S createThrustOffStatement(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public S createFireStatement(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public S createTurnStatement(E angle, SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public S createSkipStatement(SourceLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

}
