package bnsf;

public class FindPrimesWithinNAndM {
	/*
	 * Q: Given two integers N and M. Find the number of primes in the interval [N, M].
	 * 
	 * Algorithm:  (Sieve of Eratosthenes)
	 * part 1: traverse all numbers in the interval which costs M-N.
	 * part 2: Judge if a number is prime-->
	 * 		sift prime
	 * Given an integer M, from 2, we sift the multiples of prime until that is 
	 * first time greater than sqrt(M). 
	 * 
	 * T: O(MloglogM + M-N)
	 * S: O(M)
	 * */
	/*
	 * Assumption: M > 0, N > 0, M > N
	 * */
	public static int findPrime(int M, int N) {
		boolean[] isPrime = new boolean[M + 1];
		for(int i = 0; i <= M; ++i) {
			isPrime[i] = true;
		}
		for (int i = 2; i * i <= M; ++i) {
			if (isPrime[i]) {
				for (int j = i * i; j <= M; j += i) {
					isPrime[j] = false;
				}
			}
		}
		isPrime[1] = false;
		isPrime[0] = false;
		int count = 0;
		for (int i = N; i <= M; ++i) {
			if (isPrime[i]) count++;
		}
		return count;
	}
	
	public static void main(String[] args) {
		int M = 53, N = 53; // --> 1
		// M = 100, N = 50 --> 10
		System.out.println(findPrime(M, N));
	}
}
