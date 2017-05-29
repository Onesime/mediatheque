
package model;

import java.time.LocalDate;
import java.util.Date;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DAOOeuvre {

	static String selectSQL = "SELECT * FROM oeuvre WHERE id=?";

	public static void main(String[] argv) {
		/// get
		Oeuvre Oeuvre1 = DAOOeuvre.getOeuvre(1);

	}

	public static Oeuvre getOeuvre(int id)
	{
		/// conn
		Connection connection = Dtb.getConnection();


		/// prepare + set
		try	{
			PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, 4);

			/// result
			ResultSet rs = preparedStatement.executeQuery();
			System.out.println("ok");

			Oeuvre oeuvre = new Oeuvre();
			while (rs.next()) {
				System.out.println(rs.getInt("id"));
				System.out.println(rs.getInt("titre"));
				System.out.println(rs.getInt("date_acquisition"));
				System.out.println(rs.getInt("date_sortie"));
				System.out.println(rs.getInt("note"));
				System.out.println(rs.getInt("comment"));
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
			return oeuvre;
		} catch (SQLException ex) {
			System.out.println("bug");
			ex.printStackTrace();
		}

		return new Oeuvre();
	}

}
