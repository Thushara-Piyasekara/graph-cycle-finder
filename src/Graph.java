import java.util.*;

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

//    public void printCycle2()
//    {
//        HashSet<Integer> visited = new HashSet<>();
//        Stack<Integer> nodeStack = new Stack<>();
//
//        Integer startingNode = this.graphMap.keySet().iterator().next();
//        ArrayList<Integer> path = new ArrayList<>();
//
//        DFS(startingNode, path);
//    }
//
//    public void DFS(Integer parent, ArrayList<Integer> path)
//    {
//        path.add(parent);
//        for (Integer child : graphMap.get(parent))
//        {
//            if (path.contains(child))
//            {
//                printCycleArray(child, path);
//                return;
//            } else
//            {
//                DFS(child, path);
//            }
//        }
//    }

    public void printCycle()
    {
        if (!isEmpty())
        {
            Integer node = this.graphMap.keySet().iterator().next();
            ArrayList<Integer> path = new ArrayList<>();

            boolean cycleFound = false;
            while (!cycleFound)
            {
                if (path.contains(node))
                {
                    System.out.print("Cycle Found: ");
                    for (int i = path.indexOf(node); i < path.size(); i++)
                    {
                        System.out.print(path.get(i) + " ==> ");
                    }
                    System.out.println(node);
                    cycleFound = true;
                } else
                {
                    path.add(node);
                    node = this.graphMap.get(node).stream().iterator().next();
                }
            }
        }
    }

//    public static void printCycleArray(Integer duplicate, ArrayList<Integer> path)
//    {
//        System.out.print("Cycle : ");
//        for (int i = path.indexOf(duplicate); i < path.size(); i++)
//        {
//            System.out.print(path.get(i) + " ==> ");
//        }
//        System.out.println(duplicate);
//    }


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

    public boolean isEmpty()
    {
        return graphMap.isEmpty();
    }

}
