import java.util.Scanner;

public class Main {
    public static void main(String[] args) { 
        Scanner scan = new Scanner(System.in);

        Graph grafo = new Graph(4, 4);

        int option;

        option = 1;

        while (option != 0) {

            System.out.println(
            "1 --> Exibir grafo\n"+
            "2 --> Quantidade de vértices pendentes\n"+
            "3 --> Quantidade de vértices isolados\n"+
            "4 --> É simples\n"+
            "5 --> É completo\n"+ 
            "6 --> É bipartido\n"+
            "7 --> É conexo\n"+
            "0 --> Sair"
            );

            option = scan.nextInt(); 

            switch (option) {
                case 0:
                    System.out.println("\nObrigado por usar o sistema!\n");
                    break;
                case 1:
                    System.out.print("\n");
                    grafo.printGraph();
                    System.out.print("\n");
                    break;
                case 2:
                    System.out.print("\n");
                    grafo.hasLeaningVertex();
                    System.out.print("\n");
                    break;
                case 3:
                    System.out.print("\n");
                    if (grafo.hasIsolateVertex()) {
                        System.out.println("\nO grafo possui vértice(s) isolados!");
                    } else {
                        System.out.println("\nO grafo não possui vértices isolados!");
                    }
                    System.out.print("\n");
                    break;
                case 4:
                    System.out.print("\n");
                    if (grafo.isSimple() == true) {
                        System.out.println("O grafo é simples !"); 
                    } else {
                        System.out.println("O grafo não é simples !");        
                    }
                    System.out.print("\n");
                    break;
                case 5:
                    System.out.print("\n");
                    if (grafo.isComplete() == true) {
                        System.out.println("O grafo é completo !"); 
                    } else {
                        System.out.println("O grafo não é completo !");        
                    }
                    System.out.print("\n");
                    break;
                case 6:
                    System.out.print("\n");
                    if (grafo.isBipartite() == true) {
                        System.out.println("\nO grafo é bipartido !"); 
                    } else {
                        System.out.println("\nO grafo não é bipartido !");        
                    }
                    System.out.print("\n");
                    break;
                case 7:
                    System.out.println("\n");
                    if (grafo.isConnected()) {
                        System.out.println("\nO grafo é conexo !");
                    } else {
                        System.out.println("\nO grafo não é conexo !");
                    }
                    break;
                default:
                    System.out.println("\nOpção não existente!\n");
                    break;
            }
        }
      
        scan.close();
    }
}
