package graph;

import java.util.Scanner;

public class Digraph {
	private static final String NEWLINE= System.getProperty("line.seperator");
	private int v;
	private int e;
	private Bag<Integer>[] adj;
	public Digraph(int v){
		this.v = v;
		adj = (Bag<Integer>[])new Bag[v];
		for(int i=0; i < v; i++){
			adj[i] = new Bag<Integer>();
		}
	}
	public Digraph(Scanner in){
		this(in.nextInt());
		int e = in.nextInt();
		for(int i = 0; i < e; i++){
			int v = in.nextInt();
			int w = in.nextInt();
			addEdge(v,w);
		}
	}
	public Digraph reverse(){
		Digraph r = new Digraph(v);
		for(int i = 0; i < v; i++){
			for(int j: adj[i]){
				r.addEdge(j, i);
			}
		}
		return r;
	}
	public void addEdge(int v, int w){
		e++;
		adj[v].add(w);
	}
	public Iterable<Integer> adj(int v){
		return adj[v];
	}
	public int V(){
		return v;
	}
	public int E(){
		return e;
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
}
