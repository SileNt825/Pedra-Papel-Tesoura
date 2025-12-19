import exceptions.ChoiceException;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public enum opcoes {
        PEDRA,
        PAPEL,
        TESOURA;
    }

    public static opcoes opcoesAleatorias() {
        Random random = new Random();
        int indice = random.nextInt(opcoes.values().length);
        return opcoes.values()[indice];
    }

    public static void LogicaDeGanhar(String escolhaJogador, String escolhaMaquina) {
        String escolhaPedra = String.valueOf((opcoes.PEDRA.toString()));
        String escolaTesoura = String.valueOf((opcoes.TESOURA.toString()));
        String escolhaPapel = String.valueOf((opcoes.PAPEL.toString()));
        if (escolhaJogador.equals(escolhaPedra) && escolhaMaquina.equals(escolaTesoura)
                || (escolhaJogador.equals(escolaTesoura) && escolhaMaquina.equals(escolhaPapel))
                || escolhaJogador.equals(escolhaPapel) && escolhaMaquina.equals(escolhaPedra)) {
            System.out.println("Voce ganhou!");


        } else if (escolhaJogador.equals(escolhaPedra) && escolhaMaquina.equals(escolhaPapel)
                || (escolhaJogador.equals(escolhaPapel) && escolhaMaquina.equals(escolaTesoura))
                || (escolhaJogador.equals(escolaTesoura) && escolhaMaquina.equals(escolhaPedra))) {
            System.out.println("voce perdeu!");
        }

        if (escolhaJogador.equals(escolhaMaquina)) {
            System.out.println("Empate");
        }
    }

    public static void validarEntradas(String escolhaJogador){
        try{
            opcoes.valueOf(escolhaJogador);
            } catch (IllegalArgumentException e){
        throw new ChoiceException(escolhaJogador + "Não existe dentro das opções.");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String escolhaJogador = null;
        String escolhaMaquina = null;
        try {
            System.out.println("Escolha uma opção (PEDRA/PAPEL/TESOURA)");
            escolhaJogador = sc.nextLine().toUpperCase();
            System.out.println("Escolha jogador: " + escolhaJogador);

            escolhaMaquina = opcoesAleatorias().toString();
            System.out.println("Escolha da Maquina: " + escolhaMaquina);

            LogicaDeGanhar(escolhaJogador, escolhaMaquina);
        } catch (ChoiceException e) {
            System.out.println(e.getMessage());
        } finally {
            sc.close();
        }
        validarEntradas(escolhaJogador);

        long inicio = System.nanoTime();
        for (int i = 0; i < 1_000_000; i++) {
            validarEntradas(escolhaJogador);
        }
        long fim = System.nanoTime();
        System.out.println((fim - inicio) / 1_000_000.0 + " ms");
    }
}


