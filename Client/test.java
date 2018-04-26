package Client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.*;
///这个代码是完整的Java链接数据库的代码,现在没啥用了.但是可以给不懂的人看,学习作用,.
public class test {
	static public void main(String[] arg) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance() ;
			Connection conn = null;
		
			try {
			    conn =
			       DriverManager.getConnection("jdbc:mysql://127.0.0.1/xiongkun?" +
			                                   "user=root&password=3511");
			    System.out.print("OK");
			    // Do something with the Connection
			    Statement stm = conn.createStatement() ; 
			    ResultSet rs = stm.executeQuery("SELECT * from FileMng;") ;
			    while(rs.next()) {
			    	System.out.print(rs.getString("file_type") ); 
			    	System.out.print(rs.getString("file_path") );
			    }
			  
			} catch (SQLException ex) {
			    // handle any errors
			    System.out.println("SQLException: " + ex.getMessage());
			    System.out.println("SQLState: " + ex.getSQLState());
			    System.out.println("VendorError: " + ex.getErrorCode());
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
}
