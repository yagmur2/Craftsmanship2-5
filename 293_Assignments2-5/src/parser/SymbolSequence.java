package parser;

import java.util.*;

final class SymbolSequence {
	
	private final List<Symbol> PRODUCTION;
	static final SymbolSequence EPSILON = new SymbolSequence(new ArrayList<Symbol>());
	
	//Private constructor, sets production
	private SymbolSequence(List<Symbol> PRODUCTION) {
		this.PRODUCTION = PRODUCTION;
		}
	
	//Build method using a List<Symbol> to create a SymbolSequence
	static final SymbolSequence build(List<Symbol> PRODUCTION) {
		if (PRODUCTION == null) {
			throw new NullPointerException("Null argument in method build()");
		}
		//else
		return new SymbolSequence(PRODUCTION);
	}
	
	//Build method using a variable number of symbols to create a SymbolSequence
	static final SymbolSequence build(Symbol...symbols) {
		if(symbols != null) {
			List<Symbol> list = Arrays.asList(symbols);
			return new SymbolSequence(list);
		}
		else {
			throw new NullPointerException("Null argument in method build()");
		}
	}
	
	@Override
	//SymbolSequence delegates toString method to its production
	public String toString() {
		return PRODUCTION.toString();
		}
	
	/*Returns a successful ParseState if all the symbols in PRODUCTION can be matched with
	* the input; throws NullPointerException if input is null; FAILURE otherwise.
	*/
	public ParseState match(List<Token> input) {
	
		if(input == null) 
			throw new NullPointerException("Null argument in method match()");
		
		List<Token> remainder = input;
		List<Node> children = new ArrayList<Node>();
		
		for(Symbol symbol : this.PRODUCTION) {
			//if the parse is successful, adds node to children list and adjusts remainder
			if(symbol.parse(remainder).isSuccess()) {
				children.add(symbol.parse(remainder).getNode());
				remainder = symbol.parse(remainder).getRemainder();
			}
			//If the parse is unsuccessful, returns FAILURE
			else {
				return symbol.parse(remainder);
				}
			}
		return ParseState.build(InternalNode.build(children), remainder);
		}
	
}
