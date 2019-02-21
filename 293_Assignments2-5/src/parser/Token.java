package parser;

public interface Token {

	TerminalSymbol getType();

	boolean matches(TerminalSymbol type);

	boolean isOperator();
}
