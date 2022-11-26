package algo.galacticwafer.v1;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public abstract class BitUnpackerAbstract implements Iterable<BitPack> {
	protected List<BitPack> bitPackList;
}

class BitUnpackerBase extends BitUnpackerAbstract {
	@Override 
	public Iterator<BitPack> iterator() {return bitPackList.iterator();}
	@Override 
	public void forEach(Consumer<? super BitPack> action) {bitPackList.forEach(action);}
	@Override 
	public Spliterator<BitPack> spliterator() {return bitPackList.spliterator();}
}
