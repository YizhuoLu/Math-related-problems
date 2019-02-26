package math;

public class ArithmeticSlices {
	/*
	 * Q: A sequence of number is called arithmetic if it consists of at least three elements and if 
	 * the difference between any two consecutive elements is the same.
	 * A zero-indexed array A consisting of N numbers is given. A slice of that array is any pair 
	 * of integers (P, Q) such that 0 <= P < Q < N. A slice (P, Q) of array A is called arithmetic 
	 * if the sequence: A[P], A[p + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, this 
	 * means that P + 1 < Q. The function should return the number of arithmetic slices in the array A.
	 * */
	
	/*
	 * Algorithm 1:  brute force
	 * 	The most naive solution is to consider every pair of elements (with at least 1 element
	 * between them), so that the range of elements lying between these two elements acts as a 
	 * slice. Then, we can iterate over every such slice to check if all the consecutive elements
	 * within this range have the same difference. For every such range found, we can increment
	 * the count that is used to keep a track of the required result.
	 * 
	 * Complexity Analysis:
	 * T: O(n^3)
	 * S: O(1)
	 * */
	public static int numOfArithmeticSlicesI(int[] A) {
		// assumption: A is not null and has non-zero length
		int count = 0;
		int N = A.length;
		for (int s = 0; s < N - 2; ++s) {
			int d = A[s + 1] - A[s];
			for (int e = s + 2; e < N; ++e) {
				int i = 0;
				for (i = s + 1; i <= e; ++i) {
					if (A[i] - A[i - 1] != d) {
						break;
					}
				}
				if (i > e) {
					count++;
				}
			}
		}
		return count;
	}
	
	/*
	 * Algorithm 2: optimize the brute force above.
	 *  In fact, each time, we check all the pairs in the range, however, we can
	 *  just check the last pair in the current range to determine if it's a slice 
	 *  or not.
	 *  
	 *  Complexity Analysis:
	 *  T: O(n^2)
	 *  S: O(1)
	 * */
	public static int numOfArithmeticSlicesII(int[] A) {
		int count = 0;
		for (int s = 0; s < A.length - 2; ++s) {
			int d = A[s + 1] - A[s];
			for (int e = s + 2; e < A.length; ++e) {
				if (A[e] - A[e - 1] == d) {
					count++;
				} else {
					break;
				}
			}
		}
		return count;
	}
	/*
	 * Algorithm 3: recursion
	 * 	we use a helper function to return the number of slices
	 * between 0-i, if A[i] - A[i-1] = A[i-1]  - A[i-2], we know that there will be
	 * helper(i) + 1 more slices being produced by this new element. since 0~i, 1~i,
	 * ..., (i-2)~i are new slices that been produced. 
	 * 
	 * Complexity Analysis:
	 * T: O(n) since we at most run recursion (n-2) times.
	 * S: O(n) the height of the recursion tree.
	 * */
	static int sum = 0;
	public static int numOfArithmeticSlicesIII(int[] A) {
		helper(A, A.length - 1);
		return sum;
	}
	
	private static int helper(int[] A, int i) {
		// base case
		if (i < 2) {
			return 0;
		}
		int extra = 0;
		if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
			extra = 1 + helper(A, i - 1);
			sum += extra;
		} else {
			helper(A, i - 1);
		}
		return extra;
	}
	/*
	 * Algorithm 4: Dynamic Programming
	 * dp[i] represents the number of slices ending at ith index.
	 * base case: dp[0]=0, dp[1] = 0.
	 * induction rule: dp[i] = dp[i-1] + 1 if A[i] - A[i-1] = A[i-1]
	 * - A[i-2].
	 * 
	 * Complexity Analysis: 
	 * T: O(n)
	 * S: O(n)
	 * */
	public static int numOfArithmeticSlicesIV(int[] A) {
		int N = A.length;
		int[] dp = new int[N];
		int sum = 0;
		for (int i = 2; i < N; ++i) {
			if (A[i] - A[i-1] == A[i-1] - A[i-2]) {
				dp[i] = dp[i-1] + 1;
				sum += dp[i];
			}
		}
		return sum;
	}
	/*
	 * Algorithm 5: optimize the space to be O(1)
	 * Just keep track dp[i-1].
	 * 
	 * Complexity Analysis:
	 * T: O(n)
	 * S: O(1)
	 * */
	public static int numOfArithmeticSlicesV(int[] A) {
		int prev = 0;
		int sum = 0;
		for (int i = 2; i < A.length; ++i) {
			if (A[i] - A[i-1] == A[i-1] - A[i-2]) {
				prev = prev + 1;
				sum += prev;
			} else {
				prev = 0;
			}
		}
		return sum;
	}
	
	public static void main(String[] args) {
		int[] A = {1, 2, 3, 4};
		System.out.println("The number of arithmetic slices: " + numOfArithmeticSlicesV(A));
	}
}
