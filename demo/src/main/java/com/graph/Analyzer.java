package com.graph;




import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Analyzer {
    private ArrayList<Integer> connections = new ArrayList<Integer>();
    private ArrayList<ArrayList<Integer>> previousConnections = new ArrayList<ArrayList<Integer>>();

    public Analyzer(int graph[][]) {
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

    public boolean isPath(ArrayList<Integer> vertices) {
        for (int i = 0; i < vertices.size() - 1; i++) {
            int currentVertex = vertices.get(i);
            for (int j = i + 1; j < vertices.size(); j++) {
                if (currentVertex == vertices.get(j)) {
                    return false;
                }
            }
        }
        for (int i = 0; i < vertices.size() - 1; i++) {
            int currentVertex = vertices.get(i);
            int nextVertex = vertices.get(i + 1);
            if (previousConnections.get(currentVertex).get(nextVertex) == 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isCycle(ArrayList<Integer> vertices) {
        return isPath(vertices) && vertices.get(0).equals(vertices.get(vertices.size() - 1));
    }

    public boolean isWalk(ArrayList<Integer> vertices) {
        for (int i = 0; i < vertices.size() - 1; i++) {
            int currentVertex = vertices.get(i);
            int nextVertex = vertices.get(i + 1);
            if (previousConnections.get(currentVertex).get(nextVertex) == 0) {
                return false;
            }
        }
        return true;
    }

    public String analyzeVertices(ArrayList<Integer> vertices) {
        boolean isPath = isPath(vertices);
        boolean isCycle = isCycle(vertices);
        boolean isWalk = isWalk(vertices);

        if (isPath && isCycle) {
            return "Circuito";
        } else if (isPath && !isCycle) {
            return "Caminho";
        } else if (!isPath && !isCycle && isWalk) {
            return "Trajeto";
        } else {
            return "Passeio";
        }
    }

        public static void main(String[] args) {
        int[][] graph = {
                {0, 1, 0, 0},
                {1, 0, 0, 0},
                {0, 0, 0, 1},
                {0, 0, 1, 0}
        };

        Analyzer vertex = new Analyzer(graph);
        boolean isConnected = vertex.isConnected();
        System.out.println("O grafo é conexo? " + isConnected);

        int[][] graph2 = {{0, 1, 1, 0}, {1, 0, 0, 1}, {1, 0, 0, 1}, {0, 1, 1, 0}};
        Analyzer vertex2 = new Analyzer(graph2);

        ArrayList<Integer> vertices1 = new ArrayList<>();
        vertices1.add(0);
        vertices1.add(1);
        vertices1.add(3);
        vertices1.add(2);
        vertices1.add(0);

        ArrayList<Integer> vertices2 = new ArrayList<>();
        vertices2.add(0);
        vertices2.add(1);
        vertices2.add(2);
        vertices2.add(3);

        System.out.println("Vertices 1: " + vertex2.analyzeVertices(vertices1)); // Deve imprimir "Circuito"
        System.out.println("Vertices 2: " + vertex2.analyzeVertices(vertices2));
    }

}
