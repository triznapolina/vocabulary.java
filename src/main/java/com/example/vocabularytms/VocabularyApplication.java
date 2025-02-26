package com.example.vocabularytms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class VocabularyApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Надо прорисовать окно
        // Создать окно
        // Задать ему параметры
        // Нанести на это окно объекты
        // А потом все это сделать видимым - stage.show();
        FXMLLoader fxmlLoader = new FXMLLoader(VocabularyApplication.class.getResource("vocabularyTable.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 800);
        stage.setTitle("Vocabulary TMS");
        stage.setScene(scene);

        // Не надо забывать сделать окно видимым
        stage.show();
        stage.setResizable(false);
    }

    public static void main(String[] args) {
        launch();
    }
}