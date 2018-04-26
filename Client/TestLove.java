package Client;

import static org.junit.Assert.*;

import org.junit.Test;

import Message.MsgAccountDelete;
import Message.MsgCommodityByTable;
import Message.MsgLoveOperate;
import Message.MsgRegister;
import Respond.Respond;
import Respond.RspMultiRow;
import Respond.RspSingleRow;

public class TestLove {

	@Test
	public void test() {
		Respond rs ; RspMultiRow rmr ; RspSingleRow rsr ;
		rs = new MsgRegister("201492111", "1111") .sendAndReturn() ;
		assertEquals(rs.getState() , "success") ; 
		
		assertEquals(new MsgLoveOperate("201492111" , "1111" , "1111111" , MsgLoveOperate.create).sendAndReturn().getState() , "success") ; 
		rmr = (RspMultiRow) new MsgCommodityByTable("201492111" , "1111" , MsgCommodityByTable.Love).sendAndReturn() ; 
		assertEquals(rmr.size() , 1) ;
		assertEquals(rmr.getSingleRow(0).getString("brief") , "衣服") ; 
		
		assertEquals(new MsgLoveOperate("201492111" , null , "1111111", MsgLoveOperate.delete).sendAndReturn().getState() , "AccessDenied") ;
		assertEquals(new MsgLoveOperate("201492111" , "1111" , "1111111", MsgLoveOperate.delete).sendAndReturn().getState() , "success") ; 
		
		rmr = (RspMultiRow) new MsgCommodityByTable("201492111" , "1111" , MsgCommodityByTable.Love).sendAndReturn() ; 
		assertEquals(rmr.size() , 0) ;
		
		assertEquals(new MsgAccountDelete("201492111", "1111").sendAndReturn().getState() , "success") ; 
	}

}
