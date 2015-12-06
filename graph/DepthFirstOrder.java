package graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DepthFirstOrder {
	private Stack<Integer> df;
	private int[] marked;
	private int[] edgeTo;
	public DepthFirstOrder(Graph g){
		int v = g.V();
		marked = new int[v];
		edgeTo = new int[v];
		df = new Stack<Integer>();
		for(int i = 0; i < v; i++){
			if(marked[i] == 0)
				edgeTo[i] = i;
				dfs(g,i);
		}
	}
	public void dfs(Graph g, int i){
		marked[i] = 1;
		df.push(i);
		for(int j:g.adj(i)){
			if(marked[j] == 0){
				edgeTo[j] = i;
				dfs(g,j);
			}
		}
	}
	public Stack<Integer> pathTo(int v){
		Stack<Integer> path = new Stack<Integer>();
		while(edgeTo[v] != v){
			path.push(v);
			v = edgeTo[v];
		}
		path.push(v);
		return path;
	}
	public Stack<Integer> getOrder(){
		return df;
	}
	public static void main(String[] args) throws FileNotFoundException{
		Scanner scan = new Scanner(new File(args[0]));
		Graph g = new Graph(scan);
		DepthFirstOrder dfs = new DepthFirstOrder(g);
		System.out.println(dfs.pathTo(3));
		for(int a: dfs.getOrder()){
			System.out.print(a + " ");
		}
		
 	}
}
