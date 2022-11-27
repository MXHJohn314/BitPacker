package domain.core;

/**
 * 
 */
public interface CompressedSizeable {
	/** Pack the data into fewer bytes.
	 * @param bytes the bytes to pack.
	 * @return the packed version of the bytes.
	 */
	byte[] pack(byte[] bytes);
	
	/**
	 * Expand the data from compacted bytes.
	 * @param bytes the bytes to unpack.
	 * @return the unpacked version of the bytes.
	 */
	byte[] unpack(byte[] bytes);
}
