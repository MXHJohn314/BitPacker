package domain.core;

import algo.galacticwafer.v2.BitPack;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Example of a concrete implementation of a BitBuffer.
 */
public class BitBuffer {
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
			/**
			 * Pack the integer data into fewer bytes.
			 * @param bytes the bytes to pack.
			 * @return the packed version of the bytes.
			 */
			public byte[] pack(byte[] bytes) {
				String superLongBinaryString = Base64.getEncoder().encodeToString(bytes);
				Pattern pattern = Pattern.compile(".{1," + Byte.SIZE + "}");
				Matcher matcher = pattern.matcher(superLongBinaryString);
				byte[] output = new byte[matcher.groupCount()];
				for (int i = 0; matcher.find(); i++) {
					output[i] = Byte.parseByte(superLongBinaryString.substring(matcher.start(), matcher.end()));
				}
				return output;
			/*	Iterator<Byte> bytetrator = bytes.iterator();
				byte[] byteStrings = new ArrayList<>(); do {
					StringBuilder byteStringBuilder = new StringBuilder();
					while( bytetrator.hasNext() && Byte.SIZE > byteStringBuilder.length()) {
						
						// get the binary with no leading zeroes as a string.
						String currStr = Long.toBinaryString( bytetrator.next());
						
						// check if the binary we want to write is too big to fit into a possibly-reduced-capacity StringBuilder.
						if(currStr.length() > Byte.SIZE - byteStringBuilder.length()) {
							
							// if it is too big, then use the rest of the byteStringBuilder
							byteStringBuilder.append(currStr, 0, Byte.SIZE - byteStringBuilder.length());
							
							// add byteStringBuilder to the list of bytsStrings
							byteStrings.add(byteStringBuilder);
							byteStringBuilder = new StringBuilder();
							byteStringBuilder.append(currStr.substring(
							 Byte.SIZE - byteStringBuilder.length()));
						}
						byteStringBuilder.append(currStr);
					}
				} while(bytetrator.hasNext());
				
				byte[] output = new byte[byteStrings.size()];
				Iterator<StringBuilder> sbit = byteStrings.iterator();
				for(int i = 0; i < output.length; i++) {
					output[i] = Byte.parseByte(sbit.next().toString());
				}
				return output;*/
			}
			
			/**
			 * Expand the data from comacted bytes.
			 *
			 * @param packSizes how many bits to unpack at each iteration.
			 * @param bytes   the bytes to unpack.
			 * @return the unpacked version of the bytes.
			 */
			@Override public byte[] unpack(List<Integer> packSizes, byte[] bytes) {
				byte[] output = new byte[packSizes.size()];
				Iterator<Integer> bitCounts = packSizes.iterator();
				String zeroesAndOnes = Base64.getEncoder().encodeToString(bytes);
				for(int i = 0; zeroesAndOnes.length() > 0; i++) {
					int bitCount = bitCounts.next();
					output[i] = Byte.parseByte(zeroesAndOnes.substring(0, bitCount));
				}
				return output;
			}
		};
		;
		
		byte[] b = buff.pack(new byte[] {
		 (byte)0b0_001,
		 (byte)0b0_011,
		 (byte)0b0_100,
		 (byte)0b0_010
		});
		var nums = buff.unpack(List.of(4, 4, 4, 4), b);
		System.out.println(Arrays.equals(buff.getValues(), nums));
	}
}
