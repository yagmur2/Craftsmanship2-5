package parser;

import java.util.*;

public final class LeafNode implements Node{

    //TODO: "A LeafNode delegates "toString" to its Token. (find out what this means?)

    private final Token token;
    
    //Getter method for Token; returns its value.
    public Token getToken(){
        return this.token;
    }

    //Setter method for the Token value.
    private LeafNode(Token newToken) {
        this.token = newToken;
    }

    //Builds a new leaf with the given Token; throws an exception if value is null.
    public static final LeafNode build(Token token){
        if (token != null) {
            return new LeafNode(token);
        }
        else {
        	throw new NullPointerException("Token value is null in method newLeaf");
        	}
    }
    
    public String toString(){
    	return token.toString();
    }
    
    @Override
    //returns a list containing the LeafNode's Token as its single element.
	public final List<Token> toList() {
    	List<Token> list = new LinkedList<Token>();
    	list.add(token);
    	return list;
	}

}