package Client;

import static org.junit.Assert.*;

import org.junit.Test;

import Message.Message;
import Message.MsgLogin;
import Respond.RspSingleRow;

public class TestMsgLogin {

	@Test
	public void test1() { ///Success
		Message msg = new MsgLogin("201492275" , "3511") ;
		RspSingleRow rsp = (RspSingleRow) msg.sendAndReturn() ;
		assertEquals(rsp.getState() ,"success") ;
		assertEquals(rsp.getString("nick") , "熊昆") ; 
	}
	@Test
	public void test2() { ///
		Message msg = new MsgLogin("201492275" , "1111") ;
		RspSingleRow rsp = (RspSingleRow) msg.sendAndReturn() ;
		assertEquals(rsp.getState() , "WrongPassword") ;
	}
	@Test
	public void test3() { ///
		Message msg = new MsgLogin("201492276" , "3511") ;
		RspSingleRow rsp = (RspSingleRow) msg.sendAndReturn() ;
		assertEquals(rsp.getState() , "WrongPassword") ;
	}
	
	public void test4() { ///
		Message msg = new MsgLogin("201492275" , null) ;
		RspSingleRow rsp = (RspSingleRow) msg.sendAndReturn() ;
		assertEquals(rsp.getState() , "success") ;
	}
}
