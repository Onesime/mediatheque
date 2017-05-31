
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
			"origine.name, plateforme.name, morceau.name FROM oeuvre " +
			"LEFT JOIN morceau ON oeuvre.id=morceau.oeuvre_id " +
			"LEFT JOIN plateforme ON oeuvre.plateforme_id=plateforme.id " +
			"INNER JOIN origine ON oeuvre.origine_id=origine.id " +
			"INNER JOIN categorie ON oeuvre.categorie_id=categorie.id " +
			"INNER JOIN auteur ON oeuvre.auteur_id=auteur.id " +
			"INNER JOIN genre ON oeuvre.genre_id=genre.id " +
			"INNER JOIN langue ON oeuvre.langue_id=langue.id";

	static String deleteSQL = "DELETE FROM oeuvre WHERE id = ?";

	static String createSQL = "INSERT INTO auteur (auteur.name) VALUES ('tototo'); "; //+
			//"INSERT INTO genre (genre.name) VALUES ('tototot');";

	static String updateSQL = "UPDATE oeuvre SET titre=?, note=?, comment=?, categorie_id=?, genre_id=? WHERE id=?";



	public static void main(String[] argv) {
		/// get

		DAOOeuvre daoOeuvre = new DAOOeuvre();/*
		Oeuvre oeuvre1 = daoOeuvre.getOeuvre(11);
		System.out.println(oeuvre1.getTitle());

		ArrayList<Oeuvre> oeuvres = daoOeuvre.getAllOeuvre();
		System.out.println(oeuvres.get(0).getTitle());

		Oeuvre oeuvre2 = new Oeuvre(0, "jeux_video", "Matrix", "Francois Dupont", "fantasy",
				"vf", "amazon", null, null, null, 4);
		daoOeuvre.save(oeuvre2);
		System.out.println(oeuvres.get(0).getId());
		int id = oeuvre2.getId();
		daoOeuvre.delete(oeuvre2);
		Oeuvre oeuvre3 = daoOeuvre.getOeuvre(oeuvre2.getId());
		if (oeuvre3 == null) System.out.println("Delete à fonctionner");
		*/
		/// Verifier que si on obtient 1 oeuvre depuis la base de donnée
		/// Puis on la modifie et on update
		/// Que l'objet en base de donnée à était correctement update
		Oeuvre oeuvre4 = daoOeuvre.getOeuvre(11);
		String comment = "assez bien";
		oeuvre4.setComment(comment);
		//daoOeuvre.update(oeuvre4);
		daoOeuvre.updateColumn("comment", oeuvre4.getComment(), oeuvre4.getId());
		Oeuvre oeuvre3 = daoOeuvre.getOeuvre(11);
		if (oeuvre3.getComment().equals(comment)) System.out.println("Update à fonctionner");



	}

	public Oeuvre getOeuvre(int id)
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

			//Oeuvre oeuvre = new Oeuvre();
			while (rs.next()) {
				oeuvre = new Oeuvre(
						rs.getInt("id"),
						rs.getString("categorie.name"),
						rs.getString("titre"),
						rs.getString("auteur.name"),
						rs.getString("genre.name"),
						rs.getString("langue.name"),
						rs.getString("origine.name"),
						rs.getString("date_acquisition"),
						rs.getString("date_sortie"),
						rs.getString("comment"),
						rs.getInt("note"));
				System.out.println(rs.getInt("id"));
				System.out.println(rs.getString("titre"));
				System.out.println(rs.getString("date_acquisition"));
				System.out.println(rs.getString("date_sortie"));
				System.out.println(rs.getInt("note"));
				System.out.println(rs.getString("comment"));
				System.out.println(rs.getInt("auteur_id"));
				System.out.println(rs.getInt("genre_id"));
				System.out.println(rs.getInt("langue_id"));
				System.out.println(rs.getInt("plateforme_id"));
				System.out.println(rs.getInt("origine_id"));
				System.out.println(rs.getInt("categorie_id"));
				System.out.println(rs.getString("auteur.name"));
				System.out.println(rs.getString("genre.name"));
				System.out.println(rs.getString("langue.name"));
				System.out.println(rs.getString("categorie.name"));
				System.out.println(rs.getString("origine.name"));
				System.out.println(rs.getString("plateforme.name"));
				System.out.println(rs.getString("morceau.name"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return oeuvre;
	}

	public ArrayList<Oeuvre> getAllOeuvre() {
		/// conn
		ArrayList<Oeuvre> oeuvres = new ArrayList<Oeuvre>();

		/// prepare + set
		try	{
			PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);

			/// result
			ResultSet rs = preparedStatement.executeQuery();

			//Oeuvre oeuvre = new Oeuvre();
			while (rs.next()) {
				oeuvres.add(new Oeuvre(
						rs.getInt("id"),
						rs.getString("categorie.name"),
						rs.getString("titre"),
						rs.getString("auteur.name"),
						rs.getString("genre.name"),
						rs.getString("langue.name"),
						rs.getString("origine.name"),
						rs.getString("date_acquisition"),
						rs.getString("date_sortie"),
						rs.getString("comment"),
						rs.getInt("note")));
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
		/// prepare + set
		try	{
			Statement statement = connection.createStatement();
			statement.execute(createSQL);
			/*
			//PreparedStatement preparedStatement = connection.prepareStatement(createSQL, Statement.RETURN_GENERATED_KEYS);

			//preparedStatement.setString(1, oeuvre.getAuthor());
//			preparedStatement.setString(2, oeuvre.getGenre());

			/// result
			int affected = preparedStatement.executeUpdate();

			ResultSet generated = preparedStatement.getGeneratedKeys();
			if (generated.next()) {
				oeuvre.setId(generated.getInt(1));
			}
			*/
			//Oeuvre oeuvre = new Oeuvre();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void update(Oeuvre oeuvre){
		try	{
			PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setString(1, oeuvre.getTitle());
			preparedStatement.setInt(2, oeuvre.getNote());
			preparedStatement.setString(3, oeuvre.getComment());

			preparedStatement.setString(4, oeuvre.getGenre());
			preparedStatement.setString(5, oeuvre.getCat());

			preparedStatement.setInt(6, oeuvre.getId());

			/// result
			preparedStatement.executeUpdate();

			//Oeuvre oeuvre = new Oeuvre();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	public void updateColumn(String column, Object objet, int id) {
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

}
