package DyPro;

public class Lcs {
	public int lcs(String s1, String s2){
		return lcs(s1, s2, s1.length() - 1, s2.length() - 1);
	}
	private int lcs(String s1, String s2, int m, int n){
		if(m < 0 || n < 0) return 0;
		if(s1.charAt(m) == s2.charAt(n)) return 1 + lcs(s1, s2, m-1, n-1);
		else {
			int l = lcs(s1, s2, m, n-1);
			int r = lcs(s1, s2, m-1, n);
			return (l > r) ? l : r;
		}
	}
	public int dlcs(String s1, String s2){
		int[][] r = new int[s1.length() + 1][s2.length() + 1];
		for(int i = 1; i <= s1.length(); i++){
			for(int j = 1; j <= s2.length(); j++){
				if(s1.charAt(i-1) == s2.charAt(j-1))
					r[i][j] = 1 + r[i-1][j-1];
				else{
					if(r[i-1][j] > r[i][j-1]) r[i][j] = r[i-1][j];
					else r[i][j] = r[i][j-1];
				}
			}
		}
		return r[s1.length()][s2.length()];
	}
	public static void main(String[] args){
		Lcs a = new Lcs();
		System.out.println(a.lcs("AGGTAB", "GXTXAYB"));
		System.out.println(a.dlcs("AGGTAB", "GXTXAYB"));
	}
}
