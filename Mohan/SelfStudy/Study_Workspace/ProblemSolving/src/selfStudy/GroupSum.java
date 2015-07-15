package selfStudy;

public class GroupSum {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(groupSum(0,new int[]{1,2,3,4,5},10));
	}
	public static boolean groupSum(int start, int[] nums, int target) {
		  if(start>=nums.length)
		     return target==0;
		 
		    System.out.println("t");
		     if(groupSum(start+1,nums,target-nums[start]))return true;
		     if(groupSum(start+1,nums,target))return true;

		     return false;
		     
		}


}
