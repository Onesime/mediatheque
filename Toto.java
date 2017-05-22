package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Toto implements Initializable{

    @FXML
    Button bOk;
    @FXML
    Label lbTest;

    private void handleButtonAction(ActionEvent event) {
    	// Button was clicked, do something...
      lbTest.setText("Button Action\n");
			
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        bOk.setOnAction(this::handleButtonAction);
    }

}
