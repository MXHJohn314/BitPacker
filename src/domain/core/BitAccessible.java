package domain.core;

import jdk.jshell.spi.ExecutionControl;

/**
 * BitAccessible is an interface for manipulating individual bits into logical, consecutive
 * sub-arrays (bit packs) of a byte[].
 * This functionality allows the reclaiming of bits wasted due to restrictions on the domain
 * values for an object's member.
 * Bit packs can be of any unpackedSize integer on the interval [0, ).
 * The class stores data (as a byte array) in either compressed or uncompressed format via calls
 * to pack() or unpack() respectively.
 * The data must have an accompanying {@code packingOrder},  a list of key value pairs
 * instructing a {@code BitAccessible} on how to execute the pack.
 * The pairs tie an {@code integerValue} to a {@code <bitCount>} for every integer representing
 * an encoded object.
 */
interface BitAccessible {
	/**
	 * Update one bit in a particular byte.
	 *
	 * @param targetByte the index of the target byte.
	 * @param targetBit  the index of the target bit.
	 * @param bitValue   a boolean used to write either zero or one.
	 * @throws ExecutionControl.NotImplementedException because this is still alpha.
	 */
	void setBit(int targetByte, int targetBit, boolean bitValue) throws ExecutionControl.NotImplementedException;
	
	/**
	 * Update one bit in a particular byte.
	 *
	 * @param targetByte the index of the target byte.
	 * @param targetBit  the index of the target bit.
	 * @return a boolean value representing the zero or one which was read.
	 */
	boolean getBit(int targetByte, int targetBit);
	
	/**
	 * @return The byte array which this class manipulates internally.
	 */
	byte[] getBytes();
	
	void setBytes(byte[] bytes);
	
	/**
	 * @return the total number of bits currently used to compress the data.
	 */
	int size();
	
	/**
	 * Make some helpful toString representations mandatory.
	 *
	 * @param readByte index of the current byte to read from.
	 * @param readBit index of the current bit to read from.
	 * @param writeByte index of the current byte to write to.
	 * @param writeBit index of the current bit to write from.
	 * @return the string representation of all bits from the specified byte.
	 */
	String printByte(int readByte, int readBit, int writeByte, int writeBit);
	
	String printBuffer(byte[] bytes);
}

