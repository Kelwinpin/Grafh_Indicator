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
        for (int i = 0; i < graph.length; i++) {
            System.out.print("V"+(i+1)+" ");
            for (int j = 0; j < graph.length; j++) {
                if (j < this.vertex - i || i == 0) {
                    if (j == 0 || totalEdge == 0) {
                        this.graph[i][j] = 0;
                        System.out.print(graph[i][j]+" ");
                    } else {
                        this.graph[i][j] = random.nextInt(1, 5);
                        System.out.print(graph[i][j]+" ");
                        totalEdge--;
                    }
                }
                
            }                
            System.out.print("\n");
        }
    }

};