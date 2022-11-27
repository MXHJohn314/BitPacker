package example;

import domain.core.BitBufferAbstract;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Example of a concrete implementation of a BitBuffer.
 */
class BitBuffer extends  BitBufferAbstract {
	private StringBuilder binaryBuilder;
	private String binary;
	
	/**
	 * Pack the integer data into fewer bytes.
	 * @param bytes the bytes to pack.
	 * @return the packed version of the bytes.
	 */
	public byte[] pack(byte[] bytes) {
		binaryBuilder = new StringBuilder();
		for(byte aByte: bytes) {
			binaryBuilder.append("1");
			String binary = Integer.toUnsignedString(aByte,2);
			String packSize = Integer.toUnsignedString(binary.length(), 2);
			packSize = "0".repeat(3 - packSize.length()) + packSize;
			binaryBuilder.append(packSize).append(binary);
		}
		binaryBuilder.append("0");
		binary = binaryBuilder + "0".repeat(8 - ( binaryBuilder.length() % 8 ));
		byte[] output = new byte[binary.length() / 8 + ( binary.length() % 8 == 0 ? 0 : 1 )];
		for(int i = 0; i < output.length; i++) {
			String chop = chop(8, false);
			int i1 = Integer.parseUnsignedInt(chop, 2);
			output[i] = (byte)i1;
		}
		return output;
	}

	private String chop(int index, boolean doMin) {
		int index1 = doMin ? Math.min(index, binary.length()) : index;
		String temp = binary.substring(0, index1);
		binary = binary.substring(index1);
		return temp.isEmpty() ? "0" : temp;
	}

	/**
	 * Expand the data from comacted bytes.
	 *
	 * @param bytes   the bytes to unpack.
	 * @return the unpacked version of the bytes.
	 */
	@Override public byte[] unpack(byte[] bytes) {
			binary = "";
			binaryBuilder = new StringBuilder();
			for(byte aByte: bytes) {
				for(int i = 0; i < 8; i++) {
					binaryBuilder.append((( aByte >> ( 7 - i ) ) & 1 ) == 1 ? "1" : "0");
				}
			}
			binary = binaryBuilder.toString();
			List<String> strings = new ArrayList<>();
			while(binary.length() > 0 && chop(1, false).equals("1")) {
				String sizeString = chop(3, false);
				short packSize = (short)Integer.parseUnsignedInt(sizeString, 2);
				strings.add(chop(packSize, true));
			}

			var values = strings.stream().map(x -> Integer.parseUnsignedInt(x, 2)).toList();
			byte[] output = new byte[values.size()];
			Iterator<Integer> byteIterator = values.iterator();
			for(
			 int idx = 0;
			 idx < output.length; idx++) {
				output[idx] = byteIterator.next().byteValue();
			}
			return output;
		}
}
