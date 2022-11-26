package domain.core;

import java.util.List;

/**
 * @param <T> Some type that is PackDescribable.
 */
public interface CompressedSizeable<T> {
	/** Pack the data into less bytes.
	 * @param packDescribable describes how to pack the bytes.
	 * @param bytes the bytes to pack.
	 * @return the packed version of the bytes.
	 */
	byte[] pack(List<T> packDescribable, byte[] bytes);
	
	/**
	 * Expand the data from comacted bytes.
	 * @param packDescribable describes how to unpack the bytes.
	 * @param bytes the bytes to unpack.
	 * @return the unpacked version of the bytes.
	 */
	byte[] unpack(List<T> packDescribable, byte[] bytes);
}
