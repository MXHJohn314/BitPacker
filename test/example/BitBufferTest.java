package example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class BitBufferTest {
	@Test
	void packUnpack() {
		byte[] in = new byte[]{
		 0b0_111,
		 0b0_0111,
		 0b0_1010,
		 0b0_0101,
		 0b0_01001110,
		 0b0_1110001,
		 0b0_0101011,
		 0b0_00000,
		};
		
		BitBuffer bb = new BitBuffer();
		byte[] pack = bb.pack(in);
		byte[] unpack = bb.unpack(pack);
		for(byte b: unpack) System.out.print(b + " ");
		System.out.println();
		Assertions.assertArrayEquals(unpack, in);
	}
}
