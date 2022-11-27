package domain.core;

/**
 * @param <T> Some type that is PackDescribable.
 */
public interface CompressedSizeable<T> {
	/** Pack the data into less bytes.
	 * @param bytes the bytes to pack.
	 * @return the packed version of the bytes.
	 */
	byte[] pack(byte[] bytes);
	
	/**
	 * Expand the data from comacted bytes.
	 * @param bytes the bytes to unpack.
	 * @return the unpacked version of the bytes.
	 */
	byte[] unpack(byte[] bytes);
}
