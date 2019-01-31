package Tokens;

import Tokens.*;
//interfave for tokens
public interface Token {

    TerminalSymbol getType();
    boolean matches(TerminalSymbol type);

}
