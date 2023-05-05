import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Graph graph1 = new Graph();
        populateGraph(graph1);

        System.out.println("Before sink elimination");
        graph1.printGraph();

        long startTimeRS = System.nanoTime();
        graph1.removeSinks();
        long endTimeRS = System.nanoTime();

        long startTimePC = System.nanoTime();
        graph1.printCycle();
        long endTimePC = System.nanoTime();

        System.out.println("Execution time for removing sinks " + (endTimeRS - startTimeRS) + " nano Seconds");
        System.out.println("Execution time for printing cycle " + (endTimePC - startTimePC) + " nano Seconds");
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

                graph.addEdge(startVertex, endVertex);
            }

        } catch (FileNotFoundException e)
        {
            System.out.println("File not found! \nYou may have deleted the file");
        }
    }
}