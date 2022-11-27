package domain.core;

import java.util.Arrays;

/**
 * 
 */
public abstract class BitAccessBase implements BitAccessible {
	protected byte[] bytes;
	
	/**
	 * 
	 */
	public BitAccessBase() {}
	
	@Override public boolean getBit(int i, int j) {
		if(i >= getValues().length || j > 7 || i < 0 || j < 0) {
			throw new IllegalArgumentException(
			 "[" + i + ", " + j + "] is not a valid index for an array of unpackedCount [" +
			  getValues().length +
			  ", 8]");
		}
		return ( ( getValues()[i] >> 8 - 1 - j ) & 1 ) == 1;
	}
	
	@Override public void setBit(int targetByte, int targetBit, boolean bitValue) {
		if(bitValue) {
			getValues()[targetByte] |= 1 << ( 7 - targetBit );
		}
		else {
			getValues()[targetByte] &= ~0;
		}
	}
	
	@Override public byte[] getValues() {
		return bytes;
	}
	
	@Override public void setBytes(byte[] bytes) {
		this.bytes = bytes;
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
		 readBit + "]->(" + getValues()[readByte] + ")->" + getBit(readByte,
		 readBit) +
		 ", WRITE[" +
		 readByte + "," +
		 readBit + "]->(" + getValues()[readByte] + ")->" + getBit(readByte, readBit);
	}
	
	@Override public String toString() {
		return printBuffer(bytes);
	}
	
	@Override public int hashCode() {
		return
		 ( Arrays.hashCode(getValues())
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
