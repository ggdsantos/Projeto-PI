import java.util.Scanner;
import java.util.Random;

public class Main {
    static Scanner ler = new Scanner(System.in);
    static Random rand = new Random();
   public static int numero;
    public static void main(String[] args) {
        System.out.println("Bem-vindo ao ShoOkiDeRa, ShoOkiDeRi");
        System.out.println("Você quer jogar Single player ou Multiplayer? (S/M) ");
        String resposta1 = ler.next();
     switch (resposta1) {
        case "s":{
            
            Sing();
            break;
        }
        case "m":{
            Mult();
            
            break;
        }
            
     
        default:
        System.out.println("Resposta incorreta");
            break;
     }
        
    }
   

public static void Sing(){
   
        ler.nextLine();
        System.out.println("Qual é o seu nome?");
        String nome = ler.nextLine();

        System.out.println("Quanto dinheiro você quer colocar no jogo?");
        double saldo = ler.nextDouble();
        int numero;
        do {
            System.out.println("Escolha seu número da sorte (1-13):");
            numero = ler.nextInt();
            if (numero < 1 || numero > 13) {
                System.out.println("Por favor, escolha um número entre 1 e 13.");
            }
        } while (numero < 1 || numero > 13);

        int[] cartas = new int[13];
        for (int i = 0; i < 13; i++) {
            cartas[i] = i + 1;
        }

        int jogadas = 0;
        int vitorias = 0;
        boolean[] desafiosAtivados = new boolean[7]; // Variável para controlar se os desafios serão ativados
        boolean sairDoJogo = false; //A variável booleana sairDoJogo atua como algo que controla o fluxo do programa. Se sairDoJogo for verdadeira, o programa sai do loop e termina o jogo. Se for falsa, o jogo continua.
      guiaJogo(numero);
       

        // Jogadas garantidas nas duas primeiras rodadas
        while (jogadas < 2 && saldo > 0 && !sairDoJogo) {
            System.out.println("\nJogada " + (jogadas + 1) + ":");

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
            //retornará um número aleatório entre 0 e 12. Ao adicionar 1 a esse valor, obtemos um número aleatório entre 1 e 13
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
                System.out.println("\nJogada " + (jogadas + 1) + ":");

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
                if (carta1 == carta2 && carta2 == carta3 || carta1 == 3 || carta2 == 3 || carta3 == 3 ||
                        (carta1 == 7 && carta2 == 7 && carta3 == 7) || (carta1 == numero || carta2 == numero || carta3 == numero) || (carta1 == numero && carta2 == numero && carta3 == numero)) {
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
                    System.out.println("\u001B[34mParabéns, você alcançou três vitórias!");
                    System.out.println("\u001B[0mDeseja participar do desafio dos números múltiplos de 5? \nSe sair algum número múltiplo de 5, seu saldo aumenta, porém se não sair nenhum, será retirado 1/3 dele. (s/n)");
                    char resposta = ler.next().charAt(0);
                    if (resposta == 's' || resposta == 'S') {
                        System.out.println("\u001B[0mDesafio ativado! Sorteando três números...");
                        int[] numerosDesafio = new int[3];
                        for (int i = 0; i < 3; i++) {
                            numerosDesafio[i] = rand.nextInt(50) + 1; // Sorteia números entre 1 e 50
                        }
                        System.out.println("\u001B[0mNúmeros sorteados: " + numerosDesafio[0] + ", " + numerosDesafio[1] + ", " + numerosDesafio[2]);
                        int multiplosDe5 = 0;
                        for (int numeroDesafio : numerosDesafio) {
                            if (numeroDesafio % 5 == 0) {
                                multiplosDe5++;
                            }
                        }
                        if (multiplosDe5 == 0) {
                            saldo -= saldo / 3; // Perda de um terço do saldo
                            System.out.println("\u001B[31mNenhum dos números sorteados é múltiplo de 5. Seu saldo foi reduzido em um terço.");
                        } else if (multiplosDe5 == 1) {
                            saldo *= 2; // Saldo triplicado
                            System.out.println("\u001B[32mUm dos números sorteados é múltiplo de 5. Seu saldo foi triplicado!");
                        } else if (multiplosDe5 == 2) {
                            saldo *= 4; // Saldo quintuplicado
                            System.out.println("\u001B[32mDois dos números sorteados são múltiplos de 5. Seu saldo foi quintuplicado!");
                        } else if (multiplosDe5 == 3) {
                            saldo *= 20; // Saldo multiplicado por 20
                            System.out.println("\u001B[32mTrês dos números sorteados são múltiplos de 5. Seu saldo foi multiplicado por 20!");
                        }
                        System.out.println("\u001B[0mSaldo atual: R$ " + saldo);
                    } else {
                        System.out.println("\u001B[34m\u001B[0mVocê optou por não participar do desafio. \nO jogo continua.");
                    }
                    desafiosAtivados[0] = true;
                }
                // desafio 2
                if (vitorias >= 5 && !desafiosAtivados[1]) {
                    System.out.println("\u001B[34mBem-vindo ao Desafio 2: Adivinhe o número!");
                    System.out.println("\u001B[0mVocê terá 5 tentativas para adivinhar um número entre 1 e 100. \nSe acertar, seu saldo aumentará em 3x, se errar, será retirado 1/4.");
                    System.out.println("Deseja participar? (s/n)");
                    char resposta = ler.next().charAt(0);
                    if (resposta == 's' || resposta == 'S') {
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
                                System.out.println("O número secreto é maior!");
                            } else {
                                System.out.println("O número secreto é menor!");
                            }

                            tentativas++;
                        }

                        if (acertou) {
                            saldo *= 3;
                            System.out.println("\u001B[32mParabéns! Você acertou o número secreto " + numeroSecreto + " em " + (tentativas + 1) + " tentativas!");
                        } else {
                            saldo -= saldo / 4;
                            System.out.println("\u001B[31mSuas tentativas acabaram! O número secreto era " + numeroSecreto + ". Melhor sorte da próxima vez!");
                        }
                        System.out.println("\u001B[0mSaldo atual: R$ " + saldo);
                    } else {
                        System.out.println("\u001B[34m\u001B[0mVocê optou por não participar do desafio. \nO jogo continua.");
                    }
                    desafiosAtivados[1] = true;
                }
                //des3 
                if (vitorias == 6 && !desafiosAtivados[2]) {
                
                }

