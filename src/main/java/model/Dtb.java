
package model;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class Dtb
{
	private static Connection connection = null;
	
	public static Connection getConnection()
	{
		/// si set
		if (connection != null) return connection;

		/// sinon
		try {
			Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      System.out.println("Where is your MySQL JDBC Driver?");
      e.printStackTrace();
      return null;
    }

		/// ok
		try {
			connection = DriverManager
				.getConnection(Config.getUrl(), Config.getUser(), Config.getPwd());
		            //.getConnection("jdbc:mysql://localhost:3306/tchat","root", "root");
		} catch (SQLException e) {
		  System.out.println("Connection Failed! Check output console");
		  e.printStackTrace();
		  return connection;
		}
		
		return connection;
	}
}
