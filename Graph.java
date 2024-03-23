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
        int par[] = new int[this.vertex];
        int impar[] = new int[this.vertex];
        int indexPar = 0;
        int indexImpar = 0;

        for (int i = 0; i < this.vertex; i++) {
            if ((i + 1) % 2 == 0) {
                par[indexPar] = i;
                indexPar++;
            } else {
                impar[indexImpar] = i;
                indexImpar++;
            }
        };
        System.out.println("Impar");
        for (int i = 0; i <= impar.length - 1; i++) {
            System.out.print((impar[i] + 1) + " ");
        }

        System.out.println("\nPar");
        for (int i = 0; i <= par.length - 1; i++) {
            System.out.print((par[i] + 1) + " ");
        }
        if (par.length > 0 && impar.length > 0) {
            for (int i = 0; i < par.length; i++) {
                for (int j = 0; j < par.length; j++) {
                    if (this.graph[par[i]][par[j]] > 0) {
                        return false;
                    }
                }
                for (int j = 0; j < impar.length; j++) {
                    if (this.graph[par[i]][impar[j]] == 0) {
                        return false;
                    }
                }
            }

            for (int i = 0; i < impar.length; i++) {
                for (int j = 0; j < impar.length; j++) {
                    if (this.graph[impar[i]][impar[j]] > 0) {
                        return false;
                    }
                }

                for (int j = 0; j < par.length; j++) {
                    if (this.graph[impar[i]][par[j]] == 0) {
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

                    if(isPath(v)){
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

    public boolean isPath (int v[]) {
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
};