package br.com.joaocarloslima;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Jogo {
    public Nave nave;
    public List<Asset> assets = new ArrayList<>();
    private int pontos = 0;

    public Jogo() {
        nave = new Nave(320, 400); // Posição inicial da nave
    }

    /**
     * Cria um novo tiro disparado pela nave.
     * @return O objeto Tiro criado.
     */
    public Tiro atirar() {
        Tiro tiro = nave.atirar(1); // Cria um tiro com poder 1
        assets.add(tiro); // Adiciona o tiro à lista de assets
        return tiro;
    }

    /**
     * Cria um novo meteoro em uma posição aleatória no topo da tela.
     * @return O objeto Meteoro criado.
     */
    public Meteoro criarMeteoro() {
        Random random = new Random();
        double x = random.nextInt(640); // Posição aleatória no eixo X
        Meteoro meteoro = new Meteoro(x, 0); // Cria o meteoro no topo da tela
        assets.add(meteoro); // Adiciona o meteoro à lista de assets
        return meteoro;
    }

    /**
     * Incrementa a pontuação do jogador.
     */
    public void pontuar() {
        pontos++;
    }

    /**
     * Retorna a pontuação atual do jogador.
     * @return A pontuação atual.
     */
    public int getPontos() {
        return pontos;
    }
}