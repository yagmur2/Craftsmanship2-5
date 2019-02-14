package parser;
import java.util.List;
import java.util.EnumMap;
public enum TerminalSymbol implements Symbol {

    // 7 different types
    VARIABLE, PLUS, MINUS, TIMES, DIVIDE, OPEN, CLOSE;

	//Enum 
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
			
			//the first input token and the remainder (rest of the list)
			Token firstToken = input.get(0);
			List<Token> remainder = input.subList(1, input.size());
	
			//build successful ParseState if the types match, otherwise return failure
			if (firstToken.matches(this)) {
				return ParseState.build(LeafNode.build(firstToken), remainder);
				}
			return ParseState.FAILURE;
			}
		return ParseState.FAILURE;
		}
		
	
	}
