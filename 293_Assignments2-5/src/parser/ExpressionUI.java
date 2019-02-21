package parser;

import java.util.*;

public class ExpressionUI {

	public static void main(String[] args) {
		List<Token> list = toToken(args);
		Optional<Node> result = NonTerminalSymbol.parseInput(list);
		System.out.println("input list: " + list);
		System.out.println("result: " + result.get().toString());
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
	
	private static List<Token> toToken(String[] string) {
		List<Token> inputList = new ArrayList<>();
		for(int i = 0; i < string.length; i++) {
			String ptr = string[i];
			if(termMap.containsKey(ptr)) {
				inputList.add(Connector.build(termMap.get(ptr)));
				}
			else {
				inputList.add(Variable.build(ptr));
				}
			}
		return inputList;
	}
}