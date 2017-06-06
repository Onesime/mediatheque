package model;

public class DAOCategorie extends DAOPair {

	static String selectSQL = "SELECT * FROM categorie";

	@Override
	public String getSelectSQL() {
		return selectSQL;
	}
}












