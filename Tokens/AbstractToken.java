package Tokens;
import Tokens.TerminalSymbol;

public abstract class AbstractToken implements Tokens.Token {

        public final boolean matches(TerminalSymbol type){
            return getType()==type;
        }

}
