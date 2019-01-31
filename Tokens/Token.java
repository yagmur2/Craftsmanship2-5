package Tokens;

import Tokens.*;

public interface Token {

    TerminalSymbol getType();
    boolean matches(TerminalSymbol type);

}
