package MainLoop;
import Message.Message;
import Message.SqlTool;
import SqlLib.MySql;
import java.io.* ; 
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
	static public void main(String[] arg) throws SQLException, UnsupportedEncodingException {
		/*
		 * Many other thing to do here . But this can be delayed before achieve the whole framework . 
		 * Such as:
		 * 		TCP socket timeout .
		 * 		Muti-thread and thread-pool can be applied .
		 * 
		 * */
		MySql mysql = new MySql() ; 
		try {
			ServerSocket ss = new ServerSocket(3511);
			while(true) {
				try {
				System.out.printf("Listening for connect\n") ; 
				Socket cli = ss.accept() ;
				System.out.print(cli);
				System.out.print("Accept Successful\n");
				ObjectInputStream ois = new ObjectInputStream((cli.getInputStream())) ;
				ObjectOutputStream oos = new ObjectOutputStream((cli.getOutputStream())) ; 
				Message msg = (Message) ois.readObject() ;
				msg.print();
				Connection c = mysql.getConn() ;
				oos.writeObject(msg.wrapHandle(c)); 
				cli.close();
				mysql.closeConn();
				}
				catch (Exception e) {
					e.printStackTrace();
					continue ; 
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
