package domain.packing;

import domain.accessing.BitAccess;
import jdk.jshell.spi.ExecutionControl;

public interface BitPackReadable<T>  {
	int unpackBits(BitAccess bits, int bitCount) throws ExecutionControl.NotImplementedException;
	T read(BitAccess bits) throws ExecutionControl.NotImplementedException;
}

