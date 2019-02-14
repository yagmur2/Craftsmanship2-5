package parser;

import java.util.*;

final class SymbolSequence {
	
	private final List<Symbol> production;
	static final SymbolSequence EPSILON = new SymbolSequence(new ArrayList<Symbol>());
	
	//Private constructor, sets production
	private SymbolSequence(List<Symbol> production) {
		this.production = production;
		}
	
	//Build method using a List<Symbol> to create a SymbolSequence
	static final SymbolSequence build(List<Symbol> production) {
		Objects.requireNonNull(production, "Null input in SymbolSequence builder, should be List<Symbol>");
		return new SymbolSequence(production);
	}
	
	//Build method using a variable number of symbols to create a SymbolSequence
	static final SymbolSequence build(Symbol...symbols) {
			Objects.requireNonNull(symbols, "Null input in SymbolSequence builder, should be variable no. of Symbols");
			return new SymbolSequence(Arrays.asList(symbols));//Uses symbols' list representation to construct
		}
	
	@Override
	//SymbolSequence delegates toString method to its production
	public String toString() {
		return production.toString();
		}
	
	/*Returns a successful ParseState if all the symbols in production can be matched with
	* the input; throws NullPointerException if input is null; FAILURE otherwise.
	*/
	public ParseState match(List<Token> input) {
	
		Objects.requireNonNull(input, "Null input in match method, should be List<Token>");
		
		List<Token> remainder = input;
		List<Node> children = new ArrayList<Node>();
		
		for(Symbol symbol : this.production) {
			
			ParseState current = symbol.parse(remainder);
			
			//if the parse is successful, adds node to children list and adjusts remainder
			if(current.isSuccess()) {
				children.add(current.getNode());
				remainder = current.getRemainder();
			}
			//If the parse is unsuccessful, returns FAILURE
			else {
				return current;
				}
			}
		return ParseState.build(InternalNode.build(children), remainder);
		}
	
}
