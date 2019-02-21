package parser;

import java.util.*;

import parser.InternalNode.Builder;

final class SymbolSequence {

	private final List<Symbol> production;
	static final SymbolSequence EPSILON = build();

	// Private constructor, sets production
	private SymbolSequence(List<Symbol> production) {
		this.production = production;
	}

	// Build method using a List<Symbol> to create a SymbolSequence
	static final SymbolSequence build(List<Symbol> production) {
		return new SymbolSequence(
				Objects.requireNonNull(production, "Null input in SymbolSequence builder, should be List<Symbol>"));
	}

	// Build method using a variable number of symbols to create a SymbolSequence
	static final SymbolSequence build(Symbol... symbols) {
		return new SymbolSequence(Arrays.asList(Objects.requireNonNull(symbols,
				"Null input in SymbolSequence builder, should be variable no. of Symbols")));
	}

	@Override
	// SymbolSequence delegates toString method to its production
	public String toString() {
		return production.toString();
	}

	// Returns a successful ParseState if all the symbols in production can be
	// matched with
	// the input; throws NullPointerException if input is null; FAILURE otherwise.
	public ParseState match(List<Token> input) {
		Builder childListBuilder = new Builder();
		List<Token> remainder = Objects.requireNonNull(input, "Null input in match method, should be List<Token>");
		for (Symbol symbol : this.production) {
			ParseState current = symbol.parse(remainder);
			if (current.isSuccess()) {
				childListBuilder.addChild(current.getNode());
				remainder = current.getRemainder();
			}
			else {
				return current;
			}
		}
		return ParseState.build(childListBuilder.build(), remainder);
	}
}
