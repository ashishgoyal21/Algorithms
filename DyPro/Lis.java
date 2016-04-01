package DyPro;

public class Lis {
	private int[] l;
	private int[] source;
	private int length;
	private int index;
	private int[] parent;
	public Lis(int[] a){
		l = new int[a.length];
		source = a;
		parent = new int[a.length];
		lis(a);
	}
	private void lis(int[] a){
		int max = 1;
		for(int i = 0; i < l.length; i++)
			l[i] = 1;
		for(int i = 1; i < l.length; i++){
			for(int j = 0; j < i; j++){
				if(l[i] < (l[j] + 1) && a[j] < a[i]){
					l[i] = 1 + l[j];
					parent[i] = j;
				}
			}
		}
		max = l[0];
		for(int i = 1; i < l.length; i++){
			if(max < l[i]){
				length = l[i];
				index = i;
			}
		}
	}
	public int getLength(){
		return length;
	}
	public int[] getSeq(){
		int[] result = new int[length];
		result[length - 1] = source[index];
		int i = index;
		length -= 2;
		while(length >= 0){
			result[length--] = source[parent[i]];
			i = parent[i];
		}
		return result;
	}
	public static void main(String[] args){
		int[] a = {10, 22, 9, 33, 21, 50, 41, 60, 80};
		Lis b = new Lis(a);
		System.out.println(b.getLength());
		int[] c = b.getSeq();
		for(int i = 0; i < c.length; i++)
			System.out.print(c[i] + " ");
	}
}
