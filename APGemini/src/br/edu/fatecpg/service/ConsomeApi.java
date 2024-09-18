package br.edu.fatecpg.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsomeApi {
    private static final String API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash-latest:generateContent?key=AIzaSyAzQ_82G5HaebXH7bVw584xHFe4l0IEHQ4";
    private static final Pattern RESPOSTA_PATTERN = Pattern.compile("\"text\"\\s*:\\s*\"([^\"]+)\"");

    public static String fazerPergunta(String pergunta) throws IOException, InterruptedException {
        String jsonRequest = gerarJsonRequest(pergunta);
        String respostaJson = enviarRequisicao(jsonRequest);
        return extrairResposta(respostaJson);
    }

    private static String gerarJsonRequest(String Pergunta) {
        String promptFormatado = "o resultado não deve possuir formatação nem assentos:" + Pergunta;
        return "{\"contents\":[{\"parts\":[{\"text\":\"" + promptFormatado + "\"}]}]}";
    }

    private static String enviarRequisicao(String jsonRequest) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Content-Type","application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequest))
        .build();
        return client.send(request, BodyHandlers.ofString()).body();
    }

    private static String extrairResposta(String respostaJson) {
        StringBuilder resposta = new StringBuilder();
        for(String linha : respostaJson.lines().toList()) {
            Matcher matcher = RESPOSTA_PATTERN.matcher(linha);
            if(matcher.find()) {
                resposta.append(matcher.group(1)).append(" ");
            }
        }
        return  resposta.toString().trim();
    }
}
