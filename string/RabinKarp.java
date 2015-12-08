package string;

import java.math.BigInteger;
import java.util.Random;

public class RabinKarp {
	private long q;
	private long mod;
	private int R;
	private long toMatch;
	private String pat;
	public RabinKarp(String pat){
		R = 256;
		this.pat = new String(pat);
		q = getLongestPrime();
		mod = 1;
        for (int i = 1; i <= pat.length()-1; i++)
            mod = (R * mod) % q;
        //patHash = hash(pat, M);
		toMatch = 0;
		for(int i = 0; i < pat.length(); i++){
			toMatch = (toMatch*R + pat.charAt(i)) % q;
		}
	}
	public int search(String text){
		if(pat.length() > text.length()) return -1;
		long match = 0;
		int i, M = pat.length();
		for(i = 0; i < pat.length(); i++){
			match = (match*R + text.charAt(i)) % q;
		}
		System.out.println("Q: " + q);
		System.out.println("mod: " + mod);
		System.out.println("toMatch: " + toMatch);
		System.out.println("match 0: " + match);
		if(match == toMatch) return 0;
		for(i = M; i < text.length(); i++){
			match = ((match - mod*text.charAt(i - M))*R + text.charAt(i)) % q;
			if(match < 0) match+=q;
			System.out.println("match " + (i-M+1) + ": " + match + " " + (match-q));
			if(toMatch == match) return i-M+1;
		}
		return text.length();
	}
	private long getLongestPrime(){
		BigInteger prime = BigInteger.probablePrime(31, new Random());
		return prime.longValue();
	}
	public static void main(String[] args){
		 String pat = args[0];
	        String txt = args[1];

	        RabinKarp searcher = new RabinKarp(pat);
	        int offset = searcher.search(txt);

	        // print results
	        System.out.println("text:    " + txt);

	        // from brute force search method 1
	        System.out.print("pattern: ");
	        for (int i = 0; i < offset; i++)
	        	System.out.print(" ");
	        System.out.println(pat);
	}
}
