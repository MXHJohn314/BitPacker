package org.bitpacker.interfaces;

import org.bitpacker.domain.BitPacker;
import jdk.jshell.spi.ExecutionControl;

public interface BitPackWriteable<T> {
	void dumpBits(int bitCount, int value, BitPacker outputBits) throws ExecutionControl.NotImplementedException;
	
	void dumpOne(T model) throws ExecutionControl.NotImplementedException;
}
