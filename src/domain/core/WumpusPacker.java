package domain.core;

import models.org.flygingpizza.SmooBit;
import models.org.nightshadeenterprise.WumpType;
import models.org.nightshadeenterprise.Wumpus;

/**
 * Example of a concrete implementation of a BitBuffer.
 */
class WumpusPacker implements BitPackable<Wumpus> {
	public static final int MAX_WUMP_TYPE__BITS = 5;
	public static final int CHARS_PER_ID = 6;

	public static final int MAX_COORDINATE_BITS = 13;
	public static final int MAX_ANGLE_BITS = 9;
	public static final int MAX_CHAR_BITS = 7;
	private String binary;
	private static final int numBits = 186;
	
	WumpusPacker() {
	}
	
	/**
	 * Create a binary string representation of a Wumpus.
	 *
	 * @param wump
	 * @return
	 */
	@Override public String toBinaryString(Wumpus wump) {
		StringBuilder s = new StringBuilder();
		
		// 8k pixels are unsigned, up to 7680 == 13 bits
		s.append(adjust(Integer.toBinaryString(wump.homeSquare[0].left), MAX_COORDINATE_BITS));
		s.append(adjust(Integer.toBinaryString(wump.homeSquare[0].right), MAX_COORDINATE_BITS));
		s.append(adjust(Integer.toBinaryString(wump.homeSquare[1].left), MAX_COORDINATE_BITS));
		s.append(adjust(Integer.toBinaryString(wump.homeSquare[1].right), MAX_COORDINATE_BITS));
		s.append(adjust(Integer.toBinaryString(wump.homeSquare[2].left), MAX_COORDINATE_BITS));
		s.append(adjust(Integer.toBinaryString(wump.homeSquare[2].right), MAX_COORDINATE_BITS));
		s.append(adjust(Integer.toBinaryString(wump.homeSquare[3].left), MAX_COORDINATE_BITS));
		s.append(adjust(Integer.toBinaryString(wump.homeSquare[3].right), MAX_COORDINATE_BITS));
		
		s.append(adjust(Integer.toBinaryString(wump.mid.left), MAX_COORDINATE_BITS));
		s.append(adjust(Integer.toBinaryString(wump.mid.right), MAX_COORDINATE_BITS));
		
		// EquipmentType is an enum with less than 32 variants == 5 bits
		s.append(adjust(Integer.toBinaryString(wumpIndexOf(wump.wumpType.ordinal())), MAX_WUMP_TYPE__BITS));
		
		// Rotation is 0-359 == 9 bits
		s.append(adjust(Integer.toBinaryString(wump.angle), MAX_ANGLE_BITS));
		
		// ObjectId = ObjectId.length * (95 printable Characters =  7 bits) == length * 7 
		s.append(loopAdjust(( wump.id ), MAX_CHAR_BITS));
		
		return s.toString();
	}
	
	private int wumpIndexOf(int ordinal) {
		boolean found = false;
		for(int i = 0; i < WumpType.values().length; i++) {
			if(WumpType.values()[i].ordinal() == ordinal) {
				return i;
			}
		}
		throw new IllegalArgumentException("Valid WumpTypes only have indices between 0 and  " + (WumpType.values().length - 1)  + ".");
	}
	
	@Override public Wumpus fromBinaryString(String binary) {
		this.binary = binary.substring(0, numBits);
		
		SmooBit bit1 = getSmooBit();
		SmooBit bit2 = getSmooBit();
		SmooBit bit3 = getSmooBit();
		SmooBit bit4 = getSmooBit();
		SmooBit center = getSmooBit();
		
		int wumpValueIndex = Integer.parseInt(chop(MAX_WUMP_TYPE__BITS, false), 2);
		WumpType wumpT = WumpType.values()[wumpValueIndex];
		
		int rotation = Integer.parseInt(chop(MAX_ANGLE_BITS, false), 2);
		String id = binaryToPrintableCharString();
		return new Wumpus(new SmooBit[] {bit1, bit2, bit3, bit4,}, center, wumpT, id,
		 (short)rotation);
	}
	
