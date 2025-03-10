import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class LogService {
    private static final String LOG_FILE = "log_ceps.txt";

    public static void registrarLog(String cep, String endereco) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            writer.write(timestamp + " - " + cep + " - " + endereco + "\n");
        } catch (IOException e) {
            System.out.println("Erro ao gravar no log: " + e.getMessage());
        }
    }

    public static void listarLogs() {
        File file = new File(LOG_FILE);
        if (!file.exists()) {
            System.out.println("Nenhum log encontrado.");
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE))) {
            String line;
            boolean hasLogs = false;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                hasLogs = true;
            }
            if (!hasLogs) {
                System.out.println("Nenhum CEP consultado ainda.");
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o log: " + e.getMessage());
        }
    }
}