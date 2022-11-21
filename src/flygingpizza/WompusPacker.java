package flygingpizza;

import domain.accessing.BitAccess;
import domain.packing.BitPackable;
import jdk.jshell.spi.ExecutionControl;
import models.Wompus;

import java.util.List;
import java.util.Map;

/**
 * Example implementation of BitPacker.
 */
public class WompusPacker implements BitPackable<Wompus> {
	public WompusPacker() {
	}
	
	@Override public List<Map.Entry<Wompus, Integer>> getPackList() throws ExecutionControl.NotImplementedException {
		throw new ExecutionControl.NotImplementedException(
		 "Todo, return a list of (bit-count,loop-count) pairs to represent" +
		  "the current state of a `Wompus`.");
	}
	
	@Override public int unpackBits(BitAccess bits, int bitCount) throws ExecutionControl.NotImplementedException {
		throw new ExecutionControl.NotImplementedException(
		 "Todo, return a list of (bit-count,loop-count) pairs to represent" +
		  "the current state of a `Wompus`.");
	}
	
	@Override public Wompus read(BitAccess bits) throws ExecutionControl.NotImplementedException {
		throw new ExecutionControl.NotImplementedException(
		 "Todo, return a list of (bit-count,loop-count) pairs to represent" +
		  "the current state of a `Wompus`." +
		  "BitPackReadable.packBits was found.");
	}
	
	@Override public void packBits(int bitCount, int value, BitAccess outputBits) throws ExecutionControl.NotImplementedException {
		throw new ExecutionControl.NotImplementedException(
		 "Todo, return a list of (bit-count,loop-count) pairs to represent" +
		  "the current state of a `Wompus`." +
		  "BitPackReadable.packBits was found.");
	}
	
	@Override public void write(Wompus model) {
		
	}
}
