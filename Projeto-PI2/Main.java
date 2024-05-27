import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static Scanner ler = new Scanner(System.in);
    static Random rand = new Random();
    public static int numero;
    public static int jogadas = 0;
    public static int vitorias = 0;
    public static boolean[] desafiosAtivados = new boolean[7]; // Variável para controlar se os desafios serão ativados
    public static double saldo = 0;
    public static int charadaNumero;
    static String dourado = "\u001B[33m";
    static String reset = "\u001B[0m";
    static String vermelho = "\u001B[31m";
    static String verde = "\u001B[32m";
    static String azul = "\u001B[34m";
    static String roxa = "\u001B[35m";
    static String laranja = "\u001B[38;5;208m";


    public static void main(String[] args) {

        System.out.println(dourado + "	  ****** Bem-vindo ao Duat Casinos ******\n" + reset);
        System.out.println("Você quer jogar Single player ou Multiplayer? (S/M) ");
        String resposta1 = ler.next();
        switch (resposta1) {
            case "s": {

                Sing();
                break;
            }
            case "m": {
                Mult();

                break;
            }

            default:
                System.out.println(vermelho + "Resposta incorreta" + reset);
                break;
        }

    }

    public static void Sing() {

        ler.nextLine();
        System.out.println("Qual é o seu nome?");
        String nome = ler.nextLine();

        int numero;
        while (true) {
            System.out.println("Quanto dinheiro você quer colocar no jogo? (Entre 5 e 10.000 reais)");
            saldo = ler.nextDouble();
            if (saldo >= 5 && saldo <= 10000) {
                break;
            } else {
                System.out.println("O valor do saldo deve estar entre 5 e 10.000 reais. Tente novamente.");
            }
        }
        do {
            System.out.println("Escolha seu número da sorte (1-13):");
            numero = ler.nextInt();
            if (numero < 1 || numero > 13) {
                System.out.println(vermelho + "Por favor, escolha um número entre 1 e 13." + reset);
            }
        } while (numero < 1 || numero > 13);

        int[] cartas = new int[13];
        for (int i = 0; i < 13; i++) {
            cartas[i] = i + 1;
        }

        boolean sairDoJogo = false; // A variável booleana sairDoJogo atua como algo que controla o fluxo do
        // programa. Se sairDoJogo for verdadeira, o programa sai do loop e termina o
        // jogo. Se for falsa, o jogo continua.
        guiaJogo(numero);

        // Jogadas garantidas nas duas primeiras rodadas
        while (jogadas < 2 && saldo > 0 && !sairDoJogo) {
            System.out.println(azul + "\nJogada " + (jogadas + 1) + ":" + reset);

            // Pedir aposta nas duas primeiras jogadas
            System.out.println("Digite o valor da sua aposta (ou 0 para sair do jogo):");
            double aposta = ler.nextDouble();

            if (aposta == 0) {
                sairDoJogo = true; // Define a variável sairDoJogo como true
                break;
            }
            if (aposta > saldo) {
                System.out.println("Você não tem saldo suficiente para essa aposta!");
                continue;
            }

            // Simulando a saída das cartas
            int carta1 = rand.nextInt(13) + 1;
            int carta2 = rand.nextInt(13) + 1;
            // retornará um número aleatório entre 0 e 12. Ao adicionar 1 a esse valor,
            // obtemos um número aleatório entre 1 e 13
            int carta3 = 3;

            System.out.println("Cartas sorteadas: " + carta1 + ", " + carta2 + ", " + carta3);

            // Condição de vitória nas duas primeiras rodadas
            System.out.println("\u001B[32mParabéns, você ganhou esta jogada!");
            vitorias++;
            saldo += aposta * 3; // Ganhos de 3x o valor da aposta

            System.out.println("\u001B[0mSaldo atual: R$ " + saldo);
            jogadas++;
        }

        // Verifica se o jogador saiu do jogo antes de continuar
        if (sairDoJogo) {
            System.out.println("\nFim do jogo!");
            System.out.println("Você saiu do jogo nas duas primeiras rodadas.");
            System.out.println("Seu saldo final é: R$ " + saldo);
        } else {
            // Jogadas aleatórias a partir da terceira jogada
            while (saldo > 0 && !sairDoJogo) {
                System.out.println(azul + "\nJogada " + (jogadas + 1) + ":" + reset);

                // Pedir aposta apenas a partir da terceira jogada
                System.out.println("\u001B[0mDigite o valor da sua aposta (ou 0 para sair do jogo):");
                double aposta = ler.nextDouble();

                if (aposta == 0) {
                    sairDoJogo = true; // Define a variável sairDoJogo como true
                    break;
                }

                if (aposta > saldo) {
                    System.out.println("\u001B[35mVocê não tem saldo suficiente para essa aposta!");
                    continue;
                }

                // Simulando a saída das cartas
                int carta1 = rand.nextInt(13) + 1;
                int carta2 = rand.nextInt(13) + 1;
                int carta3 = rand.nextInt(13) + 1;

                System.out.println("\u001B[0mCartas sorteadas: " + carta1 + ", " + carta2 + ", " + carta3);

                // Condição de vitória: três cartas iguais ou carta 3
                if (carta1 == carta2 && carta2 == carta3 || carta1 == 3 || carta2 == 3 || carta3 == 3
                        || (carta1 == 7 && carta2 == 7 && carta3 == 7)
                        || (carta1 == numero || carta2 == numero || carta3 == numero)
                        || (carta1 == numero && carta2 == numero && carta3 == numero)) {
                    System.out.println("\u001B[32mParabéns, você ganhou esta jogada!");
                    vitorias++;
                    if (carta1 == carta2 && carta2 == carta3) {
                        saldo += aposta * 6; // Ganhos de 6x o valor da aposta
                    } else if (carta1 == 3 || carta2 == 3 || carta3 == 3) {
                        saldo += aposta * 3; // Ganhos de 2x o valor da aposta
                    } else if (carta1 == 7 && carta2 == 7 && carta3 == 7) {
                        saldo += aposta * 10; // Ganhos de 10x o valor da aposta
                    } else if (carta1 == numero || carta2 == numero || carta3 == numero) {
                        saldo += aposta * 2; // Ganhos de 3x o valor da aposta
                    } else {
                        saldo += aposta * 10; // Ganhos de 10x o valor da aposta
                    }

                } else {
                    System.out.println("\u001B[31mVocê perdeu esta jogada...");
                    saldo -= aposta * 3; // Perdas de 3x o valor da aposta
                }

                System.out.println("\u001B[0mSaldo atual: R$ " + saldo);
                jogadas++;

                // Verifica se o desafio 1 pode ser ativado
                if (vitorias >= 3 && !desafiosAtivados[0]) {
                    desf1();
                }
                // desafio 2
                else if (vitorias >= 5 && !desafiosAtivados[1]) {
                    desf2();
                }
                // des3
                else if (vitorias == 6 && !desafiosAtivados[2]) {
                    desf3();
                } else if (vitorias == 8 && !desafiosAtivados[3]) {
                    desf4();
                }
                // Verifica se o jogador saiu do jogo antes de continuar
            }
            if (sairDoJogo) {
                System.out.println("\u001B[0m\nVocê saiu do jogo.");
                System.out.println("Você fez " + jogadas + " jogadas e ganhou " + vitorias + " vezes.");
                System.out.println("\u001B[33mSeu saldo final é: R$ " + saldo);
            } else {
                System.out.println("\n\u001B[0mFim do jogo!");
                System.out
                        .println(nome + ", \u001B[0mvocê fez " + jogadas + " jogadas e ganhou " + vitorias + " vezes.");
                System.out.println("\u001B[33mSeu saldo final é: R$ " + saldo);
            }

        }
    }

    public static void desf1() {

        if (vitorias >= 3 && !desafiosAtivados[0]) {
            System.out.println(roxa + "Bem-vindo ao Quiz Game!" + reset);
            System.out.println("Escolha um tema:");
            System.out.println("1 - Egito");
            System.out.println("2 - Cartas");
            System.out.println("3 - Números");

            int tema = ler.nextInt();
            ler.nextLine(); // Consumir a nova linha

            switch (tema) {
                case 1:
                    quizEgito(ler);
                    break;
                case 2:
                    quizCartas(ler);
                    break;
                case 3:
                    quizNumeros(ler);
                    break;
                default:
                    System.out.println("Opção inválida. Saindo do jogo.");
                    break;
            }

        }

    }

    public static void desf2() {
        if (vitorias >= 5 && !desafiosAtivados[1]) {
            System.out.println("\u001B[34mBem-vindo ao Desafio 2: Adivinhe o número!");
            System.out.println(
                    "\u001B[0mVocê terá 5 tentativas para adivinhar um número entre 1 e 100. \nSe acertar, seu saldo aumentará em 3x, se errar, será retirado 1/4.");

            System.out.println("Vamos lá!");

            int numeroSecreto = rand.nextInt(100) + 1; // Gerando um número aleatório entre 1 e 100
            int tentativas = 0;
            boolean acertou = false;

            while (tentativas < 5) {
                System.out.print("Tentativa " + (tentativas + 1) + ": Digite seu palpite: ");
                int palpite = ler.nextInt();

                if (palpite == numeroSecreto) {
                    acertou = true;
                    break;
                } else if (palpite < numeroSecreto) {
                    System.out.println(verde + "O número secreto é maior!" + reset);
                } else {
                    System.out.println(vermelho + "O número secreto é menor!" + reset);
                }

                tentativas++;
            }

            if (acertou) {
                saldo *= 3;
                System.out.println("\u001B[32mParabéns! Você acertou o número secreto " + numeroSecreto + " em "
                        + (tentativas + 1) + " tentativas!");
            } else {
                saldo -= saldo / 4;
                System.out.println("\u001B[31mSuas tentativas acabaram! O número secreto era " + numeroSecreto
                        + ". Melhor sorte da próxima vez!");
            }
            System.out.println("\u001B[0mSaldo atual: R$ " + saldo);
        }

        desafiosAtivados[1] = true;
    }

    public static void desf3() {
        if (vitorias == 6 && !desafiosAtivados[2]) {
            charadaNumero = rand.nextInt(4) + 1; // Gera um número aleatório entre 1 e 4
            String respostaCorreta = "";
            String charada = "";

            switch (charadaNumero) {
                case 1:
                    charada = "Corre bastante e não possui pernas.";
                    respostaCorreta = verde + "Rio" + reset;
                    break;
                case 2:
                    charada = "Sou grande quando nova, e vou encolhendo quando velha.";
                    respostaCorreta = verde + "Vela" + reset;
                    break;
                case 3:
                    charada = "O que se quebra quando é dito?";
                    respostaCorreta = verde + "Silêncio" + reset;
                    break;
                case 4:
                    charada = "Quanto mais se tira, maior fica.";
                    respostaCorreta = verde + "Buraco" + reset;
                    break;
            }

            System.out.println(laranja + "Bem-vindo ao jogo de Charadas!" + reset);
            System.out.println("Você tem 5 chances para adivinhar a resposta.");
            System.out.println("Charada: " + charada);

            boolean acertou = false;
            for (int tentativa = 1; tentativa <= 5; tentativa++) {
                System.out.print("Tentativa " + tentativa + ": ");
                String resposta = ler.next().trim();

                if (resposta.equalsIgnoreCase(respostaCorreta)) {
                    saldo *= 3;
                    System.out.println(verde + "Parabéns, você acertou!" + reset);
                    acertou = true;
                    break;
                } else {
                    System.out.println(vermelho + "Resposta errada. Tente novamente." + reset);
                }
            }

            if (!acertou) {
                saldo -= saldo / 3;
                System.out.println(vermelho + "Você perdeu! A resposta correta era: " + respostaCorreta + reset);
            }
            System.out.println("\u001B[0mSaldo atual: R$ " + saldo);
        }
        desafiosAtivados[2] = true;
    }

    public static void quizEgito(Scanner scanner) {
        System.out.println(azul + "Tema: Egito" + reset);
        System.out.println("O pós-vida egípcio Duat, também era conhecido por outro nome, qual?");
        System.out.println("1) Edenia");
        System.out.println("2) Elíseos");
        System.out.println("3) Akert");
        System.out.println("4) Osiris");

        int resposta = scanner.nextInt();

        if (resposta == 3) {
            saldo *= 4;
            System.out.println(verde + "Parabéns, você ganhou!" + reset);
        } else {
            saldo -= saldo / 2;
            System.out.println(vermelho + "Você perdeu no desafio." + reset);
        }
    }

    public static void quizCartas(Scanner scanner) {
        System.out.println(dourado + "Tema: Cartas" + reset);
        System.out.println("No popular jogo \"Truco\", qual a carta mais forte, depois da manilha?");
        System.out.println("1) 3");
        System.out.println("2) K");
        System.out.println("3) 9");
        System.out.println("4) 2");

        int resposta = scanner.nextInt();

        if (resposta == 1) {
            saldo *= 3;
            System.out.println(verde + "Parabéns, você ganhou!" + reset);
        } else {
            saldo -= saldo / 2;
            System.out.println(vermelho + "Você perdeu no desafio." + reset);
        }
    }

    public static void quizNumeros(Scanner scanner) {
        System.out.println(vermelho + "Tema: Números" + reset);
        System.out.println("Qual é considerado o número ideal da sorte?");
        System.out.println("1) 12");
        System.out.println("2) 7");
        System.out.println("3) 13");
        System.out.println("4) 10");

        int resposta = scanner.nextInt();

        if (resposta == 2) {
            saldo *= 3;
            System.out.println(verde + "Parabéns, você ganhou!" + reset);
        } else {
            saldo -= saldo / 2;
            System.out.println(vermelho + "Você perdeu no desafio." + reset);
        }
        System.out.println("\u001B[0mSaldo atual: R$ " + saldo);

        desafiosAtivados[0] = true;
    }

    public static void desf4() {
        Scanner scanner = new Scanner(System.in);

        String palavraSecreta = "APOSTA"; // Palavra secreta para o jogo
        int tentativasMaximas = 10;

        System.out.println(roxa + "Bem-vindo ao jogo de Adivinhação de Palavras!" + reset);
        System.out.println("Você deve adivinhar a palavra secreta de 6 letras.");
        System.out.println("Dica: É algo que está te prendendo muito no momento ");
        System.out.println("Você tem " + tentativasMaximas + " tentativas.");

        boolean acertou = false;
        for (int tentativa = 1; tentativa <= tentativasMaximas; tentativa++) {
            System.out.print("Tentativa " + tentativa + ": ");
            String palpite = scanner.nextLine().trim().toUpperCase();

            if (palpite.length() != 6) {
                System.out.println(vermelho + "Por favor, insira uma palavra de 6 letras." + reset);
                tentativa--;
                continue;
            }

            if (palpite.equals(palavraSecreta)) {
                System.out.println(verde + "Parabéns, você acertou a palavra secreta!" + reset);
                acertou = true;
                break;
            } else {
                int letrasCorretas = contarLetrasCorretas(palavraSecreta, palpite);
                int posicoesCorretas = contarPosicoesCorretas(palavraSecreta, palpite);
                System.out.println(verde + "Letras corretas: " + letrasCorretas + reset);
                System.out.println(verde + "Posições corretas: " + posicoesCorretas + reset);
                System.out.println(vermelho + "Tente novamente." + reset);
            }
        }

        if (!acertou) {
            System.out.println(vermelho + "Você perdeu!" + reset + "A palavra secreta era" + palavraSecreta);
        }

    }

    public static int contarLetrasCorretas(String palavraSecreta, String palpite) {
        int corretas = 0;
        for (int i = 0; i < palpite.length(); i++) {
            if (palavraSecreta.indexOf(palpite.charAt(i)) != -1) {
                corretas++;
            }
        }
        return corretas;
    }

    public static int contarPosicoesCorretas(String palavraSecreta, String palpite) {
        int posicoes = 0;
        for (int i = 0; i < palpite.length(); i++) {
            if (palpite.charAt(i) == palavraSecreta.charAt(i)) {
                posicoes++;
            }
        }
        return posicoes;

    }

    public static void Mult() {

        System.out.println("               \u001B[32m**** Guia do Jogo **** \n");
        System.out.println("\u001B[0m-----------------------------------------------");
        System.out.println("Cada jogador escolherá um número de 1 a 13\n Três números serão sorteados\n Quem acertar mais números ganha\n Se alguém sair, perde automaticamente" );
        System.out.println("-----------------------------------------------");

        {

            Scanner scanner = new Scanner(System.in);
            Random random = new Random();

            double saldoJogador1;
            double saldoJogador2;

            while (true) {
                System.out.println(azul + "Jogador 1, digite quanto será seu saldo (De 5 a 5000 reais): " + reset);
                saldoJogador1 = ler.nextDouble();
                if (saldoJogador1 >= 5 && saldoJogador1 <= 5000) {
                    break;
                }
                System.out.println("O valor do saldo deve estar entre 5 e 5000 reais. Tente novamente.");
            }

            while (true) {
                System.out.println(laranja + "Jogador 2, digite quanto será seu saldo (De 5 a 5000 reais): " + reset);
                saldoJogador2 = ler.nextDouble();
                if (saldoJogador2 >= 5 && saldoJogador2 <= 5000) {
                    break;
                }
                System.out.println(vermelho + "O valor do saldo deve estar entre 5 e 5000 reais. Tente novamente." + reset);
            }

            boolean sairDoJogo = false;

            while (!sairDoJogo) {
                System.out.println(azul + "\nJogador 1 - Seu saldo: R$ " + saldoJogador1 + reset);
                System.out.print(azul + "Jogador 1 - Digite o valor da sua aposta (ou 0 para sair do jogo): " + reset);
                double apostaJogador1 = ler.nextDouble();

                if (apostaJogador1 == 0) {
                    System.out.println("Jogador 1 decidiu sair do jogo. Jogador 2 é o vencedor!");
                    break;
                }

                if (apostaJogador1 > saldoJogador1) {
                    System.out.println("Saldo insuficiente! Por favor, digite um valor válido.");
                    continue;
                }

                System.out.println(laranja + "\nJogador 2 - Seu saldo: R$ " + saldoJogador2 + reset);
                System.out.print(laranja + "Jogador 2 - Digite o valor da sua aposta (ou 0 para sair do jogo): " + reset);
                double apostaJogador2 = ler.nextDouble();

                if (apostaJogador2 == 0) {
                    System.out.println("Jogador 2 decidiu sair do jogo. Jogador 1 é o vencedor!");
                    break;
                }

                if (apostaJogador2 > saldoJogador2) {
                    System.out.println("Saldo insuficiente! Por favor, digite um valor válido.");
                    continue;
                }

                int escolhaJogador1Numero1, escolhaJogador1Numero2;
                int escolhaJogador2Numero1, escolhaJogador2Numero2;

                while (true) {
                    System.out.print(azul + "\nJogador 1, escolha seu primeiro número (entre 1 e 13): " + reset);
                    escolhaJogador1Numero1 = ler.nextInt();
                    if (escolhaJogador1Numero1 < 1 || escolhaJogador1Numero1 > 13) {
                        System.out.println(vermelho + "Número inválido! Escolha um número entre 1 e 13." + reset);
                        continue;
                    }

                    System.out.print(azul + "Jogador 1, escolha seu segundo número (entre 1 e 13): " + reset);
                    escolhaJogador1Numero2 = ler.nextInt();
                    if (escolhaJogador1Numero2 < 1 || escolhaJogador1Numero2 > 13) {
                        System.out.println(vermelho + "Número inválido! Escolha um número entre 1 e 13." + reset);
                        continue;
                    }

                    if (escolhaJogador1Numero1 != escolhaJogador1Numero2) {
                        break;
                    } else {
                        System.out.println(vermelho + "Os números escolhidos devem ser diferentes. Tente novamente." + reset);
                    }
                }

                while (true) {
                    System.out.print(laranja + "\nJogador 2, escolha seu primeiro número (entre 1 e 13): " + reset);
                    escolhaJogador2Numero1 = ler.nextInt();
                    if (escolhaJogador2Numero1 < 1 || escolhaJogador2Numero1 > 13) {
                        System.out.println(vermelho + "Número inválido! Escolha um número entre 1 e 13." + reset);
                        continue;
                    }

                    System.out.print(laranja + "Jogador 2, escolha seu segundo número (entre 1 e 13): " + reset);
                    escolhaJogador2Numero2 = ler.nextInt();
                    if (escolhaJogador2Numero2 < 1 || escolhaJogador2Numero2 > 13) {
                        System.out.println(vermelho + "Número inválido! Escolha um número entre 1 e 13." + reset);
                        continue;
                    }

                    if ((escolhaJogador2Numero1 != escolhaJogador2Numero2)
                            && (escolhaJogador2Numero1 != escolhaJogador1Numero1)
                            && (escolhaJogador2Numero1 != escolhaJogador1Numero2)
                            && (escolhaJogador2Numero2 != escolhaJogador1Numero1)
                            && (escolhaJogador2Numero2 != escolhaJogador1Numero2)) {
                        break;
                    } else {
                        System.out.println(
                                "Os números escolhidos devem ser diferentes dos escolhidos pelo Jogador 1 e entre si. Tente novamente.");
                    }
                }

                List<Integer> sorteados = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    sorteados.add(random.nextInt(13) + 1);
                }

                System.out.println("\nNúmeros sorteados: " + sorteados);

                boolean jogador1Ganha1 = false;
                boolean jogador1Ganha2 = false;
                boolean jogador2Ganha1 = false;
                boolean jogador2Ganha2 = false;

                // Verificação manual dos números sorteados
                for (int num : sorteados) {
                    if (num == escolhaJogador1Numero1) jogador1Ganha1 = true;
                    if (num == escolhaJogador1Numero2) jogador1Ganha2 = true;
                    if (num == escolhaJogador2Numero1) jogador2Ganha1 = true;
                    if (num == escolhaJogador2Numero2) jogador2Ganha2 = true;
                }

                if (jogador1Ganha1 && jogador1Ganha2) {
                    System.out.println(azul + "\nJogador 1 acertou os dois números!" + reset);
                    saldoJogador1 += apostaJogador2;
                    saldoJogador2 -= apostaJogador2;
                } else if (jogador2Ganha1 && jogador2Ganha2) {
                    System.out.println(laranja + "\nJogador 2 acertou os dois números!" + reset);
                    saldoJogador1 -= apostaJogador1;
                    saldoJogador2 += apostaJogador1;
                } else if (jogador1Ganha1 || jogador1Ganha2) {
                    System.out.println(azul + "\nJogador 1 acertou um dos números!" + reset);
                    saldoJogador2 -= apostaJogador2;
                } else if (jogador2Ganha1 || jogador2Ganha2) {
                    System.out.println(laranja + "\nJogador 2 acertou um dos números!" + reset);
                    saldoJogador1 -= apostaJogador1;
                } else {
                    System.out.println(vermelho + "\nNenhum jogador acertou nenhum número. Ambos perdem suas apostas." + reset);
                    saldoJogador1 -= apostaJogador1;
                    saldoJogador2 -= apostaJogador2;
                }

                System.out.println("\nSaldo atual:");
                System.out.println(azul + "Jogador 1: R$ " + saldoJogador1 + reset);
                System.out.println(laranja + "Jogador 2: R$ " + saldoJogador2 + reset);

                if (saldoJogador1 <= 0 || saldoJogador2 <= 0) {
                    System.out.println("\nUm dos jogadores ficou sem saldo. O jogo terminou.");
                    sairDoJogo = true;
                }
            }

            System.out.println(dourado + "Obrigado por jogar!" + reset);
        }

    }

    public static void guiaJogo(int numero) {

        System.out.println(verde +"                ** Guia do Jogo ** \n"+reset);
        System.out.println(verde +"-----------------------------------------------"+ reset);
        System.out.println(verde +"Ganhos:"+reset);
        System.out.println(verde +"  -> Três cartas iguais: 6x o valor da aposta"+reset);
        System.out.println(verde +"  -> Carta 3: 3x o valor da aposta"+reset);
        System.out.println(verde +"  -> Três cartas com 7: 10x o valor da aposta"+reset);
        System.out.println(verde +"  -> Carta " + numero + ": 2x o valor da aposta"+reset);
        System.out.println(verde +"  -> Três cartas com " + numero + ": 10x o valor da aposta"+reset);
        System.out.println(vermelho +"Perdas:"+ reset);
        System.out.println(vermelho +"  -> Nenhuma das anteriores: Perde 3x o valor da aposta"+reset);
        System.out.println(vermelho +"-----------------------------------------------"+ reset);

    }
}
