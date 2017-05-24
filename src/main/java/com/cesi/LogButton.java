package com.cesi;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class LogButton implements Initializable{

    @FXML
    Button logButton;

    private void loginButtonClicked(ActionEvent event) {
        System.out.println("logged");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        logButton.setOnAction(this::loginButtonClicked);
    }


}