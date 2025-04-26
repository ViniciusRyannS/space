package br.com.joaocarloslima;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class Controller {

    @FXML
    private Pane gamePane;

    private Jogo jogo;
    private List<String> teclasPressionadas = new ArrayList<>();
    private long ultimoMeteoro = 0;

    @FXML
    public void initialize() {
        jogo = new Jogo();
        gamePane.getChildren().add(jogo.nave.getImagem()); // Adiciona a nave ao painel do jogo

        // Inicia o loop do jogo
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                atualizar();
            }
        };
        gameLoop.start();
    }

    public void keyHandler(KeyEvent event) {
        String tecla = event.getCode().toString();
        if (!teclasPressionadas.contains(tecla)) {
            teclasPressionadas.add(tecla);
        }
    
        // Atira quando a tecla ESPAÇO for pressionada
        if (tecla.equals("SPACE")) {
            System.out.println("Tecla ESPAÇO pressionada. Criando tiro...");
            Tiro tiro = jogo.atirar(); // Cria um novo tiro
            gamePane.getChildren().add(tiro.getImagem()); // Adiciona a imagem do tiro ao gamePane
        }
    }

    public void keyReleasedHandler(KeyEvent event) {
        String tecla = event.getCode().toString();
        teclasPressionadas.remove(tecla);
    }

    private void atualizar() {
        long agora = System.currentTimeMillis();
        if (agora - ultimoMeteoro > 1000) { // Cria um meteoro a cada 1 segundo
            Meteoro meteoro = jogo.criarMeteoro();
            gamePane.getChildren().add(meteoro.getImagem()); // Adiciona a imagem do meteoro ao gamePane
            ultimoMeteoro = agora;
        }
    
        // Movimenta a nave com base nas teclas pressionadas
        if (teclasPressionadas.contains("LEFT")) {
            jogo.nave.direcao = Direcao.ESQUERDA;
            jogo.nave.mover();
        } else if (teclasPressionadas.contains("RIGHT")) {
            jogo.nave.direcao = Direcao.DIREITA;
            jogo.nave.mover();
        }
    
        // Atualiza os meteoros e tiros
        for (Asset asset : jogo.assets) {
            asset.mover();
            if (asset instanceof Meteoro) {
                Meteoro meteoro = (Meteoro) asset;
                if (meteoro.getY() > 480) { // Remove meteoros que saíram da tela
                    gamePane.getChildren().remove(meteoro.getImagem());
                }
            } else if (asset instanceof Tiro) {
                Tiro tiro = (Tiro) asset;
                if (tiro.getY() < 0) { // Remove tiros que saíram da tela
                    gamePane.getChildren().remove(tiro.getImagem());
                }
            }
        }
    
        // Detecta colisões e atualiza o jogo
        detectarColisoes();
    }

    private void detectarColisoes() {
        List<Asset> removidos = new ArrayList<>();
    
        // Verifica colisões entre tiros e meteoros
        for (Asset asset : jogo.assets) {
            if (asset instanceof Tiro) {
                Tiro tiro = (Tiro) asset;
                for (Asset outro : jogo.assets) {
                    if (outro instanceof Meteoro && tiro.colidiuCom(outro)) {
                        Meteoro meteoro = (Meteoro) outro;
                        meteoro.tomarTiro(tiro);
                        if (meteoro.getPoder() <= 0) {
                            removidos.add(meteoro);
                            jogo.pontuar();
                        }
                        removidos.add(tiro);
                    }
                }
            }
        }
    
        // Verifica colisões entre a nave e meteoros
        for (Asset asset : jogo.assets) {
            if (asset instanceof Meteoro) {
                Meteoro meteoro = (Meteoro) asset;
                if (jogo.nave.colidiuCom(meteoro)) {
                    System.out.println("Colisão detectada entre a nave e um meteoro!");
                    jogo.nave.tomarDano(); // A nave toma dano
                    removidos.add(meteoro); // Remove o meteoro após a colisão
                }
            }
        }
    
        // Remove os objetos que colidiram
        jogo.assets.removeAll(removidos);
        for (Asset asset : removidos) {
            gamePane.getChildren().remove(asset.getImagem());
        }
    }
}