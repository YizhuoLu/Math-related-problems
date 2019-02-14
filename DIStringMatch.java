package math;

import java.util.Deque;
import java.util.LinkedList;

public class DIStringMatch {
	/*
	 * Q: Given a string S that only contains "I" (increase) or "D" (decrease), let N = S.length.
	 * 		Return any permutation A of [0, 1, ..., N] such that for all i = 0, ..., N-1:
	 * 		If S[i] == "I", then A[i] < A[i+1]
	 * 		If S[i] == "D", then A[i] > A[i+1]
	 * */
	
	/*
	 * Data structure: Deque
	 * Algorithm: 
	 * 	we traverse from right to left for the string 
	 * 		if it's 'I': we put the largest number that we have currently.
	 * 		else: we put the smallest number that we have currently.
	 * 
	 * Performance Analysis:
	 * T: O(n)
	 * S: O(n) since I used a deque to store [0,  N] values.
	 * */
	
	public int[] diStringMatch(String S) {
		// corner case
		if (S == null || S.length() == 0) {
			return new int[] {};
		}
		int N = S.length();
		Deque<Integer> deque = new LinkedList<>();
		for (int i = 0; i <= N; ++i) {
			deque.offerFirst(i);
		}
		int[] res = new int[N + 1];
		char[] ch = S.toCharArray();
		int idx = N;
		for (int i = N - 1; i  >= 0; --i) {
			if (ch[i] == 'D') {
				res[idx--] = deque.pollLast();
			} else  {
				res[idx--] = deque.pollFirst();
			}
		}
		res[idx] = deque.pollLast();
		return res;
	}
	
	/*
	 * Improved: space complexity --> O(1)
	 * 		I use sumD and sumI to record the number of 'D', 'I' respectively.
	 * 		if ch[i] = 'I', I put N  - sumI
	 * 		else: I put 0 + sumD
	 * 	finally, post-process the last item which is res[0] to be sumD.
	 *  
	 * T: O(n)
	 * S: O(1)
	 * */
	
	public int[] diStringMatchII(String S) {
		// corner case
		if (S == null || S.length() == 0) {
			return new int[] {};
		}
		int N = S.length();
		int sumD = 0, sumI = 0;
		char[] ch = S.toCharArray();
		int idx = N;
		int[] res = new int[N + 1];
		for (int i = N - 1; i >= 0; --i) {
			if (ch[i] == 'I') {
				// put the largest number currently we have
				res[idx--] = N  - sumI;
				sumI++;
			} else {
				res[idx--] = sumD++;
			}
		}
		res[0] = sumD;
		return res;
	}
}
