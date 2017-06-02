package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import com.cesi.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DateCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import model.DAOOeuvre;
import model.Oeuvre;

import java.util.ArrayList;

public class OeuvresController implements Initializable {
    private final DAOOeuvre oeuvreRepo;
    @FXML
    private TableView<Oeuvre> oeuvresTable;
    @FXML
    private TableColumn<Oeuvre, String> titleColumn;
    @FXML
    private TableColumn<Oeuvre, String> catColumn;
    @FXML
    private TableColumn<Oeuvre, String> authorColumn;
    @FXML
    private TableColumn<Oeuvre, String> genreColumn;
    @FXML
    private TableColumn<Oeuvre, String> langColumn;
    @FXML
    private TableColumn<Oeuvre, String> originColumn;
    @FXML
    private TableColumn<Oeuvre, DateCell> acqDateColumn;
    @FXML
    private TableColumn<Oeuvre, DateCell> outDateColumn;
    @FXML
    private TableColumn<Oeuvre, String> commentColumn;
    @FXML
    private TableColumn<Oeuvre, Integer> noteColumn;


		/// Pour la recherche
		@FXML
    Button rechercher;

		@FXML
    TextField inputRechercher;

    public OeuvresController() {
        this.oeuvreRepo = new DAOOeuvre();
				this.rechercher = new Button();
				this.inputRechercher = new TextField();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
			System.out.println("OeuvresController::initialize");
      titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
      catColumn.setCellValueFactory(new PropertyValueFactory<>("cat"));
      authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
      genreColumn.setCellValueFactory(new PropertyValueFactory<>("Genre"));
      langColumn.setCellValueFactory(new PropertyValueFactory<>("langue"));
      originColumn.setCellValueFactory(new PropertyValueFactory<>("origine"));
      acqDateColumn.setCellValueFactory(new PropertyValueFactory<>("date_acquisition"));
      outDateColumn.setCellValueFactory(new PropertyValueFactory<>("date_sortie"));
      commentColumn.setCellValueFactory(new PropertyValueFactory<>("comment"));
      noteColumn.setCellValueFactory(new PropertyValueFactory<>("note"));

      oeuvresTable.getItems().addAll(oeuvreRepo.getAllOeuvre());

    }

		@FXML
		private void actionRechercher(ActionEvent event) throws IOException {
			System.out.println("OeuvresController::actionRechercher::action");
			System.out.println(this.inputRechercher.getText());

			/// Do query with value from text field
			ArrayList<Oeuvre> arrayList = this.oeuvreRepo.getAllOeuvreByTitle(this.inputRechercher.getText());
			if (!arrayList.isEmpty()) {
				for (Oeuvre item : arrayList) {
					System.out.println(item.getTitle());
				}
			} else {
				System.out.println("List is empty");
			}

			//oeuvresTable.setItems(

			/// update or load scene ?
			//oeuvresTable.setItems(
			//this.main.pAccueil();
			Stage stage; 
     	Parent root;

			//Stage primaryStage = this.main.getPrimaryStage();
			stage = (Stage) rechercher.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("/pAccueil.fxml"));
			/*
			FXMLLoader loader = new FXMLLoader();
      loader.setLocation(Main.class.getResource("/pAccueil.fxml"));
			BorderPane borderPane = loader.load();
			Scene scene = new Scene(borderPane);
      primaryStage.setScene(scene);
      primaryStage.show();
			*/

			Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
    }

    private Main main;

    public void setMainApp(Main mainApp) {
        this.main = mainApp;

        // Add observable list data to the table
        oeuvresTable.setItems(main.getOeuvresData());
    }
}
