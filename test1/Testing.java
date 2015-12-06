package test1;

public class Testing {
	int a;
	int b;
	public Testing(){
		a = 1;
		b = 2;
	}
	public void hello(){
		System.out.println(this.a + this.b);
	}
	public Testing returnMe(){
		return new Testing(1,2);
	}
}

