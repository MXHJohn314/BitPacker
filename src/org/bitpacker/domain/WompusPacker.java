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
	
	@Override public int loadBits(BitPacker bits, int bitCount) throws ExecutionControl.NotImplementedException {
		throw new ExecutionControl.NotImplementedException(
		 "No implementation for `BitPackReadable.loadBits was found." +
		  "\nTodo, use inherited BitPacker methods to take in another reference to a BitPacker object, and loadEntity one integer.`.");
	}
	@Override public Wompus loadEntity(BitPacker bits) throws ExecutionControl.NotImplementedException {
		throw new ExecutionControl.NotImplementedException(
		 "No implementation for `BitPackReadable.loadEntity` was found. " +
		  "Todo, return a list of (bit-count,loop-count) pairs to represent" +
		  " the current state of a `Wompus`.");
	}
	public List<Wompus> loadAllEntities() {return null;}

	
	@Override public void dumpBits(int bitCount, int value, BitPacker outputBits) throws ExecutionControl.NotImplementedException {
		throw new ExecutionControl.NotImplementedException(
		 "No implementation for `BitPackReadable.dumpBits` was found." +
		  " Todo, return a list of (bit-count,loop-count) pairs to represent" +
		  " the current state of a `Wompus`.");
	}
	@Override public void dumpOne(Wompus model) throws ExecutionControl.NotImplementedException {
		throw new ExecutionControl.NotImplementedException(
		 "No implementation for `BitPackReadable.dumpBits` was found." +
		  " Todo, return a list of (bit-count,loop-count) pairs to represent" +
		  " the current state of a `Wompus`.");
	}
	@Override public void dumpMany(List<Wompus> wompus) throws ExecutionControl.NotImplementedException {
		throw new ExecutionControl.NotImplementedException(
		 "No implementation for `BitPackReadable.dumpMany` was found." +
		  "\nTodo, compress and dumpMany a list of Womps..");
	}
}
