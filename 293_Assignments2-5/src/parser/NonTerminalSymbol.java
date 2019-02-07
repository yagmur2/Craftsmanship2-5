import com.sun.tools.javac.util.Name;
import sun.tools.jconsole.Tab;

import java.util.List;
import java.util.*;
enum NonTerminalSymbol implements  Symbol {
    EXPRESSION,EXPRESSION_TAIL,TERM,TERM_TAIL,UNARY,FACTOR;






    private static Map<NonTerminalSymbol, List<SymbolSequence>> maps = new HashMap<NonTerminalSymbol, List<SymbolSequence>>(){{
        // Table 1
        List<Symbol> ExpressionSequence = new ArrayList<>();
        ExpressionSequence.add(TERM);
        ExpressionSequence.add(EXPRESSION_TAIL);
        List<SymbolSequence> ExpressionFollows =new ArrayList<>();
        ExpressionFollows.add(SymbolSequence.build(ExpressionSequence));
        put(EXPRESSION,ExpressionFollows);
        //table 2

        List<Symbol> ExpressionTail1 = new ArrayList<>();
        ExpressionTail1.add(TerminalSymbol.PLUS);
        ExpressionTail1.add(TERM);
        ExpressionTail1.add(EXPRESSION_TAIL);
        List<Symbol> ExpressionTail2 = new ArrayList<>();
        ExpressionTail2.add(TerminalSymbol.MINUS);
        ExpressionTail2.add(TERM);
        ExpressionTail2.add(EXPRESSION_TAIL);

        List<SymbolSequence> ExpressionTailFollows = new ArrayList<>();

        ExpressionTailFollows.add(SymbolSequence.build(ExpressionTail1));
        ExpressionTailFollows.add(SymbolSequence.build(ExpressionTail2));
        ExpressionTailFollows.add(SymbolSequence.EPSILON);
        put(EXPRESSION_TAIL,ExpressionTailFollows);

        //table 3
        List<Symbol> TermSequence = new ArrayList<>();
        TermSequence.add(UNARY);
        TermSequence.add(TERM_TAIL);

        List<SymbolSequence> TermFllows = new ArrayList<>();
        TermFllows.add(SymbolSequence.build(TermSequence));
        put(TERM,TermFllows);
        //table 4

        List<Symbol> TermTail1 = new ArrayList<>();
        TermTail1.add(TerminalSymbol.TIMES);
        TermTail1.add(UNARY);
        TermTail1.add(TERM_TAIL);
        List<Symbol> TermTail2 = new ArrayList<>();
        TermTail2.add(TerminalSymbol.DIVIDE);
        TermTail2.add(UNARY);
        TermTail2.add(TERM_TAIL);

        List<SymbolSequence> TermTailFollows = new ArrayList<>();

        TermTailFollows.add(SymbolSequence.build(TermTail1));
        TermTailFollows.add(SymbolSequence.build(TermTail2));
        TermTailFollows.add(SymbolSequence.EPSILON);
        put(TERM_TAIL,TermTailFollows);
        //table 5
        List<Symbol> UnarySequence1 = new ArrayList<>();
        UnarySequence1.add(TerminalSymbol.MINUS);
        UnarySequence1.add(FACTOR);

        List<Symbol> UnarySequence2 = new ArrayList<>();
        UnarySequence2.add(FACTOR);
        List<SymbolSequence> UnaryFollows  = new ArrayList<>();
        UnaryFollows.add(SymbolSequence.build(UnarySequence1));
        UnaryFollows.add(SymbolSequence.build(UnarySequence2));
        put(UNARY,UnaryFollows);
        //table 6
        List<Symbol> FactorSequence1 = new ArrayList<>();
        FactorSequence1.add(TerminalSymbol.OPEN);
        FactorSequence1.add(UNARY);
        FactorSequence1.add(TerminalSymbol.CLOSE);


        List<Symbol> FactorSequence2 = new ArrayList<>();
        FactorSequence2.add(TerminalSymbol.VARIABLE);
        List<SymbolSequence> FactorFollows  = new ArrayList<>();
        FactorFollows.add(SymbolSequence.build(FactorSequence1));
        FactorFollows.add(SymbolSequence.build(FactorSequence2));
        put(FACTOR,FactorFollows);
    }};





// The main purpose of this method is to parse the input into a node, possibly leaving a remainder.
// The ParseState’s success will be true if the parsing process was successful and false otherwise.
    @Override
    public ParseState parse(List<Token> input){
        if (input == null){
            throw new NullPointerException("Input is NULL for NON terminal Parse");
        }

        for(Token item : input) {
            List<SymbolSequence> tablelookup = maps.get(item.getType());
            for(SymbolSequence have: tablelookup){
                ParseState tempParse = have.match(input);
            if (tempParse.isSuccess()) {
                return tempParse;
                //  A non-terminal parses its input by going through its productions in the order
                // given by the table and attempting to match them to the input
            }
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
        List<SymbolSequence> tablelookup = maps.get(EXPRESSION);
        for(SymbolSequence sequence: tablelookup){
            ParseState tempParse = sequence.match(input);
            if(tempParse.isSuccess() && tempParse.hasNoRemainder()){
                return  Optional.of(tempParse.getNode());
            }

        }
        return Optional.empty();
            //return optinal empty
    }



}
