package com.cesi;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ConnectionButton implements Initializable{

    @FXML
    Button bOk;
    @FXML
    Label lbTest;

    private void handleButtonAction(ActionEvent event) {
        Connect.connect();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        bOk.setOnAction(this::handleButtonAction);
    }


}
