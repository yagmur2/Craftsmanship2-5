package parser;

import java.util.Objects;

public final class Variable extends AbstractToken {

	private final String representation;
	private static Cache<String, Variable> cache = new Cache<String, Variable>();

	//Constructor
	private Variable(String representation) {
		this.representation = representation;
	}

	// FUNCTIONS
	@Override
	public TerminalSymbol getType() {
		return TerminalSymbol.VARIABLE;
	}

	public final String getRepresentation() {
		return representation;
	}

	// build function for Variables.
	public static final Variable build(String representation) {
		return cache.get(Objects.requireNonNull(representation, "Variable representation is null"), Variable::new);
	}

	@Override
	public String toString() {
		return getRepresentation();
	}

	@Override
	public boolean isOperator() {
		return false;
	}
}
