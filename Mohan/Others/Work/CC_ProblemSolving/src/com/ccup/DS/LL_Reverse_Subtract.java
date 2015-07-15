package com.ccup.DS;

import com.ccup.DS.LinkedList.Node;




public class LL_Reverse_Subtract {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LinkedList ll=new LinkedList();
		ll.add(7);
		ll.add(6);
		ll.add(5);
		ll.add(4);
		ll.add(3);
		ll.add(2);
		ll.add(1);
		ll.add(0);
		ll.add(-1);
		
		printLinkedList(ll);
		//ll.setRoot(reverse(ll.getRoot()));
		getDifference(ll);
		printLinkedList(ll);
		
		
	}

	private static void printLinkedList(LinkedList ll) {
		// TODO Auto-generated method stub
		Node root=ll.getRoot();
		System.out.println(" ");
		while(root!=null)
		{
			System.out.print(root.getData()+" ");
			root=root.getNext();
		}
	}

	private static void getDifference(LinkedList ll)
	{
		int size=ll.getSize();
		
		Node midPoint=findMidPoint(ll.getRoot());
		
		if(size%2!=0)
		{
			midPoint.setData(0);
			midPoint=midPoint.getNext();
		}
		
		midPoint=(reverse(midPoint));
		//printLinkedList(ll);
		subtract(ll.getRoot(),midPoint);
		midPoint=(reverse(midPoint));
	}
	
	
	private static void subtract(Node node, Node midpt) {
		// TODO Auto-generated method stub
		while(midpt!=null)
		{
			node.setData(node.getData()-midpt.getData());
			node=node.getNext();
			midpt=midpt.getNext();
		}
	}

	private static Node reverse(Node node) {
		// TODO Auto-generated method stub
		if(node==null || node.getNext()==null)
			return node;
		Node cur,next;
		Node prev=null;
		cur=node;
		next=node.getNext();
		while(next!=null)
		{
			cur.setNext(prev);
	        prev = cur;
	        cur = next;
	        next = next.getNext();
		}
		cur.setNext(prev);
		return cur;
	}

	private static Node findMidPoint(Node fastptr)
	{
		Node slwptr=fastptr;
			while(fastptr!=null && fastptr.getNext()!=null)
			{
				slwptr=slwptr.getNext();
				fastptr=fastptr.getNext().getNext();
				
			}
		
		//System.out.println("Midpoint"+slwptr.getData());
		return slwptr;
	}
	
	
	
}
