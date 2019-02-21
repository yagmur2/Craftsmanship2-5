package parser;

import java.util.List;
import java.util.*;

enum NonTerminalSymbol implements Symbol {

	EXPRESSION, EXPRESSION_TAIL, TERM, TERM_TAIL, UNARY, FACTOR;

	private static final Map<NonTerminalSymbol, Map<TerminalSymbol, SymbolSequence>> productions = new HashMap<>();

	static {
		// Look ahead Table first row
		Map<TerminalSymbol, SymbolSequence> ExpressionLookAhead = new HashMap<>();
		ExpressionLookAhead.put(TerminalSymbol.OPEN, SymbolSequence.build(Arrays.asList(TERM, EXPRESSION_TAIL)));
		productions.put(EXPRESSION, ExpressionLookAhead);

		// Look ahead Table for expression tail
		Map<TerminalSymbol, SymbolSequence> Expression_TAIL_LookAhead = new HashMap<>();
		Expression_TAIL_LookAhead.put(TerminalSymbol.PLUS,
				SymbolSequence.build(Arrays.asList(TerminalSymbol.PLUS, TERM, EXPRESSION_TAIL)));
		Expression_TAIL_LookAhead.put(TerminalSymbol.MINUS,
				SymbolSequence.build(Arrays.asList(TerminalSymbol.MINUS, TERM, EXPRESSION_TAIL)));
		Expression_TAIL_LookAhead.put(null, SymbolSequence.EPSILON);
		productions.put(EXPRESSION_TAIL, Expression_TAIL_LookAhead);

		// Look ahead Table TERM
		Map<TerminalSymbol, SymbolSequence> TermLookAhead = new HashMap<>();
		TermLookAhead.put(TerminalSymbol.OPEN, SymbolSequence.build(Arrays.asList(UNARY, TERM_TAIL)));
		productions.put(TERM, TermLookAhead);

		// Look ahead Table TERM_TAIL
		Map<TerminalSymbol, SymbolSequence> TermTailLookAhead = new HashMap<>();
		TermTailLookAhead.put(TerminalSymbol.TIMES,
				SymbolSequence.build(Arrays.asList(TerminalSymbol.TIMES, UNARY, TERM_TAIL)));
		TermTailLookAhead.put(TerminalSymbol.DIVIDE,
				SymbolSequence.build(Arrays.asList(TerminalSymbol.DIVIDE, UNARY, TERM_TAIL)));
		TermTailLookAhead.put(null, SymbolSequence.EPSILON);
		productions.put(TERM_TAIL, TermTailLookAhead);

		// Look ahead Table UNARY
		Map<TerminalSymbol, SymbolSequence> UnaryLookAhead = new HashMap<>();
		UnaryLookAhead.put(TerminalSymbol.MINUS, SymbolSequence.build(Arrays.asList(TerminalSymbol.MINUS, FACTOR)));
		UnaryLookAhead.put(TerminalSymbol.OPEN, SymbolSequence.build(Arrays.asList(FACTOR)));
		productions.put(UNARY, UnaryLookAhead);

		// LOOK ahead TABLE FACTOR
		Map<TerminalSymbol, SymbolSequence> FactorLookAhead = new HashMap<>();
		FactorLookAhead.put(TerminalSymbol.VARIABLE, SymbolSequence.build(Arrays.asList(TerminalSymbol.VARIABLE)));
		FactorLookAhead.put(TerminalSymbol.OPEN,
				SymbolSequence.build(Arrays.asList(TerminalSymbol.OPEN, FACTOR, TerminalSymbol.CLOSE)));
		productions.put(FACTOR, FactorLookAhead);
	}

	// The main purpose of this method is to parse the input into a node, possibly
	// leaving a remainder.
	// The ParseState's success will be true if the parsing process was successful
	// and false otherwise.
	@Override
	public ParseState parse(List<Token> input) {
		SymbolSequence tablelookup = productions.get(this).get(Objects.requireNonNull(input, "Null input for parse method in NonTerminalSymbol").get(0));
		if (tablelookup != null) {
			ParseState tempParse = tablelookup.match(input);
			if (tempParse.isSuccess()) {
				return tempParse;
			}
		}
		return ParseState.FAILURE;
	}

	// Parses expression type and returns the Node as an optional class
	static final Optional<Node> parseInput(List<Token> input) {
		ParseState result = EXPRESSION.parse(Objects.requireNonNull(input, "Null input for parseInput method in NonTerminalSymbol"));
		if (result.isSuccess() && result.hasNoRemainder()) {
			return Optional.of(result.getNode());
		}
		return Optional.empty();
	}
}