package string;
import graph.Queue;
public class Trie {
	private static class TrieNode{
		public String v;
		public TrieNode[] keys;
		public TrieNode(){
			keys = new TrieNode[256];
			v = null;
		}
	}
	private TrieNode root;
	//private int size;
	public Trie(){
		root = new TrieNode();
	}
	public void insert(String s){
		root = insert(root, s, 0);
	}
	public Queue<String> collect(){
		Queue<String> q = new Queue<String>();
		collect(root, "", q);
		return q;
	}
	public void collect(TrieNode x, String pat, Queue<String> q){
		if(x == null)return;
		if(x.v != null)q.enqueue(x.v);
		for(char c = 0; c < 256; c++){
			collect(x.keys[c], pat+c, q);
		}
	}
	public Queue<String> keysWithPrefix(String pat){
		Queue<String> c = new Queue<String>();
		collect(get(root, pat, 0), pat, c);
		return c;
	}
	public TrieNode insert(TrieNode x, String s, int d){
		if(x == null) x = new TrieNode();
		if(d == s.length()){
			x.v = s;
		}else{
			char c = s.charAt(d);
			x.keys[c] = insert(x.keys[c], s, d+1);
		}
		return x;
	}
	public String get(String key){
		TrieNode x = get(root, key, 0);
		if(x == null) return null;
		return x.v;
	}
	public TrieNode get(TrieNode x, String key, int d){
		if(x == null) return null;
		if(d == key.length()) return x;
		return get(x.keys[key.charAt(d)], key, d+1);
	}
	public boolean contains(String key){
		return get(key) != null;
	}
	public String longestPrefix(String pat){
		TrieNode x = get(root, pat, 0);
		if(x == null) return null;
		return longestPrefix(x, pat, "");
	}
	public String longestPrefix(TrieNode x, String pat, String match){
		if(x == null)return match;
		if(x.v != null && x.v.length() > match.length()) match = x.v;
		for(char c = 0; c < 256; c++){
			match = longestPrefix(x.keys[c], pat+c, match);
		}
		return match;
	}
	public static void main(String[] args){
		Trie tree = new Trie();
		tree.insert("hello");
		tree.insert("hell");
		tree.insert("abcdefgh");
		tree.insert("abdefgh");
		System.out.println(tree.longestPrefix("ab"));
		for(String c: tree.keysWithPrefix("hell"))
			System.out.println(c);
	}
}
