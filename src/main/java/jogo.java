import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class Forca {
    private String palavraSecreta;
    private String dica;
    private StringBuilder palavraAtual;
    private int tentativasRestantes;
    private boolean jogoAcabou;

    public Forca(String palavraSecreta, String dica, int tentativas) {
        this.palavraSecreta = palavraSecreta.toUpperCase();
        this.dica = dica;
        this.palavraAtual = new StringBuilder("_".repeat(palavraSecreta.length()));
        this.tentativasRestantes = tentativas;
        this.jogoAcabou = false;
    }

    public boolean verificarPalpite(char letra) {
        letra = Character.toUpperCase(letra);
        boolean acertou = false;

        for (int i = 0; i < palavraSecreta.length(); i++) {
            if (palavraSecreta.charAt(i) == letra) {
                palavraAtual.setCharAt(i, letra);
                acertou = true;
            }
        }

        if (!acertou) {
            tentativasRestantes--;
            if (tentativasRestantes == 0) {
                jogoAcabou = true;
            }
        } else if (palavraAtual.toString().equals(palavraSecreta)) {
            jogoAcabou = true;
        }

        return acertou;
    }

    public boolean isJogoAcabou() {
        return jogoAcabou;
    }

    public String getPalavraAtual() {
        return palavraAtual.toString();
    }

    public int getTentativasRestantes() {
        return tentativasRestantes;
    }

    public String getPalavraSecreta() {
        return palavraSecreta;
    }

    public String getDica() {
        return dica;
    }

    public static void main(String[] args) {
        List<String[]> palavrasEDicas = new ArrayList<>();
        palavrasEDicas.add(new String[]{"JAVA", "Linguagem de programação popular"});
        palavrasEDicas.add(new String[]{"PYTHON", "Linguagem de programação com um réptil no nome"});
        palavrasEDicas.add(new String[]{"ELEFANTE", "O maior animal terrestre"});
        palavrasEDicas.add(new String[]{"CACHORRO", "Melhor amigo do homem"});
        palavrasEDicas.add(new String[]{"AVIAO", "Meio de transporte aéreo"});

        Random random = new Random();
        String[] palavraEDica = palavrasEDicas.get(random.nextInt(palavrasEDicas.size()));
        String palavraSecreta = palavraEDica[0];
        String dica = palavraEDica[1];

        Scanner scanner = new Scanner(System.in);
        System.out.println("Bem-vindo ao jogo da Forca!");
        System.out.println("Dica: " + dica);
        System.out.println("Digite o número de tentativas:");
        int tentativas = scanner.nextInt();

        Forca jogo = new Forca(palavraSecreta, dica, tentativas);

        while (!jogo.isJogoAcabou()) {
            System.out.println("Palavra atual: " + jogo.getPalavraAtual());
            System.out.println("Tentativas restantes: " + jogo.getTentativasRestantes());
            System.out.println("Digite uma letra:");
            char letra = scanner.next().charAt(0);
            if (!Character.isLetter(letra)) {
                System.out.println("Por favor, digite uma letra válida.");
                continue;
            }
            if (!jogo.verificarPalpite(letra)) {
                System.out.println("Letra errada. Tente novamente.");
            } else {
                System.out.println("Letra correta!");
            }
        }

        if (jogo.getTentativasRestantes() == 0 && !jogo.getPalavraAtual().equals(jogo.getPalavraSecreta())) {
            System.out.println("Você perdeu! A palavra secreta era: " + jogo.getPalavraSecreta());
        } else {
            System.out.println("Parabéns! Você ganhou! A palavra secreta era: " + jogo.getPalavraSecreta());
        }
        scanner.close();
    }
}
