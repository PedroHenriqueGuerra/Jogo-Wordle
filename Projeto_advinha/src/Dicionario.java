import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Dicionario {

    public Dicionario() throws IOException {
    }

    Scanner sc = new Scanner(System.in);
    Random random = new Random();
    int index = random.nextInt(261798);
    Path path = Paths.get("src/", "dict.txt");
    List<String> palavras = Files.readAllLines(path);
    String palavra = palavras.get(index);

    public String getPalavras(int tamanho, int tentativas) {

        while (palavra.length() != tamanho) {
            index = random.nextInt(261798);
            palavra = palavras.get(index);
        }

        palavra = removerAcentos(palavra);

        int result;
        int cont = 1;

        while (cont <= tentativas) {
            System.out.println("Tentativa " + cont + ": ");

            String respostaUsuario = sc.nextLine();

            respostaUsuario = removerAcentos(respostaUsuario);

            respostaUsuario = respostaUsuario.toLowerCase();

            String vPalavra = palavraExiste(palavras, respostaUsuario);

            int tentativasPalavrasExiste = 3;

            while (tentativasPalavrasExiste > 0) {
                if (vPalavra.equals("Nao encontrado")) {
                    System.out.println("A palavra digitada nao existe no nosso dicionario. Voce possui mais " + tentativasPalavrasExiste + " tentativas.");
                    tentativasPalavrasExiste -= 1;
                    respostaUsuario = sc.nextLine();
                    respostaUsuario = removerAcentos(respostaUsuario);
                    vPalavra = palavraExiste(palavras, respostaUsuario);
                }
                else {
                    respostaUsuario = vPalavra;
                    tentativasPalavrasExiste = -1;
                }
            }

            if (tentativasPalavrasExiste == -1) {
                if (respostaUsuario.length() != tamanho) {
                    String vTamanho = verificaTamanho(tamanho, respostaUsuario, palavras);
                    if (vTamanho.compareTo("Nao encontrado!") == 0) {
                        return "Voce digitou palavras de tamanho invalido! Voce perdeu.\n" +
                                "A palavra correta era " + palavra;
                    }
                    respostaUsuario = vTamanho;
                }

                result = palavra.compareTo(respostaUsuario);

                if (result == 0) {
                    return "Você Venceu!\n" + "A resposta é: " + palavra;
                }
                else {
                    char[] palavraArray = palavra.toCharArray(); //transforma a palavra correta em array
                    char[] respostaArray = respostaUsuario.toCharArray(); // transforma a tentativa em array
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
            else {
                return "Voce digitou muitas palavras invalidas, por isso fim de jogo!\n" + ":( \n" + "A resposta correta é: " + palavra;
            }
        }
        sc.close();
        return "Voce perdeu!\n" + ":( \n" + "A resposta correta é: " + palavra;
    }

    public String palavraExiste(List<String> palavras, String palavra) {
        for (String palavraProcurada : palavras) {
            if (palavraProcurada.equals(palavra)) {
                return palavra;
            }
        }
        return "Nao encontrado";
    }

    public String verificaTamanho(int tamanho, String resposta, List<String> palavras) {
        int vTamanho = 3;
        while (vTamanho > 0) {
            System.out.println("A palavra digitada tem tamanho " + resposta.length() + ", favor inserir palavra de tamanho " + tamanho);
            System.out.println("Voce tem mais " + vTamanho + " chances.");

            resposta = sc.nextLine();

            resposta = removerAcentos(resposta);

            int tentativasPalavrasExiste = 3;

            String vPalavra = palavraExiste(palavras, resposta);

            while (tentativasPalavrasExiste > 0) {
                if (vPalavra.equals("Nao encontrado")) {
                    System.out.println("A palavra digitada nao existe no nosso dicionario. Voce possui mais " + tentativasPalavrasExiste + " tentativas.");
                    tentativasPalavrasExiste -= 1;
                    resposta = sc.nextLine();
                    resposta = removerAcentos(resposta);
                    vPalavra = palavraExiste(palavras, resposta);
                }
                else {
                    resposta = vPalavra;
                    tentativasPalavrasExiste = -1;
                }
            }

            if (tentativasPalavrasExiste == 0) {
                return "Nao encontrado!";
            }

            if (resposta.length() == tamanho) {
                return resposta;
            }
            else {
                vTamanho -= 1;
            }
        }
        return "Nao encontrado!";
    }

    public static String removerAcentos(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

}


