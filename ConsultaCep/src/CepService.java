import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;


class CepService {
    public static String consultarCep(String cep) {
        try {
            URL url = new URL("https://viacep.com.br/ws/" + cep + "/json/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            if (conn.getResponseCode() == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                return response.toString();
            } else {
                System.out.println("Erro ao consultar o CEP.");
            }
        } catch (IOException e) {
            System.out.println("Erro ao conectar à API: " + e.getMessage());
        }
        return null;
    }
}