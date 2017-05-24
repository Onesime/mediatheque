package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.cesi.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DateCell;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Oeuvres;
import java.util.Date;

public class OeuvresController {
    @FXML
    private TableView<Oeuvres> oeuvresTable;
    @FXML
    private TableColumn<Oeuvres, String> titleColumn;
    @FXML
    private TableColumn<Oeuvres, String> catColumn;
    /*@FXML
    private TableColumn<Oeuvres, DateCell> acqDateColumn;
    @FXML
    private TableColumn<Oeuvres, DateCell> addDateColumn;*/
    @FXML
    private TableColumn<Oeuvres, String> authorColumn;
    @FXML
    private TableColumn<Oeuvres, String> genreColumn;

    private int iNumber = 1;

    final ObservableList<Oeuvres> data = FXCollections.observableArrayList();

    public void initialize(URL location, ResourceBundle resources) {
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        catColumn.setCellValueFactory(cellData -> cellData.getValue().catProperty());
        authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        genreColumn.setCellValueFactory(cellData -> cellData.getValue().genreProperty());


    }

    private Main main;

    public void setMainApp(Main mainApp) {
        this.main = mainApp;

        // Add observable list data to the table
        oeuvresTable.setItems(main.getOeuvresData());
    }
}