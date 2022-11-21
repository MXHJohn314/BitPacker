package org.bitpacker.domain;

import jdk.jshell.spi.ExecutionControl;
import org.bitpacker.domain.models.Wompus;

import java.util.List;

public abstract class BitPacker extends BitAccessBase {
	
	public BitPacker(byte[] bytes, int bitTotal) {
		super(bytes, bitTotal);
	}
	
	public BitPacker() {super();}
	
	@Override public boolean currentEquals1()  {
		return ((bytes[currentByte] >> (7 - currentBit)) & 1) == 1;
	}
	
	
	@Override boolean hasNext() {
		return lastBit <= totalBitsCounted
		 && currentBit < 8 && currentByte < bytes.length;
	}
	
	public static String prettyPrint(byte[] result) {
		StringBuilder sb = new StringBuilder();
		BitPacker ba = new BitPacker(result, 8 * result.length) {
			@Override public void dumpMany(List<Wompus> wompus) throws ExecutionControl.NotImplementedException {
				
			}
		};
		while(ba.hasNext()) {
			sb.append(ba).append("\n");
		}
		return sb.toString();
	}
	
	@Override public void setCurrentBitTo(boolean shouldWrite1){
		if(!currentEquals1() && shouldWrite1) {
			bytes[currentByte] |= 1 << (7 - currentBit);
		}
		else if(currentEquals1() && !shouldWrite1) {
			bytes[currentByte] &= ~0;
		}
	}
	
	@Override public void next() {
		totalBitsCounted++;
		currentBit++;
		if(currentBit == 8) {
			currentBit = 0;
			currentByte++;
		}
	}
	
	@Override public boolean getAnyBitByPosition(int i, int j) {
		if(i >= bytes.length || j > 7 || i < 0 || j < 0) {
			throw new IllegalArgumentException("[" + i + ", " + j + "] is not a valid index for an array of size [" + bytes.length + ", 8]" );
		}
		return ((bytes[i] >> 8 - 1 - j) & 1) == 1;
	}
	
	@Override
	public boolean getCurrentBit() {
		return getAnyBitByPosition(currentByte, currentBit);
	}
	
	@Override
	public String prettyPrintByte(boolean b) {return b ? "1" : "0"; }
	
	@Override 
	public String toString() {
		StringBuilder sb = new StringBuilder(" ".repeat(Math.max(2 * currentBit + 1, 0)))
		 .append("v\n________________\n");
		for(int i = 0; i < 8; i++) {
			sb.append("|").append(prettyPrintByte(getAnyBitByPosition(currentByte, i)));
		}
		sb.append("|\n________________\n");
		return sb.toString();
	}
	
	@Override 
	public byte[] getAllBytes() {
		return bytes;
	}
	
	@Override public int[] indices() {
		return new int[]{this.currentByte, this.currentBit};
	}
	
	public abstract void dumpMany(List<Wompus> wompus) throws ExecutionControl.NotImplementedException;
}
