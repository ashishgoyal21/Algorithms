package test1;

public class Test {
	public void segregate(int[] a){
		int l = 0, r = a.length-1;
		while(l <= r) {
			while(l < a.length && a[l]%2 == 0) l++;
			while(r >= 0 && a[r] % 2!=0) r--;
			if (l <= r){
				int temp = a[l];
				a[l] = a[r];
				a[r] = temp;
			}
		}
	}
	public int findLast(int[] a, int s, int e, int k){
		if (e >= s){
			int mid = s + (e-s)/2;
			if((mid == a.length-1 || k < a[mid+1]) && k == a[mid]) return mid;
			else if (a[mid] > k ) return findLast(a, s, mid -1, k);
			else return findLast(a, mid+1, e, k);
		} else return -1;
	}
	public int findFirst(int[] a, int s, int e, int k){
		if (e >= s){
			int mid = s + (e-s)/2;
			if((mid == 0 || k > a[mid-1]) && k == a[mid]) return mid;
			else if (a[mid] < k ) return findFirst(a, mid+1, e, k);
			else return findFirst(a, s, mid-1, k);
		} else return -1;
	}
	public void subsort(int[] a){
		int l = 0, r = a.length -1;
		while(l < a.length - 1 && a[l] <= a[l+1]) l++;
		while(r > 0 && a[r-1] <= a[r]) r--;
		if (l >= r) System.out.println(-1);
		else System.out.println("l:" + l + " h:" + r);
	}
	public void print(int[] a){
		for(int i=0; i < a.length; i++) System.out.println(a[i]);
	}
	public static void main(String[] args){
		Test a = new Test();
		int[] b = new int[]{1,2,2,2,3,3,3,3,4,4,5};
		System.out.println(a.findLast(b, 0, b.length -1, 3));
		//a.print(b);
	}
}
