package parser;

import java.util.*;

public class Testing {
    public static  void main(String args[]){

        List<Token> list = new ArrayList<>();
        list.add(Variable.build("a"));


        System.out.println(NonTerminalSymbol.parseInput(list).get().toString());
    }
}