import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Graph {
    private int vertex;
    private int edge; 
    private int[][] graph;
    private Random random = new Random();

    public Graph(int vertex, int edge) {
        this.vertex = vertex;
        this.edge = edge;
        this.graph = new int[vertex][vertex];
        initializeGraph();
    }

    public int getEdge() {
        return edge;
    }

    public int getVertex() {
        return vertex;
    }

    public void initializeGraph(){
        int totalEdge = this.edge;
        int maxZero = this.vertex;

        for (int i = 0; i < this.vertex; i++) {
            for (int j = 0; j < this.vertex; j++) {
                    if (totalEdge == 0 && maxZero == 0) {
                        this.graph[i][j] = 0;
                        this.graph[j][i] = 0;
                    } else {
                        if (this.graph[i][j] == 0 && totalEdge != 0) {
                            this.graph[i][j] = random.nextInt(5);
                            this.graph[j][i] = this.graph[i][j];
                            if (this.graph[i][j] > 0) {
                                totalEdge--;                            
                            } else {
                                maxZero--;
                            }
                        }
                    }
            }                
        }
    }

    public void printGraph(){
        for (int i = 0; i < graph.length; i++) {
            System.out.print("V"+(i + 1)+" ");
            for (int j = 0; j < graph.length; j++) {
                System.out.print(this.graph[i][j]+" ");
            }
            System.out.print("\n");
        }
    }

    public void hasLeaningVertex(){
        int leaningVertex = 0;
        int leaningAutoLoopVertex = 0;
        printGraph();
        System.out.println("\n");

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if (i == j && this.graph[i][j] == 1) {
                  System.out.print("["+i+","+j+"] ");
                  leaningAutoLoopVertex++;  
                } else if (this.graph[i][j] == 1) {
                    System.out.print("["+i+","+j+"] ");
                    leaningVertex++;
                }
            }
        }

        int sumLeaningVertex = leaningAutoLoopVertex + (leaningVertex / 2);

        if (sumLeaningVertex > 0) {
            System.out.println("\nO grafo possui "+sumLeaningVertex+" vértices pendentes!");
        } else {
            System.out.println("\nO grafo não possui vértices pendentes!");
        }
    }

    public boolean hasIsolateVertex(){
        int isolateVertex = 0;
        printGraph();
        System.out.println("\n");

        for (int i = 0; i < this.graph.length; i++) {
            int countUnlinks = 0;

            for (int j = 0; j < this.graph.length; j++) {
                if(this.graph[i][j] == 0) {
                    countUnlinks++;
                }
            }

            if (countUnlinks == this.vertex) {
                System.out.print("["+(i + 1)+"] ");
                isolateVertex++;
            }
        }

        if (isolateVertex > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isSimple(){
         for (int i = 0; i < this.graph.length; i++) {
            if (this.graph[i][i] > 0) {
                return false;
            }
        }
        return true;
    }

     public boolean isComplete(){
        int n = (this.vertex*(this.vertex - 1) / 2);

        if (this.isSimple() == true && n == this.edge) {        
            return true;
        } else {
            return false;
        }
    }

    public boolean isBipartite(){
        ArrayList<Integer> par = new ArrayList<>();
        ArrayList<Integer> impar = new ArrayList<>();

        for (int i = 0; i < this.vertex; i++) {
            if ((i + 1) % 2 == 0) {
                par.add(i);
            } else {
               impar.add(i);
            }
        };
        System.out.println("Impar");
        for (int i = 0; i <= impar.size() - 1; i++) {
            System.out.print((impar.get(i) + 1) + " ");
        }

        System.out.println("\nPar");
        for (int i = 0; i <= par.size() - 1; i++) {
            System.out.print((par.get(i) + 1) + " ");
        }
        if (par.size() > 0 && impar.size() > 0) {
            for (int i = 0; i < par.size(); i++) {
                for (int j = 0; j < par.size(); j++) {
                    if (this.graph[par.get(i)][par.get(i)] > 0) {
                        return false;
                    }
                }
                for (int j = 0; j < impar.size(); j++) {
                    if (this.graph[par.get(i)][impar.get(j)] == 0) {
                        return false;
                    }
                }
            }

            for (int i = 0; i < impar.size(); i++) {
                for (int j = 0; j < impar.size(); j++) {
                    if (this.graph[impar.get(i)][impar.get(j)] > 0) {
                        return false;
                    }
                }

                for (int j = 0; j < par.size(); j++) {
                    if (this.graph[impar.get(i)][par.get(j)] == 0) {
                        return false;
                    }
                }
            }
        
            return true;
        } else {
            return false;
        }
    }

    public boolean isConnected() {
        if(hasIsolateVertex()){                        
            return false;
        } else {
            int v[]= new int[2];
            int countConnections = 0;

            for (int i = 0; i < this.vertex - 1; i++) {
                int v2 = i;                    
                v[0] = v2;
                for (int j = 0; j < this.vertex - 1; j++) {
                    int v3 = j + 1;
                    v[1] = v3;

                    if(hasConnection(v)){
                        countConnections++;
                    }
                }  
            }

            if (countConnections >= this.vertex - 1) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean hasConnection (int v[]) {
        int step = 0;       
        if (this.graph[v[0]][v[1]] > 0) {
            step++;
        }
        
        if (step == 1) { 
            return true;
        } else {
            return false;
        }
    }

    public boolean isIsomorphic(Graph graphB) {
        ArrayList<Integer> degree = new ArrayList<>();
        Set<Integer> degreeSet = new HashSet<>();
        Set<Integer> degreeGraphBSet = new HashSet<>();


        if (this.vertex == graphB.vertex && this.edge == graphB.edge) {
            for (int i = 0; i < graph.length; i++) {
                for (int j = 0; j < graph.length; j++) {
                    if(graph[i][j] != 0){
                        degreeSet.add(graph[i][j]);
                        degree.add(graph[i][j]);
                    }
                }
            }
            for (int i = 0; i < graph.length; i++) {
                for (int j = 0; j < graph.length; j++) {
                    if(graphB.graph[i][j] != 0){
                        degreeGraphBSet.add(graphB.graph[i][j]);
                        degree.add(graphB.graph[i][j]);
                    }
                }
            }

            this.printGraph();
            System.out.print("\n");
            graphB.printGraph();
            System.out.print("\n");

            System.out.println("Graus no grafo A:" + degreeSet);
            System.out.print("\n");
            System.out.println("Graus no grafo B:" + degreeGraphBSet);
            System.out.print("\n");

            if (!degreeSet.equals(degreeGraphBSet)) {
                return false;
            }

            for (int i = 1; i < 5; i++) {
              System.out.println(i+" - "+countElement(degree, i)); 
               if (countElement(degree, i) % 2 != 0) {
                return false;
               } 
            }
            
            return true;
        } else {
            return false;
        }
    }

    public static int countElement(ArrayList<Integer> array, int element) {
        int count = 0;
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) == element) {
                count++;
            }
        }
        return count;
    }
};