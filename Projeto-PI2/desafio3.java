import java.util.Scanner;
import java.util.Random;

public class desafio3 {
public static void main(String[] args){
    Scanner ler = new Scanner(System.in);
   Random rand = new Random();
   System.out.println("As opções da roleta são, ganhar R$ 1000, perder R$1000 ou rodada valendo o dobro, escolha 1 para girar a roleta ou 0 para pular");
   int escolhaRoleta = ler.nextInt();

    String [] roleta = {"Você ganhou R$ 1000 ","Essa rodada vale o dobro","Você perdeu R$ 1000"};
    
    if(escolhaRoleta == 1){
        int index = (int) (Math.random() * roleta.length);
        System.out.println(roleta[index]);
        if(index == 0){
            System.out.println("Mil adcionado");
        }
        else if(index == 1){
            System.out.println("Rodada valendo o dobro");
        }else{
            System.out.println("Você perdeu mil");
        }
    }else {
        System.out.println("Desafio pulado");
    }
    
}
}
/*for (int i = 0; i < roleta.length; i++)
{
    roleta[i] = rand.nextInt(); // gera 1 a 10 e vai gerar quantos o v.Count() mandar

}*/