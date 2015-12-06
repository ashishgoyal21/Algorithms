package test1;

import java.util.Random;

public class Matrix {
	int[][] a;
	public Matrix(int[][] b){
		int r = b.length;
		int c = b[0].length;
		a = new int[r][c];
		for(int i = 0; i < r; i++)
			for(int j = 0; j < c; j++)
				a[i][j] = b[i][j];
	}
	public Matrix(int N){
		this(N,N);
	}
	public Matrix(int r, int c){
		a = new int[r][c];
		Random rand = new Random();
		for(int i = 0; i < r; i++)
			for(int j = 0; j < c; j++)
				a[i][j] = rand.nextInt(200);
	}
	public void print(){
		for(int i = 0; i < a.length; i++){
			for(int j = 0; j < a[i].length; j++){
				System.out.print(a[i][j] + " ");
			}
			System.out.println();
		}
	}
	public void spiral(){
		spiral(0,0,a.length, a[0].length);
	}
	public void spiral(int i, int j, int N){
		int k = 0, l = 0;
		if(N < 1)return;
		for(l = j,k = i; l < j + N; l++)System.out.print(a[k][l] + " ");
		for(l = l-1, k++; k < i + N; k++)System.out.print(a[k][l] + " ");
		for(k = k -1, l--; l >= j; l--)System.out.print(a[k][l] + " ");
		for(l = l+1, k--; k>i; k--)System.out.print(a[k][l] + " ");
		spiral(i+1, j+1, N-2);
	}
	public void spiral(int i, int j, int r, int c){
		if(r < 1 || c < 1)return;
		int k = i, l = j;
		for(; l < i + c; l++)System.out.print(a[k][l] + " ");
		for(l--, k++; k < j + r; k++)System.out.print(a[k][l] + " ");
		if(i+1 < i+r)for(k--, l--; l >= j; l--)System.out.print(a[k][l] + " ");
		if(j+1 < j+c)for(k--, l++; k > i; k--)System.out.print(a[k][l] + " ");
		spiral(i+1, j+1, r-2, c-2);
	}
	public static void main(String[] args){
		Matrix a = new Matrix(4,1);
		a.print();
		System.out.println();
		//(new Matrix(2,2)).print();
		a.spiral();
	}
}
