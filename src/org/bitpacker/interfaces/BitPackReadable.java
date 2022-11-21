package org.bitpacker.interfaces;

import org.bitpacker.domain.BitPacker;
import jdk.jshell.spi.ExecutionControl;

public interface BitPackReadable<T>  {
	int unpackBits(BitPacker bits, int bitCount) throws ExecutionControl.NotImplementedException;
	T read(BitPacker bits) throws ExecutionControl.NotImplementedException;
}

