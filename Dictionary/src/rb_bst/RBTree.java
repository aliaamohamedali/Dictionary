package rb_bst;

import java.util.LinkedList;
import java.util.Queue;

public class RBTree {

	//public static final boolean RED = true;
	//public static final boolean BLACK = false;

	private Node root;
	private int size;

	public RBTree() {
		size = 0;
		root = Node.NIL;
	}
	
	public int getSize() {
		return this.size;
	}

	public Node getRoot() {
		return this.root;
	}

	public Node find(String data) {
		Node currentNode = root;

		if (root.getData() == data)
			return root;
		else {
			while (currentNode != Node.NIL) {

				if (currentNode.getData().equals(data)) {
					return currentNode;
				} else if (currentNode.getData().compareTo(data) < 0) {
					currentNode = currentNode.getRightChild();
				} else {
					currentNode = currentNode.getLeftChild();
				}
			}
		}
		return Node.NIL;
	}

	public void addNode(String data) {

		if (root == Node.NIL) {
			root = new Node(data, Node.NIL, Node.BLACK);
			++size;
		} else {
			addNodeUtil(data);
		}
		System.out.println("Size: " + this.size);
		System.out.println("Height: " + this.treeHeight());
	}

	private void addNodeUtil(String data) {
		Node previous;
		Node node = root;
		int comp;

		while (node != Node.NIL) {

			previous = node;
			comp = data.compareTo(node.getData());

			// System.out.println(data + " " + node.getData() + " " + comp);

			if (comp > 0) {
				node = node.getRightChild();
				if (node == Node.NIL) {
					node = new Node(data, previous, Node.RED);
					previous.setRightChild(node);
					++size;
					if (node.getParent() != root) {
						this.insertionFix(node);
					}
					node = Node.NIL;
				}
			} else if (comp < 0) {
				node = node.getLeftChild();
				if (node == Node.NIL) {
					node = new Node(data, previous, Node.RED);
					previous.setLeftChild(node);
					++size;
					if (node.getParent() != root) {
						this.insertionFix(node);
					}
					node = Node.NIL;
				}
			} else {
				System.out.println(data + "  -  Word already exists");
				node = Node.NIL;
			}
		}

	}

	private void insertionFix(Node node) {

		Node uncle;
		while (node.getParent() != Node.NIL && node.getParent().getColor() == Node.RED) {
			//System.out.println(node.getData());
			if (node.getParent().getParent().getLeftChild() != Node.NIL
					&& node.getParent() == node.getParent().getParent().getLeftChild()) {
				uncle = node.getParent().getParent().getRightChild();
				if (uncle != Node.NIL && uncle.getColor() == Node.RED) { // Case 1
					node.getParent().setColor(Node.BLACK);
					uncle.setColor(Node.BLACK);
					node.getParent().getParent().setColor(Node.RED);
					node = node.getParent().getParent();
				} else {
					if (node == node.getParent().getRightChild()) { // Case 2
						node = node.getParent();
						this.rotateLeft(node);
					}
					node.getParent().setColor(Node.BLACK); // Case 3
					node.getParent().getParent().setColor(Node.RED);
					this.rotateRight(node.getParent().getParent());
				}
			} else {
				uncle = node.getParent().getParent().getLeftChild();
				if (uncle != Node.NIL && uncle.getColor() == Node.RED) { // Case 1
					node.getParent().setColor(Node.BLACK);
					uncle.setColor(Node.BLACK);
					node.getParent().getParent().setColor(Node.RED);
					node = node.getParent().getParent();
				} else {
					if (node == node.getParent().getLeftChild()) { // Case 2
						node = node.getParent();
						this.rotateRight(node);
					}
					node.getParent().setColor(Node.BLACK); // Case 3
					node.getParent().getParent().setColor(Node.RED);
					this.rotateLeft(node.getParent().getParent());
				}
			}
		}
		this.root.setColor(Node.BLACK);
	}

	private void rotateLeft(Node node) {

		Node y = node.getRightChild();
		node.setRightChild(y.getLeftChild());

		if (y.getLeftChild() != Node.NIL) {
			y.getLeftChild().setParent(node);
		}
		y.setParent(node.getParent());

		if (node.getParent() == Node.NIL) {
			this.root = y;
		} else if (node == node.getParent().getLeftChild()) {
			node.getParent().setLeftChild(y);
		} else {
			node.getParent().setRightChild(y);
		}

		y.setLeftChild(node);
		node.setParent(y);

	}

	private void rotateRight(Node node) {

		Node y = node.getLeftChild();
		node.setLeftChild(y.getRightChild());

		if (y.getRightChild() != Node.NIL) {
			y.getRightChild().setParent(node);
		}
		y.setParent(node.getParent());

		if (node.getParent() == Node.NIL) {
			this.root = y;
		} else if (node == node.getParent().getRightChild()) {
			node.getParent().setRightChild(y);
		} else {
			node.getParent().setLeftChild(y);
		}

		y.setRightChild(node);
		node.setParent(y);

	}

	public void deleteNode(String data) {

		Node node = find(data);
		if (node != Node.NIL) {
			this.deleteNodeUtil(node);
			--size;
		} else {
			System.out.println("Error - Data Not Found!");
		}
		System.out.println("Size: " + this.size);
		System.out.println("Height: " + this.treeHeight());
	}

