/** @author rbk
 *  Sample IO class
 *  Ver 1.0: 2017/08/08
 **/


package cs6301.g50;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BreadthFirstSearch {
    // Class to store information about a vertex in this algorithm
    class BFSVertex {
        Graph.Vertex element;
        boolean seen;
        int cno;
        BFSVertex(Graph.Vertex u) {
            element = u;
            seen = false;
            cno = -1;
        }
    }
    // Algorithm uses a parallel array for storing information about vertices
    BFSVertex[] bfsVertex;
    Graph g;

    public BreadthFirstSearch(Graph g) {
        this.g = g;
        bfsVertex = new BFSVertex[g.size()];
        for(Graph.Vertex u: g) { bfsVertex[u.name] = new BFSVertex(u); }
    }

    // Main algorithm for finding the number of connected components of g using DFS
    int findCC() {
        int cno = 0;
        for(Graph.Vertex u: g) {
            if(!seen(u)) {
                cno++;
                dfsVisit(u, cno);
            }
        }
        return cno;
    }

    void dfsVisit(Graph.Vertex u, int cno) {
        visit(u, cno);
        for(Graph.Edge e: u) {
            Graph.Vertex v = e.otherEnd(u);
            if(!seen(v)) {
                dfsVisit(v, cno);
            }
        }
    }

    boolean seen(Graph.Vertex u) {
        BFSVertex ccu = getCCVertex(u);
        return ccu.seen;
    }

    // Visit a node by marking it as seen and assigning it a component no
    void visit(Graph.Vertex u, int cno) {
        BFSVertex ccu = getCCVertex(u);
        ccu.seen = true;
        ccu.cno = cno;
    }

    // From Vertex to BFSVertex (ugly)
    BFSVertex getCCVertex(Graph.Vertex u) {
        return bfsVertex[u.name];
    }

    // From BFSVertex to Vertex
    Graph.Vertex getVertex(BFSVertex c) {
        return c.element;
    }

    public static void main(String[] args) throws FileNotFoundException {
        int evens = 0;
        Scanner in;
        if (args.length > 0) {
            File inputFile = new File(args[0]);
            in = new Scanner(inputFile);
        } else {
            in = new Scanner(System.in);
        }
        Graph g = Graph.readGraph(in);
        BreadthFirstSearch cc = new BreadthFirstSearch(g);
        int nc = cc.findCC();

        System.out.println("Input Graph has " + nc + " components:");
        for(Graph.Vertex u: g) {
            System.out.print(u + " [ " + cc.getCCVertex(u).cno + " ] :");
            for(Graph.Edge e: u.adj) {
                Graph.Vertex v = e.otherEnd(u);
                System.out.print(e + " ");
            }
            System.out.println();
        }
    }
}

/******************************
 $ java cs6301.g00.BreadthFirstSearch
 7 6
 1 3 1
 4 3 1
 4 1 1
 2 6 1
 6 7 1
 7 2 1

 Output:
 Input Graph has 3 components:
 1 [ 1 ] :(1,3) (4,1) 
 2 [ 2 ] :(2,6) (7,2) 
 3 [ 1 ] :(1,3) (4,3) 
 4 [ 1 ] :(4,3) (4,1) 
 5 [ 3 ] :
 6 [ 2 ] :(2,6) (6,7) 
 7 [ 2 ] :(6,7) (7,2)

 */
