package domain.core;

import algo.galacticwafer.v2.PackInfoIndex;

import java.util.Map;

/**
 * 
 */
public interface PackDescribable extends Map.Entry<PackInfoIndex, Short> {
	/**
	 * @return The number of bits consumed by packing the associated component.
	 */
	int packedCount();
	
	/**
	 * @return The number of bits normally consumed by the associated component, without packing.
	 */
	int unpackedCount();
}
