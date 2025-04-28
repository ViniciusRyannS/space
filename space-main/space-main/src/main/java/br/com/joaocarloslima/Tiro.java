package br.com.joaocarloslima;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Tiro extends Asset {
    private int poder;

    public Tiro(int x, int y, int velocidade, Direcao direcao, int poder) {
        super(x, y, velocidade, direcao);
        this.poder = poder;

        String path = "images/laser/laser" + poder + ".png";
        this.imagem = new ImageView(new Image(App.class.getResource(path).toString()));
        this.imagem.setFitWidth(20);
        this.imagem.setFitHeight(40);
        this.imagem.setLayoutX(x);
        this.imagem.setLayoutY(y);
    }

    public int getPoder() {
        return poder;
    }
}
