package model;

public class DAOGenre extends DAOPair {

	static String selectSQL = "SELECT * FROM genre";

	@Override
	public String getSelectSQL() {
		return selectSQL;
	}
}
