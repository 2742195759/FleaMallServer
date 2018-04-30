package Client;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import Message.* ; 
import Respond.* ; 
public class TestImage {

	@Test
	public void test() throws IOException {
		RspImage g1 = new RspImage("/home/xopngkun/桌面/"
				+ "g1.png" , -1) ; 
		RspImage g2 = new RspImage("/home/xopngkun/桌面/"
				+ "linux-kernel-map-linux_story.jpg" , -1) ; 
		
		Respond rsp = new MsgImageSave("201492275" , null , "1111111").addImage(g1).sendAndReturn() ;
		assertEquals(rsp.getState() , "AccessDenied") ;
		rsp = new MsgImageSave("201492275" , "3511" , "1111111").addImage(new RspImage("/home/xopngkun/桌面/"
				+ "g1.png" , -1)).sendAndReturn() ;
		assertEquals(rsp.getState() , "success") ;
		RspImage rimg = (RspImage) new MsgImageFetch("201492275" , null , "1111111" , 0).sendAndReturn() ; 
		assertEquals(rimg.getState() , "success") ; 
		assertNotEquals(rimg.size() , -1) ;
		rsp = new MsgImageSave("201492275" , "3511" , "1111111").addImage(g2).sendAndReturn() ;
		assertEquals(rsp.getState() , "success") ;
		
		rsp = new MsgImageSave("201492275" , "3511" , "1111111").addImage(g1).addImage(g2).sendAndReturn() ;
		assertEquals(rsp.getState() , "success") ;
		
		rsp = new MsgImageSave("201492275" , "3511" , "1111111").addImage(new RspImage(1)).sendAndReturn() ;
		assertEquals(rsp.getState() , "success") ;
	}

}
