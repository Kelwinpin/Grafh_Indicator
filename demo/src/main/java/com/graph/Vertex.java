package com.graph;




import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Vertex {
    private ArrayList<Integer> connections = new ArrayList<Integer>();
    private ArrayList<ArrayList<Integer>> previousConnections = new ArrayList<ArrayList<Integer>>();

     public Vertex(int graph[][]) {
        this.defineConnections(graph);
    }

    public void defineConnections(int graph[][]) {
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                connections.add(graph[i][j]);
            }
            previousConnections.add(new ArrayList<>(connections));
            System.out.println((i + 1)  + " " + previousConnections.get(i));
            connections.clear();
        }
    }

    public boolean isConnected() {
        int n = previousConnections.size();
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();

        queue.add(0);
        visited[0] = true;

        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();
            for (int neighbor = 0; neighbor < n; neighbor++) {
                if (previousConnections.get(currentVertex).get(neighbor) > 0 && !visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }

        for (boolean v : visited) {
            if (!v) {
                return false;
            }
        }
        return true;
    }

        public static void main(String[] args) {
        int[][] graph = {
                {0, 1, 0, 0},
                {1, 0, 0, 0},
                {0, 0, 0, 1},
                {0, 0, 1, 0}
        };

        Vertex vertex = new Vertex(graph);
        boolean isConnected = vertex.isConnected();
        System.out.println("O grafo é conexo? " + isConnected);
    }

}
