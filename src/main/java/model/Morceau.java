
package model;

import java.sql.Time;
import javafx.beans.property.*;

public class Morceau {
	private IntegerProperty id;
  private StringProperty name;
  private ObjectProperty<Time> duree;
	private IntegerProperty oeuvre_id;

	public Morceau(int id, String name, String duree, int oeuvre_id) {
		this.id = new SimpleIntegerProperty(id);
		this.name = new SimpleStringProperty(name);
		this.duree = new SimpleObjectProperty(Time.valueOf(duree));
		this.oeuvre_id = new SimpleIntegerProperty(oeuvre_id);
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


  public String getName() {
		return name.get();
  }
  public void setName(String name) {
		this.name.set(name);
  }
  public StringProperty nameProperty() {
		return name;
  }
	

  public Time getDuree() {
		return duree.get();
  }
	public String getStringDuree() {
      return duree.get().toString();
  }
  public void setDuree(String duree) {
		this.duree.set(Time.valueOf(duree));
  }
	public void setDuree(Time duree) {
		this.duree.set(duree);
  }
  public ObjectProperty<Time> dureeProperty() {
		return duree;
  }


  public int getOeuvreId() {
		return oeuvre_id.get();
  }
  public void setOeuvreId(int oeuvre_id) {
		this.oeuvre_id.set(oeuvre_id);
  }
  public IntegerProperty oeuvreIdProperty() {
		return oeuvre_id;
  }
}
