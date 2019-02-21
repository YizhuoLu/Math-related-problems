package math;

import java.util.ArrayList;
import java.util.List;

public class BinaryGap {
	/*
	 * Q: Given a positive integer N, find and return the longest distance between two consecutive 1's 
	 * in the binary representation of N. If there aren't two consecutive 1's, return 0.
	 * */
	
	/*
	 * Algorithm 1: 
	 * I just store the index of each 1's, and I traverse these indices by pairs to find the max distance.
	 * 
	 * Complexity Analysis:
	 * T: O(n) n is the number of bits of the binary format of the given integer.
	 * S: O(n)
	 * */
	
	public static int maxDistance(int x) {
		List<Integer> idx = new ArrayList<>();
		int t = 0;
		while (x != 0) {
			int bit =  x % 2;
			if (bit == 1) idx.add(t);
			t++;
			x /= 2;
		}
		// corner case
		if (idx.size() == 1) {
			return 0;
		}
		int globalMax = Integer.MIN_VALUE;
		for (int i =  1; i < idx.size(); ++i) {
			globalMax = Math.max(idx.get(i) - idx.get(i - 1), globalMax);
		}
		return globalMax;
	}
	
	/*
	 * Algorithm 2: use bit operation  -- optimize space complexity to O(1).
	 * since an integer is 32 bits, I try each position's 1 to & x, if it != 0, I just found a '1', then
	 * I will update max to be max(max, i - prev). i is just the index of '1'.
	 * 
	 * T: O(1)
	 * S: O(1)
	 * */
	public static int maxDistanceII(int x) {
		int prev  = -1;
		// prev stores the last index of '1'.
		int mask = 1;
		int res = 0;
		for (int i = 0; i < 32; ++i) {
			if ((x & mask) != 0) {
				if (prev != -1) {
					res = Math.max(res, i - prev);
				}
				prev = i;
			}
			mask <<= 1;
		}
		return res;
	}
	
	public static void main(String[] args) {
		int x  =22;
		// 5, 6, 8,22
		System.out.println("The longest distance is: " + maxDistanceII(x));
	}
}
