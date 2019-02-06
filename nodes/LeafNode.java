package nodes;

import java.util.*;
import Tokens.*;

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
    public LeafNode newLeaf(Token token){
        if (token != null) {
            LeafNode leaf = new LeafNode(token);
            return leaf;
        }
        else throw new NullPointerException("Token value is null in method newLeaf");
    }
    
    @Override
    //TODO: returns a list containing the LeafNode's Token as its single element.
	public final List<Token> toList() {
    	List<Token> list = new ArrayList<Token>();
    	list.add(this.getToken());
    	return list;
	}

}