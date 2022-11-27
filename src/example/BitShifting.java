package example;

import algo.galacticwafer.example.BitPack;
import domain.core.BitBufferAbstract;
import domain.core.PackDescribable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Example of a concrete implementation of a BitBuffer.
 */
class BitBuffer {
	/**
	 * @param args N/A
	 */
	public static void main(String[] args) {
		List<PackDescribable> list = List.of(
		 new BitPack(4, 8),
		 new BitPack(4, 8),
		 new BitPack(4, 8),
		 new BitPack(4, 8)
		);
		BitBufferAbstract buff = new BitBufferAbstract(list) {
			private StringBuilder bin;
			private String binary;
			
			/**
			 * Pack the integer data into fewer bytes.
			 * @param bytes the bytes to pack.
			 * @return the packed version of the bytes.
			 */
			public byte[] pack(byte[] bytes) {
				bin = new StringBuilder();
				for(byte aByte: bytes) {
					bin.append("1");
					String binary = Integer.toBinaryString(aByte);
					String packSize =
					 Integer.toBinaryString(Integer.parseUnsignedInt(Integer.toString(binary.length())));
					packSize =
					 "0".repeat(3 - Integer.toBinaryString(packSize.length()).length()) + packSize;
					bin.append(packSize).append(binary);
				}
				bin.append("0");
				binary = bin + "0".repeat(8 - ( bin.length() % 8 ));
				byte[] output =
				 new byte[binary.length() / 8 + ( binary.length() % 8 == 0 ? 0 : 1 )];
				for(int i = 0; i < output.length; i++) {
					String chop = chop(8, false);
					int i1 = Integer.parseUnsignedInt(chop, 2);
					output[i] = (byte)i1;
					System.out.println(i1);
				}
				return output;
			}
			
			private String chop(int index, boolean doMin) {
				int index1 = doMin ? Math.min(index, binary.length()) : index;
				String temp = binary.substring(0, index1);
				binary = binary.substring(index1);
				return temp;
			}
			
			/**
			 * Expand the data from comacted bytes.
			 *
			 * @param packSizes how many bits to unpack at each iteration.
			 * @param bytes   the bytes to unpack.
			 * @return the unpacked version of the bytes.
			 */
			@Override public byte[] unpack(List<Integer> packSizes, byte[] bytes) {
				binary = "";
				bin = new StringBuilder();
				for(byte aByte: bytes) {
					for(int i = 0; i < 8; i++) {
						bin.append((( aByte >> ( 7 - i ) ) & 1 ) == 1 ? "1" : "0");
					}
					System.out.println();;
				}
				binary = bin.toString();
				List<String> strings = new ArrayList<>();
				while(binary.length() > 0 && chop(1, false).equals("1")) {
					String sizeString = chop(3, false);
					short packSize = (short)Integer.parseUnsignedInt(sizeString, 2);
					System.out.print(
					 " separated off the first 4 bits to get a '1' + size of " + sizeString +
					  ", (" + packSize + ")");
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
		};
		var nums = buff.unpack(List.of(3, 3, 3, 3),  buff.pack(new byte[] {
		 (byte)0b0_001,
		 (byte)0b0_011,
		 (byte)0b0_100,
		 (byte)0b0_010
		}));
		System.out.println(Arrays.toString(nums));
	}
}
