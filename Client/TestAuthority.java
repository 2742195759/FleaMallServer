package Client;

import static org.junit.Assert.*;

import org.junit.Test;

import Message.MsgAccountDelete;
import Message.MsgRegister;
import Respond.Respond;

public class TestAuthority {

	@Test
	public void test1() {
		//权限失败。MsgDeleteAccount失败。
		Respond r ; 
		new MsgRegister("201492111" , "3511").sendAndReturn() ; 
		r = new MsgAccountDelete("201492111" , null).sendAndReturn();
		assertEquals(r.getState() , "AccessDenied") ; 
		r = new MsgAccountDelete("201492111" , "3511").sendAndReturn();
		assertEquals(r.getState() , "success") ; 
	}

}
