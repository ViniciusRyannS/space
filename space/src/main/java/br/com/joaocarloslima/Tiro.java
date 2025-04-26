package br.com.joaocarloslima;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tiro extends Asset {
    private int poder;

    public Tiro(double x, double y, int poder) {
        super(x, y, 10, Direcao.CIMA, new ImageView(new Image("file:src/main/resources/br/com/joaocarloslima/images/laser/laserBlue.png")));
        this.poder = poder;
        this.imagem.setLayoutX(x);
        this.imagem.setLayoutY(y);
    }

    @Override
    public void mover() {
        y -= velocidade; // Faz o tiro subir
        imagem.setLayoutY(y);
    }

    public int getPoder() {
        return poder;
    }
}