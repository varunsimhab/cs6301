/**
 * Created by Varun on 2/13/2017.
 */
package LP3;
import java.util.Iterator;
import java.util.LinkedList;
public class BFS {

     private int vertices;  // To hold the no of vertices

     // Creating an Adjacency List
    private LinkedList<Integer> adjacency_list[];


    // Constructor


    BFS(int no_of_vertices) {
        vertices = no_of_vertices;
        adjacency_list=new LinkedList[no_of_vertices];
        for (int i = 0; i < vertices; i++) {
            adjacency_list[i] = new LinkedList();

        }

    }
        // function to add the edges

        void add_edge(int v, int w) {

        adjacency_list[v].add(w);

        }

        void Breadfirstsearch(int s )  // Here we would be receiving the node from where BFS needs to be done
        {
           //Making a Boolean List to keep Track of what vertices have been visited. True means visited and False means
            // Not visited
            // By deafault the following declaration makes all the values false.
            boolean visited[]= new boolean [vertices];

            // Creating a queue for BFS
            LinkedList<Integer> queue = new LinkedList<Integer>();
            visited[s]=true;
            queue.add(s);

            while(queue.size()!=0) {

                 s=queue.poll();
                 System.out.println("s"+s);
                 Iterator<Integer> i = adjacency_list[s].listIterator();
                 while(i.hasNext()) {
                     int n=i.next();
                     if(!visited[n]) {

                     visited[n]=true;
                     queue.add(n);
                     }




                 }

            }



        }


    // Driver method to
    public static void main(String args[])
    {
        BFS g = new BFS(4);

        g.add_edge(0, 1);
        g.add_edge(0, 2);
        g.add_edge(1, 2);
        g.add_edge(2, 0);
        g.add_edge(2, 3);
        g.add_edge(3, 3);

        System.out.println("Following is Breadth First Traversal "+
                "(starting from vertex 2)");

        g.Breadfirstsearch(2);
    }


}
