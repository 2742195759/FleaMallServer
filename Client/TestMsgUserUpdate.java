package Client;

import static org.junit.Assert.*;

import org.junit.Test;
import Message.*;
import Respond.* ; 
public class TestMsgUserUpdate {

	@Test
	public void test() {
		Respond r ; RspSingleRow rsr ; 
		r = new MsgAccoutUpdate("201492275" , "3511", "熊雄" , null , "4舍210" , "18340879753").sendAndReturn() ; 
		assertEquals(r.getState() , "success");
		rsr = (RspSingleRow) new MsgAccountInfo("201492275" , "3511").sendAndReturn() ;
		assertEquals(rsr.getState() , "success") ; 
		assertEquals(rsr.getString("nick") , "熊雄") ;
		assertEquals(rsr.getString("addr") , "4舍210") ; 
		r = new MsgAccoutUpdate("201492275" , "3511" , "熊昆" , null , null , null).sendAndReturn() ; 
	}

}
