package string;

public class KMPSearch {
	private int[] presuf;
	private String pat;
	private int M;
	public KMPSearch(String pat){
		this.pat = pat;
		M = pat.length();
		presuf = new int[M + 1];
		computeStateArray();
	}
	private void computeStateArray(){
		int k = 0;
		presuf[0] = 0;
		for(int i = 1; i < M; i++){
			while(k > 0 && this.pat.charAt(k) != this.pat.charAt(i)){
				k = presuf[k-1];
			}
			if(this.pat.charAt(k) == this.pat.charAt(i)){
				k = k + 1;
			}
			presuf[i] = k;
		}
	}
	public int search(String text){
		int N = text.length();
		int k = 0;
		for(int i = 0; i < N; i++){
			while(k > 0 && this.pat.charAt(k) != text.charAt(i))
				k = presuf[k-1];
			if(this.pat.charAt(k) == text.charAt(i)){
				k++;
				if(k == M){
					return i-M + 1;
				}
			}
		}
		return N;
	}
	public static void main(String[] args){
		String pat = args[0];
        String txt = args[1];

        KMPSearch searcher = new KMPSearch(pat);
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
