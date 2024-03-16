import java.util.Random;

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

    public void hasIsolateVertex(){
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
            System.out.println("\nO grafo possui "+isolateVertex+" vértices isolados!");
        } else {
            System.out.println("\nO grafo não possui vértices isolados!");
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
        int numCount = 0;

        if (this.isSimple() == true) {
            for (int i = 0; i < this.graph.length; i++) {
                int completeEdge = 0;
                for (int j = 0; j < this.graph.length; j++) {
                    if (this.graph[i][j] > 0 && this.graph[j][i] > 0) {
                        completeEdge++;
                    }
                }
                if (completeEdge / 2 == this.vertex - 1) {
                    numCount++;
                }   
            }

            if (numCount == (this.vertex - 1)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

};