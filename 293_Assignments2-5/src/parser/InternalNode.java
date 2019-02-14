package parser;
import java.util.*;

public final class InternalNode implements Node{

    private final List<Node> children;
    
    //Stores previous computations of toList and toString so it is not re-calculated.
    private List<Token> childList = null;
    private String childString = null;

    //Getter method for children of the InternalNode.
    public List<Node> getChildren(){
        return new ArrayList<Node>(children);
    }

    //Setter (constructor) method. Sets children to an unmodifiable copy of newChildren.
    private InternalNode(List<Node> newChildren){
        children = new ArrayList<Node>(newChildren);
    }

    //Builds a new InternalNode with the given children. Throws a NullPointerException if List is null.
    public static final InternalNode build(List<Node> children){
        Objects.requireNonNull(children, "Null children value in InternalNode builder");
        return new InternalNode(children);
    }

    @Override
    //Returns concatenation of the children's lists.
    public final List<Token> toList(){
    	
    	if (childList == null) {
    		List<Token> list = new LinkedList<Token>();
    	
    		for(Node item : children) {
    			for(Token token : item.toList()) {
    				list.add(token);
    			}
    		}
    		childList = list;
    	}
    	return childList;
    }
    
    @Override
    //Returns the string representation of the node's children
    public String toString(){
    	
    	if (childString != null) { //in the case the string has been computed before
	    	for (Node item : children) {
	    		childString += "[" + (item.toString()) + "[";
	    	}
	    	return childString;
    	}
    	else 
    		return childString;
    	}
    
    }