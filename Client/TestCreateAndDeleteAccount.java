package Client;

import static org.junit.Assert.*;

import org.junit.Test;

import Message.Message;
import Message.MsgAccountDelete;
import Message.MsgLogin;
import Message.MsgRegister;
import Respond.Respond;
import Respond.RspSingleRow;

public class TestCreateAndDeleteAccount {

	@Test
	public void test() {
		Respond rsp = null ; 
		///先创建一个账户.
		Message msg = new MsgRegister("201492111" , "1111");
		rsp = (Respond) msg.sendAndReturn() ;
		assertEquals(rsp.getState() , "success") ; 
		assertEquals(rsp.getExtra() , "1") ; 
		///先创建一个账户.
		msg = new MsgRegister("201492111" , "1112");
		rsp = (Respond) msg.sendAndReturn() ;
		assertEquals(rsp.getState() , "AlreadyExist") ; 
		///查看是否存在
		MsgLogin mlg = new MsgLogin("201492111" , "1111") ; 
		RspSingleRow rsl = (RspSingleRow) mlg.sendAndReturn() ; 
		assertEquals(rsl.getState() , "success") ; 
		assertEquals(rsl.getString("sno") , "201492111") ;
		///删除这个账户
		MsgAccountDelete mda = new MsgAccountDelete("201492111" ,  "1111") ;
		rsp = (Respond) mda.sendAndReturn() ; 
		assertEquals(rsp.getState() , "success") ; 
		assertEquals(rsp.getExtra() , "1") ; 
		///查看是否存在
		rsl = (RspSingleRow) mlg.sendAndReturn() ; 
		assertNotEquals(rsl.getState() , "success") ; 
		assertEquals(rsl.getState() , "WrongPassword") ;
	}
}
