import java.util.ArrayList;
import java.util.Random;
import graph.Stack;

public class BinaryTree {
	private Node root;
	
	private static class Node{
		public int val;
		public Node left;
		public Node right;
		
		Node(int val){
			this.val = val;
			left = null;
			right = null;
		}
	}
	BinaryTree(){
		root = null;
	}
	public void insert(int data){
		root = insert(root, data);
	}
	private Node insert(Node node, int data){
		if (node == null) return new Node(data);
		if(node.val == data){
			node.val = data;
		}
		else if(data < node.val) node.left = insert(node.left, data);
		else node.right = insert(node.right, data);
		return node;
	}
	public ArrayList<Integer> range(int l, int h){
		ArrayList<Integer> range = new ArrayList<Integer>();
		range(root, range, l, h);
		return range;
	}
	private void range(Node root, ArrayList<Integer> range, int l, int h){
		if (root == null) return;
		if(root.val >= l && root.val <= h){
			range(root.left, range, l, h);
			range.add(root.val);
			range(root.right, range, l, h);
		}
		if(root.val > h) {
			range(root.left, range, l, h);
		}
		if(root.val < l){
			range(root.right, range, l, h);
		}
	}
	public boolean lookup(int data){
		return lookup(root, data);
	}
	private boolean lookup(Node node, int data){
		if(node == null) return false;
		if(node.val == data) return true;
		else if(node.val > data) return (lookup(node.left, data));
		else return lookup(node.right, data);
	}
	public int size(){
		return size(root);
	}
	private int size(Node node){
		if(node == null) return 0;
		return 1 + size(node.left) + size(node.right);
	}
	public int maxdepth(){
		return maxdepth(root);
	}
	private int maxdepth(Node node){
		if (node == null) return 0;
		int ldepth = maxdepth(node.left);
		int rdepth = maxdepth(node.right);
		if (ldepth >= rdepth)
			return 1 + ldepth;
		else return 1 + rdepth;
	}
	public int min(){
		return min(root);
	}
	private int min(Node node){
		if (node == null) return -1;
		Node current = node;
		while(current.left != null){
			current = current.left;
		}
		return current.val;
	}
	public boolean hasPathSum(int sum){
		return hasPathSum(root, sum);
	}
	private boolean hasPathSum(Node node, int sum){
		if(node == null) return sum == 0;
		int n = sum - node.val;
		return hasPathSum(node.left, n) || hasPathSum(node.right, n);
	}
	public void mirror(){
		mirror(root);
	}
	private void mirror(Node node){
		if(node == null) return;
		mirror(node.left);
		mirror(node.right);
		Node temp = node.left;
		node.left = node.right;
		node.right = temp;
	}
	public void doubleTree(){
		doubleTree(root);
	}
	private void doubleTree(Node node){
		if(node == null) return;
		doubleTree(node.left);
		doubleTree(node.right);
		Node nnode = new Node(node.val);
		Node temp = node.left;
		node.left = nnode;
		nnode.left = temp;
	}
	public boolean sameTree(BinaryTree other) {
		return sameTree(root, other.root);
	}
	private boolean sameTree(Node node, Node onode){
		if (node == null && onode == null) return true;
		else if (node != null && onode != null)
			return sameTree(node.left, onode.left) && (node.val == onode.val) && sameTree(node.right, onode.right);
		else return false;
	}
	public void toList(){
		root = toList(root);
		root = root.right;
	}
	private Node toList(Node node){
		if (node == null) return null;
		Node lnode = toList(node.left);
		lnode.right = node;
		node.left = lnode;
		Node rnode = toList(node.right);
		node.right = rnode;
		rnode.left = node;
		
		rnode.right = lnode.left;
		return rnode;
	}
	public void printList(){
		Node current = root;
		while (current.right != root){
			System.out.println(current.val + "--");
		}
		System.out.println(current.val);
	}
	public void print(){
		print(root);
	}
	public void preprint(){
		preprint(root);
	}
	private void preprint(Node root){
		if(root == null) return;
		System.out.print(root.val + " ");
		preprint(root.left);
		preprint(root.right);
	}
	public void postprint(){
		postprint(root);
	}
	private void postprint(Node root){
		if(root == null) return;
		postprint(root.left);
		postprint(root.right);
		System.out.print(root.val + " ");
	}
	public void ipreprint(){
		ipreprint(root);
	}
	public void ipreprint(Node root){
		Stack<Node> s = new Stack<Node>();
		s.push(root);
		while(true){
			if(s.isEmpty())break;
			Node current = s.pop();
			Node left = current.left;
			System.out.print(current.val + " ");
			if(current.right != null) s.push(current.right);
			if(left != null)s.push(left);
		}
	}
	public void iInOrder(){
		iInOrder(root);
	}
	public void iInOrder(Node root){
		if(root == null) return;
		Stack<Node> s = new Stack<Node>();
		//s.push(root);
		while(true){
			while(root != null){
				s.push(root);
				root = root.left;
			}
			if(s.isEmpty())break;
			root = s.pop();
			System.out.print(root.val + " ");
			if(root.right != null) root = root.right;
			else{
				root = null;
			}
		}
	}
	public void ipostprint(){
		ipostprint(root);
	}
	public void ipostprint(Node root){
		Stack<Node> s = new Stack<Node>();
		Node current = root;
		while(true){
			while(current != null){
				if(current.right != null)s.push(current.right);
				s.push(current);
				current = current.left;
			}
			if(s.isEmpty())break;
			current = s.pop();
			if(current.right != null && current.right == s.peek()){
				s.pop();
				s.push(current);
				current = current.right;
			} else{
				System.out.print(current.val + " ");
				current = null;
			}
		}
	}
	private void print(Node node){
		if(node == null) return;
		print(node.left);
		System.out.print(node.val + " ");
		print(node.right);
	}
	public static void main(String[] args){
		BinaryTree tree = new BinaryTree();
		int[] a = {10, 5, 15, 2,4,11,18};
		for(int i = 0; i < a.length; i++){
			//tree.insert((int)Math.floor(Math.random()*100));
			tree.insert(a[i]);
		}
		tree.print();
		System.out.println();
		tree.preprint();
		System.out.println();
		tree.ipreprint();
		System.out.println();
		tree.postprint();
		System.out.println();
		tree.iInOrder();
	}
}