                // Verifica se o jogador saiu do jogo antes de continuar
            }if (sairDoJogo) {
                    System.out.println("\u001B[0m\nVocê saiu do jogo.");
                    System.out.println("Você fez " + jogadas + " jogadas e ganhou " + vitorias + " vezes.");
                    System.out.println("\u001B[33mSeu saldo final é: R$ " + saldo);
                } else {
                    System.out.println("\n\u001B[0mFim do jogo!");
                    System.out.println(nome + ", \u001B[0mvocê fez " + jogadas + " jogadas e ganhou " + vitorias + " vezes.");
                    System.out.println("\u001B[33mSeu saldo final é: R$ " + saldo);
                }
                
            
            
        }
}



    public static void Mult(){

        System.out.println("vcescolheu multi");
        //em andamento
        {

            Scanner scanner = new Scanner(System.in);
            Random random = new Random();

            double saldoJogador1 = 5000;
            double saldoJogador2 = 5000;

            boolean sairDoJogo = false;

            while (!sairDoJogo) {
                // Jogador 1 faz sua jogada
                System.out.println("\nJogador 1 - Seu saldo: R$ " + saldoJogador1);
                System.out.print("Jogador 1 - Digite o valor da sua aposta (ou 0 para sair do jogo): ");
                double apostaJogador1 = scanner.nextDouble();

                if (apostaJogador1 == 0) {
                    sairDoJogo = true;
                    break;
                }

                if (apostaJogador1 > saldoJogador1) {
                    System.out.println("Saldo insuficiente! Por favor, digite um valor válido.");
                    continue;
                }

                // Jogador 2 faz sua jogada
                System.out.println("\nJogador 2 - Seu saldo: R$ " + saldoJogador2);
                System.out.print("Jogador 2 - Digite o valor da sua aposta (ou 0 para sair do jogo): ");
                double apostaJogador2 = scanner.nextDouble();

                if (apostaJogador2 == 0) {
                    sairDoJogo = true;
                    break;
                }

                if (apostaJogador2 > saldoJogador2) {
                    System.out.println("Saldo insuficiente! Por favor, digite um valor válido.");
                    continue;
                }

                // Simulação do jogo
                int resultado = random.nextInt(2); // 0 ou 1

                if (resultado == 0) {
                    System.out.println("\nJogador 1 ganhou esta rodada!");
                    saldoJogador1 += apostaJogador2;
                    saldoJogador2 -= apostaJogador2;
                } else {
                    System.out.println("\nJogador 2 ganhou esta rodada!");
                    saldoJogador1 -= apostaJogador1;
                    saldoJogador2 += apostaJogador1;
                }

                System.out.println("\nSaldo atual:");
                System.out.println("Jogador 1: R$ " + saldoJogador1);
                System.out.println("Jogador 2: R$ " + saldoJogador2);
            }

            System.out.println("Obrigado por jogar!");
        }

    }
   
   
   
    public static void guiaJogo(int numero){

 // Mensagem das regras
 System.out.println("\nGuia do Jogo:");
 System.out.println("Você ganha se sair três cartas iguais, se sair um 3, se sair três cartas com 7, se sair um " + numero + " ou se sair três cartas com " + numero);
 System.out.println("Ganhos:");
 System.out.println("  - Três cartas iguais: 6x o valor da aposta");
 System.out.println("  - Carta 3: 3x o valor da aposta");
 System.out.println("  - Três cartas com 7: 10x o valor da aposta");
 System.out.println("  - Carta " + numero + ": 2x o valor da aposta");
 System.out.println("  - Três cartas com " + numero + ": 10x o valor da aposta");
 System.out.println("Perdas:");
 System.out.println("  - Nenhuma das anteriores: Perde 3x o valor da aposta");
    }
}