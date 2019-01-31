package Tokens;
import Tokens.*;
public final class Variable extends AbstractToken{
    private TerminalSymbol type = TerminalSymbol.VARIABLE;
    private final String representation;
    private static  Cache<String, Variable> cache = new Cache<String, Variable>();

    @Override
    public TerminalSymbol getType() {
        return this.type;
    }
    public final String getRepresentation(){
        return representation;
    }

    private Variable(String representation){
        this.representation = representation;
    }
    public static final Variable build(String representation){
        if(representation != null){
            return cache.get(representation,  Variable::new);
        }
        else{
            throw new NullPointerException("The represnation of the Variable is null");
        }
    }
    @Override
    public String toString(){
        return getRepresentation();
    }


}
