package graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BreadthFirstOrder {
	private boolean[] marked;
	private int[] edgeTo;
	public BreadthFirstOrder(Graph g, int s){
		int v = g.V();
		marked = new boolean[v];
		edgeTo = new int[v];
		bfs(g,s);
	}
	public void bfs(Graph g, int s){
		Queue<Integer> q = new Queue<Integer>();
		q.enqueue(s);
		edgeTo[s] = s;
		marked[s] = true;
		while(!q.isEmpty()){
			int v = q.dequeue();
			for(int w:g.adj(v)){
				if(!marked[w]){
					marked[w] = true;
					edgeTo[w] = v;
					q.enqueue(w);
				}
			}
		}
	}
	public Stack<Integer> pathTo(int v){
		Stack<Integer> s = new Stack<Integer>();
		while(edgeTo[v] != v){
			s.push(v);
			v = edgeTo[v];
		}
		s.push(v);
		return s;
	}
	public static void main(String[] args) throws FileNotFoundException{
		Scanner scan = new Scanner(new File(args[0]));
		Graph g = new Graph(scan);
		BreadthFirstOrder bfs = new BreadthFirstOrder(g, 0);
		System.out.println(bfs.pathTo(2));
		
 	}
}
