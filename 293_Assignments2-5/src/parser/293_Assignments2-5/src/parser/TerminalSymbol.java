package parser;

import java.util.List;
import java.util.EnumMap;

public enum TerminalSymbol implements Symbol {

	VARIABLE, PLUS, MINUS, TIMES, DIVIDE, OPEN, CLOSE;

	static EnumMap<TerminalSymbol, String> TerminalStringTable = new EnumMap<>(TerminalSymbol.class);

	static {
		TerminalStringTable.put(PLUS, "+");
		TerminalStringTable.put(MINUS, "-");
		TerminalStringTable.put(TIMES, "*");
		TerminalStringTable.put(DIVIDE, "/");
		TerminalStringTable.put(OPEN, "(");
		TerminalStringTable.put(CLOSE, ")");
	}
	
	@Override
	public ParseState parse(List<Token> input) {
		if (!input.isEmpty()) {
			Token firstToken = input.get(0);
			List<Token> remainder = input.subList(1, input.size());
			if (firstToken.matches(this)) {
				return ParseState.build(LeafNode.build(firstToken), remainder);
			}
			return ParseState.FAILURE;
		}
		return ParseState.FAILURE;
	}
}
