package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;

import javafx.beans.property.*;

public class Oeuvre {

	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  private final IntegerProperty id;
  private final StringProperty title;
  private final StringProperty cat;
  private final StringProperty author;
  private final StringProperty genre;
  private final StringProperty langue;
  private final StringProperty origine;
  private final ObjectProperty<LocalDate> date_acquisition;
  private final ObjectProperty<LocalDate> date_sortie;
  private final StringProperty comment;
  private final IntegerProperty note;
  private final ArrayList<String> supports;

	private final StringProperty plateforme;

  public Oeuvre(int id, String title, String cat, String author, String genre,
		String langue, String origine, String date_acquisition, String date_sortie, 
		String comment, int note, ArrayList<String> supports) {
      this.id = new SimpleIntegerProperty(id);
      this.title = new SimpleStringProperty(title);
      this.cat = new SimpleStringProperty(cat);
      this.author = new SimpleStringProperty(author);
      this.genre = new SimpleStringProperty(genre);
      this.langue = new SimpleStringProperty(langue);
      this.origine = new SimpleStringProperty(origine);

			/// Utiliser le formatter
      this.date_acquisition = new SimpleObjectProperty(
				LocalDate.parse(date_acquisition, formatter));
      this.date_sortie = new SimpleObjectProperty(
				LocalDate.parse(date_sortie, formatter));

      this.comment = new SimpleStringProperty(comment);
      this.note = new SimpleIntegerProperty(note);
      this.supports = supports;

			this.plateforme = new SimpleStringProperty("");
  }

	
  public int getId() {
		return id.get();
  }
  public void setId(int id) {
		this.id.set(id);
  }
  public IntegerProperty idProperty() {
		return id;
  }


  public String getTitle() {
		return title.get();
  }
  public void setTitle(String title) {
		this.title.set(title);
  }
  public StringProperty titleProperty() {
		return title;
  }
	

  public String getCat() {
		return cat.get();
  }
  public void setCat(String cat) {
		this.cat.set(cat);
  }
  public StringProperty catProperty() {
		return cat;
  }


  public String getAuthor() {
		return author.get();
  }
  public void setAuthor(String author) {
		this.author.set(author);
  }
  public StringProperty authorProperty() {
		return author;
  }


  public String getGenre() {
      return genre.get();
  }
  public void setGenre(String genre) {
      this.genre.set(genre);
  }
  public StringProperty genreProperty() {
      return genre;
  }


  public String getLangue() {
      return langue.get();
  }
  public void setLangue(String langue) {
      this.langue.set(langue);
  }
  public StringProperty langueProperty() {
      return langue;
  }


  public String getOrigine() {
      return origine.get();
  }
  public void setOrigine(String origine) {
      this.origine.set(origine);
  }
  public StringProperty origineProperty() {
      return origine;
  }


  public LocalDate getDate_acquisition() {
      return date_acquisition.get();
  }
  public String getStringDate_acquisition() {
      return date_acquisition.get().toString();
  }
  public void setDate_acquisition(LocalDate date_acquisition) {
      this.date_acquisition.set(date_acquisition);
  }
	public void setDate_acquisition(String date_acquisition) {
      this.date_acquisition.set(LocalDate.parse(date_acquisition, formatter));
  }
  public ObjectProperty<LocalDate> date_acquisitionProperty() {
      return date_acquisition;
  }


  public LocalDate getDate_sortie() {
      return date_sortie.get();
  }
  public String getStringDate_sortie() {
      return date_sortie.get().toString();
  }
  public void setDate_sortie(LocalDate date_sortie) {
      this.date_sortie.set(date_sortie);
  }
	public void setDate_sortie(String date_sortie) {
      this.date_sortie.set(LocalDate.parse(date_sortie, formatter));
  }
  public ObjectProperty<LocalDate> date_sortieProperty() {
      return date_sortie;
  }


  public String getComment() {
      return comment.get();
  }
  public void setComment(String comment) {
      this.comment.set(comment);
  }
  public StringProperty commentProperty() {
      return comment;
  }


  public Integer getNote() {
      return note.get();
  }
  public void setNote(Integer note) {
      this.note.set(note);
  }
  public IntegerProperty noteProperty() {
      return note;
  }

	public String getPlateforme() {
      return plateforme.get();
  }
  public void setPlateforme(String plateforme) {
      this.plateforme.set(plateforme);
  }
  public StringProperty plateformeProperty() {
      return plateforme;
  }

}
