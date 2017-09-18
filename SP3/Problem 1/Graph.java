/**
 * @author rbk
 */
import java.util.*;

public class Graph implements Iterable<Graph.Vertex> {
    Vertex[] v;
    int n;
    boolean directed;

    public static class Vertex implements Iterable<Edge> {
        int name;
        List<Edge> adj, revAdj;
        Vertex(int n) {
            name = n;
            adj = new ArrayList<>();
            revAdj = new ArrayList<>();   /* only for directed graphs */
        }

        public int getName() {
            return name;
        }

        public Iterator<Edge> iterator() { return adj.iterator(); }

        // Helper function for parallel arrays used to store vertex attributes
        public static<T> T getVertex(T[] node, Vertex u) {
            return node[u.name];
        }

        /**
         * Method to get vertex number.  +1 is needed because [0] is vertex 1.
         */
        public String toString() {
            return Integer.toString(name+1);
        }
    }

    public static class Edge {
        Vertex from;
        Vertex to;
        int weight;
        Edge(Vertex u, Vertex v, int w) {
            from = u;
            to = v;
            weight = w;
        }

        public Vertex otherEnd(Vertex u) {
            assert from == u || to == u;
            // if the vertex u is the head of the arc, then return the tail else return the head
            if (from == u) {
                return to;
            } else {
                return from;
            }
        }

        /**
         * Return the string "(x,y)", where edge goes from x to y
         */
        public String toString() {
            return "(" + from + "," + to + ")";
        }

        public String stringWithSpaces() {
            return from + " " + to + " " + weight;
        }
    }

    public Graph(int n) {
        this.n = n;
        this.v = new Vertex[n];
        this.directed = false;
        for (int i = 0; i < n; i++)
            v[i] = new Vertex(i);
    }

    public Vertex getVertex(int n) {
        return v[n-1];
    }

    public void addEdge(Vertex from, Vertex to, int weight) {
        Edge e = new Edge(from, to, weight);
        if(this.directed) {
            from.adj.add(e);
            to.revAdj.add(e);
        } else {
            from.adj.add(e);
            to.adj.add(e);
        }
    }

    public int size() {
        return n;
    }

    public Iterator<Vertex> iterator() {
        return new ArrayIterator<Vertex>(v);
    }

    public static Graph readDirectedGraph(Scanner in) {
        return readGraph(in, true);
    }

    public static Graph readGraph(Scanner in) {
        return readGraph(in, false);
    }

    public static Graph readGraph(Scanner in, boolean directed) {
        int n = in.nextInt();
        int m = in.nextInt();

        // create a graph instance
        Graph g = new Graph(n);
        g.directed = directed;
        for (int i = 0; i < m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();
            g.addEdge(g.getVertex(u), g.getVertex(v), w);
        }
        return g;
    }

}
