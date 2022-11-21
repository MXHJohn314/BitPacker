package org.bitpacker.interfaces;

import org.bitpacker.domain.BitPacker;
import jdk.jshell.spi.ExecutionControl;

public interface BitPackWriteable<T> {
	void packBits(int bitCount, int value, BitPacker outputBits) throws ExecutionControl.NotImplementedException;
	
	void write(T model) throws ExecutionControl.NotImplementedException;
}
