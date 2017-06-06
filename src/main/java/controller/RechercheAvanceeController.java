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

import model.*;

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

	/// Recherche by note
	@FXML
	Button btnSearchByNote;
	@FXML
	ListView listViewNote;
	public /*static final*/ ObservableList notes = FXCollections.observableArrayList();

	/// Recherche by date
	@FXML
	Button btnSearchByDate;
	@FXML
	TextField inputBegin;
	@FXML
	TextField inputEnd;

  public RechercheAvanceeController() {
		/// Recherche by categorie
		this.btnSearchByCat = new Button();
		this.listViewCat = new ListView();

		/// Recherche by genre
		this.btnSearchByGenre = new Button();
		this.listViewGenre = new ListView();

	  /// Recherche by note
	  this.btnSearchByNote = new Button();
	  this.listViewNote = new ListView();

	  /// date
	  this.inputBegin = new TextField();
	  this.inputEnd = new TextField();
  }


  @Override
  public void initialize(URL location, ResourceBundle resources) {

		/// Feed cats
		cats.addAll(new DAOCategorie().findAllString());
		listViewCat.setItems(cats);

		/// Feed genres
		genres.addAll(new DAOGenre().findAllString());
		listViewGenre.setItems(genres);

	  /// Feed notes
	  notes.addAll("0", "1", "3", "4", "5");
	  listViewNote.setItems(notes);


  }

	@FXML
	private void actionSearchByCat(ActionEvent event) throws IOException {
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

	@FXML
	private void actionSearchByNote(ActionEvent event) throws IOException {

		int note = Integer.valueOf(this.listViewNote.getSelectionModel().getSelectedItem().toString());
		System.out.println(note);

		/// Set la Command
		ListOeuvresController.setNextSearch(new Command<ArrayList<Oeuvre>>() {
			int noteB = note;
			public ArrayList<Oeuvre> get() {
				return new DAOOeuvre().getAllOeuvreByNote(note);
			}
		});

		/// Switch scene
		this.changeContent(btnSearchByNote, "/listOeuvres.fxml");
		//this.changeScene(btnSearchByCat, "/pAccueil.fxml");
	}

	@FXML
	private void actionSearchByDate(ActionEvent event) throws IOException {

		/// Set la Command
		ListOeuvresController.setNextSearch(new Command<ArrayList<Oeuvre>>() {
			String begin = inputBegin.getText();
			String end = inputEnd.getText();
			public ArrayList<Oeuvre> get() {
				System.out.println(begin + " " + end);
				return new DAOOeuvre().getAllOeuvreByDate(begin, end);
			}
		});

		/// Switch scene
		this.changeContent(btnSearchByNote, "/listOeuvres.fxml");
		//this.changeScene(btnSearchByCat, "/pAccueil.fxml");
	}
}







