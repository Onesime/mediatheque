package com.cesi;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import scene.AdOeuvreScene;

public class ConnectionButton implements Initializable{
    protected final FXMLLoader loader = new FXMLLoader();
    protected final Stage stage = new Stage();

    @FXML
    Button ad_oeuvre;

    private void handleButtonAction(ActionEvent event) {
        ad_oeuvre.setOnAction(loader.setLocation(Main.class.getResource("/ad.oeuvre.fxml")).show());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        ad_oeuvre.setOnAction(this::handleButtonAction);
    }

}

