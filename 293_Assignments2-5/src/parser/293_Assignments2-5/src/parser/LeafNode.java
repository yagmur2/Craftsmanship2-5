package parser;

import java.util.*;

public final class LeafNode implements Node {

	private final Token token;

	// Getter method for Token; returns its value.
	public Token getToken() {
		return this.token;
	}

	// Setter method for the Token value.
	private LeafNode(Token newToken) {
		this.token = newToken;
	}

	// Builds a new leaf with the given Token; throws an exception if value is null.
	public static final LeafNode build(Token token) {
		Objects.requireNonNull(token, "Token value is null in LeafNode builder");
		return new LeafNode(token);
	}

	public String toString() {
		return token.toString();
	}

	@Override
	// returns a list containing the LeafNode's Token as its single element.
	public final List<Token> toList() {
		return Arrays.asList(token);
	}

	// LeafNodes have no children
	@Override
	public List<Node> getChildren() {
		return null;
	}

	// Always returns true as the node should always be a leaf
	@Override
	public boolean isFruitful() {
		return true;
	}

	@Override
	public boolean isOperator() {
		return token.isOperator();
	}

	@Override
	public boolean isStartedByOperator() {
		return false;
	}

	@Override
	public Optional<Node> firstChild() {
		return Optional.empty();
	}

	@Override
	public boolean isSingleLeafParent() {
		return false;
	}
}