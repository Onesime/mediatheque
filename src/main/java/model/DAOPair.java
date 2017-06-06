package model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;


public abstract class DAOPair implements IDao<Pair>{

	private Connection connection = Dtb.getInstance();

	public abstract String getSelectSQL();

	protected Pair fetch(ResultSet resultSet) throws SQLException{
		return  new Pair(
			resultSet.getInt("id"),
			resultSet.getString("name"));
	}

	@Override
	public Pair find(int id)
	{
		/// conn
		Pair pair = null;
		String query = getSelectSQL() + " WHERE id=?";

		/// prepare + set
		try	{
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			/// result
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				pair = fetch(resultSet);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return pair;
	}

	@Override
	public ArrayList<Pair> findAll() {
		/// conn
		ArrayList<Pair> pairs = new ArrayList<Pair>();

		/// prepare + set
		try	{
			PreparedStatement preparedStatement = connection.prepareStatement(getSelectSQL());
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				pairs.add(this.fetch(resultSet));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return pairs;
	}

	@Override
	public void save(Pair pair){
	
	}

	@Override
	public void update(Pair pair){

	}

	@Override
	public void delete(Pair pair){

	}

	public ArrayList<String> findAllString() {
		/// conn
		ArrayList<String> pairs = new ArrayList<String>();

		/// prepare + set
		try	{
			PreparedStatement preparedStatement = connection.prepareStatement(getSelectSQL());
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				pairs.add(resultSet.getString("name"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return pairs;
	}

}



