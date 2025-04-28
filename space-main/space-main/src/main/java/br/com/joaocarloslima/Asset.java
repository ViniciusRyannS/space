package br.com.joaocarloslima;

import javafx.scene.image.ImageView;

public abstract class Asset {
    protected int x;
    protected int y;
    protected int velocidade;
    protected ImageView imagem;
    protected Direcao direcao;

    public Asset(int x, int y, int velocidade, Direcao direcao) {
        this.x = x;
        this.y = y;
        this.velocidade = velocidade;
        this.direcao = direcao;
    }

    public void mover() {
        switch (direcao) {
            case CIMA:
                y -= velocidade;
                break;
            case BAIXO:
                y += velocidade;
                break;
            case ESQUERDA:
                x -= velocidade;
                break;
            case DIREITA:
                x += velocidade;
                break;
        }
    }

    public boolean colidiuCom(Asset asset) {
        return x < asset.getX() + 50 &&
               x + 50 > asset.getX() &&
               y < asset.getY() + 50 &&
               y + 50 > asset.getY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ImageView getImagem() {
        return imagem;
    }

    public void setDirecao(Direcao direcao) {
        this.direcao = direcao;
    }
}
