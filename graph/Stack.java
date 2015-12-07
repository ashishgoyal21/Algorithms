package graph;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<Item> implements Iterable<Item>{
	private Item[] arr;
	private int N;
	public Stack(){
		N = 0;
		arr = (Item[])new Object[2];
	}
	private void resize(int n){
		Item[] temp = (Item[])new Object[n];
		for(int i = 0; i < N;i++){
			temp[i] = arr[i];
		}
		arr = temp;
	}
	public void push(Item i){
		if(N == arr.length)resize(2*N);
		arr[N++] = i;
	}
	public boolean isEmpty(){
		return N == 0;
	}
	public String toString(){
		StringBuilder a = new StringBuilder();
		for(Item i : this){
			a.append(i + " ");
		}
		return a.toString();
	}
	public Item pop(){
		if(isEmpty())throw new NoSuchElementException();
		Item i = arr[--N];
		arr[N] = null;
		if(N == arr.length/4)resize(arr.length/2);
		return i;
	}
	public int size(){
		return N;
	}
	public Item peek(){
		if(this.isEmpty()) return null;
		return arr[N-1];
	}
	public Item[] data(){
		return arr;
	}
	public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ReverseArrayIterator implements Iterator<Item> {
        private int i;

        public ReverseArrayIterator() {
            i = N-1;
        }

        public boolean hasNext() {
            return i >= 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return arr[i--];
        }
    }

}
