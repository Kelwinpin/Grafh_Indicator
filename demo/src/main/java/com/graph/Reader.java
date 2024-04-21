package com.graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    private String filename;

    public Reader(String filename) {
        this.filename = filename;
    }

    public List<int[]> readGraphs() {
        List<int[]> graphs = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int[] graphInfo = parseGraph(line);
                graphs.add(graphInfo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return graphs;
    }

    private int[] parseGraph(String line) {
        String[] parts = line.substring(2, line.length() - 1).split(", ");
        int[] graphInfo = new int[2];
        graphInfo[0] = Integer.parseInt(parts[0]);
        graphInfo[1] = Integer.parseInt(parts[1]);
        return graphInfo;
    }

    public static void main(String[] args) {
        Reader reader = new Reader("graphs.txt");
        List<int[]> graphs = reader.readGraphs();

        System.out.println("Graphs read from file:");
        for (int[] graph : graphs) {
            System.out.println("Vertices: " + graph[0] + ", Edges: " + graph[1]);
        }
    }
}

