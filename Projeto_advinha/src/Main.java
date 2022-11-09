import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

import static java.lang.Integer.parseInt;


class Main {
    public static void main(String[] args) throws IOException {

        Dicionario dict = new Dicionario();

        Scanner sc = new Scanner(System.in);

        System.out.println("Informe quantas partidas você deseja jogar hoje: ");
        int quantPartidas = sc.nextInt();
        if (quantPartidas > 0) {
            for (int x = 0; x <= quantPartidas; x++) {

                System.out.println("Digite o tamanho da palavra: ");
                int tamanho = sc.nextInt();

                if (tamanho > 1) {
                    System.out.println("Digite a quantidade de tentativas ou pressione enter para padrão: ");

                    sc.nextLine();

                    String quantTentativas = sc.nextLine();

                    int tentativas;

                    if (!Objects.equals(quantTentativas, "")) {
                        tentativas = parseInt(quantTentativas);
                    } else {
                        tentativas = 6;
                        System.out.println("Voce tem " + tentativas + " tentativas!");
                    }
                    System.out.println(dict.getPalavras(tamanho, tentativas));
                }
                else {
                    System.out.println("Opcao invalida, programa finalizado!");
                }
            }
        }
        else{
            System.out.println("Opcao invalida, programa finalizado!");
        }
    }
}