import java.util.ArrayList;  // Importa a classe ArrayList para uso de listas dinâmicas
import java.util.List;       // Importa a interface List para listas tipadas
import java.util.Random;     // Importa a classe Random para geração de números aleatórios
import java.util.Scanner;    // Importa a classe Scanner para leitura de entrada do usuário

class Forca {
    private String palavraSecreta;   // Armazena a palavra secreta
    private String dica;             // Armazena a dica associada à palavra secreta
    private StringBuilder palavraAtual; // Armazena a palavra sendo formada pelo jogador
    private int tentativasRestantes; // Armazena o número de tentativas restantes
    private boolean jogoAcabou;      // Indica se o jogo acabou

    // Construtor que inicializa a palavra secreta, dica e número de tentativas
    public Forca(String palavraSecreta, String dica, int tentativas) {
        this.palavraSecreta = palavraSecreta.toUpperCase();  // Define a palavra secreta em maiúsculas
        this.dica = dica;  // Armazena a dica
        this.palavraAtual = new StringBuilder("_".repeat(palavraSecreta.length()));  // Inicializa a palavra atual com underscores
        this.tentativasRestantes = tentativas;  // Define o número de tentativas restantes
        this.jogoAcabou = false;  // Inicializa o jogo como não terminado
    }

    // Método que verifica se uma letra está na palavra secreta
    public boolean verificarPalpite(char letra) {
        letra = Character.toUpperCase(letra);  // Converte a letra para maiúscula
        boolean acertou = false;  // Variável para indicar se a letra está correta

        // Percorre a palavra secreta e verifica se a letra está presente
        for (int i = 0; i < palavraSecreta.length(); i++) {
            if (palavraSecreta.charAt(i) == letra) {
                palavraAtual.setCharAt(i, letra);  // Substitui o underscore pela letra correta
                acertou = true;  // Marca que a letra foi encontrada
            }
        }

        // Se a letra não foi encontrada
        if (!acertou) {
            tentativasRestantes--;  // Decrementa o número de tentativas restantes
            if (tentativasRestantes == 0) {  // Se acabaram as tentativas
                jogoAcabou = true;  // Marca que o jogo acabou
            }
        } else if (palavraAtual.toString().equals(palavraSecreta)) {  // Se a palavra foi completamente adivinhada
            jogoAcabou = true;  // Marca que o jogo acabou
        }

        return acertou;  // Retorna se a letra foi encontrada ou não
    }

    // Método que retorna se o jogo acabou
    public boolean isJogoAcabou() {
        return jogoAcabou;
    }

    // Método que retorna a palavra atual sendo formada pelo jogador
    public String getPalavraAtual() {
        return palavraAtual.toString();
    }

    // Método que retorna o número de tentativas restantes
    public int getTentativasRestantes() {
        return tentativasRestantes;
    }

    // Método que retorna a palavra secreta
    public String getPalavraSecreta() {
        return palavraSecreta;
    }

    // Método que retorna a dica associada à palavra secreta
    public String getDica() {
        return dica;
    }

    // Método principal que executa o jogo
    public static void main(String[] args) {
        // Cria uma lista de palavras e suas respectivas dicas
        List<String[]> palavrasEDicas = new ArrayList<>();
        palavrasEDicas.add(new String[]{"JAVA", "Linguagem de programação popular"});
        palavrasEDicas.add(new String[]{"PYTHON", "Linguagem de programação com um réptil no nome"});
        palavrasEDicas.add(new String[]{"ELEFANTE", "O maior animal terrestre"});
        palavrasEDicas.add(new String[]{"CACHORRO", "Melhor amigo do homem"});
        palavrasEDicas.add(new String[]{"AVIAO", "Meio de transporte aéreo"});

        Random random = new Random();  // Cria uma instância de Random para selecionar palavras aleatoriamente
        String[] palavraEDica = palavrasEDicas.get(random.nextInt(palavrasEDicas.size()));  // Seleciona uma palavra e dica aleatoriamente
        String palavraSecreta = palavraEDica[0];  // Obtém a palavra secreta
        String dica = palavraEDica[1];  // Obtém a dica

        Scanner scanner = new Scanner(System.in);  // Cria uma instância de Scanner para leitura de entrada do usuário
        System.out.println("Bem-vindo ao jogo da Forca!");  // Mensagem de boas-vindas
        System.out.println("Dica: " + dica);  // Exibe a dica da palavra secreta
        System.out.println("Digite o número de tentativas:");  // Solicita o número de tentativas ao jogador
        int tentativas = scanner.nextInt();  // Lê o número de tentativas

        Forca jogo = new Forca(palavraSecreta, dica, tentativas);  // Cria uma instância do jogo da forca com a palavra secreta, dica e tentativas

        // Loop principal do jogo
        while (!jogo.isJogoAcabou()) {
            System.out.println("Palavra atual: " + jogo.getPalavraAtual());  // Exibe a palavra atual
            System.out.println("Tentativas restantes: " + jogo.getTentativasRestantes());  // Exibe o número de tentativas restantes
            System.out.println("Digite uma letra:");  // Solicita uma letra ao jogador
            char letra = scanner.next().charAt(0);  // Lê a letra digitada

            // Verifica se a entrada é uma letra válida
            if (!Character.isLetter(letra)) {
                System.out.println("Por favor, digite uma letra válida.");  // Mensagem de erro para entrada inválida
                continue;  // Continua para a próxima iteração do loop
            }

            // Verifica o palpite do jogador
            if (!jogo.verificarPalpite(letra)) {
                System.out.println("Letra errada. Tente novamente.");  // Mensagem para letra incorreta
            } else {
                System.out.println("Letra correta!");  // Mensagem para letra correta
            }
        }

        // Verifica o resultado do jogo e exibe a mensagem apropriada
        if (jogo.getTentativasRestantes() == 0 && !jogo.getPalavraAtual().equals(jogo.getPalavraSecreta())) {
            System.out.println("Você perdeu! A palavra secreta era: " + jogo.getPalavraSecreta());  // Mensagem de derrota
        } else {
            System.out.println("Parabéns! Você ganhou! A palavra secreta era: " + jogo.getPalavraSecreta());  // Mensagem de vitória
        }

        scanner.close();  // Fecha o Scanner
    }
}
