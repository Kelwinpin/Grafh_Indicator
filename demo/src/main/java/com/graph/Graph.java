package com.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

public class Graph {
    private int vertex;
    private int edge;
    private ArrayList<ArrayList<Integer>> graph;
    private Random random = new Random();

    public Graph(int vertex, int edge) {
        this.vertex = vertex;
        this.edge = edge;
        this.graph = new ArrayList<>(vertex);
           for (int i = 0; i < vertex; i++) {
            ArrayList<Integer> row = new ArrayList<>(vertex);
            for (int j = 0; j < vertex; j++) {
                row.add(0);
            }
            graph.add(row);
        }
        initializeGraph();
    }


    public int getVertex() {
        return vertex;
    }

    public int getEdge() {
        return edge;
    }

    private void initializeGraph() {
        int totalEdge = edge;

        for (int i = 0; i < vertex; i++) {
            for (int j = i; j < vertex; j++) {
                if (totalEdge == 0) {
                    break;
                }

                int weight = random.nextInt(10);

                if (weight != 0) {
                    graph.get(i).set(j, 1);
                    graph.get(j).set(i, 1);
                    totalEdge--;
                }
            }
        }
    }

    public void printGraph() {
        for (int i = 0; i < graph.size(); i++) {
            System.out.print("V" + (i + 1) + " ");
            for (int j = 0; j < graph.get(i).size(); j++) {
                System.out.print(graph.get(i).get(j) + " ");
            }
            System.out.print("\n");
        }
    }

    public void hasLeaningVertex() {
        ArrayList<Integer> leaningVertex = new ArrayList<Integer>();

         for (int i = 0; i < graph.size(); i++) {
            int countEdge = 0;
            for (int j = 0; j < graph.get(i).size(); j++) {
                if (graph.get(i).get(j) > 0) {
                    countEdge++;
                }
            }
            if (countEdge == 1) {
                leaningVertex.add(i);
            }
        }

        System.out.println(leaningVertex+", o grafo possui " + leaningVertex.size() + " v pendente(s)");
    }
    public boolean hasIsolateVertex() {
        for (int i = 0; i < graph.size(); i++) {
            int countUnlinks = 0;

            for (int j = 0; j < graph.get(i).size(); j++) {
                if (graph.get(i).get(j) == 0) {
                    countUnlinks++;
                }
            }

            if (countUnlinks == vertex) {
                System.out.print("[" + (i + 1) + "] ");
                return true;
            }
        }
        return false;
    }
    public ArrayList<Integer> calcDegree() {
        ArrayList<Integer> degree = new ArrayList<Integer>();
        for (int i = 0; i < graph.size(); i++) {
            int grau = 0;
            for (int j = 0; j < graph.get(i).size(); j++) {
                if (graph.get(i).get(j) > 0) {
                    grau++;
                }
            }
            degree.add(grau);
        }
        for (int i = 0; i <  graph.size(); i++) {
            System.out.println("V: [" + i + "] | Grau - " + degree.get(i));
        }

        return degree;

    }

    public boolean isSimple() {
        for (int i = 0; i < graph.size(); i++) {
            if (graph.get(i).get(i) > 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isComplete() {
        int maxEdges = (vertex * (vertex - 1)) / 2;
        return isSimple() && edge == maxEdges;
    }

    public boolean isBipartite() {
        int[] colors = new int[vertex];
        for (int i = 0; i < vertex; i++) {
            colors[i] = -1; 
        }

        for (int i = 0; i < vertex; i++) {
            if (colors[i] == -1) { 
                colors[i] = 0;
                Queue<Integer> queue = new LinkedList<>();
                queue.offer(i); 

                while (!queue.isEmpty()) {
                    int current = queue.poll();
                    for (int j = 0; j < vertex; j++) {
                        if (graph.get(current).get(j) > 0) { 
                            if (colors[j] == -1) { 
                                colors[j] = 1 - colors[current]; 
                                queue.offer(j);
                            } else if (colors[j] == colors[current]) { 
                                return false; 
                            }
                        }
                    }
                }
            }
        }
        return true; 
    }

    public boolean isConnected() {
        boolean[] checked = new boolean[graph.size()]; 
        Stack<Integer> stack = new Stack<>(); 

        stack.push(0);
        checked[0] = true;

        while (!stack.isEmpty()) {
            int i = stack.pop();

            for (int n = 0; n < graph.get(i).size(); n++) {
                if (graph.get(i).get(n) > 0 && !checked[n]) {
                    stack.push(n);
                    checked[n] = true;
                }
            }
        }

        for (boolean v : checked) {
            if (!v) {
                return false;
            }
        }

        return true; 
    }
    public boolean areIsomorphic(Graph graphB) {
        if (this.getVertex() != graphB.getVertex() || this.getEdge() != graphB.getEdge()) {
            System.out.println("Quantidade de vertices no grafo 1: " + this.getVertex() + "Quantidade de vertices no grafo 2: " + graphB.getVertex());
            System.out.println("Quantidade de arestas no grafo 1: " + this.getVertex() + "Quantidade de arestas no grafo 2: " + graphB.getVertex());

            return false;
        }

        System.out.print("Graus grafo 1:\n");
        ArrayList<Integer> grausGrafoA = calcDegree();
        System.out.print("Graus grafo 2:\n");
        ArrayList<Integer> grausGrafoB = graphB.calcDegree();

        Set<Integer> grausSetA = new HashSet<>(grausGrafoA);
        Set<Integer> grausSetB = new HashSet<>(grausGrafoB);
        if (!grausSetA.equals(grausSetB)) {
            return false;
        }

        return true;
    }

     public String type(ArrayList<Integer> selectedVertexs) {
        int nextVertex = selectedVertexs.size();
        System.out.println(selectedVertexs);
        if (selectedVertexs.get(0).equals(selectedVertexs.get(nextVertex - 1))) {
            boolean distintVertex = true;
            for (int i = 0; i < nextVertex - 1; i++) {
                if (selectedVertexs.get(i).equals(selectedVertexs.get(i + 1))) {
                    distintVertex = false;
                    break;
                }
            }
            if (distintVertex) {
                boolean edgeDistint = true;
                boolean[] checkedEdge = new boolean[graph.size()];
                for (int i = 0; i < nextVertex - 1; i++) {
                    int origin = selectedVertexs.get(i);
                    int destiny = selectedVertexs.get(i + 1);
                    if (graph.get(origin).get(destiny) == 0 || checkedEdge[graph.get(origin).get(destiny)]) {
                        edgeDistint = false;
                        break;
                    }
                    checkedEdge[graph.get(origin).get(destiny)] = true;
                }
                if (edgeDistint) {
                    return "circuito";
                } else {
                    return "trajeto";
                }
            }
        }

        boolean walk = true;
        for (int i = 0; i < nextVertex - 1; i++) {
            int origin = selectedVertexs.get(i);
            int destiny = selectedVertexs.get(i + 1);
            if (graph.get(origin).get(destiny) == 0) {
                walk = false;
                break;
            }
        }
        if (walk) {
            return "passeio";
        }

        return "nenhum";
    }

}