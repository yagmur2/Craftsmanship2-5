package parser;
import parser.TerminalSymbol;

public abstract class AbstractToken implements Token {

		//Compares the Token to the TerminalSymbol and tells if it matches
        public final boolean matches(TerminalSymbol type){
            return getType()==type;
        }

}
