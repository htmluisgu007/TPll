import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (Connection connection = DB.connect()) {
            // Conexão bem-sucedida
            System.out.println("Digite c 'c' para consulta e i 'i' para inserir novos usuários: ");
            String option = scanner.nextLine();

            switch (option) {
                case "c":
                    // Consulta
                    List<Aluno> alunos = new ArrayList<>();
                    String query = "SELECT cpf, nome FROM aluno";
                    try (PreparedStatement stmt = connection.prepareStatement(query)) {
                        ResultSet rs = stmt.executeQuery();
                        while (rs.next()) {
                            Aluno aluno = new Aluno(rs.getString("cpf"), rs.getString("nome"));
                            alunos.add(aluno);
                        }
                    }
                    alunos.forEach(System.out::println); // Exibe os alunos
                    break;

                case "i":
                    // Inserção
                    System.out.println("Digite o nome e CPF do aluno:");
                    String name = scanner.nextLine();
                    String cpf = scanner.nextLine();

                    // Inserir no banco de dados
                    String insertQuery = "INSERT INTO aluno(cpf, nome) VALUES (?, ?)";
                    try (PreparedStatement stmt = connection.prepareStatement(insertQuery)) {
                        stmt.setString(1, cpf);
                        stmt.setString(2, name);
                        int rowsAffected = stmt.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.println("Aluno inserido com sucesso!");
                        } else {
                            System.out.println("Falha ao inserir o aluno.");
                        }
                    }
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        } catch (SQLException e) {
            System.out.println("Erro de conexão: " + e.getMessage());
        } finally {
            scanner.close(); // Fechar o scanner ao final
        }
    }
}
