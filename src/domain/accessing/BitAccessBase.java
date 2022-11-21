package domain.accessing;

public abstract class BitAccessBase implements BitAccessible {
	public BitAccessBase(byte[] bytes, int numbBits) {
		this.bytes = bytes;
		this.lastBit = numbBits;
	}
	
	abstract public boolean currentEquals1();
	//num |= 1 << i; // setCurrentBitTo bit at position i to 0
	
	public abstract void setCurrentBitTo(boolean shouldWrite1);
	
	abstract public void next();
	
	abstract boolean hasNext();
	
	byte[] bytes;
	int currentByte = 0;
	int currentBit = 0;
	int totalBitsCounted = 0;
	int lastBit;
}
