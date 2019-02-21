package parser;

import java.util.List;
import java.util.Objects;

final class ParseState {

    private final boolean success;
    private final Node node ;
    private final List<Token> remainder  ;
    final static ParseState FAILURE = new ParseState(false,null,null);

    private ParseState(boolean success, Node node, List<Token> tokenlist){
        this.success = success;
        this.node = node;
        this.remainder = tokenlist;
    }
    
    public boolean isSuccess(){
        return this.success;
    }

    public Node getNode(){
        return this.node;
    }

    public List<Token> getRemainder(){
        return this.remainder;
    }

    public boolean hasNoRemainder(){
        return remainder.isEmpty();
    }
 
    public static final ParseState build(Node node, List<Token> givenRemainder){
            return new ParseState(true, Objects.requireNonNull(node, "Node is null in ParseState build()"), Objects.requireNonNull(givenRemainder, "Remainder is null in ParseState build()"));
        }
}

