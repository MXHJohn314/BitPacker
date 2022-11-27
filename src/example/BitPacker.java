package example;

import domain.core.BitBufferAbstract;
import domain.core.PackDescribable;

import java.util.List;

/**
 * 
 */
public class BitPacker /*extends BitBufferAbstract*/ {
	/**
	 * @param descriptions A list of {@code PackDescribable} objects to execute instructions
	 *                     against a byte array.
	 */
/*	public BitPacker(List<PackDescribable> descriptions) {
		super(descriptions);
	}*/
	

	/**
	 * Pack the data into fewer bytes.
	 *
	 * @param descriptions describes how to pack the bytes.
	 * @param input        the bytes to pack.
	 * @return the packed version of the bytes.
	 */
	
/*	@Override*/ public byte[] pack(List<PackDescribable> descriptions, byte[] input) {
		int sum = descriptions.stream().mapToInt(PackDescribable::packedCount).sum();
		byte[] output = new byte[sum / 64 + sum % 64 == 0 ? 0 : 1];
		int readHead_I = 0, writeHead_I = 0;
		for(PackDescribable description: descriptions) {
			int readHead = 0, writeHead_J = 0;
			int packedCount = description.packedCount();
			int unpackedCount = description.unpackedCount();
			int numPaddedZeroes = 64 - packedCount;
			int valueStartBit = numPaddedZeroes + readHead;
			int valueEndBit = numPaddedZeroes + readHead  + packedCount;
			int valueStartByte = valueStartBit / 64;
			int valueEndByte = valueEndBit / 64;
			if (valueStartByte != valueEndByte) {
				// process a chunk, readHead
			}

			// calculate the amount of space left in the byte
			readHead_I += unpackedCount;
			writeHead_I += packedCount;
		}
		return output;
	}
	
	/**
	 * Expand the data from compacted bytes.
	 *
	 * @param packDescribable describes how to unpack the bytes.
	 * @param bytes           the bytes to unpack.
	 * @return the unpacked version of the bytes.
	 */
	/*@Override*/ public byte[] unpack(List<PackDescribable> packDescribable, byte[] bytes) {
		return new byte[0];
	}
}
