package com.graph;




import java.util.ArrayList;

public class Vertex {
    private int numVertex;
    private ArrayList<Integer> connections = new ArrayList<Integer>();
    private ArrayList<ArrayList<Integer>> previousConnections = new ArrayList<ArrayList<Integer>>();

     public Vertex(int numVertex, int graph[][]) {
        this.numVertex = numVertex;
        this.defineConnections(graph);
    }

    public void defineConnections ( int graph[][]) {
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                connections.add(j ,graph[i][j]);
            }
            previousConnections.add(connections);
            System.out.println((i + 1)  + " " + previousConnections.get(i));

        }
        System.out.println(previousConnections);
    }

}
