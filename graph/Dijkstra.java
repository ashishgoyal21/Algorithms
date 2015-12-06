package graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Dijkstra {
	private boolean[] marked;
	private MinPQ<Vertex> pq;
	private Edge[] edgeTo;
	private double[] distTo;
	private class Vertex implements Comparable<Vertex>{
		public int v;
		public double w;
		public Vertex(int v, double w){
			this.v = v;
			this.w = w;
		}
		public int compareTo(Vertex that){
			if(w < that.w) return -1;
			else if(w > that.w) return 1;
			else return 0;
		}
	}
	public Dijkstra(EdgeWieghtedDigraph g, int s){
		int v = g.V();
		marked = new boolean[v];
		edgeTo = new Edge[v];
		pq = new MinPQ<Vertex>();
		distTo = new double[v];
		for(int i = 0; i < v; i++)
			distTo[i] = Double.POSITIVE_INFINITY;
		distTo[s] = 0.0;
		pq.add(new Vertex(s,distTo[s]));
		while(!pq.isEmpty()){
			Vertex e = pq.removeMin();
			if(!marked[e.v]){
				for(Edge f: g.adj(e.v)){
					if(!marked[f.to()]){
						relax(f);
					}
				}
				marked[e.v] = true;
			}
		}
	}
	public void relax(Edge e){
		int v = e.from(), w = e.to();
		double we = e.weight();
		if(distTo[w] > distTo[v] + we){
			distTo[w] = distTo[v] + we;
			edgeTo[w] = e;
			pq.add(new Vertex(w,distTo[w]));
		}
	}
	/*public void scan(EdgeWieghtedDigraph g, int s){
		marked[s] = true;
		for(Edge e: g.adj(s)){
			if(!marked[e.to()]){
				pq.add(e);
			}
		}
	}*/
	public boolean hasPathTo(int v){
		return distTo[v] < Double.POSITIVE_INFINITY;
	}
	public double distTo(int v){
		return distTo[v];
	}
	public Iterable<Edge> pathTo(int v){
		if(!hasPathTo(v)) return null;
		ArrayList<Edge> list = new ArrayList<Edge>();
		for(Edge e = edgeTo[v]; e!=null; e = edgeTo[e.from()])
			list.add(e);
		return list;
	}
	public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File(args[0]));
        EdgeWieghtedDigraph G = new EdgeWieghtedDigraph(in);
        int s = Integer.parseInt(args[1]);

        // compute shortest paths
        Dijkstra sp = new Dijkstra(G, s);


        // print shortest path
        for (int t = 0; t < G.V(); t++) {
            if (sp.hasPathTo(t)) {
                System.out.printf("%d to %d (%.2f)  ", s, t, sp.distTo(t));
                for (Edge e : sp.pathTo(t)) {
                	System.out.print(e + "   ");
                }
                System.out.println();
            }
            else {
            	System.out.printf("%d to %d         no path\n", s, t);
            }
        }
    }
}
