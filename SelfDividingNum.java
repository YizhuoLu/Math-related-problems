package math;

import java.util.ArrayList;
import java.util.List;

public class SelfDividingNum {
	/*
	 * Q: A self-dividing number is a number that is divisible by every digit it contains. For example, 
	 * 128 is a self-dividing number because 128 % 1 == 0, 128 % 2 == 0, and 128 % 8 == 0. Also, a 
	 * self-dividing number is not allowed to contain the digit zero. Given a lower and upper number 
	 * bound, output a list of every possible self dividing number, including the bounds if possible.
	 * */
	
	/*
	 * Algorithm: traverse the values from left to right to check each number in the range.
	 * When we are looking at each number,  we do check each digit to see if it can be divisible.
	 * 
	 * Performance Analysis:
	 * T: O(nlogn)
	 * S: O(1)
	 * */
	
	public List<Integer> selfDividingNumbers(int left, int right){
		List<Integer> res = new ArrayList<>();
		for (int i = left; i <= right; ++i) {
			if (isSelfDivisable(i)) {
				res.add(i);
			}
		}
		return res;
	}
	
	private boolean isSelfDivisable(int num) {
		int cur = num;
		while (cur != 0) {
			int lastBit = cur % 10;
			cur = cur / 10;
			if (num % lastBit != 0) return false;
		}
		return true;
	}
}
