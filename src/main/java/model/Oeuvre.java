package model;

import java.time.LocalDate;
import java.util.Date;

import javafx.beans.property.*;

public class Oeuvre {

    private IntegerProperty id;
    private final StringProperty title;
    private final StringProperty cat;
    private final StringProperty author;
    private final StringProperty genre;


    public Oeuvre(int id, String cat, String title, String author, String genre) {
        this.id = new SimpleIntegerProperty(id);
        this.title = new SimpleStringProperty(title);
        this.cat = new SimpleStringProperty(cat);
        this.author = new SimpleStringProperty(author);
        this.genre = new SimpleStringProperty(genre);
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

}
