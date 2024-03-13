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

};