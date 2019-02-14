package parser;

import java.util.*;

public interface Node {

	//Returns list representation of subtree rooted at node
    List<Token> toList();
    
    //Returns a copy of this node's children
    List<Node> getChildren();
    
    //Returns true if the node is an internal node with 1+ children or if it is a leaf
    boolean isFruitful();
    
}