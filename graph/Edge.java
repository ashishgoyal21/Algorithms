package graph;

public class Edge implements Comparable<Edge>{
	private int from;
	private int to;
	private double weight;
	public Edge(int v, int w, double weight){
		from = v;
		to = w;
		this.weight = weight;
	}
	public int from(){
		return from;
	}
	public int to(){
		return to;
	}
	public double weight(){
		return weight;
	}
	public int other(int v){
		if(v == from) return to;
		else if(v == to) return from;
		else return -1;
	}
	public String toString(){
		StringBuilder s = new StringBuilder();
		s.append(from + ">" + to + " " + weight);
		return s.toString();
	}
	@Override
	public int compareTo(Edge o) {
		Edge that = o;
		if(weight < that.weight) return -1;
		else if(weight > that.weight) return 1;
		else return 0;
	}
}
