/**
 * @author  Thushara Piyasekara
 * @UoW_ID  w1899372
 * @IIT_ID
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * This is the main class to perform the cycle finding algorithm
 *
 */
public class Main
{
    /**
     * Main method for executing the program
     * Concludes whether the given graph is acyclic or not
     * Prints a cycle if the graph is not acyclic
     *
     */
    public static void main(String[] args)
    {
        Graph graph1 = populateGraph();

        if (graph1 != null)
        {
            long startTimeRS = System.nanoTime();
            graph1.removeSinks();
            long endTimeRS = System.nanoTime();

            long startTimePC = System.nanoTime();
            graph1.printCycle();
            long endTimePC = System.nanoTime();

            System.out.println();
            System.out.println("Execution time for removing sinks " + (endTimeRS - startTimeRS) + " nano seconds");
            System.out.println("Execution time for printing cycle " + (endTimePC - startTimePC) + " nano seconds");
        }
    }

    /**
     * Creates a graph object and populates it using data from GraphData.txt file
     * Throws a FileNotFoundException if the file is not found
     * Throws a NoSuchElementException if the format of the file is incorrect
     *
     * @return populated Graph object
     */
    public static Graph populateGraph()
    {
        try
        {
            Graph graph = new Graph();
            Scanner fileScanner = new Scanner(new File("GraphData.txt"));
            Integer startVertex;
            Integer endVertex;

            while (fileScanner.hasNextInt())
            {
                startVertex = fileScanner.nextInt();
                endVertex = fileScanner.nextInt();

                graph.addEdge(startVertex, endVertex);
            }
            System.out.println("Graph successfully populated using data from file.");
            return graph;
        } catch (FileNotFoundException e)
        {
            System.out.println("File not found! \nYou may have deleted the file.");
        } catch (NoSuchElementException e)
        {

            System.out.println("""
                    The format of the given input file is incorrect!
                    The input format of GraphData.txt should be as follows,
                    1 2
                    1 3
                    3 4
                    3 3
                    """);
        }
        return null;
    }
}