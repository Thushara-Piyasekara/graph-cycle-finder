import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Graph graph1 = new Graph();

//        graph1.addEdge(2,3);
//        graph1.addEdge(3,4);
//        graph1.addEdge(3,2);
//        graph1.addEdge(3,2);
//        graph1.addEdge(3,2);
//        graph1.addEdge(3,2);
//        graph1.addEdge(3,2);
//        graph1.addEdge(3,100);
//        graph1.addEdge(3,7);
//        graph1.addEdge(4,9);
//        graph1.addEdge(4,13);
//        graph1.addEdge(5,2);

        populateGraph(graph1);

        graph1.printGraph();

        graph1.removeSinks();
        graph1.printGraph();


    }

    public static void populateGraph(Graph graph)
    {
        try
        {

            Scanner fileScanner = new Scanner(new File("GraphData.txt"));
            Integer startVertex;
            Integer endVertex;

            while (fileScanner.hasNextLine())
            {
                startVertex = fileScanner.nextInt();
                endVertex = fileScanner.nextInt();

                graph.addEdge(startVertex,endVertex);
            }

        } catch (FileNotFoundException e)
        {
            System.out.println("File not found! \nYou may have deleted the file");
        }
    }
}