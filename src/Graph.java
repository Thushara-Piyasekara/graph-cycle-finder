/**
 * @author  Thushara Piyasekara
 * @UoW_ID  w1899372
 * @IIT_ID
 *
 */

import java.util.*;

/**
 * Represents a directed unweighted graph in an adjacency list structure
 *
 */
public class Graph
{
    /**
     * Property graphMap represents the graph as a HashMap of HashSets
     * Key of the HashMap represents the starting node
     * Value of the HashMap represents a HashSet of all the ending nodes
     */
    private HashMap<Integer, HashSet<Integer>> graphMap;
    /**
     * Property hashSinks is used to prevent the Graph.printCycle() method being called before eliminating the sinks
     * Read the Graph.printCycle() description for more details
     */
    private boolean hasSinks = true;

    /**
     * Constructs an empty graph
     *
     */
    public Graph()
    {
        this.graphMap = new HashMap<>();
    }


    /**
     * Adds an edge to the graph
     * New node keys will be added to the HashMap if the nodes are not already existing
     * @param startVertex starting node of the edge
     * @param endVertex ending node of the edge
     *
     */
    public void addEdge(Integer startVertex, Integer endVertex)
    {
        //Adding vertexes to the graph if they don't already exist
        if (!graphMap.containsKey(startVertex))
        {
            graphMap.put(startVertex, new HashSet<>());
        }
        if (!graphMap.containsKey(endVertex))
        {
            graphMap.put(endVertex, new HashSet<>());
        }

        //Create the edge between the vertexes
        graphMap.get(startVertex).add(endVertex);
    }


    /**
     * Removes all sink nodes from a Graph
     * Prints conclusions after the sink elimination is over
     *
     */
    public void removeSinks()
    {
        printGraph();                                           // Please note this line was omitted in the performance analysis in the report
        if (graphMap.isEmpty())                                 // If the graph is empty, it should be acyclic
        {
            System.out.println("Graph is empty");
            System.out.println("Conclusion : Yes. The graph is acyclic!!!");
        } else                                                  // If the graph is not empty, there should be more sinks left or the graph is cyclic
        {
            HashSet<Integer> sinks = new HashSet<>();
            for (Integer node : graphMap.keySet())
            {
                if (graphMap.get(node).isEmpty())               // If a node has no outgoing edges, it is a sink
                {
                    System.out.printf("Vertex %d is a sink. It will be eliminated. \n", node);
                    sinks.add(node);
                }
            }
            if (sinks.isEmpty())                                // If there are no sinks in the current graph, it is not acyclic
            {
                hasSinks = false;
                System.out.println("No more sinks found. Graph is not empty after eliminating the sinks.");
                System.out.println("Conclusion : No. The graph is not acyclic!");
            } else
            {
                graphMap.keySet().removeAll(sinks);             // Eliminate the identified sink keys

                for (Integer key : this.graphMap.keySet())
                {
                    this.graphMap.get(key).removeAll(sinks);    // Eliminate the identified sink values
                }
                removeSinks();                                  // Identify and remove new potential sinks
            }
        }
    }


    /**
     * Prints a cycle from a non-empty graph that has no sink nodes
     * Graph.removeSinks() should be called beforehand for this method to execute
     * Logic :- If a graph has no sink nodes, and it is not empty, it should have a cycle
     *          Since all the sinks are eliminated, every node should have at least one outgoing edge
     *          If we traverse from one outgoing edge to another, a cycle should be found after enough traversals
     *
     */
    public void printCycle()
    {
        if (!isEmpty() && !hasSinks)
        {
            Integer node = this.graphMap.keySet().iterator().next();       // Selects a random node as the starting node
            ArrayList<Integer> path = new ArrayList<>();                   // Keeps track of the traversed nodes

            boolean cycleFound = false;
            while (!cycleFound)
            {
                if (path.contains(node))                                   // If the node was already traversed, it is a cycle
                {
                    System.out.print("\nCycle Found: ");
                    for (int i = path.indexOf(node); i < path.size(); i++)
                    {
                        System.out.print(path.get(i) + " ==> ");
                    }
                    System.out.println(node);
                    cycleFound = true;
                } else                                                     // If the node was not already traversed, move on to the next node
                {
                    path.add(node);
                    node = this.graphMap.get(node).stream().iterator().next();
                }
            }
        }
    }


    /**
     * Prints the graph in the form of an Adjacency List
     *
     */
    public void printGraph()
    {
        System.out.println();
        for (Integer key : this.graphMap.keySet())
        {
            System.out.print(key + " : ");
            for (Integer edge : graphMap.get(key))
            {
                System.out.print(edge + " ");
            }
            System.out.println(" ");
        }
        System.out.println();
    }


    /**
     * Checks whether the graph is empty or not
     * @return true if the graph is empty, false if the graph is not empty
     *
     */
    public boolean isEmpty()
    {
        return graphMap.isEmpty();
    }
}
