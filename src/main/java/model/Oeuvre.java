package model;

import java.time.LocalDate;
import java.util.Date;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Oeuvre {

    private final StringProperty title;
    private final StringProperty cat;
    private final StringProperty author;
    private final StringProperty genre;


    public Oeuvres(String title, String cat, String author, String genre) {
        this.title = new SimpleStringProperty(title);
        this.cat = new SimpleStringProperty(cat);
        this.author = new SimpleStringProperty(author);
        this.genre = new SimpleStringProperty(genre);
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public StringProperty titleProperty() {
        return genre;
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

}
