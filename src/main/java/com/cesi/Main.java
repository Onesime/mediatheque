package com.cesi;

import controller.OeuvresController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Oeuvre;

import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;

    /**
     * Définition des Table Data dans une ObservableList.
     */
    final ObservableList<Oeuvre> oeuvresData = FXCollections.observableArrayList(
        new Oeuvre("Cornelia", "Movie", "truc", "sf"),
        new Oeuvre("Werner", "Video Game", "chose", "fantasy"),
        new Oeuvre("Anna", "Book", "glup", "thriller" ),
        new Oeuvre("Martin", "Music", "fiofjf", "rock")
    );

    public ObservableList<Oeuvre> getOeuvresData() {
        return oeuvresData;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");
        this.pAccueil();

    }

    public void pAccueil() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/pAccueil.fxml"));
            BorderPane logButton = loader.load();

            //loader.setController(new LogButton());

            oeuvresData.add(new Oeuvre("Werner", "Video Game", "chose", "fantasy"));

            OeuvresController controller = loader.getController();
            controller.setMainApp(this);

            Scene scene = new Scene(logButton);
            primaryStage.setScene(scene);
            primaryStage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void BoutonCo() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/BoutonCo.fxml"));
            AnchorPane BoutonCo = loader.load();

            //loader.setController(new Toto());
            loader.setController(new ConnectionButton());

            Scene scene = new Scene(BoutonCo);
            primaryStage.setScene(scene);
            primaryStage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}// a suppr
