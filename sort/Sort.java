package sort;

public class Sort {
	public static <t extends Comparable<t>> void mergesort(t[] a){
		t[] aux = (t[])new Comparable[a.length];
		mergesort(a, aux, 0, a.length - 1);
	}
	public static <t extends Comparable<t>> void mergesort(t[] a, t[] aux, int l, int h){
		if(l < h){
			int mid = l + (h - l)/2;
			mergesort(a, aux, l, mid);
			mergesort(a, aux, mid+1, h);
			merge(a, aux, l, mid, h);
		}
	}
	public static <t extends Comparable<t>> void merge(t[] a, t[] aux, int l, int m, int h){
		for(int i = l; i <= h; i++)
			aux[i] = a[i];
		int k = l, j = m+1;
		for(int i = l; i <= h; i++){
			if(k > m) a[i] = aux[j++];
			else if(j > h)a[i] = aux[k++];
			else if(((t)aux[k]).compareTo((t)aux[j]) < 0) a[i] = aux[k++];
			else a[i] = aux[j++];
		}
	}
	public static <t extends Comparable<t>> void quicksort(t[] a){
		quicksort(a, 0, a.length -1);
	}
	public static <t extends Comparable<t>> void quicksort(t[] a, int l, int h){
		if(l >= h) return;
		t p = (t)a[l];
		int lt = l, i = l, gt = h;
		while(i <= gt){
			int c = ((t)a[i]).compareTo(p);
			if(c < 0)swap(a, i++, lt++);
			else if(c > 0)swap(a,i,gt--);
			else i++;
		}
		quicksort(a, l, lt-1);
		quicksort(a, gt+1, h);
	}
	public static <t extends Comparable<t>> void swap(t[] a, int i, int j){
		t temp = (t)a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	public static void main(String[] args){
		Integer[] a = {4,4,4,4,4,4,4,4,4,4};
		Sort.quicksort(a);
		for(int i: a){
			System.out.println(i);
		}
	}
}
