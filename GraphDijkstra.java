package Graph;

import java.util.ArrayList;
import java.util.Arrays;

public class GraphDijkstra {
	private int[][] adjacencyMatrix;
    private int numVertices;
    private GraphNode[] nodes;

    public GraphDijkstra(int numVertices) {
        this.numVertices = numVertices;
        this.nodes = new GraphNode[numVertices];
        this.adjacencyMatrix = new int[numVertices][numVertices];

        for (int i = 0; i < numVertices; i++) {
            Arrays.fill(adjacencyMatrix[i], Integer.MAX_VALUE); 
        }

    }
    
    public void addNode(int index, GraphNode node) {
        nodes[index] = node;
    }

    public void addEdge(int source, int destination, int weight) {
        adjacencyMatrix[source][destination] = weight;
    }
 
    public int smallestDistance(int[] distance, boolean[] visited) {
    	int minimum = Integer.MAX_VALUE;
    	int index = 0;
    	
    	for (int i = 0; i < numVertices; i++) {
			if (distance[i] <= minimum && visited[i] == false) {
				minimum = distance[i];
				index = i;
				
			}
		}
    	return index;

    }
    
	public void dijkstra(int start){
	        boolean[] visited = new boolean[numVertices];
	        int[] distances = new int[numVertices];

	        Arrays.fill(distances, Integer.MAX_VALUE);
	        distances[start] = 0;

	        for (int i = 0; i < numVertices - 1; i++) {
	            int vertex = smallestDistance(distances, visited);
	            visited[vertex] = true;

	            for (int v = 0; v < numVertices; v++) {
	                if (!visited[v] && (adjacencyMatrix[vertex][v] != Integer.MAX_VALUE) && (distances[vertex] != Integer.MAX_VALUE) && (distances[vertex] + adjacencyMatrix[vertex][v] < distances[v])) {
	                	 distances[v] = distances[vertex] + adjacencyMatrix[vertex][v];
	                     nodes[v].setDistance(distances[v]);
	                     nodes[v].setShortestPath(new ArrayList<GraphNode>(nodes[vertex].getShortestPath()));
	                     nodes[v].getShortestPath().add(nodes[vertex]);
	                }
	            }
	        }
	        printDijkstraResults(distances) ; 
	}

    public void printDijkstraResults(int[] distances) {
        System.out.println("Vertex\tDistance from Source\tShortest Path");
        for (int i = 0; i < distances.length; i++) {
            if (distances[i] == Integer.MAX_VALUE) {
                System.out.println(nodes[i].getName() + "\t∞");
            } else {
                System.out.print(nodes[i].getName() + "\t" + distances[i] + "\t\t\t");
                for (GraphNode node : nodes[i].getShortestPath()) {
                    System.out.print(node.getName() + " > ");
                }
                System.out.println(nodes[i].getName());
            }
        }
    }
}
