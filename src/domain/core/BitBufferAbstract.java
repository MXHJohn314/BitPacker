package domain.core;

import java.util.Iterator;
import java.util.List;

/**
 * 
 */
public abstract class BitBufferAbstract extends BitAccessBase implements CompressedSizeable<PackDescribable> {
	protected int sizeInBits;
	
	/**
	 * @param packJob The list of args to complete the pack job with.
	 */

	public BitBufferAbstract(List<PackDescribable> packJob) {
		super();
		this.bytes = bytes;
	}
	
	protected static byte[] bytesFrom(List<PackDescribable> list) {
		var bights = new byte[list.size()];
		Iterator<PackDescribable> iterator = list.iterator();
		PackDescribable next = iterator.next();
		for(int i = 0; i < bights.length; i++) {
			bights[i] = (byte)next.packedCount();
		}
		return bights;
	}
	
	protected static int countLeadingZeroes(int bitsPerInteger, Integer intValue) {
		return (int)( bitsPerInteger - ( 1 + Math.log(intValue) / Math.log(2) ) );
	}
	
	@Override public int size() {
		return this.sizeInBits;
	}
}

