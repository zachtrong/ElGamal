package com.zachtrong.elgamaljava;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ElGamalApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ElGamalApplication.class.getResource("application.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        scene.getRoot().setStyle("-fx-font-family: 'serif'");
        scene.getStylesheets().addAll(this.getClass().getResource("application.css").toExternalForm());

        stage.setTitle("ElGamal Encryption");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}