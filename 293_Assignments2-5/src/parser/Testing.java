package parser;

import java.util.*;

public class Testing {
    public static  void main(String args[]){

        List<Token> list = new ArrayList<>();
        list.add(Variable.build("a"));
        list.add(Connector.build(TerminalSymbol.PLUS));
        list.add(Variable.build("b"));
        list.add(Connector.build(TerminalSymbol.DIVIDE));
        list.add(Variable.build("c"));

        System.out.println(NonTerminalSymbol.parseInput(list));
    }
}