import java.util.HashMap;
import java.util.HashSet;

public class Graph
{
    private final HashMap<Integer, HashSet<Integer>> graphMap;

    public Graph()
    {
        this.graphMap = new HashMap<>();
    }

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

    public void removeSinks()
    {
        if (graphMap.isEmpty())
        {
            System.out.println("Graph is empty");
            System.out.println("Graph is acyclic!!!");
        } else
        {
            HashSet<Integer> sinks = new HashSet<>();
            for (Integer node : graphMap.keySet())
            {
                if (graphMap.get(node).isEmpty())
                {
                    System.out.printf("Vertex %d is a sink. It will be eliminated. \n", node);
                    sinks.add(node);
                }
            }
            if (sinks.isEmpty())
            {
                System.out.println("No more sinks found.");
                System.out.println("Graph is not empty after eliminating the sinks.");
                System.out.println("Graph is not acyclic!");
            } else
            {
                //Remove the identified sink keys
                graphMap.keySet().removeAll(sinks);

                //Remove the identified sink values
                for (Integer key : this.graphMap.keySet())
                {
                    this.graphMap.get(key).removeAll(sinks);
                }
                printGraph();
                removeSinks();
            }
        }
    }

    public void printGraph()
    {
        // Filler TODO
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
        // Filler TODO
        System.out.println(" ");
    }

}
