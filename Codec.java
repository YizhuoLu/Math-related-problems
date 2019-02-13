package math;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*
 * Q: Encode and decode tinyURL
 *  TinyURL is a URL shortening service where you enter a URL such as 
 *  https://google.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk.
 *  
 *  Design the encode and decode methods for the TinyURL service. There is no restriction on how your encode/decode 
 *  algorithm should work. You just need to ensure that a URL can be encoded to a tiny URL and the 
 *  tiny URL can be decoded to the original URL.
 * */

public class Codec {
	/*
	 * Algorithm 1: Use a counter(i) to encode each new longURL, and store in a HashMap which will make it
	 * 	easy to return decoded string.
	 * 
	 * Performance Analysis:
	 * 1. The range of codes depend on the range of integer which is limited, so this algorithm's performance
	 * will degraded if the number of given longURL is large.
	 * 2. The length is not necessarily shorter than the given longURL, it depends on the order of it.
	 * */
	// Encodes a URL to a shortened URL.
	Map<Integer, String> map1 = new HashMap<>();
	int i = 0;
	public String encode1(String longUrl) {
		map1.put(i, longUrl);
		return "http://tinyurl.com/" + i++;
	}

	// Decodes a shortened URL to its original URL.
	public String decode1(String shortUrl) {
		return map1.get(Integer.parseInt(shortUrl.replace("http://tinyurl.com/", "")));
	}
	
	/*
	 * Algorithm 2: use variable length of code (including both number and alphabets) code (0-61) to encode 
	 * 	the given longURL. Also use a HashMapto store the mapping.
	 * 
	 * Performance Analysis:
	 * 1. the range of codes still depends on the range of integer, it will be used out when integer overflow.
	 * 2. The length is still not necessarily shorter, it still depends on the order that longURL appears. It
	 * will have such order: 1(62 times), 2 (62 times) so on so forth.
	 * */
	String chars  = "0123456789abcdeefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	Map<String, String> map2 = new HashMap<>();
	int count = 0;
	
	private String getString(String longUrl) {
		int c = count;
		StringBuilder sb = new StringBuilder();
		while (c  > 0) {
			c--;
			sb.append(chars.charAt(c % 62));
			c /= 62;
		}
		return sb.toString();
	}
	
	public String encode2(String longUrl) {
		String key = getString(longUrl);
		map2.put(key, longUrl);
		count++;
		return "http://tinyurl.com/" + key;
	}
	
	public String decode2(String shortUrl) {
		return map2.get(shortUrl.replace("http://tinyurl.com/", ""));
	}
	
	/*
	 * Algorithm 3:  Use hashCode
	 * s[0]∗31^(n−1)+s[1]∗31^(n−2)+...+s[n−1] , where s[i] is the ith character of the string, 
	 * n is the length of the string.
	 * 
	 * Performance Analysis: 
	 * 1. still have range limitation that depends on the range of int since hashCode use integer calculation.
	 * 2. may have hash collision
	 * 3. hard to predict.
	 * */
	// here we use the precious map2 not making a new map
	public String encode3(String longUrl) {
		int key = longUrl.hashCode();
		map1.put(key, longUrl);
		return "http://tinyurl.com/" + key;
	}
	
	public String decode3(String shortUrl) {
		return map1.get(Integer.parseInt(shortUrl.replace("http://tinyurl.com/", "")));
	}
	
	/*
	 * Algorithm 4: Use random number 
	 * To avoid duplication, we choose a new random number each time for a new longURL.
	 * 
	 * Performance Analysis:
	 * 1. range limitation depending on range of integer.
	 * 2. increasing probability for collision when # of longURL is increasing.
	 * 3. hard to predict
	 * 4. length of code not necessarily shorter than the longURL, it depends on the relative order of longURL.
	 * */
	Random r = new Random();
	int keykey = r.nextInt(Integer.MAX_VALUE);
	
	public String encode4(String longUrl) {
		while (map1.containsKey(keykey)) {
			keykey = r.nextInt(Integer.MAX_VALUE);
		}
		map1.put(keykey, longUrl);
		return "http://tinyurl.com/" + keykey;
	}
	
	public String decode4(String shortUrl) {
		return map1.get(Integer.parseInt(shortUrl.replace("http://tinyurl.com/", "")));
	}
	
	/*
	 * Algorithm 5: random fixed length (6 bits code here) which integrates the algorithm 2 + 4.
	 * 
	 * Performance Analysis:
	 * 1. range is larger which is (10 + 26 * 2)^6
	 * 2. low possibility for duplication.
	 * 3. hard to predict
	 * 4. length is fixed which is good improvement for large number of URLs.
	 * */
	String kkey = getRandChars();
	private String getRandChars() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <  6; ++i) {
			sb.append(chars.charAt(r.nextInt(62)));
		}
		return sb.toString();
	}
	
	public String encode5(String longUrl) {
		while (map2.containsKey(kkey)) {
			kkey = getRandChars();
		}
		map2.put(kkey, longUrl);
		return "http://tinyurl.com/" + kkey;
	}
	
	public String decode5(String shortUrl) {
		return map2.get(shortUrl.replace("http://tinyurl.com/", ""));
	}
}
