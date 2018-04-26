package Client;

import static org.junit.Assert.*;

import org.junit.Test;

import Message.*;
import Respond.Respond;
import Respond.RspMultiRow;

public class TestGetCommodity {
	@Test
	public void test1() throws InterruptedException {
		Respond r ; 
		r = new MsgRegister("201492111", "1111") .sendAndReturn() ;
		
		r = new MsgCommodityCreateSell("201492111" ,"这个是个帽子", "胡歌同款帽子(二手)" , "120" , "4舍210" , "1111" , null).sendAndReturn() ; 
		assertEquals(r.getState() , "success") ; 
	
		Thread.sleep(2000);
		
		r = new MsgCommodityCreateSell("201492111" ,"这个是个头巾", "胡歌同款头巾(二手)" , "20" , "4舍210" , "0000" , null).sendAndReturn() ; 
		assertEquals(r.getState() , "WrongPassword") ; 
	
		r = new MsgCommodityCreateSell("20149	2111" ,"这个是个头巾", "胡歌同款头巾(二手)" , "20" , "4舍210" , "1111" , null).sendAndReturn() ; 
		assertEquals(r.getState() , "success") ; 
		///测试ByTable
		RspMultiRow rmr = (RspMultiRow) new MsgCommodityByTable("201492111" , null , MsgCommodityByTable.Sell).sendAndReturn() ; 
		assertEquals(rmr.getState() , "success") ;
		assertEquals(rmr.size() , 2) ; 
		///测试ByTime
		rmr = (RspMultiRow) new MsgCommodityByTime(1 , 2).sendAndReturn() ;  ///获取地一个
		assertEquals(rmr.getState() , "success") ;
		assertEquals(rmr.size() , 2) ; 
		assertEquals(rmr.getSingleRow(0).getString("brief") , "胡歌同款帽子(二手)") ;
		assertEquals(rmr.getSingleRow(1).getString("brief") , "衣服") ;
		
		r = new MsgAccountDelete("201492111" , "1111").sendAndReturn() ; 
		assertEquals(rmr.getState() , "success") ;
		//assertEquals(rmr.getSingleRow(0).getString("detail"), "这个是个帽子") ; 
		//assertEquals(rmr.getSingleRow(1).getString("detail"), "这个是个头巾") ; 
		
		
	}
}
