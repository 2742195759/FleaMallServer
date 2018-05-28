package Client;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import Message.* ; 
import Respond.* ; 
public class TestGetOwner {

	@Test
	public void test() throws IOException {
		RspSingleRow rsr = (RspSingleRow) new MsgCommodityOwner("1111111").sendAndReturn() ; 
		assertEquals(rsr.getState() , "success") ; 
		assertEquals(rsr.getString("sno") , "201492275") ; 
	}

}
