package parser;
import parser.TerminalSymbol;

public abstract class AbstractToken implements parser.Token {

        public final boolean matches(TerminalSymbol type){
            return getType()==type;
        }

}
