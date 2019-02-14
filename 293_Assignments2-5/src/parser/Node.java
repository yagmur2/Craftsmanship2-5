package parser;

import java.util.*;

public interface Node {

	// Returns list representation of subtree rooted at node
    public List<Token> toList();
    
}