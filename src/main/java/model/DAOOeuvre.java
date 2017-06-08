
package model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DAOOeuvre implements IDao<Oeuvre>{

	private Connection connection = Dtb.getInstance();

	static String selectSQL = "SELECT *, auteur.name, genre.name, langue.name, categorie.name, " +
			"origine.name, plateforme.name FROM oeuvre " +
			//"LEFT JOIN morceau ON oeuvre.id=morceau.oeuvre_id " +
			"LEFT JOIN plateforme ON oeuvre.plateforme_id=plateforme.id " +
			"LEFT JOIN origine ON oeuvre.origine_id=origine.id " +
			"LEFT JOIN categorie ON oeuvre.categorie_id=categorie.id " +
			"LEFT JOIN auteur ON oeuvre.auteur_id=auteur.id " +
			"LEFT JOIN genre ON oeuvre.genre_id=genre.id " +
			"LEFT JOIN langue ON oeuvre.langue_id=langue.id";

	static String selectSQLSupport = "SELECT t1.name AS supportName FROM oeuvre_has_support " +
			"LEFT JOIN support AS t1 ON oeuvre_has_support.support_id = t1.id WHERE oeuvre_has_support.oeuvre_id=?";// +
			//"LEFT JOIN oeuvre  AS t2 ON oeuvre_has_support.oeuvre_id = t2.id ;

	static String deleteSQL = "DELETE FROM oeuvre WHERE id = ?";

	static String createSQL = "INSERT INTO oeuvre (titre, date_acquisition, date_sortie, note, comment) " +
			"VALUES (?,?,?,?,?)";// +
			//"INSERT INTO genre (genre.name) VALUES ('rdtfyugih')";

	static String updateSQL = "UPDATE oeuvre SET titre=?, date_acquisition=?, date_sortie=?, note=?, comment=? WHERE id=?";



	public static void main(String[] argv) {
		/// get
	}

	protected Oeuvre fetch(ResultSet resultSet, ArrayList<String> supports) throws SQLException{
		return  new Oeuvre(
				resultSet.getInt("id"),
				resultSet.getString("titre"),
				resultSet.getString("categorie.name"),
				resultSet.getString("auteur.name"),
				//(rs.getString("auteur.name")==null) ? "" : rs.getString("auteur.name"),
				resultSet.getString("genre.name"),
				resultSet.getString("langue.name"),
				resultSet.getString("origine.name"),
				resultSet.getString("date_acquisition"),
				resultSet.getString("date_sortie"),
				resultSet.getString("comment"),
				resultSet.getInt("note"),
				supports,
				resultSet.getString("plateforme.name"));
	}

	@Override
	public Oeuvre find(int id)
	{
		/// conn
		Oeuvre oeuvre = null;
		String query = selectSQL + " WHERE oeuvre.id=?";


		/// prepare + set
		try	{
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			/// result
			ResultSet rs = preparedStatement.executeQuery();

			ArrayList<String> supports = getSupports(id);

			while (rs.next()) {
				oeuvre = fetch(rs, supports);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return oeuvre;
	}

	private ArrayList<String> getSupports(int id) throws SQLException {
		PreparedStatement preparedStatement2 = connection.prepareStatement(selectSQLSupport);
		preparedStatement2.setInt(1, id);
		ResultSet rs2 = preparedStatement2.executeQuery();
		ArrayList<String> supports = new ArrayList<String>();
		while (rs2.next()) {
            supports.add(rs2.getString("supportName"));
        }
		return supports;
	}

	@Override
	public ArrayList<Oeuvre> findAll() {
		/// conn
		ArrayList<Oeuvre> oeuvres = new ArrayList<Oeuvre>();

		/// prepare + set
		try	{
			PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				ArrayList<String> supports = getSupports(id);
				oeuvres.add(this.fetch(rs, supports));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return oeuvres;
	}

	@Override
	public void delete(Oeuvre oeuvre){

		/// prepare + set
		try	{
			PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);

			preparedStatement.setInt(1, oeuvre.getId());

			/// result
			preparedStatement.executeUpdate();

			//Oeuvre oeuvre = new Oeuvre();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void save(Oeuvre oeuvre){
		try	{
			PreparedStatement preparedStatement = connection.prepareStatement(createSQL, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, oeuvre.getTitle());
			preparedStatement.setString(2, oeuvre.getStringDate_acquisition());
			preparedStatement.setString(3, oeuvre.getStringDate_sortie());
			preparedStatement.setInt(4, oeuvre.getNote());
			preparedStatement.setString(5, oeuvre.getComment());

			/// result
			preparedStatement.executeUpdate();

			ResultSet generated = preparedStatement.getGeneratedKeys();
			if (generated.next()) {
				oeuvre.setId(generated.getInt(1));
			}

			/// Utilise update pour les champs foreign
			this.updateForeignKey(oeuvre.getId(), "auteur", oeuvre.getAuthor());
			this.updateForeignKey(oeuvre.getId(), "categorie", oeuvre.getCat());
			this.updateForeignKey(oeuvre.getId(), "genre", oeuvre.getGenre());
			this.updateForeignKey(oeuvre.getId(), "langue", oeuvre.getLangue());
			this.updateForeignKey(oeuvre.getId(), "origine", oeuvre.getOrigine());
			//this.updateForeignKey(oeuvre.getId(), "plateforme", oeuvre.getPlateforme());
			// supports
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void update(Oeuvre oeuvre){
		try	{
			PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);

			preparedStatement.setString(1, oeuvre.getTitle());
			preparedStatement.setString(2, oeuvre.getStringDate_acquisition());
			preparedStatement.setString(3, oeuvre.getStringDate_sortie());
			preparedStatement.setInt(4, oeuvre.getNote());
			preparedStatement.setString(5, oeuvre.getComment());

			preparedStatement.setInt(6, oeuvre.getId());

			/// result
			preparedStatement.executeUpdate();

			this.updateForeignKey(oeuvre.getId(), "auteur", oeuvre.getAuthor());
			this.updateForeignKey(oeuvre.getId(), "categorie", oeuvre.getCat());
			this.updateForeignKey(oeuvre.getId(), "genre", oeuvre.getGenre());
			this.updateForeignKey(oeuvre.getId(), "langue", oeuvre.getLangue());
			this.updateForeignKey(oeuvre.getId(), "origine", oeuvre.getOrigine());
			this.updateForeignKey(oeuvre.getId(), "plateforme", oeuvre.getPlateforme());
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void updateColumn(int id, String column, Object objet) {
		try	{
			String query = "UPDATE oeuvre SET " + column + "=? WHERE id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setObject(1, objet);

			preparedStatement.setInt(2, id);

			/// result
			preparedStatement.executeUpdate();

			//Oeuvre oeuvre = new Oeuvre();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void updateForeignKey(int idOeuvre, String key, Object objet) {
		try	{
			/// lookup
			String query1 = "SELECT * FROM " + key + " WHERE name=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query1);
			preparedStatement.setObject(1, objet);
			ResultSet rs = preparedStatement.executeQuery();

			/// Find the right id for the foreign table
			int idForeign = 0;
			if (rs.first()) {
				System.out.println(rs.getInt("id"));
				idForeign = rs.getInt("id");
			} else {
				String query2 = "INSERT INTO " + key + " (name) VALUES (?)";

				preparedStatement = connection.prepareStatement(query2, Statement.RETURN_GENERATED_KEYS);
				preparedStatement.setObject(1, objet);
				preparedStatement.executeUpdate();

				ResultSet generated = preparedStatement.getGeneratedKeys();
				if (generated.next()) {
					idForeign = generated.getInt(1);
				}
			}

			/// Set the right id for foreign column of the right oeuvre
			String query3 = "UPDATE oeuvre SET " + key + "_id=? WHERE id=?";
			preparedStatement = connection.prepareStatement(query3);
			preparedStatement.setInt(1, idForeign);
			preparedStatement.setInt(2, idOeuvre);
			preparedStatement.executeUpdate();

			System.out.println(idForeign);

			/// modify

			//Oeuvre oeuvre = new Oeuvre();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public ArrayList<Oeuvre> getAllOeuvreByTitle(String title){
		ArrayList<Oeuvre> oeuvres = new ArrayList<Oeuvre>();

		/// prepare + set
		try	{
			String query = selectSQL + " WHERE oeuvre.titre LIKE '%" + title + "%'";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			//preparedStatement.setString(1, title);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				ArrayList<String> supports = getSupports(id);
				oeuvres.add(this.fetch(rs, supports));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return oeuvres;
	}

	public ArrayList<Oeuvre> getAllOeuvreByCat(String cat){
		System.out.println("DAOOeuvre::getAllOeuvreByCat::" + cat);
		ArrayList<Oeuvre> oeuvres = new ArrayList<Oeuvre>();

		/// prepare + set
		try	{
			String query = selectSQL + " WHERE categorie.name=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, cat);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				ArrayList<String> supports = getSupports(id);
				oeuvres.add(this.fetch(rs, supports));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return oeuvres;
	}

	public ArrayList<Oeuvre> getAllOeuvreByGenre(String genre) {
		System.out.println("DAOOeuvre::getAllOeuvreByGenre::" + genre);
		ArrayList<Oeuvre> oeuvres = new ArrayList<Oeuvre>();

		/// prepare + set
		try {
			String query = selectSQL + " WHERE genre.name=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, genre);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				ArrayList<String> supports = getSupports(id);
				oeuvres.add(this.fetch(rs, supports));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return oeuvres;
	}

	public ArrayList<Oeuvre> getAllOeuvreByNote(int note) {
		System.out.println("DAOOeuvre::getAllOeuvreByNote::" + note);
		ArrayList<Oeuvre> oeuvres = new ArrayList<Oeuvre>();

		/// prepare + set
		try {
			String query = selectSQL + " WHERE oeuvre.note=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, note);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				ArrayList<String> supports = getSupports(id);
				oeuvres.add(this.fetch(rs, supports));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return oeuvres;
	}

	public ArrayList<Oeuvre> getAllOeuvreByYear(int year) {
		return getAllOeuvreBetweenYears(year, year+1);
	}

	public ArrayList<Oeuvre> getAllOeuvreBetweenYears(int begin, int end) {
		System.out.println("DAOOeuvre::getAllOeuvreBetweenYears::" + begin + ":" + end);
		ArrayList<Oeuvre> oeuvres = new ArrayList<Oeuvre>();

		/// prepare + set
		try {
			String query = selectSQL + " WHERE oeuvre.date_sortie>=? AND oeuvre.date_sortie<?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			String dateMin = Integer.toString(begin) + "-01-01";
			String dateMax = Integer.toString(end) + "-01-01";
			preparedStatement.setString(1, dateMin);
			preparedStatement.setString(2, dateMax);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				ArrayList<String> supports = getSupports(id);
				oeuvres.add(this.fetch(rs, supports));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return oeuvres;
	}

	public ArrayList<Oeuvre> getAllOeuvreByDate(String begin, String end) {
		ArrayList<Oeuvre> oeuvres = new ArrayList<Oeuvre>();

		/// prepare + set
		try {
			String query = selectSQL + " WHERE oeuvre.date_sortie>=? AND oeuvre.date_sortie<?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, begin);
			preparedStatement.setString(2, end);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				ArrayList<String> supports = getSupports(id);
				oeuvres.add(this.fetch(rs, supports));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return oeuvres;
	}

}
