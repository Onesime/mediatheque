package controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;

import com.cesi.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DateCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Oeuvre;

public class OeuvresController {
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


    private int iNumber = 1;

    final ObservableList<Oeuvre> data = FXCollections.observableArrayList();

    public void initialize(URL location, ResourceBundle resources) {
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        catColumn.setCellValueFactory(cellData -> cellData.getValue().catProperty());
        authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        genreColumn.setCellValueFactory(cellData -> cellData.getValue().genreProperty());
        langColumn.setCellValueFactory(cellData -> cellData.getValue().langueProperty());
        originColumn.setCellValueFactory(cellData -> cellData.getValue().origineProperty());
        acqDateColumn.setCellValueFactory(new PropertyValueFactory<>("Acquisition"));
        outDateColumn.setCellValueFactory(new PropertyValueFactory<>("Sortie"));
        commentColumn.setCellValueFactory(cellData -> cellData.getValue().commentProperty());
        noteColumn.setCellValueFactory(cellData -> cellData.getValue().noteProperty().asObject());

    }

    private Main main;

    public void setMainApp(Main mainApp) {
        this.main = mainApp;

        // Add observable list data to the table
        oeuvresTable.setItems(main.getOeuvresData());
    }
}