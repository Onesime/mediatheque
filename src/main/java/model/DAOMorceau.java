
package model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DAOMorceau implements IDao<Morceau>{

	private Connection connection = Dtb.getInstance();

	static String selectSQL = "SELECT * FROM morceau";

	static String deleteSQL = "DELETE FROM morceau WHERE id = ?";

	static String createSQL = "INSERT INTO morceau (name, duree, oeuvre_id) " +
			"VALUES (?,?,?)";

	static String updateSQL = "UPDATE morceau SET name=?, duree=?, oeuvre_id=? WHERE id=?";

	protected Morceau fetch(ResultSet resultSet) throws SQLException{
		return  new Morceau(
				resultSet.getInt("id"),
				resultSet.getString("name"),
				resultSet.getString("duree"),
				resultSet.getInt("oeuvre_id"));
	}

	@Override
	public Morceau find(int id)
	{
		/// conn
		Morceau morceau = null;
		String query = selectSQL + " WHERE oeuvre.id=?";


		/// prepare + set
		try	{
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			/// result
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				morceau = fetch(rs);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return morceau;
	}

	@Override
	public ArrayList<Morceau> findAll() {
		/// conn
		ArrayList<Morceau> morceaux = new ArrayList<Morceau>();

		/// prepare + set
		try	{
			PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				morceaux.add(this.fetch(resultSet));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return morceaux;
	}

	@Override
	public void delete(Morceau morceau){

		/// prepare + set
		try	{
			PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL);

			preparedStatement.setInt(1, morceau.getId());

			/// result
			preparedStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void save(Morceau morceau){
		try	{
			PreparedStatement preparedStatement = connection.prepareStatement(createSQL, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, morceau.getName());
			preparedStatement.setString(2, morceau.getStringDuree());
			preparedStatement.setInt(3, morceau.getOeuvreId());

			preparedStatement.executeUpdate();

			ResultSet generated = preparedStatement.getGeneratedKeys();
			if (generated.next()) {
				morceau.setId(generated.getInt(1));
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void update(Morceau morceau){
		try	{
			PreparedStatement preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setString(1, morceau.getName());
			preparedStatement.setString(2, morceau.getStringDuree());
			preparedStatement.setInt(3, morceau.getOeuvreId());

			preparedStatement.setInt(4, morceau.getId());

			/// result
			preparedStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public ArrayList<Morceau> findAllByOeuvre(int oeuvre_id) {
		ArrayList<Morceau> morceaux = new ArrayList<Morceau>();

		/// prepare + set
		try {
			String query = selectSQL + " WHERE oeuvre_id=?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, oeuvre_id);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				morceaux.add(this.fetch(rs));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return morceaux;
	}

}
