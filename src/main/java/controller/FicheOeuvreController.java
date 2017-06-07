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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Label;


/**
 * Created by Jordan TROADEC on 06/06/2017.
 */
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

    public FicheOeuvreController() {


    }

    public void initialize(URL location, ResourceBundle resources) {
        Oeuvre oeuvre = new DAOOeuvre().find(idOeuvre);
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

       /* if (oeuvre.getCat() == "album") {
            System.out.println("ID invalide");
            return;*/
        /*SupportO.setCellValueFactory(new PropertyValueFactory<>("support"));
        PlateformeO.setCellValueFactory(new PropertyValueFactory<>("plateforme"));*/


    }

    public void ModAction(ActionEvent event) throws IOException {
        this.changeScene(modButton, "/modOeuvre.fxml");
    }
    /// Feed the data
        //Oeuvre.getItems().addOeuvre(nextSearch.get());


}
