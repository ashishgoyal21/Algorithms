package graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Graph {
	private static final String NEWLINE = System.getProperty("line.separator");
	private int v;
	private int e;
	private Bag<Integer>[] adj;
	
	public Graph(int v){
		this.v = v;
		e = 0;
		adj = (Bag<Integer>[])new Bag[v];
		for(int i = 0; i < v; i++){
			adj[i] = new Bag<Integer>();
		}
	}
	public Graph(Scanner in){
		this(in.nextInt());
		int e = in.nextInt();
		for(int i = 0; i<e; i++){
			int v = in.nextInt();
			int w = in.nextInt();
			addEdge(v,w);
		}
	}
	public void addEdge(int v, int w){
		e++;
		adj[v].add(w);
		adj[w].add(v);
	}
	public int V(){
		return v;
	}
	public int E(){
		return e;
	}
	public Iterable<Integer> adj(int v){
		return adj[v];
	}
	 public String toString() {
	        StringBuilder s = new StringBuilder();
	        int V = v, E = e;
	        s.append(V + " vertices, " + E + " edges " + NEWLINE);
	        for (int v = 0; v < V; v++) {
	            s.append(v + ": ");
	            for (int w : adj[v]) {
	                s.append(w + " ");
	            }
	            s.append(NEWLINE);
	        }
	        return s.toString();
	    }
	 public static void main(String[] args) throws FileNotFoundException{
		 Scanner scan = new Scanner(new File(args[0]));
		 Graph graph = new Graph(scan);
		 scan.close();
		 System.out.print(graph);
	 }
}
