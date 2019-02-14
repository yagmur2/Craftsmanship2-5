package parser;


import java.util.List;
import java.util.*;
enum NonTerminalSymbol implements  Symbol {
	
    EXPRESSION,EXPRESSION_TAIL,TERM,TERM_TAIL,UNARY,FACTOR;
	//ONLY TO SHOW CHANGES FROM LAST WEEK --- WILL DELETE
    static EnumMap<NonTerminalSymbol, List<SymbolSequence>> Table = new
            EnumMap<NonTerminalSymbol, List<SymbolSequence>> (NonTerminalSymbol.class);
            
    private static final Map<NonTerminalSymbol, Map<TerminalSymbol, SymbolSequence>> productions = new HashMap<>();

    static{
    //ONLY TO SHOW CHANGES FROM LAST WEEK --- WILL DELETE
    Table.put(EXPRESSION,Arrays.asList(SymbolSequence.build(Arrays.asList(TERM,EXPRESSION_TAIL))));
    Table.put(EXPRESSION_TAIL,Arrays.asList(SymbolSequence.build(Arrays.asList(TerminalSymbol.PLUS,TERM,EXPRESSION_TAIL)),SymbolSequence.build(Arrays.asList(TerminalSymbol.MINUS,TERM,EXPRESSION_TAIL)),SymbolSequence.EPSILON));
    Table.put(TERM,Arrays.asList(SymbolSequence.build(Arrays.asList(UNARY,TERM_TAIL))));
    Table.put(TERM_TAIL,Arrays.asList(SymbolSequence.build(Arrays.asList(TerminalSymbol.TIMES,UNARY,TERM_TAIL)),SymbolSequence.build(Arrays.asList(TerminalSymbol.DIVIDE,UNARY,TERM_TAIL)),SymbolSequence.EPSILON));
    Table.put(UNARY,Arrays.asList(SymbolSequence.build(Arrays.asList(TerminalSymbol.MINUS,FACTOR)),SymbolSequence.build(Arrays.asList(FACTOR))));
    Table.put(FACTOR,Arrays.asList(SymbolSequence.build(Arrays.asList(TerminalSymbol.OPEN,EXPRESSION,TerminalSymbol.CLOSE)),SymbolSequence.build(Arrays.asList(TerminalSymbol.VARIABLE))));

    //Look ahead Table first row
    Map<TerminalSymbol, SymbolSequence> ExpressionLookAhead = new HashMap<>();
    ExpressionLookAhead.put(TerminalSymbol.OPEN,SymbolSequence.build(Arrays.asList(TERM,EXPRESSION_TAIL)) );
    productions.put(EXPRESSION, ExpressionLookAhead);

    //Look ahead Table dor expression tail
    Map<TerminalSymbol, SymbolSequence> Expression_TAIL_LookAhead = new HashMap<>();
    Expression_TAIL_LookAhead.put(TerminalSymbol.PLUS,SymbolSequence.build(Arrays.asList(TerminalSymbol.PLUS,TERM,EXPRESSION_TAIL)) );
    //Look ahead Table second row
    Expression_TAIL_LookAhead.put(TerminalSymbol.MINUS,SymbolSequence.build(Arrays.asList(TerminalSymbol.MINUS,TERM,EXPRESSION_TAIL)) );
    Expression_TAIL_LookAhead.put(null,SymbolSequence.EPSILON );
    productions.put(EXPRESSION_TAIL, Expression_TAIL_LookAhead);

    //Look ahead Table TERM
    Map<TerminalSymbol, SymbolSequence> TermLookAhead = new HashMap<>();
    TermLookAhead.put(TerminalSymbol.OPEN,SymbolSequence.build(Arrays.asList(UNARY,TERM_TAIL)) );
    productions.put(TERM, TermLookAhead);

    //Look ahead Table TERM_TAIL
    Map<TerminalSymbol, SymbolSequence> TermTailLookAhead = new HashMap<>();
    TermTailLookAhead.put(TerminalSymbol.TIMES,SymbolSequence.build(Arrays.asList(TerminalSymbol.TIMES,UNARY,TERM_TAIL)) );
    TermTailLookAhead.put(TerminalSymbol.DIVIDE,SymbolSequence.build(Arrays.asList(TerminalSymbol.DIVIDE,UNARY,TERM_TAIL)) );
    TermTailLookAhead.put(null,SymbolSequence.EPSILON );
    productions.put(TERM_TAIL, TermTailLookAhead);

    //Look ahead Table UNARY
    Map<TerminalSymbol, SymbolSequence> UnaryLookAhead = new HashMap<>();
    UnaryLookAhead.put(TerminalSymbol.MINUS, SymbolSequence.build(Arrays.asList(TerminalSymbol.MINUS,FACTOR)));
    UnaryLookAhead.put(TerminalSymbol.OPEN, SymbolSequence.build(Arrays.asList(FACTOR)));
    productions.put(UNARY, UnaryLookAhead);

    //LOOK ahead TABLE FACTOR
    Map<TerminalSymbol, SymbolSequence> FactorLookAhead = new HashMap<>();
    FactorLookAhead.put(TerminalSymbol.VARIABLE, SymbolSequence.build(Arrays.asList(TerminalSymbol.VARIABLE)));
    FactorLookAhead.put(TerminalSymbol.OPEN, SymbolSequence.build(Arrays.asList(TerminalSymbol.OPEN,FACTOR,TerminalSymbol.CLOSE)));
    productions.put(FACTOR, FactorLookAhead);
    }

    //The main purpose of this method is to parse the input into a node, possibly leaving a remainder.
    //The ParseState's success will be true if the parsing process was successful and false otherwise.
    @Override
    public ParseState parse(List<Token> input){
    	
	    Objects.requireNonNull(input, "Null input for parse method in NonTerminalSymbol");
	    
        List<SymbolSequence> tablelookup = Table.get(this);
        
            for(SymbolSequence have: tablelookup){
                ParseState tempParse = have.match(input);
                if (tempParse.isSuccess()) {
                	return tempParse;
                //  A non-terminal parses its input by going through its productions in the order
                // given by the table and attempting to match them to the input
                }
            }
        return ParseState.FAILURE;
    }
    
    static final Optional<Node> parseInput(List<Token> input){
    	
        Objects.requireNonNull(input, "Null input for parseInput method in NonTerminalSymbol");
        
        //which attempts to parse the input with an EXPRESSION, and returns the root node if the parsing process is successful and has not remainder,
        // and an empty Optional otherwise.
        // It also throws a NullPointerException with an appropriate error message if the input is null.
        ParseState result = EXPRESSION.parse(input);

        if(result.isSuccess() && result.hasNoRemainder()){
                return  Optional.of(result.getNode());
        }
        return Optional.empty();
    }

}