package controller;

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


    public OeuvresController() {
        this.oeuvreRepo = new DAOOeuvre();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
        catColumn.setCellValueFactory(new PropertyValueFactory<>("Catégorie"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("Auteur"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("Genre"));
        langColumn.setCellValueFactory(new PropertyValueFactory<>("Langue"));
        originColumn.setCellValueFactory(new PropertyValueFactory<>("Origine"));
        acqDateColumn.setCellValueFactory(new PropertyValueFactory<>("Acquisition"));
        outDateColumn.setCellValueFactory(new PropertyValueFactory<>("Sortie"));
        commentColumn.setCellValueFactory(new PropertyValueFactory<>("Commentaire"));
        noteColumn.setCellValueFactory(new PropertyValueFactory<>("Note"));

        oeuvresTable.getItems().addAll(oeuvreRepo.getAllOeuvre());

    }

    private Main main;

    public void setMainApp(Main mainApp) {
        this.main = mainApp;

        // Add observable list data to the table
        oeuvresTable.setItems(main.getOeuvresData());
    }
}