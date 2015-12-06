package graph;

import java.util.Iterator;
import java.util.NoSuchElementException;

//import graph.Graph.Bag.ListIterator;
//import graph.Graph.Bag.Node;

public class Bag<Item> implements Iterable<Item>{
	private Node<Item> root;
	private int N;
	private class Node<Item>{
		private Item v;
		private Node<Item> next;
		Node(Item c){
			v = c;
			next = null;
		}
	}
	public Bag(){
		root = null;
		N = 0;
	}
	public boolean isEmpty(){
		return N == 0;
	}
	public void add(Item v){
		Node<Item> oldf = root;
		root = new Node<Item>(v);
		root.next = oldf;
		N++;
	}
	public int size(){
		return N;
	}
	@Override
	public Iterator<Item> iterator() {
		return new ListIterator<Item>(root);
	}
	private class ListIterator<Item> implements Iterator<Item>{
		private Node<Item> current;
		ListIterator(Node<Item> root){
			current = root;
		}
		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Item next() {
			if(!hasNext())throw new NoSuchElementException();
			Item i = current.v;
			current = current.next;
			return i;
		}
	}
}
