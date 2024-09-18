package br.edu.fatecpg.view;

import br.edu.fatecpg.service.ConsomeApi;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("Pergunte ao Gemini");

        while (true) {

            System.out.println("Informe o que deseja fazer - | P - Para fazer uma pergunta - | S - Para sair do programa \n\n\n");
            String escolha = scan.nextLine();

            if (escolha.equals("p")) {
                System.out.println("Faça a Pergunta: \n");
                String pergunta = scan.nextLine();
                String resposta = ConsomeApi.fazerPergunta(pergunta);
                System.out.println("A resposta para sua pergunta é: " + resposta);
                System.out.println("\n\n\n");
            }

            else if (escolha.equals("s")) {
                System.out.println("programa encerrado\n");
                break;
            }

            else {
                System.out.println("Código Invalido\n\n\n");
            }

        }

    }

}
