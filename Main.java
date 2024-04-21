import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
public static void main(String[] args){
    Scanner scan = new Scanner(System.in);
    Reader reader = new Reader("/home/kelwin/Portfolio/University/Graphs/Graph_Indicator/graph.text");
    List<int[]> graphs = reader.readGraphs();
    
    int option;
    option = 1;
    while (option != 0) {

        System.out.println(
        "1 --> Exibir grafo\n"+
        "2 --> Quantidade de vértices pendentes\n"+
        "3 --> Quantidade de vértices isolados\n"+
        "4 --> Grau de cada vértice\n"+
        "5 --> É simples\n"+
        "6 --> É completo\n"+ 
        "7 --> É bipartido\n"+
        "8 --> É conexo\n"+
        "9 --> É isomorfo\n"+
        "10 --> O grafo é passeio, caminho, trajeto ou circuito?\n"+
        "11 --> Quantidade de vértices\n"+
        "12 --> Quantidade de arestas\n"+
        "0 --> Sair"
        );

        option = scan.nextInt();

       

        Graph G = new Graph(graphs.get(0)[0], graphs.get(0)[1]);

        switch (option) {
                case 0:
                    System.out.println("\nObrigado por usar o sistema!\n");
                    break;
                case 1:
                    System.out.print("\n");
                    G.printGraph();
                    System.out.print("\n");
                    break;
                case 2:
                    System.out.print("\n");
                    G.hasLeaningVertex();
                    System.out.print("\n");
                    break;
                case 3:
                    System.out.print("\n");
                    if (G.hasIsolateVertex()) {
                        System.out.println("\nO grafo possui vértice(s) isolados!");
                    } else {
                        System.out.println("\nO grafo não possui vértices isolados!");
                    }
                    System.out.print("\n");
                    break;
                case 4:
                    System.out.print("\n");
                    G.calcDegree();
                    System.out.print("\n");
                    break;
                case 5:
                    System.out.print("\n");
                    if (G.isSimple() == true) {
                        System.out.println("O grafo é simples !"); 
                    } else {
                        System.out.println("O grafo não é simples !");        
                    }
                    System.out.print("\n");
                    break;
                case 6:
                    System.out.print("\n");
                    if (G.isComplete() == true) {
                        System.out.println("O grafo é completo !"); 
                    } else {
                        System.out.println("O grafo não é completo !");        
                    }
                    System.out.print("\n");
                    break;
                case 7:
                    System.out.print("\n");
                    if (G.isBipartite() == true) {
                        System.out.println("\nO grafo é bipartido !"); 
                    } else {
                        System.out.println("\nO grafo não é bipartido !");        
                    }
                    System.out.print("\n");
                    break;
            case 8:
                System.out.println("\n");
                    if (G.isConnected()) {
                        System.out.println("\nO grafo é conexo !");
                    } else {
                        System.out.println("\nO grafo não é conexo !");
                    }               
                break;
            case 9:
                System.out.println("\n");
                Graph graphB = new Graph(4, 6);
                if (G.areIsomorphic(graphB)) {
                    System.out.println("\nO grafo é isomorfo !");
                } else {
                    System.out.println("\nO grafo não é isomorfo !");
                }
                break;
            case  10:
                System.out.println("\n");
                ArrayList<Integer> selectedVertex = new ArrayList<Integer>();
                int choice = 1;
                while (choice != 0) {
                    System.out.println(
                    "1 --> Adicionar vértice\n"+
                    "0 --> Sair"
                    );

                    choice = scan.nextInt();

                    switch (choice) {
                        case 0:
                            System.out.println("\nSaindo...\n");
                        break;

                        case 1:
                            System.out.println("Digite um vértice:");
                            int value = scan.nextInt();
                            selectedVertex.add(value);
                            System.out.println(selectedVertex);
                        break;
                    }

                    System.out.println(G.type(arrayListToIntArray(selectedVertex)));
                }
                System.out.println("\n");
            case 11:
                System.out.println("\n");
                System.out.println("O Grafo possui "+G.getVertex()+" vertices");
                System.out.println("\n");
                break;
            case 12:
                System.out.println("\n");
                System.out.println("O Grafo possui "+G.getEdge()+" arestas");
                System.out.println("\n");
                break;
            default:
                System.out.println("\nOpção não existente!\n");
                break;
        }
    }
      
    scan.close();
}
    public static int[] arrayListToIntArray(ArrayList<Integer> arrayList) {
            int[] intArray = new int[arrayList.size()];

            for (int i = 0; i < arrayList.size(); i++) {
                intArray[i] = arrayList.get(i);
            }

            return intArray;
    }
}