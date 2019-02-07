package parser;
import java.util.List;

public enum TerminalSymbol implements Symbol {

    // 7 different types
    VARIABLE, PLUS, MINUS, TIMES, DIVIDE, OPEN, CLOSE;

	@Override
	public ParseState parse(List<Token> input) {
		
		//the first input token and the remainder (rest of the list)
		Token firstToken = input.get(0);
		List<Token> remainder = input.subList(1, input.size() - 1);
		
		//build successful ParseState if the types match, otherwise return failure
		if (firstToken.matches(firstToken.getType())) {
			return ParseState.build(LeafNode.build(firstToken), remainder);
		}
		else return ParseState.FAILURE;
	}
}
