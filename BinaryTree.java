import java.util.ArrayList;

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
	private void print(Node node){
		if(node == null) return;
		print(node.left);
		System.out.println(node.val + " ");
		print(node.right);
	}
	public static void main(String[] args){
		BinaryTree tree = new BinaryTree();
		tree.insert(6);
		tree.insert(1);
		tree.insert(8);
		tree.insert(12);
		tree.insert(10);
		tree.insert(2);
		tree.insert(3);
		tree.insert(11);
		//tree.print();
		System.out.println(tree.range(6,10));
		//tree.doubleTree();
		//tree.print();
		//tree.toList();
		//tree.printList();
		
		/*BinaryTree ntree = new BinaryTree();
		ntree.insert(6);
		ntree.insert(1);
		ntree.insert(8);
		ntree.insert(12);
		ntree.insert(10);
		ntree.insert(2);
		ntree.insert(3);
		ntree.insert(11);
		System.out.println(tree.sameTree(ntree));*/
	}
}
