package parser;


import java.util.List;
import java.util.*;
enum NonTerminalSymbol implements  Symbol {
	
    EXPRESSION,EXPRESSION_TAIL,TERM,TERM_TAIL,UNARY,FACTOR;
    static EnumMap<NonTerminalSymbol, List<SymbolSequence>> Table = new
            EnumMap<NonTerminalSymbol, List<SymbolSequence>> (NonTerminalSymbol.class);


    static{

    Table.put(EXPRESSION,Arrays.asList(SymbolSequence.build(Arrays.asList(TERM,EXPRESSION_TAIL))));
    Table.put(EXPRESSION_TAIL,Arrays.asList(SymbolSequence.build(Arrays.asList(TerminalSymbol.PLUS,TERM,EXPRESSION_TAIL)),SymbolSequence.build(Arrays.asList(TerminalSymbol.MINUS,TERM,EXPRESSION_TAIL)),SymbolSequence.EPSILON));
    Table.put(TERM,Arrays.asList(SymbolSequence.build(Arrays.asList(UNARY,TERM_TAIL))));
    Table.put(TERM_TAIL,Arrays.asList(SymbolSequence.build(Arrays.asList(TerminalSymbol.TIMES,UNARY,TERM_TAIL)),SymbolSequence.build(Arrays.asList(TerminalSymbol.DIVIDE,UNARY,TERM_TAIL)),SymbolSequence.EPSILON));
    Table.put(UNARY,Arrays.asList(SymbolSequence.build(Arrays.asList(TerminalSymbol.MINUS,FACTOR)),SymbolSequence.build(Arrays.asList(FACTOR))));
    Table.put(FACTOR,Arrays.asList(SymbolSequence.build(Arrays.asList(TerminalSymbol.OPEN,EXPRESSION,TerminalSymbol.CLOSE)),SymbolSequence.build(Arrays.asList(TerminalSymbol.VARIABLE))));

    }
   


// The main purpose of this method is to parse the input into a node, possibly leaving a remainder.
// The ParseState�s success will be true if the parsing process was successful and false otherwise.
    @Override
    public ParseState parse(List<Token> input){
        if (input == null){
            throw new NullPointerException("Input is NULL for NON terminal Parse");
        }
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
        Optional<Node> rootNode;
        if(input == null){
            throw new NullPointerException("The input for Non terminal optaional parser is NULL");
        }
        //which attempts to parse the input with an EXPRESSION, and returns the root node if the parsing process is successful and has not remainder,
        // and an empty Optional otherwise.
        // It also throws a NullPointerException with an appropriate error message if the input is null.
        ParseState result = EXPRESSION.parse(input);

        if(result.isSuccess() && result.hasNoRemainder()){
                return  Optional.of(result.getNode());
        }
        else {
            return Optional.empty();
        }
        //return optinal empty
    }



}