	private void deleteNodeUtil(Node z) {
		
		Node y = z;
		Node x;
		boolean yOriginalColor = y.getColor();

		// What if both children are null ???
		
		if (z.getLeftChild() == Node.NIL) {
			x = z.getRightChild();
			this.transplant(z, z.getRightChild());
		} else if (z.getRightChild() == Node.NIL) {
			x = z.getLeftChild();
			this.transplant(z, z.getLeftChild());
		} else {
			y = this.treeMin(z.getRightChild());
			yOriginalColor = y.getColor();
			x = y.getRightChild();
			if (y.getParent() == z) {
				x.setParent(y);
			} else {
				this.transplant(y, y.getRightChild());
				y.setRightChild(z.getRightChild());
				y.getRightChild().setParent(y);
			}
			this.transplant(z, y);
			y.setLeftChild(z.getLeftChild());
			y.getLeftChild().setParent(y);
			y.setColor(z.getColor());
		}
		if (yOriginalColor == Node.BLACK) {
			this.deletionFix(x);
		}
	}

	private void deletionFix(Node x) {

		Node w;
		while (x != this.root && x.getColor() == Node.BLACK) {
			if (x == x.getParent().getLeftChild()) {
				w = x.getParent().getRightChild();
				if (w.getColor() == Node.RED) {
					w.setColor(Node.BLACK);
					x.getParent().setColor(Node.RED);
					this.rotateLeft(x.getParent());
					w = x.getParent().getRightChild();
				}
				if (w.getLeftChild().getColor() == Node.BLACK && w.getRightChild().getColor() == Node.BLACK) {
					w.setColor(Node.RED);
					x = x.getParent();
				} else {
					if (w.getRightChild().getColor() == Node.BLACK) {
						w.getLeftChild().setColor(Node.BLACK);
						w.setColor(Node.RED);
						this.rotateRight(w);
						w = x.getParent().getRightChild();
					}
				}
				w.setColor(x.getParent().getColor());
				x.getParent().setColor(Node.BLACK);
				w.getRightChild().setColor(Node.BLACK);
				this.rotateLeft(x.getParent());
				x = this.root;
			} else {
				if (x == x.getParent().getRightChild()) {
					w = x.getParent().getLeftChild();
					if (w.getColor() == Node.RED) {
						w.setColor(Node.BLACK);
						x.getParent().setColor(Node.RED);
						this.rotateRight(x.getParent());
						w = x.getParent().getLeftChild();
					}
					if (w.getLeftChild().getColor() == Node.BLACK && w.getRightChild().getColor() == Node.BLACK) {
						w.setColor(Node.RED);
						x = x.getParent();
					} else {
						if (w.getLeftChild().getColor() == Node.BLACK) {
							w.getRightChild().setColor(Node.BLACK);
							w.setColor(Node.RED);
							this.rotateLeft(w);
							w = x.getParent().getLeftChild();
						}
					}
					w.setColor(x.getParent().getColor());
					x.getParent().setColor(Node.BLACK);
					w.getLeftChild().setColor(Node.BLACK);
					this.rotateRight(x.getParent());
					x = this.root;
				}
			}
		}
		x.setColor(Node.BLACK);
	}

	private void transplant(Node u, Node v) {
		// u = node to be deleted.
		// v = node at end of tree to replace
		// Replace node u with v
		
		if (u.getParent() == Node.NIL) {
			this.root = v;
		} else if (u == u.getParent().getLeftChild()) {
			u.getParent().setLeftChild(v);
		} else {
			u.getParent().setRightChild(v);
		}
		v.setParent(u.getParent());
	}

	private Node treeMin(Node x) {

		while (x.getLeftChild() != Node.NIL) {
			x = x.getLeftChild();
		}
		return x;
	}

	public int treeHeight() {
		Node node = this.root;
		return this.treeHeightUtil(node);
	}
	
	private int treeHeightUtil(Node node) {

		if (node == Node.NIL)
			return 0;
		return Math.max(treeHeightUtil(node.getLeftChild()), treeHeightUtil(node.getRightChild())) + 1;

	}

	public void inOrderTraversal() {
		Node node = this.root;
		this.inOrderTraversalUtil(node);
	}
	
	private void inOrderTraversalUtil(Node node) {

		if (node.getLeftChild() != Node.NIL) {
			inOrderTraversalUtil(node.getLeftChild());
		}

		if (node.getData() != null) {
			System.out.println(node.getData());
		}

		if (node.getRightChild() != Node.NIL) {
			inOrderTraversalUtil(node.getRightChild());
		}
	}

	public void levelOrderTraversal() {
		Queue<Node> queue = new LinkedList<Node>();
		Node node;
		if (this.root == Node.NIL)
			return;
		queue.clear();
		queue.add(this.root);
		while (!queue.isEmpty()) {
			node = queue.remove();
			System.out.print(node.getData() + " ");
			if (node.getLeftChild() != Node.NIL)
				queue.add(node.getLeftChild());
			if (node.getRightChild() != Node.NIL)
				queue.add(node.getRightChild());
		}
		System.out.println();
	}

}