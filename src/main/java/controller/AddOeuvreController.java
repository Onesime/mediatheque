package controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.awt.event.MouseEvent;
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

import javafx.scene.control.ChoiceBox;
import javafx.beans.value.ObservableValue;

public class AddOeuvreController extends Pagination implements Initializable {
	/// Inputs
	@FXML TextField titreInput;

	@FXML ChoiceBox listViewCat;
	public /*static final*/ ObservableList cats = FXCollections.observableArrayList();

	@FXML TextField dateAcquisitionInput;
	@FXML TextField dateSortieInput;
	@FXML TextField auteurInput;
	@FXML TextField genreInput;
	@FXML TextField langueInput;
	@FXML TextField origineInput;
	@FXML TextField supportInput;
	@FXML TextField noteInput;
	@FXML TextArea commentInput;
	@FXML TextField plateformeInput;
	/// submit
	Button valider;
	@FXML Label labelPlateforme;
	
  public AddOeuvreController() {
		System.out.println("AddOeuvreController::DialogControlleur");
		
		this.titreInput = new TextField();
		this.dateAcquisitionInput = new TextField();
		this.dateSortieInput = new TextField();
		this.auteurInput = new TextField();
		this.genreInput = new TextField();
		this.langueInput = new TextField();
		this.origineInput = new TextField();
		this.supportInput = new TextField();
		this.noteInput = new TextField();
		this.commentInput = new TextArea();
		this.plateformeInput = new TextField();

		this.valider = new Button();

		this.listViewCat = new ChoiceBox();

		this.labelPlateforme = new Label();
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
		System.out.println("AddOeuvreController::initialize");
		/// Feed cats
		cats.addAll(new DAOCategorie().findAllString());
		listViewCat.setItems(cats);
		listViewCat.setValue(cats.get(0));
		plateformeInput.setVisible(false);

		/// addd
		listViewCat.getSelectionModel().selectedItemProperty().addListener( 
			new ChangeListener<String>() {
				@Override
      	public void changed(ObservableValue observableValue, String number, String number2) {
        	System.out.println(box.getItems().get((Integer) number2));
      	}
			});
  }

	@FXML
	private void handleCatSelection(Event event) throws IOException {
		System.out.println(listViewCat.getSelectionModel().getSelectedItem().toString());
		if (listViewCat.getSelectionModel().getSelectedItem().toString().equals("jeux_video")) {
			plateformeInput.setVisible(true);
			labelPlateforme.setVisible(true);
		} else {
			plateformeInput.setVisible(false);
			labelPlateforme.setVisible(false);
		}
	}

	@FXML
	private void submitOeuvre(ActionEvent event) throws IOException {
		System.out.println("AddOeuvreController::submitOeuvre");
		/// Supports
		ArrayList<String> supports = new ArrayList();
		supports.add(this.supportInput.getText());

		System.out.println(this.titreInput.getText());
		System.out.println(this.listViewCat.getSelectionModel().getSelectedItem().toString());
		System.out.println(this.auteurInput.getText());
		System.out.println(this.genreInput.getText());
		System.out.println(this.langueInput.getText());
		System.out.println(this.origineInput.getText());
		System.out.println(this.dateAcquisitionInput.getText());
		System.out.println(this.dateSortieInput.getText());
		System.out.println(this.commentInput.getText());
		System.out.println(Integer.parseInt(this.noteInput.getText()));
		System.out.println(supports.get(0));

		/// Oeuvre
		Oeuvre oeuvre = new Oeuvre(
			0,
			this.titreInput.getText(),
			this.listViewCat.getSelectionModel().getSelectedItem().toString(),
			this.auteurInput.getText(),
			this.genreInput.getText(),
			this.langueInput.getText(),
			this.origineInput.getText(),
			this.dateAcquisitionInput.getText(),
			this.dateSortieInput.getText(),
			this.commentInput.getText(),
			Integer.parseInt(this.noteInput.getText()),
			supports);

		System.out.println("AddOeuvreController::actionClick:oeuvre_made");
		System.out.println(oeuvre.getStringDate_acquisition());
		System.out.println(oeuvre.getStringDate_sortie());
		/// Save
		new DAOOeuvre().save(oeuvre);

		/// Redirect
		
  }
}







