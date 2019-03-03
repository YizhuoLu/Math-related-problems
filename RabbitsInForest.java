package math;

import java.util.HashMap;
import java.util.Map;

public class RabbitsInForest {
	/*
	 * Q: In a forest, each rabbit has some color. Some subset of rabbits (possibly all of them) 
	 * tell you how many other rabbits have the same color as them. Those answers are placed in an array.
	 * Return the minimum number of rabbits that could be in the forest.
	 * */
	
	/*
	 * Algorithm 1:
	 *  if there are x + 1 rabbits answering x, we have x + 1 rabbits having the same color.
	 *  if n % (x+1) == 0, we need n  / (x+1) groups of (x+1) rabbits.
	 *  if n % (x+1) != 0, we need n  / (x+1) + 1 groups of (x+1) rabbits.
	 *  I use hashMap to store n, key is x, value is n. res += Math.ceil(n/(x+1)) * (x+1).
	 *  
	 * Complexity Analysis:
	 * T: O(n)
	 * S: O(n)
	 * */
	public static int numRabbits(int[] ans) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int num : ans) {
			map.put(num, map.getOrDefault(num, 0) + 1);
		}
		int res = 0;
		for (int x : map.keySet()) {
			res += Math.ceil((double) map.get(x)  / (x + 1)) * (x+1);
		}
		return res;
	}
	
	/*
	 * Algorithm 2:
	 * we can also count in the process of looping.
	 * Since the number of ans is at most 1000, we can set up an array with size = 1000.
	 * 
	 * Complexity Analysis:
	 * T: O(n)
	 * S: O(1)
	 * */
	public static int numRabbitsII(int[] ans) {
		int[] cnt = new int[1000];
		int res = 0;
		for (int x : ans) {
			if (cnt[x]++ % (x + 1) == 0) {
				res += (x + 1);
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		int[] ans = {1,2,1};
		// 10, 10, 10
		// 1,2,1
		System.out.println("Min number of rabbits: " + numRabbitsII(ans));
	}
}
