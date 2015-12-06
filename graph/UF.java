package graph;

public class UF {
	private int[] a;
	private int[] s;
	public UF(int v){
		a = new int[v];
		s = new int[v];
		for(int i = 0; i < v; i++){
			a[i] = i;
		}
		for(int i = 0; i < v; i++){
			s[i] = 1;
		}
	}
	public int parent(int v){
		while(a[v] != v){
			v = a[v];
		}
		return v;
	}
	public void union(int v, int w){
		int pv = parent(v);
		int pw = parent(w);
		if(pv != pw) {
			if(s[pv] <= s[pw]){
				a[pv] = a[pw];
				s[pw] += s[pv];
			}
			else{
				a[pw] = a[pv];
				s[pv] += s[pw];
			}
		}
	}
	public boolean isConnected(int v, int w){
		return parent(v) == parent(w);
	}
	public static void main(String[] args){
		UF uf = new UF(10);
	}
}
