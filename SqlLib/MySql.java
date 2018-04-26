package SqlLib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySql {
	/*
	 * Configuration of the sql account .
	 * Database Name = "FleaMall"
	 * Database Table = "User"
	 */
	
	private String DataBase_Name = "FleaMall" ;
	private String DataBase_User = "root" ;
	private String DataBase_Url = "jdbc:mysql://127.0.0.1/" ; 
	private String DataBase_Pword = "3511" ;
	
	
	

	protected Connection conn = null; 
	private String data_base_path = DataBase_Url + DataBase_Name + "?" + "user=" + DataBase_User+"&password=" + DataBase_Pword + "&useUnicode=true&characterEncoding=utf-8";
	public MySql() {
		init() ; 
	}	
	void init() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance() ;
			try {
			    conn =
			       DriverManager.getConnection(data_base_path);
			    System.out.print("MySql Connect Successful\n");
			    // Do something with the Connection
//			    Statement stm = conn.createStatement() ; 
//			    ResultSet rs = stm.executeQuery("SELECT * from FileMng;") ;
//			    while(rs.next()) {
//			    	System.out.print(rs.getString("file_type") ); 
//			    	System.out.print(rs.getString("file_path") );
//			    }
			  
			} catch (SQLException ex) {
			    // handle any errors
			    System.out.println("SQLException: " + ex.getMessage());
			    System.out.println("SQLState: " + ex.getSQLState());
			    System.out.println("VendorError: " + ex.getErrorCode());
			    System.exit(-1);
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getConn() {
		if(conn == null) init() ; 
		return conn ; 
	}
	public void closeConn() {
		try  {
				conn.close() ; 
				conn = null ;
		}
		catch (Exception e) {
			e.printStackTrace();
			conn = null ; 
		}
	}
}
