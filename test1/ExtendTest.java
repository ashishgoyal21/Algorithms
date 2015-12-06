package test1;

public class ExtendTest extends Testing{
	static int c;
	ExtendTest(){
		c = 3;
		System.out.println(c);
	}

	static{
		c = 4;
		System.out.println(c);
	}
	public void hello(){
		System.out.println(this.a + this.b + c);
	}
	public ExtendTest returnMe(){
		return new ExtendTest();
	}
	public static void main(String[] args){
		ExtendTest a = new ExtendTest();
		a.hello();
	}
}
