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
    /// submit
    @FXML
    Button valider;


   /* public ModOeuvreController() {

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

    }*/

    @Override

    public void initialize(URL location, ResourceBundle resources) {
        Oeuvre oeuvre = new DAOOeuvre().find(idOeuvre);
        if (oeuvre == null) {
            System.out.println("ID invalide");
            return;
        }

        titreInput.setText(oeuvre.getTitle());
        listViewCat.setValue(1);
        auteurInput.setText(oeuvre.getAuthor());
        genreInput.setText(oeuvre.getGenre());
        langueInput.setText(oeuvre.getLangue());
        origineInput.setText(oeuvre.getOrigine());
        dateAcquisitionInput.setText(oeuvre.getStringDate_acquisition());
        dateSortieInput.setText(oeuvre.getStringDate_sortie());
        commentInput.setText(oeuvre.getComment());
        noteInput.setText(Integer.toString(oeuvre.getNote()));
    }


}