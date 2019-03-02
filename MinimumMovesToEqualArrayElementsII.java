package math;

import java.util.Arrays;

public class MinimumMovesToEqualArrayElementsII {
	/*
	 * Q: Given a non-empty integer array, find the minimum number of moves required to 
	 * make all array elements equal, where a move is incrementing a selected element by 1 
	 * or decrementing a selected element by 1.
	 * You may assume the array's length is at most 10,000.
	 * */
	
	/*
	 * Algorithm 1: brute force
	 *  Since we can prove that the number to be settle must be the one in the array.
	 *  proof: say x4 -> sum=(x1-x4)+...(xn-x4)
	 *  change to x' = x4 + delta->sum2=(x--x4-delta)+...+(xn-x4-delta)=sum+delta which is larger than previous 
	 *  sum.
	 *  
	 * Complexity Analysis:
	 * T: O(n^2)
	 * S: O(1)
	 * */
	
	public static int minMoves2I(int[] nums) {
		long min = Long.MAX_VALUE;
		for (int num : nums) {
			int sum = 0;
			for (int item : nums) {
				sum += Math.abs(item - num);
			}
			min = Math.min(min, sum);
		}
		return (int) min;
	}
	
	/*
	 * Algorithm 2: sorting
	 * Since we have known that we want to find the number (k) that we need to settle.
	 * Number of moves = k * (number of items < k) - sum(less items) + sum(larger items) - k * (number of larger
	 * items).
	 * we can do this in the process of traversing the sorted array while each time we start 0 - totalSum.
	 * current index is the boundary. each time we add the current item to the previous sum, subtract it from
	 * the behind larger items' sum. Find the min in the process.
	 * 
	 * Complexity Analysis:
	 * T: O(nlogn)
	 * S: O(1)
	 * */
	public static int minMoves2II(int[] nums) {
		Arrays.sort(nums);
		long min = Long.MAX_VALUE;
		long largerSum = 0L;
		long lessSum = 0L;
		for (int num :  nums) {
			largerSum += num; 
		}
		for (int i = 0; i < nums.length; ++i) {
			// nums[i] is the one to settle
			long tmp = (long)nums[i] * i - lessSum + (largerSum - lessSum) - (long)nums[i] * (nums.length - i);
			min = Math.min(min, tmp);
			lessSum += nums[i];
		}
		return (int) min;
	}
	/*
	 * Algorithm 3: find the median
	 * By taking the derivative,  we can know that the differences want to be the minimum, we need to
	 * make (number of less items) = (number of larger items) which is just the term of median.
	 * so we can just sort and get the median nums[nums.length / 2] and sum the difference in the traversal.
	 * 
	 * Complexity Analysis:
	 * T: O(nlogn)
	 * S: O(1)
	 * */
	public static int minMoves2III(int[] nums) {
		Arrays.sort(nums);
		int sum = 0, median = nums[nums.length / 2];
		for (int num : nums) {
			sum += Math.abs(num - median);
		}
		return sum;
	}
	/*
	 * Algorithm 4: without find the median
	 * After we sorted the array, we can actually find the difference between larger item and smaller item 
	 * since larger - smaller = (larger - median) + (median - smaller).
	 * we can use two pointers from both side to go in opposite direction, each time we store the difference
	 * until left >=right.
	 * 
	 * Complexity Analysis: 
	 * T: O(nlogn)
	 * S: O(1)
	 * */
	public static int minMoves2IV(int[] nums) {
		int l = 0, r = nums.length - 1;
		int sum = 0;
		while (l < r) {
			sum+= nums[r--] - nums[l++];
		}
		return sum;
	}
	/*
	 * Algorithm 5: quick select
	 * we use quick select to select the median and use algorithm3 to find the answer.
	 * 
	 * Complexity Analysis:
	 * T: O(n) in average, O(n^2) in the worst case
	 * S: O(1)
	 * */
	public static int minMoves2V(int[] nums) {
		int median = select(nums, 0, nums.length - 1, nums.length / 2);
		int sum = 0;
		for (int num : nums) {
			sum += Math.abs(num - median);
		}
		return sum;
	}
	
	private static int select(int[] nums, int left, int right, int target) {
		// base casee
		if (left == right) {
			return nums[left];
		}
		int pivotIndex = partition(nums, left, right);
		if (pivotIndex == target) {
			return nums[pivotIndex];
		} else if (pivotIndex <  target) {
			return select(nums, pivotIndex + 1, right, target);
		} else {
			return select(nums, left, pivotIndex - 1, target);
		}
	}
	
	private static int partition(int[] nums, int left, int right) {
		int pivot = nums[right];
		int l = left, r = right - 1;
		while (l <= r) {
			if (nums[l] < pivot) {
				l++;
			} else if (nums[r] >= pivot) {
				r--;
			} else {
				swap(nums, l++, r--);
			}
		}
		swap(nums, l, right);
		return l;
	}
	
	private static void swap(int[] nums, int left, int right) {
		int tmp = nums[left];
		nums[left] = nums[right];
		nums[right] = tmp;
	}
	
	public static void main(String[] args) {
//		double k = 9.3;
//		double h = 9.9;
//		System.out.println(Math.round(k));
//		System.out.println(Math.round(h));
		int[] nums = {1,2,3, 1, 3,4,5};
		System.out.println("Min moves: " + minMoves2V(nums));
	}
}
