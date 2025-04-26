package br.com.joaocarloslima;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

public class Meteoro extends Asset {
    private int poder;

    public Meteoro(double x, double y) {
        super(x, y, 2, Direcao.BAIXO, null);
        Random random = new Random();
        int tamanho = random.nextInt(8) + 1; // Define o tamanho do meteoro (1 a 8)
        this.poder = tamanho;
        this.imagem = new ImageView(new Image("file:src/main/resources/br/com/joaocarloslima/images/meteoro/meteoro" + tamanho + ".png"));
        this.imagem.setLayoutX(x);
        this.imagem.setLayoutY(y);
    }

    @Override
    public void mover() {
        y += velocidade; // Faz o meteoro descer
        imagem.setLayoutY(y);
    }

    public void tomarTiro(Tiro tiro) {
        this.poder -= tiro.getPoder();
    }

    public int getPoder() {
        return poder;
    }
}