	private String loopAdjust(String id, int bitCount) {
		StringBuilder s = new StringBuilder();
		
		char[] chars = id.toCharArray();
		for(int i = 0; i < chars.length; i++) {
			String bin = Integer.toBinaryString(( chars[i] - ' ' ));
			String adjustment = adjust(bin, 7);
			s.append(adjustment);
		}
		return s.toString();
	}
	
	private String adjust(String s, int i) {
		System.out.println(s + " needs " + ( i - s.length() ) + " more zeroes or ones...");
		return "0".repeat(Math.max(0, i - s.length())) + s;
	}
	
	private String toBinary(byte[] bytes) {
		StringBuilder sb = new StringBuilder(bytes.length * Byte.SIZE);
		for(int i = 0; i < Byte.SIZE * bytes.length; i++) {
			sb.append(( bytes[i / Byte.SIZE] << i % Byte.SIZE & 0x80 ) == 0 ? '0' : '1');
		}
		return sb.toString();
	}
	
	byte[] fromBinary(String s) {
		int sLen = s.length();
		byte[] toReturn = new byte[( sLen + Byte.SIZE - 1 ) / Byte.SIZE];
		char c;
		for(int i = 0; i < sLen; i++) {
			if(( c = s.charAt(i) ) == '1') {
				toReturn[i / Byte.SIZE] =
				 (byte)( toReturn[i / Byte.SIZE] | ( 0x80 >>> ( i % Byte.SIZE ) ) );
			}
			else if(c != '0') {
				throw new IllegalArgumentException();
			}
		}
		return toReturn;
	}
	
	/**
	 * Expand the data from compacted bytes.
	 *
	 * @param bytes the bytes to unpack.
	 * @return the unpacked version of the bytes.
	 */
	@Override public Wumpus unpack(byte[] bytes) {
		String s = this.toBinary(bytes);
		System.out.println(s);
		return this.fromBinaryString(s);
	}
	
	/**
	 * Pack the integer data into fewer bytes.
	 *
	 * @param data the bytes to pack.
	 * @return the packed version of the bytes.
	 */
	@Override public byte[] pack(Wumpus data) {
		String s = this.toBinaryString(data);
		System.out.println(s);
		return this.fromBinary(s);
	}
	
	/**
	 * Convert the binary String to a String of printable ASCII  characters by
	 * reading 7 bytes at a time.
	 * In order to represent the 95 printable chars within 7 bits, indexing must begin at zero.
	 * Therefore, unpacking must add 'A' to each char to restore the original value.
	 *
	 * @return The converted String as ASCII characters.
	 */
	private String binaryToPrintableCharString() {
		StringBuilder sb = new StringBuilder();
		//if(binary.length() % 8 != 0) {
		//	throw new UnpackableCharacterException(
		//	 "Expected the rest of the binary to be 7-bit chars...");
		//}
		int i = 0;
		while(i++ < CHARS_PER_ID) {
			String charAsBinString = chop(MAX_CHAR_BITS, false);
			int charAsInt = Integer.parseInt(charAsBinString, 2) + ' ';
			char charAsChar = (char)charAsInt;
			sb.append(charAsChar);
		}
		return sb.toString();
	}
	
	private SmooBit getSmooBit() {
		return new SmooBit(
		 Integer.parseInt(chop(MAX_COORDINATE_BITS, false), 2),
		 Integer.parseInt(chop(MAX_COORDINATE_BITS, false), 2)
		);
	}
	
	public enum BitCount {
		BOOL(1),
		BYTE(8),
		CHAR(16),
		SHORT(16),
		INT(32),
		FLOAT(32),
		LONG(64),
		DOUBLE(64);
		public final int size;
		
		public byte get() {
			return (byte)size;
		}
		
		BitCount(int bitCount) {
			this.size = bitCount;
		}
	}
	
	private String chop(int index, boolean doMin) {
		int index1 = doMin ? Math.min(index, binary.length()) : index;
		String temp = binary.substring(0, index1);
		binary = binary.substring(index1);
		return temp.isEmpty() ? "0" : temp;
	}
}
