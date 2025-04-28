package br.com.joaocarloslima;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Nave extends Asset {

    public Nave(int x, int y, int velocidade, Direcao direcao) {
        super(x, y, velocidade, direcao);
        this.imagem = new ImageView(new Image(App.class.getResource("images/ships/playerShip1_blue.png").toString()));
        this.imagem.setFitWidth(50);
        this.imagem.setFitHeight(50);
        this.imagem.setLayoutX(x);
        this.imagem.setLayoutY(y);
    }

    @Override
    public void mover() {
        switch (direcao) {
            case ESQUERDA:
                if (x > 0) {
                    x -= velocidade;
                }
                break;
            case DIREITA:
                if (x < 640 - 50) {
                    x += velocidade;
                }
                break;
            default:
                break;
        }
    }

    public Tiro atirar(int poder) {
        return new Tiro(x + 15, y - 20, 10, Direcao.CIMA, poder);
    }
}
