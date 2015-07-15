package selfStudy;

import selfStudy.BinarySearchTree.Node;

public class TestBST {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BinarySearchTree bst=new BinarySearchTree();
		bst.insert(100);
		bst.insert(10);
		bst.insert(107);
		bst.insert(110);
		bst.insert(50);
		bst.insert(1);
		System.out.println("TEST"+bst.search(110));
		bst.printAllNode(bst.getRoot());
		
		//bst.delete(bst.getRoot(), 100);
		//bst.printAllNode(bst.getRoot());
		
		System.out.println("LCA for 107 50 is"+bst.lca(bst.getRoot(), 11, 50));
		System.out.println(">>>>>>>>INORDER<<<<<<<<<");
		bst.inOrderTraversal(bst.getRoot());
		System.out.println(">>>>>>>>PREORDER<<<<<<<<<");
		bst.preOrderTraversal(bst.getRoot());
		System.out.println(">>>>>>>>POSTORDER<<<<<<<<<");
		bst.postOrderTraversal(bst.getRoot());
		
		System.out.println("constructBST");
		int[] in={100,10,1,50,107,110};
		int[] pre={1,10,50,100,107,110};
		Node r=bst.constructBST(in,pre,0);
		bst.printAllNode(r);
	}

}
