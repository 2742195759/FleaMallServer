package Client;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import Message.*;
import Respond.*;
class MM {
	public int xx ; 
	MM set(int x) {
		xx = x ; return this ; 
	}
	@Override
	public boolean equals(Object m) {
		MM tm = (MM) m ; 
		return tm.xx == xx ; 
	}
	@Override 
	public int hashCode() {
		return Objects.hash(xx);
	}
}
public class main {
	static public void main(String[] arg) throws UnsupportedEncodingException {
		HashMap<MM , MM>as = new HashMap<MM , MM>() ; 
		MM key1 = new MM().set(1) ; 
		MM key2 = new MM().set(1) ; 
		System.out.println(key1.equals(key2)) ; 
		MM ori = new MM().set(12) ; 
		as.put(key1 , ori) ; 
		System.out.println(as.get(key2).xx); 
	}
}
