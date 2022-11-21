package models;

import domain.accessing.BitAccess;
import jdk.jshell.spi.ExecutionControl;
import org.flygingpizza.WompusPacker;
import org.junit.jupiter.api.Test;

class WompusPackerTest {
	@Test
	void getPackList() throws ExecutionControl.NotImplementedException {
		new WompusPacker().getPackList();
	}
	
	@Test
	void unpackBits() throws ExecutionControl.NotImplementedException {
		new WompusPacker().unpackBits(new BitAccess(new byte[0], 0), 0);
	}
	
	@Test
	void packBits() throws ExecutionControl.NotImplementedException {
		new WompusPacker().packBits(0,0, new BitAccess(new byte[0], 0));
	}
	
	@Test
	void read() throws ExecutionControl.NotImplementedException {
		new WompusPacker().read(new BitAccess(new byte[0], 0));
	}
	
	@Test
	void write() {
		new WompusPacker().write(new Wompus());
	}
}
