import java.util.*;

/**
 * Created by
 * Group 50
 *
 * Venkata Sarath Chandra Prasad Nelapati
 * Varun Simha Balaraju
 * Jithin Paul
 * Sunit Mathew
 *
 */

public class TopoSort {

    public static void main(String[] args) {
        TopoSort topoSort = new TopoSort();

        // function to create graph - can be changed to take input from a file
        Graph graph = topoSort.initGraph();

        List<Graph.Vertex> sortedVertices = topoSort.topologicalSort1(graph);
        List<Graph.Vertex> sortedVertices2 = topoSort.topologicalSort2(graph);
        System.out.println("Topological order from algo 1 : ");
        System.out.println(sortedVertices==null? "Graph is Cyclic" : sortedVertices);
        System.out.println("Topological order from algo 2 : ");
        System.out.println(sortedVertices2==null? "Graph is Cyclic" : sortedVertices2);
    }

    private List<Graph.Vertex> topologicalSort2(Graph graph) {

        //cycles checking
        if (checkForCycles(graph)) return null;

        Stack<Graph.Vertex> stack = new Stack<>();
        List<Graph.Vertex> resultList = new LinkedList<>();

        boolean[] visited = new boolean[graph.size()];
        Arrays.fill(visited, false);
        for (Graph.Vertex vertex : graph) {
            if (!visited[vertex.getName()]) {
                topoSortRecursive(vertex, visited, stack);
            }
        }
        while (!stack.isEmpty()) {
            resultList.add(stack.pop());
        }
        return resultList;

    }

    private boolean checkForCycles(Graph graph) {
        boolean[] visited = new boolean[graph.size()];
        boolean[] stack = new boolean[graph.size()];
        for (Graph.Vertex vertex : graph) {
            if (visited[vertex.getName()]) continue;
            return isCyclic(vertex, visited, stack);
        }
        return false;
    }

    private boolean isCyclic(Graph.Vertex vertex, boolean[] visited, boolean[] stack) {
        visited[vertex.getName()] = true;
        stack[vertex.getName()] = true;

        for (Graph.Edge edge : vertex) {
            Graph.Vertex to = edge.to;
            if (!visited[to.getName()])
                return isCyclic(to, visited, stack);
            if (stack[to.getName()]) ;
            return stack[to.getName()];
        }
        stack[vertex.getName()] = false;
        return false;
    }

    private void topoSortRecursive(Graph.Vertex vertex, boolean[] visited, Stack<Graph.Vertex> stack) {
        visited[vertex.getName()] = true;
        for (Graph.Edge edge : vertex) {
            Graph.Vertex to = edge.to;
            if (!visited[to.getName()]) {
                topoSortRecursive(to, visited, stack);
            }
        }
        //pushing to stack only when all its children are pushed to stack.
        stack.push(vertex);
    }

    private List<Graph.Vertex> topologicalSort1(Graph graph) {
        int[] inDegree = new int[graph.size()];
        calculateInDegrees(graph, inDegree);
        int count = 0;
        Queue<Graph.Vertex> queue = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.add(graph.getVertex(i + 1));
            }
        }
        List<Graph.Vertex> finalQueue = new LinkedList<>();

        while (!queue.isEmpty()) {
            Graph.Vertex vertex = queue.poll();
            finalQueue.add(vertex);
            count++;
            for (Graph.Edge edge : vertex) {
                Graph.Vertex to = edge.to;
                if (--inDegree[to.getName()] == 0) {
                    queue.add(to);
                }
            }
        }
        if (count != graph.size()) {
            return null;
        }
        return finalQueue;

    }

    private void calculateInDegrees(Graph graph, int[] inDegree) {
        for (Graph.Vertex vertex : graph) {
            for (Graph.Edge edge : vertex) {
                int to = edge.to.getName();
                inDegree[to]++;
            }
        }
    }

    private Graph initGraph() {
        Graph graph = new Graph(7);

        // used graph is attached as png for reference.
        graph.directed = true;
        graph.addEdge(graph.getVertex(1), graph.getVertex(2), 0);
        graph.addEdge(graph.getVertex(1), graph.getVertex(3), 0);
        graph.addEdge(graph.getVertex(2), graph.getVertex(4), 0);
        graph.addEdge(graph.getVertex(2), graph.getVertex(5), 0);
        graph.addEdge(graph.getVertex(3), graph.getVertex(6), 0);
        graph.addEdge(graph.getVertex(4), graph.getVertex(7), 0);
        graph.addEdge(graph.getVertex(5), graph.getVertex(7), 0);
        graph.addEdge(graph.getVertex(6), graph.getVertex(5), 0);
        graph.addEdge(graph.getVertex(6), graph.getVertex(7), 0);
        //uncommment below line and comment above line to make the graph cyclic
//        graph.addEdge(graph.getVertex(7), graph.getVertex(6), 0);

        return graph;

    }

}
