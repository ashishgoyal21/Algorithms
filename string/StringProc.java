package string;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class StringProc {
	public void indexedSort(String[] a, int d, int R){
		int[] count = new int[R+1];
		int N = a.length;
		String[] aux = new String[N];
		for(int i = 0; i < N; i++){
			count[a[i].charAt(d) + 1]++;
		}
		for(int i = 0; i < R; i++){
			count[i+1] += count[i];
		}
		for(int i = 0; i < N; i++){
			aux[count[a[i].charAt(d)]++] = a[i];
		}
		for(int i = 0; i < N; i++){
			a[i] = aux[i];
		}
	}
	public void print(String[] a){
		for(int i = 0; i < a.length; i++){
			System.out.println(a[i]);
		}
	}
	public void lsd(String[] a, int W){
		for(int i = W-1; i >= 0; i--){
			indexedSort(a, i, 256);
		}
	}
	public void msd(String[] a){
		String[] aux = new String[a.length];
		msd(a, 0, a.length, 0, aux);
	}
	public void msd(String[] a, int lo, int hi, int d, String[] aux){
		if(lo < hi){
			int R = 256;
			int[] count = new int[R+2];
			for(int i = lo; i < hi; i++){
				count[charAt(a[i], d) + 2]++;
			};
			for(int i = 0; i < R+1; i++){
				count[i+1]+=count[i];
			}
			for(int i = lo; i < hi; i++){
				aux[lo + count[charAt(a[i], d) + 1]++] = a[i];
			}
			for(int i = lo; i < hi; i++){
				a[i] = aux[i];
			}
			for(int i = 0; i < R; i++){
				msd(a, lo + count[i], lo + count[i+1], d+1, aux);
			}
		}
	}
	public int charAt(String a, int i){
		if(i >= a.length()) return -1;
		else return a.charAt(i);
	}
	public static void main(String[] args) throws FileNotFoundException{
		//String[] a = {"a", "B"};
		StringProc b = new StringProc();
		//b.indexedSort(a, 0, 256);
		Scanner scan = new Scanner(new File(args[0]));
		ArrayList<String> c = new ArrayList<String>();
		while(scan.hasNextLine()){
			c.add(scan.nextLine());
		}
		String[] a = new String[c.size()];
		int i = 0;
		for(String d: c){
			a[i++] = d;
		}
		
		int W = a[0].length();
		//System.out.println("a" + 1);
		b.msd(a);
		b.print(a);
	}
}
