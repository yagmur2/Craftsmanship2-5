package parser;

import java.util.*;

public interface Node {

    public List<Token> toList();
    // Returns list representation of subtree rooted at node
    
    public String toString();
    
}