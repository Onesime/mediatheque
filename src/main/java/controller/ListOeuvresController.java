package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
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
import model.Command;

import java.util.ArrayList;

import javafx.application.Platform;

import javafx.stage.Window;

public class ListOeuvresController extends Pagination implements Initializable {
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

	/// Prochaine Command à rechercher, static car controller est initialize à chaque load
	static private Command<ArrayList<Oeuvre> > nextSearch = getCommandAccueil();

	/// Permet d'obtenir la commad standard
	static public Command<ArrayList<Oeuvre>> getCommandAccueil() {
		return new Command<ArrayList<Oeuvre>>() { 
			public ArrayList<Oeuvre> get() {
				return new DAOOeuvre().findAll();
			}
		};
	}

	/// Permet de set la prochaine recherche
	public static void setNextSearch(Command<ArrayList<Oeuvre> > command) {
		nextSearch = command;
	}

  public ListOeuvresController() {
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
		System.out.println("ListOeuvresController::initialize");
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

		/// Feed the data
    oeuvresTable.getItems().addAll(nextSearch.get());
  }
}















