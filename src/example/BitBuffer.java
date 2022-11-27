package example;

import domain.core.BitBufferAbstract;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Example of a concrete implementation of a BitBuffer.
 */
class BitBuffer {
	/**
	 * @param args N/A
	 */
	public static void main(String[] args) throws IOException {
//		List<PackDescribable> list = List.of(
//		 new BitPack(4, 8),
//		 new BitPack(4, 8),
//		 new BitPack(4, 8),
//		 new BitPack(4, 8)
//		);
		BitBufferAbstract buff = new BitBufferAbstract() {
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
					String binary = Integer.toUnsignedString(aByte,2);
					String packSize =
//					 Integer.toBinaryString(Integer.parseUnsignedInt(Integer.toString(binary.length())));
							Integer.toUnsignedString(binary.length(), 2);
					packSize =
					 "0".repeat(3 - packSize.length()) + packSize;
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
				bin = new StringBuilder();
				for(byte aByte: bytes) {
					for(int i = 0; i < 8; i++) {
						bin.append((( aByte >> ( 7 - i ) ) & 1 ) == 1 ? "1" : "0");
					}
				}
				binary = bin.toString();
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
		};
		byte[] bytes = {
		 (byte)0b0_0111000, //56
		 (byte)0b0_0101010, //46
		 (byte)0b0_10, //2
		 (byte)0b0_01101, //13
		 (byte)0b0_01101,
		 (byte)0b0_01101,
		 (byte)0b0_01101,
		 (byte)0b0_01101,
		 (byte)0b0_01101,
		 (byte)0b0_01101,
		 (byte)0b0_01101,
		 (byte)0b0_01101,
		 (byte)0b0_01101,
		};

		byte[] bytes1 = buff.pack(bytes);
		String fileName = args[0];
		Files.write(Paths.get(fileName), bytes1);

		Path path = Paths.get("byte_array.malc");
		byte[] bytes2 = Files.readAllBytes(path);

		byte[] unpackOutput = buff.unpack(bytes2);
		for (byte b : unpackOutput) {
			System.out.println(b);
		}

	}
}
