package br.com.joaocarloslima;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class Controller implements Initializable {

    @FXML
    private ImageView imgNave;

    @FXML
    private AnchorPane pane;

    @FXML
    private Label pontos;

    @FXML
    private Label level;

    private int frame = 0;

    private Jogo jogo = new Jogo();

    public void keyHandler(KeyEvent event) {
        switch (event.getCode()) {
            case LEFT:
                jogo.nave.setDirecao(Direcao.ESQUERDA);
                jogo.nave.mover();
                imgNave.setLayoutX(jogo.nave.getX());
                imgNave.setLayoutY(jogo.nave.getY());
                break;
            case RIGHT:
                jogo.nave.setDirecao(Direcao.DIREITA);
                jogo.nave.mover();
                imgNave.setLayoutX(jogo.nave.getX());
                imgNave.setLayoutY(jogo.nave.getY());
                break;
            case SPACE:
                atirar();
                break;
            default:
                break;
        }
    }

    private void atualizar() {
        try {
            frame++;
            if (frame % 100 == 0) {
                var meteoro = jogo.criarMeteoro();
                pane.getChildren().add(meteoro.getImagem());
            }

            for (int i = 0; i < jogo.assets.size(); i++) {
                Asset asset = jogo.assets.get(i);
                asset.mover();

                // 🔥 NOVO trecho: Verificar colisão Meteoro x Nave
                if (asset instanceof Meteoro) {
                    if (asset.colidiuCom(jogo.nave)) {
                        System.out.println("Game Over! Nave colidiu com meteoro.");
                        System.exit(0);
                    }
                }

                if (asset instanceof Tiro) {
                    Tiro tiro = (Tiro) asset;
                    for (int j = 0; j < jogo.assets.size(); j++) {
                        Asset outro = jogo.assets.get(j);
                        if (outro instanceof Meteoro) {
                            if (tiro.colidiuCom(outro)) {
                                ((Meteoro) outro).tomarTiro(tiro);
                                if (((Meteoro) outro).getPoder() <= 0) {
                                    pane.getChildren().remove(outro.getImagem());
                                    jogo.assets.remove(outro);
                                }
                                pane.getChildren().remove(tiro.getImagem());
                                jogo.assets.remove(tiro);
                                jogo.pontuar();
                                i--;
                                break;
                            }
                        }
                    }
                }

                ImageView img = asset.getImagem();
                img.setLayoutX(asset.getX());
                img.setLayoutY(asset.getY());

                if (asset.getY() < -50 || asset.getY() > 480 || asset.getX() < -50 || asset.getX() > 640) {
                    pane.getChildren().remove(asset.getImagem());
                    jogo.assets.remove(asset);
                    i--;
                }
            }

            pontos.setText(String.valueOf(jogo.getPontos()));
            level.setText(String.valueOf(jogo.getNivel()));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void atirar() {
        var tiro = jogo.atirar();
        pane.getChildren().add(tiro.getImagem());
    }

    public void clockThread() {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(20);
                    Platform.runLater(() -> atualizar());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clockThread();
        imgNave.setLayoutX(jogo.nave.getX());
        imgNave.setLayoutY(jogo.nave.getY());
    }
}
