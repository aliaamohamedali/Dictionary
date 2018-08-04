package rb_bst;

public class Node {

	public static final boolean RED = true;
	public static final boolean BLACK = false;
	public static final Node NIL = new Node(null, null, Node.BLACK);

	private String data;

	private Node leftChild;
	private Node rightChild;
	private Node parent;

	private boolean color; // Red = True , Black = False

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Node getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(Node leftChild) {
		this.leftChild = leftChild;
	}

	public Node getRightChild() {
		return rightChild;
	}

	public void setRightChild(Node rightChild) {
		this.rightChild = rightChild;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public boolean getColor() {
		return color;
	}

	public void setColor(boolean color) {
		this.color = color;
	}

	public Node(String data, Node parent, boolean color) {
		this.data = data;
		this.parent = parent;
		this.color = color;
		this.leftChild = Node.NIL;
		this.rightChild = Node.NIL;
	}

}
