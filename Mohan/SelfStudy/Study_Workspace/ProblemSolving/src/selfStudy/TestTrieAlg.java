package selfStudy;

public class TestTrieAlg {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TrieAlgorithm t=new TrieAlgorithm();
		System.out.println(t.searchTrieAlgorithm("1"));
		
		t.insertTrieAlgorithm("rohan");
		System.out.println(t.searchTrieAlgorithm("rohan"));
	}

}
