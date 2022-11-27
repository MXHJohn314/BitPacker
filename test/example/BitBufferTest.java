package example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class BitBufferTest {
	@Test
	void pack() {
		byte[] in = new byte[]{
		 0b0_111, // "1011111"
		 0b0_0111, // "1011111"
		 0b0_1010, // "11001010"
		 0b0_0101, // "10110101"
		 0b0_01001110, // "11111001110"
		 0b0_1110001, // "11111110001"
		 0b0_0101011, // "1110101011"
		 0b0_00000, // "10010"
		 // ending "0"
		}; //->"1011111101111111001010101101011111100111011111110001111010101110010"
		String binary = "1011111101111111001010101101011111100111011111110001111010101110010";
		byte[] bval = new BigInteger(binary, 2).toByteArray();
		Assertions.assertArrayEquals(new BitBuffer().pack(in), bval);
	}
	@Test
	void unpack() {
		
	}
}
