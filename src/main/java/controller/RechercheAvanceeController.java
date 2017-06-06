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

import javafx.application.Platform;

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
import model.DAOCategorie;
import model.DAOGenre;

import java.util.ArrayList;

import javafx.scene.control.ListView;

import javafx.scene.layout.TilePane;

public class RechercheAvanceeController extends Pagination implements Initializable {
	/// Recherche by cat
	@FXML
  Button btnSearchByCat;
	@FXML
  ListView listViewCat;
	public /*static final*/ ObservableList cats = FXCollections.observableArrayList();

	/// Recherche by genre
	@FXML
	Button btnSearchByGenre;
	@FXML
  ListView listViewGenre;
	public /*static final*/ ObservableList genres = FXCollections.observableArrayList();

	/// Container
	@FXML private TilePane content;

  public RechercheAvanceeController() {
		/// Recherche by categorie
		this.btnSearchByCat = new Button();
		this.listViewCat = new ListView();

		/// Recherche by genre
		this.btnSearchByGenre = new Button();
		this.listViewGenre = new ListView();
  }


  @Override
  public void initialize(URL location, ResourceBundle resources) {
		System.out.println("RechercheAvanceeController::initialize");

		/// Feed cats
		cats.addAll(new DAOCategorie().findAllString());
		listViewCat.setItems(cats);

		/// Feed genres
		genres.addAll(new DAOGenre().findAllString());
		listViewGenre.setItems(genres);
  }

	@FXML
	private void actionSearchByCat(ActionEvent event) throws IOException {
		System.out.println("RechercheAvanceeController::actionSearchByCat");
		String cat = this.listViewCat.getSelectionModel().getSelectedItem().toString();
		System.out.println(cat);

		/// Set la Command
		ListOeuvresController.setNextSearch(new Command<ArrayList<Oeuvre>>() {
			String cat = listViewCat.getSelectionModel().getSelectedItem().toString();
			public ArrayList<Oeuvre> get() {
				return new DAOOeuvre().getAllOeuvreByCat(cat);
			}
		});

		/// Switch scene
		this.changeContent(btnSearchByCat, "/listOeuvres.fxml");
		//this.changeScene(btnSearchByCat, "/pAccueil.fxml");
  }

	@FXML
	private void actionSearchByGenre(ActionEvent event) throws IOException {
		System.out.println("RechercheAvanceeController::actionSearchByGenre");
		String genre = this.listViewGenre.getSelectionModel().getSelectedItem().toString();
		System.out.println(genre);

		/// Set la Command
		ListOeuvresController.setNextSearch(new Command<ArrayList<Oeuvre>>() {
			String genre = listViewGenre.getSelectionModel().getSelectedItem().toString();
			public ArrayList<Oeuvre> get() {
				return new DAOOeuvre().getAllOeuvreByGenre(genre);
			}
		});

		/// Switch scene
		this.changeContent(btnSearchByCat, "/listOeuvres.fxml");
		//this.changeScene(btnSearchByCat, "/pAccueil.fxml");
  }
}







