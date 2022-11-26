package algo.galacticwafer.v1;

import domain.core.PackDescribable;

/**
 * @param nextBitCount
 * @param packedSize
 * @param unpackedSize
 */
public record BitPack(int nextBitCount, int packedSize,
					  int unpackedSize) implements PackDescribable {
	/**
	 * @return The number of bits to read for the next integer.
	 */
	@Override public int nextBitCount() {
		return nextBitCount;
	}
	
	/**
	 * @return the number of bits used to represent the integer in Java.
	 */
	@Override public int packedSize() {
		return packedSize;
	}
	
	/**
	 * @return The number of bits the next value to read is guaranteed to fit within.
	 */
	@Override public int unpackedSize() {
		return 0;
	}
}
