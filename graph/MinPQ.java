package graph;
import java.util.Random;

public class MinPQ<key extends Comparable<key>> {
	private int N;
	private key[] keys;
	
	public MinPQ(){
		N = 0;
		keys = (key[])new Comparable[4];
	}
	private void resize(int size){
		key[] temp = (key[])new Comparable[size];
		for(int i = 0; i < N; i++){
			temp[i] = keys[i];
		}
		keys = temp;
	}
	private boolean less(key[] a, int i, int j){
		return a[i-1].compareTo(a[j-1]) < 0;
	}
	private void swap(key[] a, int i, int j){
		key temp = a[i-1];
		a[i-1] = a[j-1];
		a[j-1] = temp;
	}
	private void sink(int k){
		while(2*k <= N){
			int j = 2*k;
			if(j+1 <= N && less(keys, j+1, j)) j++;
			if(less(keys, k, j)) break;
			swap(keys, k, j);
			k = j;
		}
	}
	private void sink(key[] keys,int k){
		int N = keys.length;
		while(2*k <= N){
			int j = 2*k;
			if(j+1 <= N && less(keys, j+1, j)) j++;
			if(less(keys, k, j)) break;
			swap(keys, k, j);
			k = j;
		}
	}
	private void sink(key[] keys,int k, int N){
		//int N = keys.length;
		while(2*k <= N){
			int j = 2*k;
			if(j+1 <= N && less(keys, j+1, j)) j++;
			if(less(keys, k, j)) break;
			swap(keys, k, j);
			k = j;
		}
	}
	private void swim(int k) {
		while(k/2 > 0) {
			if(less(keys, k, k/2)) {
				swap(keys, k, k/2);
				k = k/2;
			}
			else break;
		}
	}
	public void add(key a){
		if(N == keys.length) resize(2*N);
		keys[N++] = a;
		swim(N);
	}
	public key removeMin(){
		key temp = keys[0];
		swap(keys, 1, N);
		keys[N-1] = null;
		N--;
		sink(1);
		if(N <= keys.length/4) resize(keys.length/2);
		return temp;
	}
	private void heapify(key[] a, int N){
		for(int i = N; i > 0; i--){
			sink(a,i,N);
		}
	}
	private key[] sort(){
		key[] temp = (key[])new Comparable[N];
		System.arraycopy(keys, 0, temp, 0, N);
		heapify(temp, temp.length);
		int N = temp.length;
		while(N > 0){
			swap(temp,1,N--);
			heapify(temp, N);
		}
		return temp;
	}
	public int size(){
		return N;
	}
	public boolean isEmpty(){
		return N == 0;
	}
	public void print(){
		print(keys);
	}
	public void print(key[] a){
		for(key t : a){
			System.out.print(t + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args){
		MinPQ<Integer> pq = new MinPQ<Integer>();
		MinPQ<Integer> pq1 = new MinPQ<Integer>();
		/*pq.add(2);
		pq.add(32);
		pq.add(9);
		pq.add(20);
		pq.add(3);
		pq.add(1);*/
		Random ran = new Random();
		for(int i = 0; i < 20; i++){
			int a = ran.nextInt(200);
			pq.add(a);
			pq1.add(a);
		}
		while(!pq1.isEmpty())System.out.print(pq1.removeMin() + " ");
		System.out.println();
		//System.out.println(pq.size() + " = " + 20);
		System.out.println(pq.size());
		pq.print();
		Integer[] a = (Integer[])pq.sort();
		
	}
}
