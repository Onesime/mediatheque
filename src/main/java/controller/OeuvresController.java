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
import model.Oeuvre;
import java.util.Date;

public class OeuvresController {
    @FXML
    private TableView<Oeuvre> oeuvresTable;
    @FXML
    private TableColumn<Oeuvre, String> titleColumn;
    @FXML
    private TableColumn<Oeuvre, String> catColumn;
    /*@FXML
    private TableColumn<Oeuvre, DateCell> acqDateColumn;
    @FXML
    private TableColumn<Oeuvre, DateCell> addDateColumn;*/
    @FXML
    private TableColumn<Oeuvre, String> authorColumn;
    @FXML
    private TableColumn<Oeuvre, String> genreColumn;

    private int iNumber = 1;

    final ObservableList<Oeuvre> data = FXCollections.observableArrayList();

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