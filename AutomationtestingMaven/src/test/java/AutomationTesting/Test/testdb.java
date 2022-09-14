package AutomationTesting.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class testdb {
	
	private static String url= "jdbc:mysql://localhost:3306/testDB";
	private static String DriverName= "com.mysql.jdbc.Driver";
	private static String username="root";
	private static String password="Omkar96@";
	private static Connection con;
	
	public static void main(String[] args) {
		try {
			Class.forName(DriverName);
			
			try {
				con = DriverManager.getConnection(url,username,password);
				Statement stmt = con.createStatement();
				
				//execute query
				stmt.executeUpdate("INSERT INTO testDB.employee VALUES('RAM',95000)");
				ResultSet rs = stmt.executeQuery("SELECT *FROM testDB.employee");
				rs.last();
				int rows = rs.getRow();
				ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
				int cols = rsmd.getColumnCount();
				System.out.println(rows + "--" +cols);
				String [][] inputArr = new String[rows][cols];
				int i=0;
				rs.beforeFirst();
				
				while(rs.next())
				{
					for(int j=0;j<cols;j++)
					{
						inputArr[i][j]=rs.getString(j+1);
						System.out.println(inputArr[i][j]);
						
					}
					System.out.println();
				}
			}catch(SQLException ex) {
				ex.printStackTrace();
				System.out.println("failed to create db connection");
				
			}
		} catch(ClassNotFoundException ex) {
			ex.printStackTrace();
			System.out.println("Driver not found");
		}
	
	}

}
