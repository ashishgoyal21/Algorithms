
 package string;

import java.util.ArrayList;

public class TernaryTrie<t> {
	private class Node<t>{
		public t v;
		public char c;
		public Node<t> r,m,l;
		public Node(char c){
			this.c = c;
		}
	}
	private Node<t> root;
	private int n;
	public int size(){
		return n;
	}
	public void insert(String s, t value){
		if(value == null) return;
		root = insert(root, s, value, 0);
	}
	public Node<t> insert(Node<t> root, String s, t v, int d){
		if(root == null) root = new Node<t>(s.charAt(d));
		if(d == s.length() - 1){
			if(root.v == null) n++;
			root.v = v;
			return root;
		}else {
			char e = s.charAt(d);
			if(e < root.c){
				root.l = insert(root.l, s, v, d);
			} else if (e > root.c) {
				root.r = insert(root.r, s, v, d);
			} else {
				root.m = insert(root.m, s, v, d+1);
			}
			return root;
		}
	}
	public t get(String s){
		Node<t> v = get(root, s, 0);
		if(v != null) return v.v;
		else return null;
	}
	public Node<t> get(Node<t> root, String s, int d){
		if(root == null) return null;
		char c = root.c;
		if(s.charAt(d) < c) return get(root.l, s, d);
		else if(s.charAt(d) > c) return get(root.r, s, d);
		else {
			if(d == s.length() - 1) return root;
			else return get(root.m, s, d+1);
		}
	}
	public Iterable<String> keys(){
		ArrayList<String> keys = new ArrayList<String>();
		//String s = root == null ? "" : "" + root.c;
		collect(root, "", keys);
		return keys;
	}
	private void collect(Node<t> root, String s, ArrayList<String> keys){
		if(root == null) return;
		if(root.v != null) keys.add(s + root.c);
		collect(root.l, s, keys);
		collect(root.r, s, keys);
		collect(root.m, s + root.c, keys);
	}
	public Iterable<String> keysWithPrefix(String s){
		ArrayList<String> list = new ArrayList<String>();
		collect(get(root, s, 0).m, s, list);
		return list;
	}
	public ArrayList<String> keyThatMatch(String s){
		ArrayList<String> list = new ArrayList<String>();
		search(root, s, "", 0, list);
		return list;
	}
	public void search(Node<t> root, String pat, String pre, int d, ArrayList<String> list){
		if(root == null) return;
		char c = pat.charAt(d);
		if ( c == ".".charAt(0) || c == root.c){
			if(d == pat.length() - 1 && root.v != null) list.add(pre + root.c);
			if (d < pat.length() - 1)search(root.m, pat, pre + root.c, d+1, list);
		} if (c == ".".charAt(0) || c < root.c){
			search(root.l, pat, pre,d,list);
		} if(c == ".".charAt(0) || c > root.c) {
			search(root.r,pat,pre,d,list);
		}
	}
	public static void main(String[] args){
		TernaryTrie<String> tree = new TernaryTrie<String>();
		tree.insert("hello", "world");
		tree.insert("h", "error");
		tree.insert("aloho", "mora");
		tree.insert("aeoho", "mora1");
		tree.insert("blah", "notme");
		tree.insert("baah", "notme2");
		tree.insert("blahblah", "me");
		tree.insert("expecto", "patronum");
		System.out.println(tree.get("a"));
		/*System.out.println(tree.get("aloho"));
		System.out.println(tree.get("hell"));*/
		for(String s: tree.keyThatMatch(".e..o"))
			System.out.println(s);
	}
}
