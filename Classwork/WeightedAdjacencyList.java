import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class WeightedAdjacencyList<T> {
    private Map<T, List<Edge<T>>> adjList = new HashMap<>();

    public static class Edge<T> {
        public T target;
        public double weight;

        public Edge(T target, double weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    public void addVertex(T vertex) {
        adjList.putIfAbsent(vertex, new ArrayList<>());
    }

    public void addEdge(T source, T destination, double weight) {
        addVertex(source);
        addVertex(destination);
        adjList.get(source).add(new Edge<>(destination, weight));
    }

    public void addUndirectedEdge(T v1, T v2, double weight) {
        addEdge(v1, v2, weight);
        addEdge(v2, v1, weight);
    }

    public List<Edge<T>> getNeighbors(T vertex) {
        return adjList.getOrDefault(vertex, Collections.emptyList());
    }

    public Map<T, Double> DijkstrasAlgorithm(T source) {
        Map<T, Double> distances = new HashMap<>();

        PriorityQueue<Map.Entry<T, Double>> pq = new PriorityQueue<>(Map.Entry.comparingByValue());

        for (T vertex : adjList.keySet()) {
            distances.put(vertex, Double.POSITIVE_INFINITY); // set all to inf
        }
        distances.put(source, 0.0); // source to 0

        pq.add(new AbstractMap.SimpleEntry<>(source, 0.0));

        while (!pq.isEmpty()) {
            T current = pq.poll().getKey();

            for (Edge<T> edge : getNeighbors(current)) {
                double newDist = distances.get(current) + edge.weight;

                if (newDist < distances.get(edge.target)) {
                    distances.put(edge.target, newDist);
                    pq.add(new AbstractMap.SimpleEntry<>(edge.target, newDist));
                } 
            }
        }

        return distances;
    }
}


/*
Djikstras Algo Pseudocode
Step 1. Init; start by setting the shortest distance to the source vertex as 0 and to all other vertices as infinity. Set the source as the current vertex.
Step 2. Relaxation: For the current vertex, consider all of its unvisited neighbors and calculate the tentative distances through the current vertex. Compare the newly calculated tentative distance to the current assigned value and assign the smaller one.
Step 3. Updating; Once we have considered all unvisited neighbors of the current vertex, mark the current vertex as visited. a visitied vertex will not be checked again.
Step 4. Selection: Select the unvisited vertex with the smallest tentative distance set during the relaxation process, and set it as the new 'current vertex'
Step 5. Termination: Repeat steps 2 and 4 until all vertices have been visited

*/

