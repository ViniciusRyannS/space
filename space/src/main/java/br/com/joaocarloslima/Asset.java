package br.com.joaocarloslima;

import javafx.scene.image.ImageView;

public abstract class Asset {
    protected double x, y;
    protected double velocidade;
    protected Direcao direcao;
    protected ImageView imagem;

    public Asset(double x, double y, double velocidade, Direcao direcao, ImageView imagem) {
        this.x = x;
        this.y = y;
        this.velocidade = velocidade;
        this.direcao = direcao;
        this.imagem = imagem;
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
        imagem.setLayoutX(x); // Atualiza a posição X da imagem
        imagem.setLayoutY(y); // Atualiza a posição Y da imagem
    }

    /**
     * Verifica se este objeto colidiu com outro objeto.
     * @param outro O outro objeto a ser verificado.
     * @return true se os objetos colidirem, false caso contrário.
     */
    public boolean colidiuCom(Asset outro) {
        if (this.imagem == null || outro.imagem == null) {
            return false; // Evita NullPointerException
        }
        return this.imagem.getBoundsInParent().intersects(outro.imagem.getBoundsInParent());
    }

    public ImageView getImagem() {
        return imagem;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}