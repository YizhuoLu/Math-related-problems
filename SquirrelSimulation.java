package math;

public class SquirrelSimulation {
	/*
	 * Q: There's a tree, a squirrel, and several nuts. Positions are represented by the cells in a 2D 
	 * grid. Your goal is to find the minimal distance for the squirrel to collect all the nuts and put 
	 * them under the tree one by one. The squirrel can only take at most one nut at one time and can 
	 * move in four directions - up, down, left and right, to the adjacent cell. The distance is 
	 * represented by the number of moves.
	 * */
	
	/*
	 * Algorithm: 
	 * Intuitively, since other than the first nut that we choose, the distance to the all other left nuts and
	 * tree are fixed. (tree -> nut, nut -> tree). Therefore, the most important step is to choose the first
	 * nut which should have the minimum distance sum to distance of (nut-squirrel, nut-tree). So we can just
	 * iterate all the positions of nuts to get that one. And then we just add all the distance sum between 
	 * tree and other nuts will be enough.
	 * But we can find the max(distance(tree, nut) - distance(nut, squirrel)
	 * 
	 * Complexity: 
	 * T: O(n) n is the number of nuts' position.
	 * S: O(1)
	 * */
	
	public int minDistance(int height, int width, int[] tree, int[]squirrel, int[][] nuts) {
		int res = 0, max = Integer.MIN_VALUE;
		for (int[] nut : nuts) {
			res += distance(nut, tree) * 2;
			max = Math.max(max, distance(nut, tree) - distance(nut, squirrel));
		}
		return res - max;
	}
	
	private int distance(int[] one, int[] two) {
		return Math.abs(one[0] - two[0]) + Math.abs(one[1] - two[1]);
	}
}
