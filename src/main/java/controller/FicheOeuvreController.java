package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;

import model.DAOCategorie;
import model.Oeuvre;
import model.DAOOeuvre;
import model.Command;
import model.Morceau;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Label;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import javafx.scene.control.TableColumn;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.sql.Time;

public class FicheOeuvreController extends Pagination implements Initializable {

    private static int idOeuvre = 1;

    public static void setIdOeuvre(int id) {
        idOeuvre = id;
    }

    @FXML
    private Label titreO = new Label();
    @FXML
    private Label catO = new Label();
    @FXML
    private Label acqO = new Label();
    @FXML
    private Label sortieO = new Label();
    @FXML
    private Label auteurO = new Label();
    @FXML
    private Label genreO = new Label();
    @FXML
    private Label langueO = new Label();
    @FXML
    private Label origineO = new Label();
    @FXML
    private Label supportO = new Label();
    @FXML
    private Label noteO = new Label();
    @FXML
    private Label commentO = new Label();
    @FXML
    private Label plateformeO = new Label();

    @FXML
    Button modButton;

		/// Table
		@FXML
		private TableView<Morceau> morceauxTable;
		@FXML
		private TableColumn<Morceau, String> nameColumn;
		@FXML
		private TableColumn<Morceau, Time> dureeColumn;


		private Oeuvre oeuvre;

		@FXML
		private ListMorceauxController listMorceauxController;

    public FicheOeuvreController() {
			oeuvre = new DAOOeuvre().find(idOeuvre);
			listMorceauxController = new ListMorceauxController(oeuvre);
    }

    public void initialize(URL location, ResourceBundle resources) {
        if (oeuvre == null) {
            System.out.println("ID invalide");
            return;
        }

        titreO.setText(oeuvre.getTitle());
        catO.setText(oeuvre.getCat());
        auteurO.setText(oeuvre.getAuthor());
        genreO.setText(oeuvre.getGenre());
        langueO.setText(oeuvre.getLangue());
        origineO.setText(oeuvre.getOrigine());
        acqO.setText(oeuvre.getStringDate_acquisition());
        sortieO.setText(oeuvre.getStringDate_sortie());
        commentO.setText(oeuvre.getComment());
        noteO.setText(Integer.toString(oeuvre.getNote()));

				this.listMorceauxController.initialize(morceauxTable, nameColumn, dureeColumn);

    }

    public void ModAction(ActionEvent event) throws IOException {
        ModOeuvreController.setIdOeuvre(idOeuvre);
        this.changeContent(modButton, "/modOeuvre.fxml");
    }
   

		/// Delete
		@FXML
    Button btnSupprimer = new Button();
		public void deleteOeuvre(ActionEvent event) throws IOException {
			new DAOOeuvre().delete(oeuvre);
			Alert dialog = new Alert(
				Alert.AlertType.CONFIRMATION,
				"L'oeuvre " + oeuvre.getTitle() + ", id:" + oeuvre.getId() + " a bien était suppprimer.");
			dialog.showAndWait()
				.filter(response -> response.equals(ButtonType.OK))
				.ifPresent(response -> System.out.println("ok"));
			this.changeContent(btnSupprimer, "/listOeuvres.fxml");
		}


}
