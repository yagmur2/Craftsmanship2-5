package parser;
import java.util.*;
import java.util.stream.*;

public final class InternalNode implements Node{

	public static class Builder{
		
		private List<Node> children = new ArrayList<>();
		
		//Appends a new Node to the children
		public boolean addChild(Node node) {
			return this.children.add(node);
		}
		
		//Removes all childless Nodes from children, and if this results in only one internal node,
		//replace it with its children
		public Builder simplify() {
			this.children = this.children.stream()
				.filter(child -> child.isFruitful())
				.collect(Collectors.toList());
			//If the children list has a single internal node left, replace it with its children
			if (children.size() == 1) {
				children = children.get(0).getChildren();
			}
			return this;
		}   
		
		//Returns new InternalNode with the Builder's simplified children list
		public InternalNode build() {
			return InternalNode.build(this.simplify().children);	
		}
	}
	
    private final List<Node> children;
    
    //Stores previous computations of toList and toString so it is not re-calculated.
    private List<Token> childList = null;
    private String childString = null;

    //Getter method for children of the InternalNode.
    public List<Node> getChildren() {
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
    	return childString;
    	}

	@Override
	public boolean isFruitful() {
		return (!children.isEmpty());
	}
    
}