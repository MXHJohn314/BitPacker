package domain.core;

import java.util.Arrays;
import java.util.List;

public abstract class BitAccessBase implements BitAccessible {
	private final List<PackDescribable> packJob;
	
	public BitAccessBase(byte[] bytes, List<PackDescribable> packJob) {
		this.setBytes(bytes);
		this.packJob = packJob;
	}
	
	private byte[] bytes;
	private int packSize;
	private int sizeInBits;
	
	private static int countLeadingZeroes(int bitsPerInteger, Integer intValue) {
		return (int)( bitsPerInteger - ( 1 + Math.log(intValue) / Math.log(2) ) );
	}
	
	@Override public boolean getBit(int i, int j) {
		if(i >= getBytes().length || j > 7 || i < 0 || j < 0) {
			throw new IllegalArgumentException(
			 "[" + i + ", " + j + "] is not a valid index for an array of unpackedSize [" +
			  getBytes().length +
			  ", 8]");
		}
		return ( ( getBytes()[i] >> 8 - 1 - j ) & 1 ) == 1;
	}
	
	@Override public void setBit(int targetByte, int targetBit, boolean bitValue) {
		if(bitValue) {
			getBytes()[targetByte] |= 1 << ( 7 - targetBit );
		}
		else {
			getBytes()[targetByte] &= ~0;
		}
	}
	
	@Override public byte[] getBytes() {
		return bytes;
	}
	
	@Override public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
	
	@Override public int size() {
		return sizeInBits;
	}
	
	@Override public String printBuffer(byte[] bytes) {
		StringBuilder border = new StringBuilder("v\n");
		StringBuilder mid = new StringBuilder();
		for(int i = 0; i < bytes.length; i++) {
			border.append("________________");
			for(int j = 0; j < 8; j++) {
				mid.append("|").append(getBit(i, j) ? "1" : "0");
			}
		}
		return "" + border + "\n" + mid + border;
	}
	
	@Override public String printByte(int readByte, int readBit, int writeByte, int writeBit) {
		return "READ[" +
		 readByte + "," +
		 readBit + "]->(" + getBytes()[readByte] + ")->" + getBit(readByte,
		 readBit) +
		 ", WRITE[" +
		 readByte + "," +
		 readBit + "]->(" + getBytes()[readByte] + ")->" + getBit(readByte, readBit);
	}
	
	@Override public String toString() {
		return printBuffer(bytes);
	}
	
	@Override public int hashCode() {
		return
		 ( Arrays.hashCode(getBytes())
		  + size()
		  + toString().hashCode()
		 ) % Integer.MAX_VALUE;
	}
	
	@Override public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	@Override protected Object clone() throws CloneNotSupportedException {
		
		return super.clone();
	}
}
