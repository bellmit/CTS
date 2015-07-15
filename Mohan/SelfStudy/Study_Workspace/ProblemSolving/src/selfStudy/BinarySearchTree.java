package selfStudy;

import java.util.Arrays;

public class BinarySearchTree {

	public class Node
	{
		private int data;
		private Node left;
		private Node right;
		
		public int getData() {
			return data;
		}
		public void setData(int data) {
			this.data = data;
		}
		public Node getLeft() {
			return left;
		}
		public void setLeft(Node left) {
			this.left = left;
		}
		public Node getRight() {
			return right;
		}
		public void setRight(Node right) {
			this.right = right;
		}
		
		public Node(int data)
		{
			this.data=data;
			this.left=null;
			this.right=null;
		}
	}
	
	private Node root;
	
	public BinarySearchTree(int data)
	{
		if(root==null)
		{
			root=new Node(data);
		}
		else
		{
			insert(data);
		}
		
	}

	public BinarySearchTree() {
		// TODO Auto-generated constructor stub
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public void insert(int data) {
		// TODO Auto-generated method stub
		Node node=new Node(data);
		if(root==null)
		{
			root=node;
		}
		else
		{
			Node temp=root;
			while(temp!=null)
			{
				if(temp.data<data)
				{
					if(temp.right==null)
					{
						temp.right=node;
						break;
					}
					temp=temp.right;
				}
				else
				{
					if(temp.left==null)
					{
						temp.left=node;
						break;
					}
					temp=temp.left;
				}
					
			}
		}
	}
	
	public boolean search(int data)
	{	
		Node temp=root;
		while(temp!=null)
		{
			if(temp.data==data)
			{
				return true;
			}
			else
			if(temp.data<data)
			{
				temp=temp.right;
			}
			else 
			{
				temp=temp.left;
			}
				
		}
		return false;
	}
	
	public void printAllNode(Node root)
	{
		if(root==null)
			return;		
		printAllNode(root.getLeft());
		System.out.print(root.getData()+" ");
		printAllNode(root.getRight());
	}
	
	public boolean delete(Node root,int data)
	{
		while(root!=null)
		{
			if(data==root.getData())
				break;
			else
			if(data>root.getData())
				root=root.right;
			else
				root=root.left;
		}
		System.out.println("delete"+data);
		if(root!=null)
		{
			Node temp=root;
			while(temp.right!=null)
			{
				temp=temp.right;
			}
			if(temp.left!=null)
			{
				root.setData(temp.left.data);
				temp.left=null;
			}else
			{
				root.setData(temp.data);
				temp=null;
			}
		}
		
		return false;
	}
	
	public int lca(Node root,int d1,int d2)
	{
		if(root==null)
			return 0;
		
		if(root.data<d1 && root.data<d2)
		{
			//System.out.println("lca==>root"+root.data);
			return lca(root.right, d1, d2);
		}
		else
			if(root.data>d1 && root.data>d2)
		{
				//System.out.println("lca==>root"+root.data);
				return lca(root.left, d1, d2);
		}
		if(search(d1) && search(d2))
		{
			//System.out.println("lca==>root"+root.data);
			return root.data;
		}
		
		return 0;
	}
	
	public void inOrderTraversal(Node root){
		if(root==null)
			return;
		System.out.print(root.getData()+" ");
		inOrderTraversal(root.getLeft());
		inOrderTraversal(root.getRight());
	}
	public void preOrderTraversal(Node root){
		if(root==null)
			return;
		
		preOrderTraversal(root.getLeft());
		System.out.print(root.getData()+" ");
		preOrderTraversal(root.getRight());
	}
	public void postOrderTraversal(Node root){
		if(root==null)
			return;
		
		postOrderTraversal(root.getLeft());
		postOrderTraversal(root.getRight());
		System.out.print(root.getData()+" ");
	}
	
	static int j=0;
	public Node constructBST(int[] in,int[] pre,int pos)
	{
		
		if (pre.length == 0 || pos==in.length) return null;
		  int rootVal = in[pos];
		//  System.out.println(in[pos]+"in"+Arrays.toString(in)+"pre"+Arrays.toString(pre)+"pos"+pos);  
		 int i=Arrays.binarySearch(pre, in[pos]);
		  Node root = new Node(rootVal);
		  root.left = constructBST(in, Arrays.copyOfRange(pre, 0, i),++j);
		  System.out.println("LEFT done");
		  root.right = constructBST(in,Arrays.copyOfRange(pre, i+1, pre.length),j );
		  return root;
	}
}
