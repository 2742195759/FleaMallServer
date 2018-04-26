package Client;

import static org.junit.Assert.*;

import org.junit.Test;
import Respond.* ; 
import Message.* ; 
public class TestUpdateCommodity {
	@Test
	public void test() {
		Respond rs ; RspMultiRow rmr ; 
		rs = new MsgRegister("201492111", "1111") .sendAndReturn() ;
		assertEquals(rs.getState() , "success") ; 
		
		rs = new MsgCommodityCreateSell("201492111" ,"这个是个帽子", "帽子" , "120" , "4舍210" , "1111" , null).sendAndReturn() ; 
		assertEquals(rs.getState() , "success") ; 
	///查看修改之前 的.
		rmr = (RspMultiRow) new MsgCommodityByTable("201492111" , null , MsgCommodityByTable.Sell).sendAndReturn() ; 
		assertEquals(rmr.getState() , "success") ;
		assertEquals(rmr.size() , 1) ; 
		String cno = rmr.getSingleRow(0).getString("cno") ; 
		assertEquals(rmr.getSingleRow(0).getString("brief") , "帽子") ;
		///修改
		rs = new MsgCommodityCreateSell("201492111" ,"这个是个衣服", "衣服" , "120" , "4舍210" , "1111" , cno).sendAndReturn() ; 
		assertEquals(rs.getState() , "success") ; 
		
		///查看修改的结果
		rmr = (RspMultiRow) new MsgCommodityByTable("201492111" , null , MsgCommodityByTable.Sell).sendAndReturn() ; 
		assertEquals(rmr.getState() , "success") ;
		assertEquals(rmr.size() , 1) ; 
		assertEquals(rmr.getSingleRow(0).getString("brief") , "衣服") ;
		///清除side effect
		rs = new MsgAccountDelete("201492111" , "1111").sendAndReturn() ; 
		assertEquals(rs.getState() , "success") ;
	}

}
