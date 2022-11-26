package domain.core;

import algo.galacticwafer.v1.BitPack;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 
 */
public class BitBuffer extends BitAccessBase implements CompressedSizeable<PackDescribable> {
	/**
	 * @param descriptions A list of {@code PackDescribable} objects to execute instructions against a byte array. 
	 */
	public BitBuffer(List<PackDescribable> descriptions) {
		super(BitBuffer.bytesFrom(descriptions), descriptions);
	}
	
	private static byte[] bytesFrom(List<PackDescribable> list) {
		var bights = new byte[list.size()];
		Iterator<PackDescribable> iterator = list.iterator();
		PackDescribable next = iterator.next();
		for(int i = 0; i < bights.length; i++) {
			bights[i] = (byte)next.packedSize();
		}
		return bights;
	}
	
	/**
	 * @param args N/A
	 */
	public static void main(String[] args) {
		List<PackDescribable> list = List.of(
		 new BitPack(8, 15, 4),
		 new BitPack(8, 15, 4),
		 new BitPack(8, 15, 4),
		 new BitPack(8, 15, 4));
		BitBuffer buff = new BitBuffer(list);
		
		byte[] packedBytes = new byte[4];
		packedBytes = buff.pack(list, packedBytes);
		var unpackedBytes = buff.unpack(list, packedBytes);
		buff.getBytes();
		System.out.println(Arrays.equals(buff.getBytes(), unpackedBytes));
	}
		
	@Override public byte[] pack(List<PackDescribable> descriptions, byte[] bytes) {
		return null;
	}
	
	@Override public byte[] unpack(List<PackDescribable> descriptions, byte[] bytes) {
		return null;
	}
}
