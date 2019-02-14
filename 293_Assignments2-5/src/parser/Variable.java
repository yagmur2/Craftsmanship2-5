package parser;

import java.util.Objects;

public final class Variable extends AbstractToken{

    //VARIABLES
    private final String representation;
    private static  Cache<String, Variable> cache = new Cache<String, Variable>();

    //private constructor
    private Variable(String representation){
        this.representation = representation;
    }

    //FUNCTIONS
    @Override
    public TerminalSymbol getType() {
        return TerminalSymbol.VARIABLE;
    }
    
    public final String getRepresentation(){
        return representation;
    }

    //build function for Variables.
    public static final Variable build(String representation){
    	Objects.requireNonNull(representation, "Variable representation is null");
        return cache.get(representation,  Variable::new);
    }

    @Override
    public String toString(){
        return getRepresentation();
    }


}
