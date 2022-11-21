package domain.accessing;

import jdk.jshell.spi.ExecutionControl;

public interface BitAccessible {
	boolean currentEquals1() throws ExecutionControl.NotImplementedException;
	
	void setCurrentBitTo(boolean bit) throws ExecutionControl.NotImplementedException;
	void next() throws ExecutionControl.NotImplementedException;
	boolean getAnyBitByPosition(int i, int j);
	
	boolean getCurrentBit();
	
	String prettyPrintByte(boolean b);
	
	byte[] getAllBytes();
	
	int[] indices();
	
}

