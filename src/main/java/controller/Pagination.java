package controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;

import java.io.IOException;

import javafx.application.Platform;

public class Pagination {
	
	@FXML
	public void changeScene(Button button, String page) throws IOException {
		Stage stage; 
   	Parent root;

		stage = (Stage) button.getScene().getWindow();
		//root = FXMLLoader.load(getClass().getResource(page));
		root = FXMLLoader.load(getClass().getResource(page));
		Platform.runLater(() -> {
			Scene scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
		});
	}

	/// Change le content du node
	@FXML
	public void changeContent(TilePane tilePane, String page) throws IOException {
		tilePane.getChildren().clear();
		tilePane.getChildren().add(FXMLLoader.load(getClass().getResource(page)));
	}

	@FXML
	public void changeContent(Node node, String page) throws IOException {
		TilePane tilePane = (TilePane)node.getScene().lookup("#content");
		tilePane.getChildren().clear();
		tilePane.getChildren().add(FXMLLoader.load(getClass().getResource(page)));
	}
}
