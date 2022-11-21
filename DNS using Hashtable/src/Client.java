import java.util.Scanner;

public class Client {
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        int choice = 1;
        String searchedUrl = ""; 
        Server server = new Server(); // abre o servidor;

        System.out.println("========= NAVEGADOR =========");

        while (choice != 0) {
            System.out.print("Digite uma URL: ");
            searchedUrl = sc.next();

            server.request(searchedUrl);

            System.out.println("Deseja pesquisar novamente? (sim [1] | não [0])");
            choice = sc.nextInt();
        }
    }
}
