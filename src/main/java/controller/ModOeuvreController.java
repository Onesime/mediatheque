package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.DAOCategorie;
import model.DAOOeuvre;
import model.Oeuvre;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import model.Morceau;
import model.DAOMorceau;
import java.sql.Time;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Pair;
import model.DAOCategorie;

import java.util.ArrayList;

import javafx.event.Event;

import javafx.scene.control.Alert;

/**
 * Created by Jordan TROADEC on 07/06/2017.
 */
public class ModOeuvreController extends Pagination implements Initializable {

    private static int idOeuvre = 1;

    public static void setIdOeuvre(int id) {
			idOeuvre = id;
    }

    @FXML
    private TextField titreInput = new TextField();
    @FXML
    private ChoiceBox listViewCat = new ChoiceBox();
    @FXML
    private TextField dateAcquisitionInput = new TextField();
    @FXML
    private TextField dateSortieInput = new TextField();
    @FXML
    private TextField auteurInput = new TextField();
    @FXML
    private TextField genreInput = new TextField();
    @FXML
    private TextField langueInput = new TextField();
    @FXML
    private TextField origineInput = new TextField();
    @FXML
    private TextField supportInput = new TextField();
    @FXML
    private TextField noteInput = new TextField();
    @FXML
    private TextArea commentInput = new TextArea();
    @FXML
    private TextField plateformeInput = new TextField();

		public /*static final*/ ObservableList cats = FXCollections.observableArrayList();

    /// Valider et supprimer
    @FXML
    Button btnValider = new Button();
		@FXML
    Button btnSupprimer = new Button();

		/// Morceau
		@FXML
    private TextField nameMorceau = new TextField();
    @FXML
    private TextField dureeMorceau = new TextField();
		@FXML
    Button btnModifierMorceau = new Button();
		@FXML
    Button btnAjouterMorceau = new Button();

		/// Table
		@FXML
		private TableView<Morceau> morceauxTable;
		@FXML
		private TableColumn<Morceau, String> nameColumn;
		@FXML
		private TableColumn<Morceau, Time> dureeColumn;

		/// Oeuvre
		private Oeuvre oeuvre;

    public ModOeuvreController() {
			oeuvre = new DAOOeuvre().find(idOeuvre);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      if (oeuvre == null) {
				System.out.println("oeuvre is null");
				return;
      }

      titreInput.setText(oeuvre.getTitle());

			ArrayList<Pair> categories = new DAOCategorie().findAll();
			int id = 0;
			for (int i = 0; i < categories.size(); i++) {
				if (categories.get(i).getName().equals(oeuvre.getCat())) {
					id = i;
					break;
				}
			}
			cats.addAll(new DAOCategorie().findAllString());
			listViewCat.setItems(cats);
      listViewCat.setValue(cats.get(id));

      auteurInput.setText(oeuvre.getAuthor());
      genreInput.setText(oeuvre.getGenre());
      langueInput.setText(oeuvre.getLangue());
      origineInput.setText(oeuvre.getOrigine());
      dateAcquisitionInput.setText(oeuvre.getStringDate_acquisition());
      dateSortieInput.setText(oeuvre.getStringDate_sortie());
      commentInput.setText(oeuvre.getComment());
      noteInput.setText(Integer.toString(oeuvre.getNote()));

			/// morceaux
			nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
	    dureeColumn.setCellValueFactory(new PropertyValueFactory<>("duree"));

			/// table view
			morceauxTable.getItems().addAll(new DAOMorceau().findAllByOeuvre(idOeuvre));
    }

		/// Ajouter morceau
		public void ajouterMorceau(ActionEvent event) throws IOException {
			Morceau morceau = new Morceau(0, nameMorceau.getText(), dureeMorceau.getText(), idOeuvre);
			new DAOMorceau().save(morceau);
			morceauxTable.getItems().add(morceau);
		}

		/// Update les champs
		public void updateInputMorceau(Event event) throws IOException {
			nameMorceau.setText(morceauxTable.getSelectionModel().getSelectedItem().getName());
			dureeMorceau.setText(morceauxTable.getSelectionModel().getSelectedItem().getStringDuree());
		}

		/// Modify
		public void modifyMorceau(ActionEvent event) throws IOException {
			Morceau morceau = morceauxTable.getSelectionModel().getSelectedItem();
			morceau.setName(nameMorceau.getText());
			morceau.setDuree(dureeMorceau.getText());
			new DAOMorceau().update(morceau);
		}

		/// Delete
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

		/// Modifier
		public void modifyOeuvre(ActionEvent event) throws IOException {
			oeuvre.setTitle(titreInput.getText());
			oeuvre.setCat(listViewCat.getSelectionModel().getSelectedItem().toString());
			oeuvre.setDate_acquisition(dateAcquisitionInput.getText());
			oeuvre.setDate_sortie(dateSortieInput.getText());
			oeuvre.setAuthor(auteurInput.getText());
			oeuvre.setGenre(genreInput.getText());
			oeuvre.setLangue(langueInput.getText());
			oeuvre.setOrigine(origineInput.getText());
			//oeuvre.setSupport(supportInput.getText());
			oeuvre.setNote(Integer.valueOf(noteInput.getText()));
			oeuvre.setComment(commentInput.getText());
			oeuvre.setPlateforme(plateformeInput.getText());
			new DAOOeuvre().update(oeuvre);

			Alert dialog = new Alert(
				Alert.AlertType.CONFIRMATION,
				"L'oeuvre " + oeuvre.getTitle() + ", id:" + oeuvre.getId() + " a bien était modifier.");
			dialog.showAndWait()
				.filter(response -> response.equals(ButtonType.OK))
				.ifPresent(response -> System.out.println("ok"));
			this.changeContent(btnSupprimer, "/ficheOeuvre.fxml");
		}
}
