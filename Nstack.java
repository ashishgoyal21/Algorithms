
public class Nstack<t> {
	private int[] top;
	private int[] next;
	private t[] stack;
	private int free;
	Nstack(int n,int k) {
		top = new int[k];
		next = new int[n];
		stack = (t[])new Object[n];
		free = 0;
		for(int i = 0; i < n; i++) next[i] = i+1;
		next[n-1] = -1;
		for(int i = 0; i < k; i++) top[i] = -1;
	}
	boolean isFull(){
		return free == -1;
	}
	boolean isEmpty(int sn) {
		return top[sn] == -1;
	}
	public void push(t item, int k){
		if(isFull()){
			System.out.println("stack full");
			return;
		}
		int i = free;
		free = next[i];
		next[i] = top[k];
		top[k] = i;
		stack[i] = item;
	}
	public t pop(int k){
		if(isEmpty(k)){
			System.out.println("Stack empty");
			return null;
		}
		int i = top[k];
		top[k] = next[i];
		next[i] = free;
		free = i;
		t item = stack[i];
		stack[i] = null;
		return item;
	}
	public void print(){
		for(int i = 0; i < stack.length; i++){
			System.out.println(stack[i]);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Nstack<String> stack = new Nstack<String>(10, 5);
		stack.push("Hello1", 0);
		stack.push("Hello2", 1);
		stack.push("Hello3", 1);
		stack.push("Hello4", 2);
		stack.push("Hello5", 4);
		stack.push("Hello6", 2);
		stack.push("Hello7", 3);
		stack.push("Hello8", 3);
		stack.push("Hello9", 0);
		stack.push("Hello10", 0);
		stack.pop(2);
		stack.pop(3);
		stack.pop(0);
		stack.push("Hello11", 2);
		stack.push("Hello12", 2);
		stack.push("Hello11", 2);
		stack.print();
	}

}
