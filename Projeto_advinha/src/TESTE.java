import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Integer.parseInt;


class TESTE {
    public static void main(String[] args) throws IOException {

        Random random = new Random();

        Path path = Paths.get("src/", "dict.txt");

        List<String> palavras = Files.readAllLines(path);

        Scanner sc = new Scanner(System.in);


        System.out.println("Informe quantas partidas você deseja jogar hoje: ");
        int quantPartidas = sc.nextInt();
        for (int x = 0; x <= quantPartidas; x++) {

            System.out.println("Digite o tamanho da palavra: ");
            int tamanho = sc.nextInt();

            int index = random.nextInt(261798);

            String palavra = palavras.get(index);

            if (tamanho > 1) {
                System.out.println("Digite a quantidade de tentativas ou pressione enter para padrão: ");
                sc.nextLine();
                String quantTentativas = sc.nextLine();
                int tentativas;
                if (!Objects.equals(quantTentativas, "")) {
                    tentativas = parseInt(quantTentativas);
                } else {
                    tentativas = 6;
                    System.out.println(tentativas);
                }

                while (palavra.length() != tamanho) {
                    index = random.nextInt(261798);
                    palavra = palavras.get(index);
                }

                int result = 1;
                int cont = 1;
                while (cont <= tentativas) {
                    System.out.println("Tentativa " + cont + ": ");
                    String resposta = sc.nextLine();
                    resposta = resposta.toLowerCase();

                    for(String palavraExiste : palavras){
                        int contador = 0;
                        if(palavraExiste.equals(resposta)){
                            break;
                        }
                        else
                            System.out.println("Palavra não existe, Tente outra vez.");
                        resposta = sc.nextLine();
                        resposta = resposta.toLowerCase();
                    }

                    result = palavra.compareTo(resposta);
                    if (result == 0) {
                        System.out.println("Você Venceu!!! A resposta é: " + resposta);
                        break;
                    }
                    else {
                        char[] palavraArray = palavra.toCharArray(); //transforma a palavra correta em array
                        char[] respostaArray = resposta.toCharArray(); // transforma a tentativa em array
                        char[] saida = new char[tamanho];
                        for (int i = 0; i < tamanho; i++) {
                            for (int j = 0; j < tamanho; j++) {
                                if (palavraArray[i] == respostaArray[j] && i != j && saida[j] != (Character.toUpperCase(palavraArray[j]))) {
                                    saida[j] = (Character.toLowerCase(respostaArray[j]));
                                } else if (palavraArray[i] == respostaArray[j] && i == j) {
                                    saida[j] = (Character.toUpperCase(respostaArray[j]));
                                }
                            }
                        }
                        System.out.println(saida);
                        cont++;
                    }
                }
                if (result != 0) {
                    System.out.println("Você perdeu! :/, a resposta correta é: " + palavra);
                }
                sc.close();
            }
        }
    }
}