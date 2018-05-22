package Client;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;

import Message.*;
import Respond.*;
public class main {
	static public void main(String[] arg) throws UnsupportedEncodingException {
		RspDate rsp = (RspDate) new MsgImageDate("1111111" , 0).sendAndReturn() ; 
		//assertEquals(rsp.getState() , "successss") ; 
		System.out.println( rsp.date ) ; 
		//assert(false)  ;
	}
}
