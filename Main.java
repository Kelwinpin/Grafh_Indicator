import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Graph grafo = new Graph(5, 10);

        int option = 99; 
        while (option != 0) {

            System.out.println("1 --> Exibir grafo\n0 --> Sair");
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
                default:
                    System.out.println("\nOpção não existente!\n");
                    break;
            }
        }
      
        scan.close();
    }
}
