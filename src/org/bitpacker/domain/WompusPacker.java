package org.bitpacker.domain;

import org.bitpacker.interfaces.BitPackable;
import jdk.jshell.spi.ExecutionControl;
import org.bitpacker.domain.models.Wompus;

import java.util.List;
import java.util.Map;

/**
 * Example implementation of BitPacker.
 */
public class WompusPacker extends BitPacker implements BitPackable<Wompus> {
	
	public WompusPacker() {
		super();
	}
	
	@Override public List<Map.Entry<Wompus, Integer>> getPackList() throws ExecutionControl.NotImplementedException {
		throw new ExecutionControl.NotImplementedException(
		 "No implementation for `BitPackReadable.getPackList` was found." +
		  "\nTodo, return a list of (bit-count,loop-count) pairs to represent" +
		  "the current state of a `Wompus`.");
	}
	
	@Override public int unpackBits(BitPacker bits, int bitCount) throws ExecutionControl.NotImplementedException {
		throw new ExecutionControl.NotImplementedException(
		 "No implementation for `BitPackReadable.unpackBits was found." +
		  "\nTodo, return a list of (bit-count,loop-count) pairs to represent" +
		  "\nthe current state of a `Wompus`.");
	}
	
	@Override public Wompus read(BitPacker bits) throws ExecutionControl.NotImplementedException {
		throw new ExecutionControl.NotImplementedException(
		 "No implementation for `BitPackReadable.read` was found. " +
		  "Todo, return a list of (bit-count,loop-count) pairs to represent" +
		  " the current state of a `Wompus`.");
	}
	
	@Override public void packBits(int bitCount, int value, BitPacker outputBits) throws ExecutionControl.NotImplementedException {
		throw new ExecutionControl.NotImplementedException(
		 "No implementation for `BitPackReadable.packBits` was found." +
		  " Todo, return a list of (bit-count,loop-count) pairs to represent" +
		  " the current state of a `Wompus`.");
	}
	
	@Override public void write(Wompus model) throws ExecutionControl.NotImplementedException {
		throw new ExecutionControl.NotImplementedException(
		 "No implementation for `BitPackReadable.packBits` was found." +
		  " Todo, return a list of (bit-count,loop-count) pairs to represent" +
		  " the current state of a `Wompus`.");
	}
}
