package math;

public class SmallestRangeI {
	/*
	 * Q: Given an array A of integers, for each integer A[i] we may choose any x with -K <= x <= K, 
	 * and add x to A[i]. After this process, we have some array B. Return the smallest possible 
	 * difference between the maximum value of B and the minimum value of B.
	 * */
	
	/*
	 * Algorithm: 
	 * Let A be the original array, and B be the array after all our modifications. Towards 
	 * trying to minimize max(B) - min(B), let's try to minimize max(B) and maximize min(B) separately. 
	 * The smallest possible value of max(B) is max(A) - K, as the value max(A) cannot go lower. 
	 * Similarly, the largest possible value of min(B) is min(A) + K. So the quantity max(B) - min(B) is 
	 * at least ans = (max(A) - K) - (min(A) + K).
	 * 
	 * Performance Analysis:
	 * T: O(n)
	 * S: O(1)
	 * */
	
	public int smallestRangeI(int[] A, int K) {
		// Assumption: A is not null and its length is not zero.
		int N = A.length;
		int max = A[0], min = A[0];
		for (int i = 1; i < N; ++i) {
			max = Math.max(max, A[i]);
			min = Math.min(min, A[i]);
		}
		return Math.max(0, max - min  - 2 * K);
	}
}
