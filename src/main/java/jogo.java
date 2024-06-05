import java.util.Scanner;

class Forca {
    private String palavraSecreta;
    private StringBuilder palavraAtual;
    private int tentativasRestantes;
    private boolean jogoAcabou;

    public Forca(String palavraSecreta, int tentativas) {
        this.palavraSecreta = palavraSecreta.toUpperCase();
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite a palavra secreta:");
        String palavraSecreta = scanner.nextLine();
        System.out.println("Digite o número de tentativas:");
        int tentativas = scanner.nextInt();
        Forca jogo = new Forca(palavraSecreta, tentativas);

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

        if (jogo.getTentativasRestantes() == 0) {
            System.out.println("Você perdeu! A palavra secreta era: " + jogo.getPalavraSecreta());
        } else {
            System.out.println("Parabéns! Você ganhou!");
        }
        scanner.close();
    }
}
