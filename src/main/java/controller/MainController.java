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

import javafx.scene.control.ScrollPane;

public class MainController extends Pagination implements Initializable {

	/// Acceuil
	@FXML Button btnAccueil;

	/// Pour la recherche
	@FXML
  Button btnRechercher;
	@FXML
  TextField inputRechercher;

	/// Recherche avancee
	@FXML
  Button rechercheAvancee;

	/// Add 1 oeuvre
	@FXML
  Button add_oeuvre;

	/// Content node
	@FXML private TilePane content;

  public MainController() {
    //this.oeuvreRepo = new DAOOeuvre();
		this.btnAccueil = new Button();
		this.btnRechercher = new Button();
		this.inputRechercher = new TextField();
		this.add_oeuvre = new Button();
		this.rechercheAvancee = new Button();
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
		try {
			this.changeContent(content, "/listOeuvres.fxml");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
  }

	/// Permet d'afficher la page Acceuil
	@FXML
	private void pageAccueil(ActionEvent event) throws IOException {
		ListOeuvresController.setNextSearch(ListOeuvresController.getCommandAccueil());
		this.changeContent(content, "/listOeuvres.fxml");
		//this.changePage(btnRechercher, "/pAccueil.fxml");
	}

	/// Permet de faire 1 recherche
	@FXML
	private void actionRechercher(ActionEvent event) throws IOException {
		System.out.println("OeuvresController::actionRechercher::action");
		System.out.println(this.inputRechercher.getText());

		/// Set la Command
		ListOeuvresController.setNextSearch(new Command<ArrayList<Oeuvre>>() {
			String recherche = inputRechercher.getText();
			public ArrayList<Oeuvre> get() {
				return new DAOOeuvre().getAllOeuvreByTitle(recherche);
			}
		});

		/// Change page
		this.changeScene(btnRechercher, "/pAccueil.fxml");
  }

	/// Permet d'afficher la page recherche avancee
	@FXML
	public void pageRechercheAvancee(ActionEvent event) throws IOException {
		//this.changePage(rechercher, "/rechercheAvancee.fxml");
		this.changeContent(content, "/rechercheAvancee.fxml");
	}

	/// Permet d'afficher la page de création d'une oeuvre
	@FXML
	public void pageAddOeuvre(ActionEvent event) throws IOException {
		//this.changePage(rechercher, "/rechercheAvancee.fxml");
		this.changeContent(content, "/addOeuvre.fxml");
	}

  private Main main;

  public void setMainApp(Main mainApp) {
      this.main = mainApp;
  }

	@FXML private AddOeuvreController addOeuvreController;
	@FXML private ListOeuvresController listOeuvresController;
	@FXML private RechercheAvanceeController rechercheAvanceeController;
	@FXML private FicheOeuvreController ficheOeuvreController;

}















