package graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class KruskalMst {
	private ArrayList<Edge> mst;
	private UF uf;
	private double weight;
	public KruskalMst(EdgeWeightedGraph g){
		int v = g.V();
		mst = new ArrayList<Edge>();
		Edge[] edges = new Edge[g.E()];
		uf = new UF(v);
		weight = 0.0;
		int i = 0;
		for(Edge e: g.edges())
			edges[i++] = e;
		Arrays.sort(edges);
		for(i = 0; i < edges.length; i++){
			int c = edges[i].from(), w = edges[i].to();
			if(!uf.isConnected(w, c)){
				mst.add(edges[i]);
				weight += edges[i].weight();
				uf.union(w, c);
			}
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
        KruskalMst mst = new KruskalMst(G);
        for (Edge e : mst.mst()) {
            System.out.println(e);
        }
        System.out.printf("%.5f\n", mst.weight());
    }
}
