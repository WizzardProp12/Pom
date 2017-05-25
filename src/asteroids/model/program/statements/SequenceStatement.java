package asteroids.model.program.statements;

import java.util.List;

import asteroids.part3.programs.SourceLocation;


public class SequenceStatement extends Statement{
	
	private List<Statement> statements;
	
	public SequenceStatement(SourceLocation sourceLocation, List<Statement> statements) {
		super(sourceLocation);
		this.statements = statements;
	}
	
	private int index = 0;
	
	public Statement getCurrentStatement() {
		return this.statements.get(index);
	}
	
	
	/**
	 * Go to the next statement of the sequence statement
	 */
	public void gotoNextStatement() {
		// if the current statement is also a sequence statement
		if (getCurrentStatement() instanceof SequenceStatement) {
			if (((SequenceStatement) getCurrentStatement()).isCompletelyExecuted()) {
				this.index += 1;
			} else {
				((SequenceStatement) getCurrentStatement()).gotoNextStatement();
			}
		}
		this.index += 1;
	}
	
	public boolean isCompletelyExecuted() {
		return this.index >= this.statements.size();
	}
	
	
	
	@Override
	public void execute() {
		System.out.println("start executing sequencestatement");
		setStatementExecuted(false);

		for (Statement statement : statements){
			statement.setProgram(program);
			if (! getProgram().isTimeIsUp()) {
				System.out.println("start to execute statement " + index + " with time left: " + getProgram().getTimeLeft() );
				
				if (index < statements.size() ) {
					getCurrentStatement().setProgram(program);
					getCurrentStatement().execute();
					if (index == statements.size()-1 )
						this.setStatementExecuted(true);
					System.out.println("executed statement " + index + " printop so far " + getProgram().getPrintOutput());
					
				} else if (index == statements.size()) {
					this.setStatementExecuted(true);
					System.out.println("stm done");
					return;
				}
				if(!getProgram().isTimeIsUp()){
					System.out.println("go to next statement " + (index+1));
					gotoNextStatement();
				}
			}
			else{
				return;
			}
		}
	}

	
}