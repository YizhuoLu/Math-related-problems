package math;

import java.util.Arrays;

public class LargestPerimeterTriangle {
	/*
	 * Q: Given an array A of positive lengths, return the largest perimeter of a triangle with non-zero 
	 * area, formed from 3 of these lengths. If it is impossible to form any triangle of non-zero area, 
	 * return 0.
	 * */
	
	/*
	 * Algorithm:
	 * For a >= b >= c, they can form a triangle if a < b + c.
	 * 1. sort A
	 * 2. if A[n-1] < A[n-2] + A[n-3], we choose this tuple.
	 * 3. if A[n-1] >= A[n-2] + A[n-3], we have no other components to use to match A[n-1], so we give
	 * up  A[n-1].
	 * 4. repeat the step above.
	 * 
	 * Complexity Analysis:
	 * T: O(nlogn)
	 * S: O(1)
	 * */
	
	public static int largestPerimeter(int[] A) {
		// corner case
		int N = A.length;
		if (N < 3) {
			return 0;
		}
		Arrays.sort(A);
		for (int i = N - 3; i >= 0; --i) {
			if (A[i] + A[i + 1] > A[i + 2]) {
				return A[i] + A[i+1] + A[i+2];
			}
		}
		return 0;
	}
}
