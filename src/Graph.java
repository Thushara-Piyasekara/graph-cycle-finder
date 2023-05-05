import java.util.HashMap;
import java.util.HashSet;

public class Graph
{
    private HashMap<Integer, HashSet<Integer>> graphMap;

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

    public void removeSinks(HashSet<Integer> sinks)
    {
        //Remove the identified sink keys
        graphMap.keySet().removeAll(sinks);

        //Remove the identified sink values
        for (Integer key : this.graphMap.keySet())
        {
            this.graphMap.get(key).removeAll(sinks);
        }
        removeSinks();
    }

//    public void findSinks2()
//    {
//        HashSet<Integer> sinks = new HashSet<>();
//        for (Integer node : graphMap.keySet())
//        {
//            if (graphMap.get(node).isEmpty())
//            {
//                sinks.add(node);
//            }
//        }
//        if (graphMap.isEmpty())
//        {
//            System.out.println("Graph is empty!!!");
//        } else if (!sinks.isEmpty())
//        {
//            //Remove the identified sink keys
//            graphMap.keySet().removeAll(sinks);
//
//            //Remove the identified sink values
//            for (Integer key : this.graphMap.keySet())
//            {
//                this.graphMap.get(key).removeAll(sinks);
//            }
//            findSinks();
//        }
//    }

    public void removeSinks()
    {
        if (graphMap.isEmpty())
        {
            System.out.println("Graph is empty!!!");
        } else
        {
            HashSet<Integer> sinks = new HashSet<>();
            for (Integer node : graphMap.keySet())
            {
                if (graphMap.get(node).isEmpty())
                {
                    sinks.add(node);
                }
            }
            if (!sinks.isEmpty())
            {
                //Remove the identified sink keys
                graphMap.keySet().removeAll(sinks);

                //Remove the identified sink values
                for (Integer key : this.graphMap.keySet())
                {
                    this.graphMap.get(key).removeAll(sinks);
                }
                
                System.out.println("""
                        Sinks were identified in 
                        """);
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
                System.out.print(edge + ", ");
            }
            System.out.println(" ");
        }
        // Filler TODO
        System.out.println(" ");
    }

}
