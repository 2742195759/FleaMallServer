package Client;

import static org.junit.Assert.*;

import org.junit.Test;

import Message.*;
import Respond.*;

public class TestMsgImageDate {

	@Test
	public void test() {
		RspDate rsp = (RspDate) new MsgImageDate("1111111" , 0).sendAndReturn() ; 
		assertEquals(rsp.getState() , "success") ; 
		//assert(false)  ; 
	}

}
