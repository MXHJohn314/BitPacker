package flygingpizza;

import org.bitpacker.domain.BitPacker;
import org.bitpacker.domain.WompusPacker;
import jdk.jshell.spi.ExecutionControl;
import org.bitpacker.domain.models.Wompus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WompusPackerTest {
	@BeforeEach
	void setUp() {
	}
	
	@AfterEach
	void tearDown() {
	}
	
	@Test
	void getPackList() throws ExecutionControl.NotImplementedException {
		new WompusPacker().getPackList();
	}
	
	@Test
	void unpackBits() throws ExecutionControl.NotImplementedException {
		new WompusPacker().unpackBits(new BitPacker(new byte[0], 0), 0);
	}
	
	@Test
	void read() throws ExecutionControl.NotImplementedException {
		new WompusPacker().read(new BitPacker(new byte[0], 0));
	}
	
	@Test
	void packBits() throws ExecutionControl.NotImplementedException {
		new WompusPacker().packBits(0,0, new BitPacker(new byte[0], 0));
	}
	
	@Test
	void write() throws ExecutionControl.NotImplementedException {
		new WompusPacker().write(new Wompus());
	}
}
/*


class WompusPackerTest {

	@Test
	void packBits() 
	
	@Test
	void read() 
	
	@Test
	void write() 
}
*/
