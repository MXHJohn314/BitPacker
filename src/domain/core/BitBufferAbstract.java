package domain.core;

import java.util.Iterator;
import java.util.List;

/**
 * 
 */
public abstract class BitBufferAbstract extends BitAccessBase implements CompressedSizeable {
	protected int sizeInBits;
	
	/**
     */
	public BitBufferAbstract() {
		super();
	}
	
	@Override public int size() {
		return this.sizeInBits;
	}
}

