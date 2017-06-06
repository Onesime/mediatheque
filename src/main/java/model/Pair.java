package model;

public class Pair {

  private int id;
  private String name;

  public Pair(int id, String name) {
    this.id = id;
    this.name = name;
  }

	
  public int getId() {
      return this.id;
  }
  public void setId(int id) {
      this.id = id;
  }

  public String getName() {
      return this.name;
  }
  public void setTitle(String name) {
      this.name = name;
  }
}

