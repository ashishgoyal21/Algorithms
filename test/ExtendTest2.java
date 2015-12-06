package test;
import java.util.ArrayList;

import test1.Testing;
public class ExtendTest2<Item> extends Testing{
	int a = geta();
	Item c;
	int b = 20;
	private 
	ExtendTest2(Item a){
		c = a;
	}
	int geta(){
		return b*4;
	}
	public void hello(){
		try{
			System.out.println(c.getClass().getName());
			return;
		}finally{
			System.out.println("Where am I?");
		}
 		//System.out.println(a);
	}
	public static void main(String[] args){
		ExtendTest2<Integer> a = new ExtendTest2<Integer>(2);
		a.hello();
	}
}
