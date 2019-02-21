package parser;

import java.util.*;

public class ExpressionUI {

	public static void main(String[] args) {

		List<Token> inputList = new ArrayList<Token>();

		for (String input : args) {
			// If the index is an operator
			if (termMap.containsKey(symbol)) {
				inputList.add(stringToToken(input));
			}
		}
		printTree(NonTerminalSymbol.parseInput(inputList));
	}

	private static Token stringToToken(String input) {
		return null;
	}

	private static HashMap<String, TerminalSymbol> termMap;

	static {
		termMap = new HashMap<String, TerminalSymbol>();
		termMap.put("+", TerminalSymbol.PLUS);
		termMap.put("-", TerminalSymbol.MINUS);
		termMap.put("*", TerminalSymbol.TIMES);
		termMap.put("/", TerminalSymbol.DIVIDE);
		termMap.put("(", TerminalSymbol.OPEN);
		termMap.put(")", TerminalSymbol.CLOSE);
	}

	// Takes a ParseState result and prints either the resulting tree, or a failed
	// parse
	private static void printTree(ParseState parseResult) {
		if (parseResult == ParseState.FAILURE) {
			System.out.println("Failed ParseState.");
		} else {
			Node head = parseResult.getNode();
			System.out.print(head.toString());
		}
	}

}
