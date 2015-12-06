package graph;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<Item> implements Iterable<Item>{
	private Item[] a;
	private int f;
	private int l;
	private int n;
	public Queue(){
		a = (Item[])new Object[2];
		f = 0;
		l = 0;
		n = 0;
	}
	private void resize(int s){
		Item[] temp = (Item[])new Object[s];
		int i = 0, j = f;
		while(i < n){
			temp[i++] = a[j++];
			if(j == a.length) j = 0;
		}
		f = 0;
		l = n;
		a = temp;
	}
	public boolean isEmpty(){
		return n==0;
	}
	public void enqueue(Item i){
		if(n == a.length)resize(2*n);
		a[l++] = i;
		if(l == a.length) l = 0;
		n++;
	}
	public Item dequeue(){
		if(isEmpty())throw new NoSuchElementException();
		Item i = a[f];
		a[f++] = null;
		if(f == a.length) f = 0;
		n--;
		return i;
	}
	public int size(){
		return n;
	}
	public Item peek(){
		if(isEmpty())return null;
		return a[f];
	}
	public Iterator<Item> iterator() {
		return new ArrayIterator();
	}
	private class ArrayIterator implements Iterator<Item>{
		private int i, n;
		ArrayIterator(){
			i = f;
			n = 0;
		}
		public boolean hasNext(){
			return n < size(); 
		}
		public Item next(){
			if(!hasNext())throw new NoSuchElementException();
			Item b = a[i];
			i++;
			if(i == a.length)i = 0;
			n++;
			return b;
		}
	}
	public String toString(){
		StringBuilder s = new StringBuilder();
		for(Item i: this){
			s.append(i + " ");
		}
		return s.toString();
	}
	public static void main(String[] args){
		Queue<Integer> q = new Queue<Integer>();
		q.enqueue(1);
		q.enqueue(2);
		q.enqueue(3);
		q.enqueue(4);
		q.dequeue();
		q.dequeue();
		q.enqueue(5);
		q.enqueue(6);
		q.enqueue(7);
		q.enqueue(8);
		q.dequeue();
		q.dequeue();
		q.dequeue();
		q.dequeue();
		q.dequeue();
		System.out.println(q);
	}
}
