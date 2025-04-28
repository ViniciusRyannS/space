package br.com.joaocarloslima;

import java.util.Random;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Meteoro extends Asset {
    private int poder;
    private int tamanho;

    public Meteoro(int x, int y, int velocidade, Direcao direcao) {
        super(x, y, velocidade, direcao);

        Random random = new Random();
        this.tamanho = random.nextInt(8) + 1;
        this.poder = tamanho;

        String path = "images/meteoro/meteoro" + tamanho + ".png";
        this.imagem = new ImageView(new Image(App.class.getResource(path).toString()));
        this.imagem.setFitWidth(50);
        this.imagem.setFitHeight(50);
        this.imagem.setLayoutX(x);
        this.imagem.setLayoutY(y);
    }

    public int getPoder() {
        return poder;
    }

    public void tomarTiro(Tiro tiro) {
        this.poder -= tiro.getPoder();
    }
}
