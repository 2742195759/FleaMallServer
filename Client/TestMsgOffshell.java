package Client;

import static org.junit.Assert.*;

import org.junit.Test;

import Message.*;
import Respond.*;

public class TestMsgOffshell {

	@Test
	public void test() {
		///验证了MsgOffCommodity ; MsgGetNodify ;
		///模拟了下架.
		Respond r = new MsgCommodityOff("201492275" , "3511" , "1111111").sendAndReturn() ; 
		assertEquals(r.getState() , "success") ; 
		///模拟了权限不够.
		RspMultiRow rmr = (RspMultiRow) new MsgNodifyGet("20149060" , null).sendAndReturn() ; 
		assertEquals(rmr.getState() , "AccessDenied") ; 
		///已经被下架.获取通知
		rmr = (RspMultiRow) new MsgNodifyGet("201492060" , "3511").sendAndReturn() ; 
		assertEquals(rmr.getState() , "success")	 ; 
		assertEquals(rmr.getSingleRow(0).getString("title") , "收藏物品下架");
		///已经被删除
		rmr = (RspMultiRow) new MsgNodifyGet("201492060" , "3511").sendAndReturn() ; 
		assertEquals(rmr.getState() , "success")	 ; 
		assertEquals(rmr.size() , 0);
		///回复原样.
		assertEquals (new MsgSqlStatement( "insert into Commodity(cno , brief) values (1111111 , \'衣服\')").sendAndReturn().getState() , "success") ; 
		assertEquals (new MsgSqlStatement( "insert into Selling(sno , cno , flea_date) values (201492275 , 1111111 , now())").sendAndReturn().getState() , "success") ;  
		assertEquals (new MsgLoveOperate("201492060" , "3511" , "1111111" , MsgLoveOperate.create).sendAndReturn().getState() , "success") ; 
	}

}
