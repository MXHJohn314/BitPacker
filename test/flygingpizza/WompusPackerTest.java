package flygingpizza;

import org.bitpacker.domain.BitPacker;
import org.bitpacker.domain.WompusPacker;
import jdk.jshell.spi.ExecutionControl;
import org.bitpacker.domain.models.Wompus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class WompusPackerTest {
	@BeforeEach
	void setUp() {
	}
	
	@AfterEach
	void tearDown() {
	}
	
	
	@Test
	void unpackBits() throws ExecutionControl.NotImplementedException {
		new WompusPacker().loadBits(new BitPacker(new byte[0], 0) {
			@Override public void dumpMany(List<Wompus> wompus) throws ExecutionControl.NotImplementedException {
				
			}
		}, 0);
	}
	
	@Test
	void read() throws ExecutionControl.NotImplementedException {
		new WompusPacker().loadEntity(new BitPacker(new byte[0], 0) {
			@Override public void dumpMany(List<Wompus> wompus) throws ExecutionControl.NotImplementedException {
				
			}
		});
	}
	
	@Test
	void packBits() throws ExecutionControl.NotImplementedException {
		new WompusPacker().dumpBits(0,0, new BitPacker(new byte[0], 0) {
			@Override public void dumpMany(List<Wompus> wompus) throws ExecutionControl.NotImplementedException {
				
			}
		});
	}
	
	@Test
	void write() throws ExecutionControl.NotImplementedException {
		new WompusPacker().dumpOne(new Wompus());
	}
}
/*


class WompusPackerTest {

	@Test
	void dumpBits() 
	
	@Test
	void loadEntity() 
	
	@Test
	void dumpOne() 
}
*/
