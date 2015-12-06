package graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Topological {
	private boolean[] marked;
	private Stack<Integer> order;
	public Topological(Digraph g){
		int v = g.V();
		marked = new boolean[v];
		order = new Stack<Integer>();
		for(int i = 0; i < v; i++){
			if(!marked[i]){
				dfs(g,i);
			}
		}
	}
	public Topological(EdgeWieghtedDigraph g){
		int v = g.V();
		marked = new boolean[v];
		order = new Stack<Integer>();
		for(int i = 0; i < v; i++){
			if(!marked[i])
				dfs(g,i);
		}
	}
	public void dfs(EdgeWieghtedDigraph g, int i){
		marked[i] = true;
		for(Edge e: g.adj(i)){
			if(!marked[e.to()]){
				dfs(g, e.to());
			}
		}
		order.push(i);
	}
	public void dfs(Digraph g, int v){
		marked[v] = true;
		for(int e: g.adj(v)){
			if(!marked[e]){
				dfs(g,e);
			}
		}
		order.push(v);
	}
	public Stack<Integer> getOrder(){
		return order;
	}
	public static void main(String[] args) throws FileNotFoundException{
		Scanner scan = new Scanner(new File(args[0]));
		Topological sort = new Topological(new Digraph(scan));
		System.out.print(sort.getOrder());
	}
}
