
package com.cesi;

import model.Dtb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Oeuvre
{
	private int id;
	private String titre;
	// date acquisition
	// date sortie
	private int note;
	private String comment;

	private int auteur_id;
	private int genre_id;
	private int langue_id;
	private int plateforme_id;
	private int origine_id;
	private int categorie_id;

	public Oeuvre()
	{

	}

	public static void main(String[] argv) {
		/// get
		Oeuvre Oeuvre1 = Oeuvre.getOeuvre(1);

		/// new
		/*
		Oeuvre Oeuvre2 = new Oeuvre();
		Oeuvre2.setPseudo("tata");
		Oeuvre2.setNom("toto");
		Oeuvre2.setPrenom("titi");
		Oeuvre2.setMail("toto@gmail.com");
		Oeuvre2.save();
		*/
		/// modif
		Oeuvre1.setTitre("ahahahaah");
		Oeuvre1.update();

		/// check update
		Oeuvre Oeuvre3 = Oeuvre.getOeuvre(1);
		System.out.println("ttre: " + Oeuvre3.getTitre());
	}

	public int getId()
	{
		return this.id;
	}
	public String getTitre()
	{
		return this.titre;
	}
	// get date
	// get date
	public int getNote()
	{
		return this.note;
	}
	public String getComment()
	{
		return this.comment;
	}

	public void setId(int p)
	{
		this.id = p;
	}
	public void setTitre(String p)
	{
		this.titre = p;
	}
	// date
	// date
	public void setNote(int p)
	{
		this.note= p;
	}
	public void setComment(String p)
	{
		this.comment = p;
	}

	public static Oeuvre getOeuvre(int id)
	{
		/// conn
		Connection connection = Dtb.getInstance();

		///
		String selectSQL = "SELECT * FROM oeuvre WHERE id=?";

		/// prepare + set
		try	{
			PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, 4);

			/// result
			ResultSet rs = preparedStatement.executeQuery();
			System.out.println("ok");

			Oeuvre oeuvre = new Oeuvre();
			while (rs.next()) {
				System.out.println("toto");
				oeuvre.setId(rs.getInt("id"));
				oeuvre.setTitre(rs.getString("titre"));
				// datet
				// date
				oeuvre.setNote(rs.getInt("note"));
				oeuvre.setComment(rs.getString("comment"));

				System.out.println("id : " + oeuvre.getId());
				System.out.println("titre : " + oeuvre.getTitre());
				System.out.println("note : " + oeuvre.getNote());
			}
			return oeuvre;
		} catch (SQLException ex) {
			System.out.println("bug");
			ex.printStackTrace();
		}

		return new Oeuvre();
	}

	public boolean isDataSet()
	{
		if (this.titre == null) return false;
		return true;
	}

	public void save()
	{
		/// check
		if (this.isDataSet() == false) return;

		/// conn
		Connection connection = Dtb.getInstance();

		///
		String selectSQL = "INSERT INTO oeuvre (titre, note, date_acquisition, date_sortie, comment) VALUES (?,?,now(),now(),?)";

		try	{
			/// bind
			PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, this.titre);
			preparedStatement.setInt(2, this.note);
			preparedStatement.setString(3, this.comment);

			/// execute
			int nbModif = preparedStatement.executeUpdate();
			System.out.println("Table Oeuvre modifier avec succés " + nbModif);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void update()
	{
		/// check
		if (this.isDataSet() == false || this.id == 0) return;

		/// conn
		Connection connection = Dtb.getInstance();

		/// date
		/// date
		///
		String selectSQL = "UPDATE oeuvre SET titre=?, note=?, comment=? WHERE id=?";

		try	{
			/// bind
			PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, this.titre);
			preparedStatement.setInt(2, this.note);
			preparedStatement.setString(3, this.comment);
			
			preparedStatement.setInt(4, this.id);

			/// execute
			int nbModif = preparedStatement.executeUpdate();
			System.out.println("Table Oeuvre modifier avec succés " + nbModif);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
