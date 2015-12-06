package graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CC {
	private int[] id;
	private int[] size;
	private int count;
	private boolean[] marked;
	public CC(Digraph g){
		int v = g.V();
		id = new int[v];
		size = new int[v];
		marked = new boolean[v];
		count = 0;
		Stack<Integer> order = (new Topological(g.reverse())).getOrder();
		for(int i : order){
			if(!marked[i]){
				dfs(g, i);
				count++;
			}
		}
	}
	public void dfs(Digraph g, int v){
		marked[v] = true;
		id[v] = count;
		size[count]++;
		for(int i: g.adj(v)){
			if(!marked[i]){
				dfs(g,i);
			}
		}
	}
	public int count(){
		return count;
	}
	public int id(int v){
		return id[v];
	}
	public boolean stronglyConnected(int v, int w){
		return id[v] == id[w];
	}
	public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File(args[0]));
        Digraph G = new Digraph(in);
        CC scc = new CC(G);

        // number of connected components
        int M = scc.count();
        System.out.println(M + " components");

        // compute list of vertices in each strong component
        Queue<Integer>[] components = (Queue<Integer>[]) new Queue[M];
        for (int i = 0; i < M; i++) {
            components[i] = new Queue<Integer>();
        }
        for (int v = 0; v < G.V(); v++) {
            components[scc.id(v)].enqueue(v);
        }

        // print results
        for (int i = 0; i < M; i++) {
            for (int v : components[i]) {
                System.out.print(v + " ");
            }
            System.out.println();
        }

    }
}
