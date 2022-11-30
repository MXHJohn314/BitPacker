package domain.core;

import models.org.nightshadeenterprise.Wumpus;

/**
 * 
 */
public interface BitPackable<T> {
	/** Pack the data into fewer bytes.
	 * @param data the object to pack.
	 * @return the packed version of the bytes.
	 */
	byte[] pack(T data);
	
	String toBinaryString(Wumpus wump);
	
	Wumpus fromBinaryString(String binary);
	
	/**
	 * Expand the data from compacted bytes.
	 * @param bytes the bytes to unpack.
	 * @return the unpacked version of the bytes.
	 */
	T unpack(byte[] bytes);
}
