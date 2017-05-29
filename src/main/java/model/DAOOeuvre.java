
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DAOOeuvre {

	static String selectSQL = "SELECT oeuvre.*, auteur.name, genre.name, langue.name, categorie.name, " +
			"origine.name, plateforme.name, morceau.name FROM oeuvre " +
			"LEFT JOIN morceau ON oeuvre.id=morceau.oeuvre_id " +
			"LEFT JOIN plateforme ON oeuvre.plateforme_id=plateforme.id " +
			"INNER JOIN origine ON oeuvre.origine_id=origine.id " +
			"INNER JOIN categorie ON oeuvre.categorie_id=categorie.id " +
			"INNER JOIN auteur ON oeuvre.auteur_id=auteur.id " +
			"INNER JOIN genre ON oeuvre.genre_id=genre.id " +
			"INNER JOIN langue ON oeuvre.langue_id=langue.id " +
			"WHERE oeuvre.id=?";

	public static void main(String[] argv) {
		/// get
		int Oeuvre1 = DAOOeuvre.getOeuvre(1);

	}

	public static int getOeuvre(int id)
	{
		/// conn
		Connection connection = Dtb.getConnection();


		/// prepare + set
		try	{
			PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);

			preparedStatement.setInt(1, 11);

			/// result
			ResultSet rs = preparedStatement.executeQuery();
			System.out.println("ok");

			//Oeuvre oeuvre = new Oeuvre();
			while (rs.next()) {
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
				/*
				oeuvre.setId(rs.getInt("id"));
				oeuvre.setTitre(rs.getString("titre"));
				// datet
				// date
				oeuvre.setNote(rs.getInt("note"));
				oeuvre.setComment(rs.getString("comment"));

				System.out.println("id : " + oeuvre.getId());
				System.out.println("titre : " + oeuvre.getTitle());
				System.out.println("note : " + oeuvre.getNote());
				*/
			}
			return 1;
		} catch (SQLException ex) {
			System.out.println("bug");
			ex.printStackTrace();
		}

		return 1;
	}

}
