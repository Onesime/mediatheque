package com.cesi;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

/// Controller
import controller.MainController;
//import controller.RechercheController;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Oeuvre;
import model.DAOOeuvre;
import java.util.ArrayList;

import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;

		//ArrayList<Oeuvre> toto = new DAOOeuvre().getAllOeuvre();

    /**
     * Définition des Table Data dans une ObservableList.
     */
    /*final ObservableList<Oeuvre> oeuvresData = FXCollections.observableArrayList(new DAOOeuvre().findAll());

    public ObservableList<Oeuvre> getOeuvresData() {
        return oeuvresData;
    }*/

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Médiathèque");
        this.pAccueil();

    }

    public void pAccueil() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/pAccueil.fxml"));
            BorderPane borderPane = loader.load();
					
						/// Controller pour oeuvre
            MainController controller = loader.getController();
            controller.setMainApp(this);

						/// Controller recherche
						//Button rechercher = loader.load();
						//RechercheController rechercheController = loader.getController();
						//rechercheController.setMainApp(this);

            Scene scene = new Scene(borderPane);
            primaryStage.setScene(scene);
            primaryStage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
		/*
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
		*/

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
