package com.example.chatbot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class MyChatbot implements CommandLineRunner { // Alterei o nome da classe para MyChatbot

    private static final String API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:streamGenerateContent?alt=sse&key=AIzaSyAb2aHdq1GHkZwF0lmfJTA_Xp9YZ7b8YxQ"; // Substitua SUA_API_KEY
    private static final Pattern RESPOSTA_PATTERN = Pattern.compile("\"text\"\\s*:\\s*\"([^\"]+)\"");
    private static final String LOG_FILE = "chat_log.txt";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);

        try (FileWriter logWriter = new FileWriter(LOG_FILE, true)) {
            while (true) {
                System.out.print("Você: ");
                String pergunta = scanner.nextLine();

                if ("fatec_sair".equalsIgnoreCase(pergunta)) {
                    System.out.println("Encerrando chatbot...");
                    logMessage(logWriter, "Sistema", "Chatbot encerrado.", true);
                    break;
                }

                try {
                    String resposta = obterRespostaDaAPI(pergunta);
                    System.out.println("Gemini: " + resposta);

                    // Registrar a conversa no log
                    logMessage(logWriter, "User", pergunta, true);
                    logMessage(logWriter, "Gemini", resposta, true);


                } catch (IOException | InterruptedException e) {
                    System.err.println("Erro ao acessar a API: " + e.getMessage());
                    logMessage(logWriter, "Sistema", "Erro ao acessar a API: " + e.getMessage(), true);
                }
            }
        } catch (IOException e) { // Corrigi a indentação e coloquei o catch dentro do método run
            System.err.println("Erro ao criar/abrir arquivo de log: " + e.getMessage());
        }

    }

    private String obterRespostaDaAPI(String pergunta) throws IOException, InterruptedException {
        String jsonRequest = gerarJsonRequest(pergunta);
        String respostaJson = enviarRequisicao(jsonRequest);
        return extrairResposta(respostaJson);
    }

    private String gerarJsonRequest(String pergunta) {
        return "{\"contents\":[{\"parts\":[{\"text\":\"" + pergunta + "\"}]}]}"; // Melhoria na formatação do JSON
    }

    private String enviarRequisicao(String jsonRequest) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Erro na requisição à API. Status code: " + response.statusCode() + ", Body: " + response.body());
        }


        return response.body();
    }

    private String extrairResposta(String respostaJson) {
        StringBuilder resposta = new StringBuilder();
        String[] lines = respostaJson.split("data: ");  // Dividir a string por "data: "

        for (String linha : lines) {
            if (linha.trim().isEmpty()) {
                continue; // Ignorar linhas vazias
            }

            Matcher matcher = RESPOSTA_PATTERN.matcher(linha);
            while (matcher.find()) {  // Usar while para encontrar todas as ocorrências
                resposta.append(matcher.group(1)).append(" ");
            }
        }

        return resposta.toString().trim();
    }

    private void logMessage(FileWriter logWriter, String author, String message, boolean newline) throws IOException {
        String timestamp = LocalDateTime.now().format(DATE_TIME_FORMATTER);
        String logEntry = timestamp + " | " + author + ": " + message;

        logWriter.write(logEntry);
        if (newline) {
            logWriter.write(System.lineSeparator());
        }
        logWriter.flush();
    }


}