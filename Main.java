import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Entre com o número de arestas:");
        int edge = scan.nextInt();

        System.out.println("Entre com o número de vertices:");
        int vertex = scan.nextInt();

        Graph grafo = new Graph(vertex, edge);
        grafo.getEdge();

        int option = 0; 
        while (option != 0) {
            System.out.println("Digite uma opção: ");
            option = scan.nextInt(); 
            switch (option) {
                case 1:
                    System.out.println("Selecionou 1");
                    break;

                case 2:
                    break;
            }
        }
      
        scan.close();
    }
}
