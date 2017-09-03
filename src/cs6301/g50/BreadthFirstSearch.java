package cs6301.g50;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BreadthFirstSearch {
    BFSVertex[] bfsVertex;
    Graph g;
    public BreadthFirstSearch(Graph g) {
        this.g = g;
        bfsVertex = new BFSVertex[g.size()];
        for (Graph.Vertex u : g) {
            bfsVertex[u.name] = new BFSVertex(u);
        }
    }

    public void resetVertexStates() {
        for (Graph.Vertex u : g) {
            bfsVertex[u.name].cno = -1;
            bfsVertex[u.name].seen = false;
        }
    }

    Graph.Vertex doBfs(Graph.Vertex vertex) {
        int cno = 0;
        Queue<Graph.Vertex> adjqueue = new LinkedList<>();
        adjqueue.add(vertex);
        visit(vertex, vertex, cno);
        while (!adjqueue.isEmpty()) {
            vertex = adjqueue.remove();
            cno = getBFSVertex(vertex).cno;
            for (Graph.Edge e : vertex) {
                Graph.Vertex v = e.otherEnd(vertex);
                if (!seen(v)) {
                    visit(v, vertex, cno + 1);
                    adjqueue.add(v);
                }
            }
        }
        return vertex;
    }

    /**
     * Function to get Diameter Path in a graph given an end vertex,i.e. of of the
     * two ends of the diameter.
     * End vertex will have a parent. The other end will have itself as the parent
     *
     * @param vertex
     * @return LinkedList of vertex type
     */

    LinkedList<Graph.Vertex> getDiameterPath(Graph.Vertex vertex) {
        LinkedList<Graph.Vertex> list = new LinkedList<>();
        list.add(vertex);
        Graph.Vertex parent = getBFSVertex(vertex).parent;
        while (parent != vertex) {
            list.add(parent);
            vertex = parent;
            parent = getBFSVertex(parent).parent;
        }
        return list;
    }

    boolean seen(Graph.Vertex u) {
        BFSVertex ccu = getBFSVertex(u);
        return ccu.seen;
    }

    void visit(Graph.Vertex u, Graph.Vertex parent, int cno) {
        BFSVertex ccu = getBFSVertex(u);
        ccu.seen = true;
        ccu.cno = cno;
        ccu.parent = parent;
    }

    BFSVertex getBFSVertex(Graph.Vertex u) {
        return bfsVertex[u.name];
    }

    /**
     *
     * @param c
     * @return
     */
    Graph.Vertex getVertex(BFSVertex c) {
        return c.element;
    }

    class BFSVertex {
        Graph.Vertex element;
        Graph.Vertex parent;
        boolean seen;
        int cno;

        BFSVertex(Graph.Vertex u) {
            element = u;
            seen = false;
            cno = -1;
        }
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
        BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch(g);
        Graph.Vertex furthest = breadthFirstSearch.doBfs(g.getVertex(1));
        LinkedList<Graph.Vertex> list = breadthFirstSearch.getDiameterPath(furthest);

        System.out.println("Furthest Vertex " + furthest.getName());
    }
}

/******************************
 $ java cs6301.g00.BreadthFirstSearch
 10 9
 1 3 1
 4 3 1
 4 1 1
 2 6 1
 6 7 1
 7 2 1
 8 3 1
 8 9 1
 10 9 1

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
