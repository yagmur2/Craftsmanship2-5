package parser;
import java.util.*;

public final class InternalNode implements Node{

    private final List<Node> children;
    
    //Stores list and string representations so they aren't computed more than once
    private List<Token> childList;
    private String childString;

    //Getter method for children of the InternalNode.
    public List<Node> getChildren(){
        return this.children;
    }

    //Setter (constructor) method. Sets children to an unmodifiable copy of newChildren.
    private InternalNode(List<Node> newChildren){
        this.children = newChildren;
    }

    //Builds a new InternalNode with the given children. Throws a NullPointerException if List is null.
    public static final InternalNode build(List<Node> children){
        if (children != null){
            InternalNode node = new InternalNode(children);
            return node;
        }
        else {
        	throw new NullPointerException("List<Node> is null in method newInternal");
        }
    }

    @Override
    //Returns concatenation of the children's lists.
    public final List<Token> toList(){
    	
    	List<Token> list = new ArrayList<Token>();
   
    	if (this.childList == null) { //in the case the list has not been computed before
    		for (int i = 0; i < children.size(); i++) {
        		list.add(children.get(i).toList().get(i));
        	}
    		childList = list;
    		return childList;
    		}
    	else return childList;
    	}

    @Override
    //Returns the string representation of the node's children
    public String toString(){
    	
    	List<Token> list = this.toList();//converts children from Node list to Token list
    	StringBuilder string = new StringBuilder(500);//Change 500 to desired capacity of string
    	int closed = 0;//Number of closed brackets to add at the end
    	
    	if (childString == null) { //in the case the string has not been computed before
	    	for (int i = 0; i < list.size(); i++) {
	    		string.append("[" + list.get(i).toString());
	    		closed++;//add one more closed bracket
	    		}
	    	while (closed > 0) {
	    		string.append("]");
	    		closed--;
	    		}
	    	childString = string.toString();
	    	return childString;
	    	}
    	else return childString;
    	}
    
    }