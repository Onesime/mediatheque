package model;

import java.time.LocalDate;
import java.util.Date;

import javafx.beans.property.*;

public class Oeuvre {

    private final IntegerProperty id;
    private final StringProperty title;
    private final StringProperty cat;
    private final StringProperty author;
    private final StringProperty genre;
    private final StringProperty langue;
    private final StringProperty origine;
    private final StringProperty date_acquisition;
    private final StringProperty date_sortie;
    private final StringProperty comment;
    private final IntegerProperty note;


    public Oeuvre(int id, String cat, String title, String author, String genre, String langue, String origine,
                  String date_acquisition, String date_sortie, String comment, int note) {
        this.id = new SimpleIntegerProperty(id);
        this.title = new SimpleStringProperty(title);
        this.cat = new SimpleStringProperty(cat);
        this.author = new SimpleStringProperty(author);
        this.genre = new SimpleStringProperty(genre);
        this.langue = new SimpleStringProperty(langue);
        this.origine = new SimpleStringProperty(origine);
        this.date_acquisition = new SimpleStringProperty(date_acquisition);
        this.date_sortie = new SimpleStringProperty(date_sortie);
        this.comment = new SimpleStringProperty(comment);
        this.note = new SimpleIntegerProperty(note);
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



    public String getOrigin() {
        return origine.get();
    }

    public void setOrigin(String origin) {
        this.origine.set(origin);
    }

    public StringProperty originProperty() {
        return origine;
    }



    public String getDate_acquisition() {
        return date_acquisition.get();
    }

    public void setDate_acquisition(String date_acquisition) {
        this.date_acquisition.set(date_acquisition);
    }

    public StringProperty date_acquisitionProperty() {
        return date_acquisition;
    }


    public String getDate__sortie() {
        return date_sortie.get();
    }

    public void setDate_sortie(String date_sortie) {
        this.date_sortie.set(date_sortie);
    }

    public StringProperty date_sortieProperty() {
        return date_sortie;
    }



    public String getComment() {
        return comment.get();
    }

    public void setComment(String comment) {
        this.comment.set(comment);
    }

    public StringProperty CommentProperty() {
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

}
