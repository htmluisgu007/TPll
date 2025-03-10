import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Consultar Cep");
            System.out.println("2. Listar Ceps Consultados");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    System.out.print("Digite o CEP: ");
                    String cep = scanner.nextLine().trim();
                    if (cep.matches("\\d{8}")) {
                        String endereco = CepService.consultarCep(cep);
                        if (endereco != null) {
                            System.out.println(endereco);
                            LogService.registrarLog(cep, endereco);
                        }
                    } else {
                        System.out.println("CEP inválido. Insira um CEP com 8 dígitos numéricos.");
                    }
                    break;
                case "2":
                    LogService.listarLogs();
                    break;
                case "3":
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }
}