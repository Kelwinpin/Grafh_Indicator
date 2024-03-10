import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Graph grafo = new Graph(5, 10);

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
