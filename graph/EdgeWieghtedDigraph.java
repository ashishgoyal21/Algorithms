package graph;

import java.util.ArrayList;
import java.util.Scanner;

public class EdgeWieghtedDigraph {
	private static final String NEWLINE= System.getProperty("line.seperator");
	private int V;
	private Bag<Edge>[] adj;
	private int e;
	public EdgeWieghtedDigraph(int v){
		V = v;
		adj = (Bag<Edge>[])new Bag[v];
		for(int i = 0; i < V; i++){
			adj[i] = new Bag<Edge>();
		}
	}
	public EdgeWieghtedDigraph(Scanner in){
		this(in.nextInt());
		int e = in.nextInt();
		for(int i = 0; i < e; i++){
			int v = in.nextInt();
			int w = in.nextInt();
			double weight = in.nextDouble();
			addEdge(v, w, weight);
		}
	}
	public void addEdge(int v, int w, double weight){
		adj[v].add(new Edge(v,w,weight));
		e++;
	}
	public int V(){
		return V;
	}
	public Iterable<Edge> adj(int v){
		return adj[v];
	}
	public int E(){
		return e;
	}
	public Iterable<Edge> edges(){
		ArrayList<Edge> edges = new ArrayList<Edge>();
		for(int i = 0; i < V; i++){
			for(Edge w : adj[i])
				edges.add(w);
		}
		return edges;
	}
	public String toString() {
        StringBuilder s = new StringBuilder();
        int V = this.V, E = e;
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for (int v = 0; v < V; v++) {
            for (Edge w : adj[v]) {
                s.append(w.from() + ", " + w.to() + ", " + w.weight());
                s.append(NEWLINE);
            }
        }
        return s.toString();
    }
}
