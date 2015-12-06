package graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PrimMst {
	ArrayList<Edge> mst;
	private boolean[] marked;
	private MinPQ<Edge> q;
	private double weight;
	public PrimMst(EdgeWieghtedDigraph g){
		int v = g.V();
		mst = new ArrayList<Edge>();
		marked = new boolean[v];
		q = new MinPQ<Edge>();
		for(int i = 0; i < v; i++){
			if(!marked[i])
				prim(g, i);
		}
	}
	public PrimMst(EdgeWeightedGraph g){
		int v = g.V();
		mst = new ArrayList<Edge>();
		marked = new boolean[v];
		q = new MinPQ<Edge>();
		for(int i = 0; i < v; i++){
			if(!marked[i])
				prim(g, i);
		}
	}
	public void scan(EdgeWeightedGraph g, int v){
		marked[v] = true;
		for(Edge e: g.adj(v)){
			q.add(e);
		}
	}
	public void scan(EdgeWieghtedDigraph g, int v){
		marked[v] = true;
		for(Edge e: g.adj(v)){
			q.add(e);
		}
	}
	public void prim(EdgeWieghtedDigraph g, int v){
		scan(g,v);
		while(!q.isEmpty()){
			Edge e = q.removeMin();
			int c = e.from();
			int w = e.to();
			if(marked[c] && marked[w])continue;
			mst.add(e);
			weight += e.weight();
			if(!marked[c])scan(g,c);
			if(!marked[w])scan(g,w);
		}
	}
	public void prim(EdgeWeightedGraph g, int v){
		scan(g,v);
		while(!q.isEmpty()){
			Edge e = q.removeMin();
			int c = e.from();
			int w = e.to();
			if(marked[c] && marked[w])continue;
			mst.add(e);
			weight += e.weight();
			if(!marked[c])scan(g,c);
			if(!marked[w])scan(g,w);
		}
	}
	public Iterable<Edge> mst(){
		return mst;
	}
	public double weight(){
		return weight;
	}
	public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File(args[0]));
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        PrimMst mst = new PrimMst(G);
        for (Edge e : mst.mst()) {
            System.out.println(e);
        }
        System.out.printf("%.5f\n", mst.weight());
    }
}
