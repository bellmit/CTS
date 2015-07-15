package com.ccup.DS;

public class LinkedList {
	
	public class Node 
	{
		private int data;
		private Node next;
			
		public Node(int data) {
			// TODO Auto-generated constructor stub
			this.data=data;
			next=null;
		}
		
		
		public int getData() {
			return data;
		}
		public void setData(int data) {
			this.data = data;
		}
		public Node getNext() {
			return next;
		}
		public void setNext(Node next) {
			this.next = next;
		}
		
	}
	
	private Node root;
	private int size;
	
	public LinkedList() {
		// TODO Auto-generated constructor stub
		this.root=null;
		
	}
	
	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public void add(int data)
	{
		Node node=new Node(data);
		
		if(root==null)
			root=node;
		else
		{
			Node temp=root;
			while(temp.next!=null)
			{
				temp=temp.next;
			}
			temp.next=node;
		}
		size++;
		
	}
	

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
