package parser;

import java.util.*;
import java.util.stream.*;

public final class InternalNode implements Node {

	public static class Builder {
		
		// Variables for the children list and the children of its first child
		private List<Node> children = new ArrayList<>();
		private List<Node> firstChildChildren = children.get(0).getChildren();

		// Appends a new Node to the children
		public boolean addChild(Node node) {
			return this.children.add(node);
		}

		// Removes all childless Nodes from children, and if this results in only one
		// internal node,
		// replace it with its children
		public Builder simplify() {
			this.children = this.children.stream()
					.filter(Node::isFruitful)
					.collect(Collectors.toList());
			if (singleNonNullNode(children)) {
				children = firstChildChildren;
			}
			children = simplifyChildren(children);
			return this;
		}

		// Returns new InternalNode with the Builder's simplified children list
		public InternalNode build() {
			return InternalNode.build(this.simplify().children);
		}

		// helper method, checks if there is a single internal node
		// left with non-null children
		private boolean singleNonNullNode(List<Node> children) {
			return children.size() == 1 && firstChildChildren != null;
		}

		// Helper method, simplifies the children list by replacing internal nodes with children
		// starting with operators or having a single leaf as a child
		private List<Node> simplifyChildren(List<Node> children) {
			List<Node> newChildren = new ArrayList<Node>();
			for (int i = 0; i < children.size() - 1; i++) {
				Node tempNode = children.get(i);
				if (tempNode.isSingleLeafParent()) {
					newChildren.add(tempNode.firstChild().get());
				}else if (tempNode.isStartedByOperator() && (previousNotOperator(children, i))) {
					newChildren.addAll(tempNode.getChildren());
				}else {
				newChildren.add(tempNode);
				}
			}
			return newChildren;
		}
		
		//helper method, checks if the previous node exists and is not an operator
		private boolean previousNotOperator(List<Node> children, int index) {
			return index == 0 || !children.get(index - 1).isOperator();
		}
	}

	private final List<Node> children;
	// Stores previous computations of toList and toString so it is not
	// re-calculated.
	private List<Token> childList = null;
	private String childString = null;

	@Override
	// Getter method for children of the InternalNode.
	public List<Node> getChildren() {
		return new ArrayList<Node>(children);
	}

	// Setter (constructor) method. Sets children to an unmodifiable copy of
	// newChildren.
	private InternalNode(List<Node> newChildren) {
		children = new ArrayList<Node>(newChildren);
	}

	// Builds a new InternalNode with the given children. Throws a
	// NullPointerException if List is null.
	public static final InternalNode build(List<Node> children) {
		return new InternalNode(Objects.requireNonNull(children, "Null children value in InternalNode builder"));
	}

	@Override
	// Returns concatenation of the children's lists.
	public final List<Token> toList() {
		if (childList == null) {
			childList = new LinkedList<Token>();
			for (Node item : children) {
				childList.addAll(item.toList());
			}
		}
		return childList;
	}

	@Override
	// Returns the string representation of the node's children
	public String toString() {
		if (childString == null) { // in the case the string has not been computed before
			StringBuilder str = new StringBuilder();
			str.append("[ ");
			for (Node item : children) {
				str.append(item.toString() + ", ");
			}
			if (!children.isEmpty()) {
				str.deleteCharAt(str.length() - 2);
			}
			str.append("]");
			childString = str.toString();
		}
		return childString;
	}

	// Returns true if the InternalNode has children
	@Override
	public boolean isFruitful() {
		return (!this.getChildren().isEmpty());
	}

	@Override
	public boolean isOperator() {
		return false;
	}

	@Override
	public boolean isStartedByOperator() {
		return Objects.requireNonNull(children).get(0).isOperator();
	}

	@Override
	public Optional<Node> firstChild() {
		if (this.isFruitful()) {
			return Optional.of(children.get(0));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public boolean isSingleLeafParent() {
		return children.size() == 1 && children.get(0).getChildren() == null;
	}
}