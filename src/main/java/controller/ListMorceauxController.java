package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import java.sql.Time;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import model.Pair;
import model.DAOCategorie;
import model.Morceau;
import model.DAOMorceau;
import model.DAOCategorie;
import model.DAOOeuvre;
import model.Oeuvre;

import java.util.ArrayList;

import javafx.event.Event;

import javafx.scene.control.Alert;

/**
 * Created by Jordan TROADEC on 07/06/2017.
 */
public class ListMorceauxController {

		/// Oeuvre
		private Oeuvre oeuvre;

    public ListMorceauxController(Oeuvre oeuvre) {
			this.oeuvre = oeuvre;
    }

    public void initialize(TableView<Morceau> morceauxTable, TableColumn<Morceau, String> nameColumn, TableColumn<Morceau, Time> dureeColumn) {
      if (oeuvre == null) {
				System.out.println("oeuvre is null");
				return;
      }

			/// morceaux
			nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
	    dureeColumn.setCellValueFactory(new PropertyValueFactory<>("duree"));

			/// table view
			morceauxTable.getItems().addAll(new DAOMorceau().findAllByOeuvre(oeuvre.getId()));
    }		

}
