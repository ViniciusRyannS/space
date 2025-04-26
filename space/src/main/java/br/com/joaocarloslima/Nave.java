package br.com.joaocarloslima;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Nave extends Asset {
    private int vida;

    public Nave(double x, double y) {
        super(x, y, 5, Direcao.CIMA, new ImageView(new Image("file:src/main/resources/br/com/joaocarloslima/images/ships/playerShip1_blue.png")));
        this.vida = 3; // Define a vida inicial da nave
        this.imagem.setLayoutX(x);
        this.imagem.setLayoutY(y);
    }

    @Override
    public void mover() {
        if (direcao == Direcao.ESQUERDA && x > 0) {
            x -= velocidade;
        } else if (direcao == Direcao.DIREITA && x < 640 - 50) { // 640 é a largura da tela, 50 é a largura da nave
            x += velocidade;
        }
        imagem.setLayoutX(x); // Atualiza a posição da imagem na tela
    }

    /**
     * Cria um tiro na posição da nave.
     * @param poder O poder do tiro.
     * @return O objeto Tiro criado.
     */
    public Tiro atirar(int poder) {
        return new Tiro(x + 25, y, poder); // Ajusta a posição do tiro para sair do centro da nave
    }

    public void tomarDano() {
        vida--;
        System.out.println("A nave tomou dano! Vida restante: " + vida);
        if (vida <= 0) {
            System.out.println("Game Over!"); // Exibe mensagem de fim de jogo
            // Adicione lógica para encerrar o jogo ou reiniciar
        }
    }

    public int getVida() {
        return vida;
    }
}