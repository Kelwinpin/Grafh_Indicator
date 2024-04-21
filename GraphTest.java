public class GraphTest {

    public static void main(String[] args) {
        testHasLeaningVertex();
        testHasIsolateVertex();
        testCalcDegree();
        testIsSimple();
        testIsComplete();
        testIsBipartite();
        testIsConnected();
        testAreIsomorphic();
        testType();
    }

    public static void testHasLeaningVertex() {
        Graph graph = new Graph(5, 6);
        graph.printGraph();
        graph.hasLeaningVertex();
        System.out.println("\n");
    }

    public static void testHasIsolateVertex() {
        Graph graph = new Graph(5, 6);
        graph.printGraph();
        System.out.println("Has Isolate Vertex: " + graph.hasIsolateVertex());
        System.out.println("\n");
    }

    public static void testCalcDegree() {
        Graph graph = new Graph(5, 6);
        graph.printGraph();
        graph.calcDegree();
        System.out.println("\n");
    }

    public static void testIsSimple() {
        Graph graph = new Graph(5, 6);
        graph.printGraph();
        System.out.println("Is Simple: " + graph.isSimple());
        System.out.println("\n");
    }

    public static void testIsComplete() {
        Graph graph = new Graph(5, 6);
        graph.printGraph();
        System.out.println("Is Complete: " + graph.isComplete());
        System.out.println("\n");
    }

    public static void testIsBipartite() {
        Graph graph = new Graph(2, 1);
        graph.printGraph();
        System.out.println("Is Bipartite: " + graph.isBipartite());
        System.out.println("\n");
    }

    public static void testIsConnected() {
        Graph graph = new Graph(9, 10);
        graph.printGraph();
        System.out.println("Is Connected: " + graph.isConnected());
        System.out.println("\n");
    }

    public static void testAreIsomorphic() {
        Graph graphA = new Graph(5, 6);
        Graph graphB = new Graph(5, 6);
        System.out.println("Are Isomorphic: " + graphA.areIsomorphic(graphB));
        System.out.println("\n");
    }

    public static void testType() {
        Graph graph = new Graph(5, 6);
        int[] selectedVertices = {1, 2, 3, 4, 1};
        System.out.println("Type: " + graph.type(selectedVertices));
        System.out.println("\n");
    }
}
