package parser;

import java.util.List;
import java.util.*;

enum NonTerminalSymbol implements Symbol {

	EXPRESSION, EXPRESSION_TAIL, TERM, TERM_TAIL, UNARY, FACTOR;

	private static final Map<NonTerminalSymbol, Map<TerminalSymbol, SymbolSequence>> productions = new HashMap<>();

	static {
		// Look ahead Table for EXPRESSION
		Map<TerminalSymbol, SymbolSequence> ExpressionLookAhead = new HashMap<>();
		SymbolSequence termExpressionTail = SymbolSequence.build(TERM, EXPRESSION_TAIL);
		ExpressionLookAhead.put(TerminalSymbol.OPEN, termExpressionTail);
		ExpressionLookAhead.put(TerminalSymbol.MINUS, termExpressionTail);
		ExpressionLookAhead.put(TerminalSymbol.VARIABLE, termExpressionTail);
		productions.put(EXPRESSION, ExpressionLookAhead);

		// Look ahead Table for EXPRESSION_TAIL
		Map<TerminalSymbol, SymbolSequence> Expression_TAIL_LookAhead = new HashMap<>();
		Expression_TAIL_LookAhead.put(TerminalSymbol.PLUS,
				SymbolSequence.build(TerminalSymbol.PLUS, TERM, EXPRESSION_TAIL));
		Expression_TAIL_LookAhead.put(TerminalSymbol.MINUS,
				SymbolSequence.build(TerminalSymbol.MINUS, TERM, EXPRESSION_TAIL));
		Expression_TAIL_LookAhead.put(TerminalSymbol.CLOSE, SymbolSequence.EPSILON);
		Expression_TAIL_LookAhead.put(null, SymbolSequence.EPSILON);
		productions.put(EXPRESSION_TAIL, Expression_TAIL_LookAhead);

		// Look ahead Table TERM
		Map<TerminalSymbol, SymbolSequence> TermLookAhead = new HashMap<>();
		SymbolSequence unaryTermTail = SymbolSequence.build(UNARY, TERM_TAIL);
		TermLookAhead.put(TerminalSymbol.OPEN, unaryTermTail);
		TermLookAhead.put(TerminalSymbol.VARIABLE, unaryTermTail);
		TermLookAhead.put(TerminalSymbol.MINUS, unaryTermTail);
		productions.put(TERM, TermLookAhead);

		// Look ahead Table TERM_TAIL
		Map<TerminalSymbol, SymbolSequence> TermTailLookAhead = new HashMap<>();
		TermTailLookAhead.put(TerminalSymbol.TIMES, SymbolSequence.build(TerminalSymbol.TIMES, UNARY, TERM_TAIL));
		TermTailLookAhead.put(TerminalSymbol.DIVIDE, SymbolSequence.build(TerminalSymbol.DIVIDE, UNARY, TERM_TAIL));
		TermTailLookAhead.put(TerminalSymbol.PLUS, SymbolSequence.EPSILON);
		TermTailLookAhead.put(TerminalSymbol.MINUS, SymbolSequence.EPSILON);
		TermTailLookAhead.put(TerminalSymbol.CLOSE, SymbolSequence.EPSILON);
		TermTailLookAhead.put(null, SymbolSequence.EPSILON);
		productions.put(TERM_TAIL, TermTailLookAhead);

		// Look ahead Table UNARY
		Map<TerminalSymbol, SymbolSequence> UnaryLookAhead = new HashMap<>();
		SymbolSequence factor = SymbolSequence.build(FACTOR);
		UnaryLookAhead.put(TerminalSymbol.MINUS, SymbolSequence.build(TerminalSymbol.MINUS, FACTOR));
		UnaryLookAhead.put(TerminalSymbol.OPEN, factor);
		UnaryLookAhead.put(TerminalSymbol.VARIABLE, factor);
		productions.put(UNARY, UnaryLookAhead);

		// LOOK ahead TABLE FACTOR
		Map<TerminalSymbol, SymbolSequence> FactorLookAhead = new HashMap<>();
		FactorLookAhead.put(TerminalSymbol.VARIABLE, SymbolSequence.build(TerminalSymbol.VARIABLE));
		FactorLookAhead.put(TerminalSymbol.OPEN,
				SymbolSequence.build(TerminalSymbol.OPEN, FACTOR, TerminalSymbol.CLOSE));
		productions.put(FACTOR, FactorLookAhead);
	}

	// The main purpose of this method is to parse the input into a node, possibly
	// leaving a remainder.
	// The ParseState's success will be true if the parsing process was successful
	// and false otherwise.
	@Override
	public ParseState parse(List<Token> input) {
		TerminalSymbol lookAhead;
		if (!Objects.requireNonNull(input, "Null input for parse method in NonTerminalSymbol").isEmpty()) {
			lookAhead = input.get(0).getType();
		} else {
			lookAhead = null;
		}
		SymbolSequence tableLookup;
		if (productions.get(this).containsKey(lookAhead)) {
			tableLookup = productions.get(this).get(lookAhead);
		} else {
			return ParseState.FAILURE;
		}
		ParseState tempParse = tableLookup.match(input);
		if (tempParse.isSuccess()) {
			return tempParse;
		} else {
			return ParseState.FAILURE;
		}
	}

	// Parses expression type and returns the Node as an optional class
	static final Optional<Node> parseInput(List<Token> input) {
		ParseState result = EXPRESSION
				.parse(Objects.requireNonNull(input, "Null input for parseInput method in NonTerminalSymbol"));
		if (result.isSuccess() && result.hasNoRemainder()) {
			return Optional.of(result.getNode());
		}
		return Optional.empty();
	}
}