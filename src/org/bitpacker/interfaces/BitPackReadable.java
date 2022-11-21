package org.bitpacker.interfaces;

import org.bitpacker.domain.BitPacker;
import jdk.jshell.spi.ExecutionControl;

public interface BitPackReadable<T>  {
	int loadBits(BitPacker bits, int bitCount) throws ExecutionControl.NotImplementedException;
	T loadEntity(BitPacker bits) throws ExecutionControl.NotImplementedException;
}

