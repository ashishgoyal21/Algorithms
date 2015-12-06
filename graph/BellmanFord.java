package graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class BellmanFord {
	private double[] distTo;
	private Edge[] edgeTo;
	private int source;
	public BellmanFord(EdgeWieghtedDigraph g, int s){
		int v = g.V();
		source = s;
		distTo = new double[v];
		edgeTo = new Edge[v];
		for(int i = 0; i < v; i++)
			distTo[i] = Double.POSITIVE_INFINITY;
		distTo[s] = 0;
		for(int i = 0; i < v; i++){
			for(Edge e: g.edges()){
				relax(e);
			}
		}
	}
	public void relax(Edge e){
		int f = e.from(), t = e.to();
		double w = e.weight();
		if (distTo[t] > distTo[f] + w){
			distTo[t] = distTo[f] + w;
			edgeTo[t] = e;
		}
	}
	public boolean hasPathTo(int v){
		return distTo[v] < Double.POSITIVE_INFINITY;
	}
	public double distTo(int v){
		return distTo[v];
	}
	public Iterable<Edge> pathTo(int v){
		if(!hasPathTo(v)) return null;
		ArrayList<Edge> list = new ArrayList<Edge>();
		for(Edge e = edgeTo[v]; e!= null; e = edgeTo[e.from()])
			list.add(e);
		return list;
	}
	public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File(args[0]));
        int s = Integer.parseInt(args[1]);
        EdgeWieghtedDigraph G = new EdgeWieghtedDigraph(in);

        BellmanFord sp = new BellmanFord(G, s);

        for (int v = 0; v < G.V(); v++) {
            if (sp.hasPathTo(v)) {
                System.out.printf("%d to %d (%5.2f)  ", s, v, sp.distTo(v));
                for (Edge e : sp.pathTo(v)) {
                    System.out.print(e + "   ");
                }
                System.out.println();
            }
            else {
            	System.out.printf("%d to %d           no path\n", s, v);
            }
        }


    }
}
