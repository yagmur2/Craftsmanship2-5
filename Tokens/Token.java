package Tokens;
import Tokens.*;

//interface for tokens
public interface Token {

    TerminalSymbol getType();
    boolean matches(TerminalSymbol type);

}
