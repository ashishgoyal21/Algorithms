package string;

public class DFASearch {
	private int[][] dfa;
	final private int R = 256;
	private int M;
	public DFASearch(String pat){
		M = pat.length();
		int x = 0;
		dfa = new int[R][M];
		dfa[pat.charAt(0)][0] = 1;
		for(int j = 1; j < M; j++){
			for(int c = 0; c < R; c++){
				//if(pat.charAt(j) == ".".charAt(0))dfa[c][j] = j + 1;
				dfa[c][j] = dfa[c][x];
			}
			dfa[pat.charAt(j)][j] = j + 1;
			x = dfa[pat.charAt(j)][x];
		}
	}
	public int search(String text){
		int state = 0;
		for(int j = 0; j < text.length(); j++){
			state = dfa[text.charAt(j)][state];
			if(state == M){
				return j - M + 1;
			}
		}
		return text.length();
	}
	public static void main(String[] args){
		 String pat = args[0];
	        String txt = args[1];

	        DFASearch searcher = new DFASearch(pat);
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
