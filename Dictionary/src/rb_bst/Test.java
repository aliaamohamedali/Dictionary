package rb_bst;

public class Test {

	public static void main(String[] args) {

		RBTree rbTree = new RBTree();

		System.out.println(Node.NIL.getColor());
		
//		 // System.out.println("aaa");
//		 rbTree.addNode("aaa");
//		 // System.out.println("bz");
//		 rbTree.addNode("bz");
//		 // System.out.println("a");
//		 rbTree.addNode("a");
//		 // System.out.println("aa");
//		 rbTree.addNode("aa");
//		 // System.out.println("b");
//		 rbTree.addNode("b");
//		 // System.out.println("bx");
//		 rbTree.addNode("bx");
//		 // System.out.println("az");
//		 rbTree.addNode("az");

		rbTree.addNode("mario");
		rbTree.addNode("limbo");
		rbTree.addNode("adrian");
		rbTree.addNode("haimdal");
		rbTree.addNode("trix");
		rbTree.addNode("george");
		rbTree.addNode("xavier");
		rbTree.addNode("brock");
		rbTree.addNode("arnold");
		rbTree.addNode("becky");
		rbTree.addNode("cloc");
		rbTree.addNode("tracy");
		rbTree.addNode("groo");
		rbTree.addNode("freak");
		rbTree.addNode("sleak");
		rbTree.addNode("drake");
		rbTree.addNode("parker");

		rbTree.inOrderTraversal();
		rbTree.levelOrderTraversal();

		System.out.println(rbTree.treeHeight());
		System.out.println(rbTree.getSize());

		Node toDelete = rbTree.find("adrian");
		System.out.println("Deleting "+ toDelete.getData());
		rbTree.deleteNode(toDelete.getData());
		
		//System.out.println(toDelete.getLeftChild().getData());
		//System.out.println(toDelete.getRightChild().getData());
				
		rbTree.inOrderTraversal();
		rbTree.levelOrderTraversal();

		System.out.println(rbTree.treeHeight());
		System.out.println(rbTree.getSize());
		
		
		
		

		// rbtree.breadth(rbtree.getRoot());
	}

}
