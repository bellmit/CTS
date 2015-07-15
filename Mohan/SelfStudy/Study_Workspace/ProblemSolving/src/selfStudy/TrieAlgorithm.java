package selfStudy;

import java.util.LinkedList;

public class TrieAlgorithm {

	LinkedList<TrieNode> ll;
	
	public TrieAlgorithm()
	{
		ll=new LinkedList<TrieAlgorithm.TrieNode>();
		TrieNode tn=new TrieNode();
		tn.setData('m');
		tn.setChildNode(null);
		tn.setEofString(true);
		ll.add(tn);
		
	}
	
	public void insertTrieAlgorithm(String sinsert)
	{
		char [] cfind=sinsert.toCharArray();
		LinkedList<TrieNode> tnList=ll;
		TrieNode tn;
		for(int i=0;i<cfind.length;i++)
		{
			tn=findTrieNode(cfind[i],tnList);
			if(tn==null)
			{
				tn=new TrieNode();
				tn.setData(cfind[i]);
				tn.setChildNode(new LinkedList<TrieAlgorithm.TrieNode>());
				tnList.add(tn);
				
				tnList=tn.getChildNode();
			}
			else
			{
				if(tn.getChildNode()==null)
				{
					tn.setChildNode(new LinkedList<TrieAlgorithm.TrieNode>());
				}
				tnList=tn.getChildNode();
			}
			if(i==cfind.length-1)
			{
				tn.setEofString(true);
			}
		}
		System.out.println("LL size"+ll.size());
	}
	
	
	
	public boolean searchTrieAlgorithm(String sfind)
	{
		char [] cfind=sfind.toCharArray();
		LinkedList<TrieNode> tnList=ll;
		TrieNode tn;
		for(int i=0;i<cfind.length;i++)
		{
			tn=findTrieNode(cfind[i],tnList);
			if(tn==null)
				return false;
			else
				tnList=tn.getChildNode();
			
			if(i==cfind.length-1)
			{
				if(tn.isEofString()==true)
					return true;		
			}
		}
		return false;
		
	}
	
	
	public TrieNode findTrieNode(char cfind, LinkedList<TrieNode> tn) {
		// TODO Auto-generated method stub
		if(tn!=null)
		{
			for (TrieNode trieNode : tn) {
				if(trieNode.getData()==cfind)
					return trieNode;
			}
		}
		return null;
	}


	public class TrieNode
	{
		private char data;
		private boolean eofString;
		private LinkedList<TrieNode> childNode;
		
		public char getData() {
			return data;
		}
		public void setData(char data) {
			this.data = data;
		}
		public boolean isEofString() {
			return eofString;
		}
		public void setEofString(boolean eofString) {
			this.eofString = eofString;
		}
		public LinkedList<TrieNode> getChildNode() {
			return childNode;
		}
		public void setChildNode(LinkedList<TrieNode> childNode) {
			this.childNode = childNode;
		}
		
	}
	
}
