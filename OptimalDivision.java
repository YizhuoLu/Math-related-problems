package math;

public class OptimalDivision {
	/*
	 * Q: Given a list of positive integers, the adjacent integers will perform the float division. 
	 * For example, [2,3,4] -> 2 / 3 / 4.
	 * However, you can add any number of parenthesis at any position to change the priority of operations. 
	 * You should find out how to add parenthesis to get the maximum result, and return the corresponding 
	 * expression in string format. Your expression should NOT contain redundant parenthesis.
	 * 
	 * Note:
	 * 1. The length of the input array is [1, 10].
	 * 2. Elements in the given array will be in range [2, 1000].
	 * 3. There is only one optimal division for each test case.
	 * */
	
	/*
	 * Algorithm: 
	 * Given [a, b, c, d,..., n], a must be the numerator, b must be the denominator. So the only way to make
	 * the division to be maximum, we should make the denominator to be a fraction with very large denominators
	 * which is a/(b/c/d/.../n).
	 * 
	 * Complexity Analysis:
	 * T: O(n)
	 * S: O(n)
	 * */
	
	public String optimalDivision(int[] nums) {
		int N = nums.length;
		if(N == 1) {
			return "" + nums[0];
		} else if (N == 2) {
			return "" + nums[0] + "/" + nums[1];
		}
		StringBuilder sb = new StringBuilder();
		sb.append(nums[0]);
		sb.append("/");
		sb.append("(");
		sb.append(nums[1]);
		for (int i = 2; i < N; ++i) {
			sb.append("/");
			sb.append(nums[i]);
		}
		sb.append(")");
		return sb.toString();
	}
}
