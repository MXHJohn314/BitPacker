package org.bitpacker.interfaces;

import jdk.jshell.spi.ExecutionControl;

import java.util.List;
import java.util.Map;

/**
 * @param <T>  An object of type `T` is considered to be `BitPackable`  if it implements `BitPackReadable` T and `BitPackWriteable T>.
 */
public interface BitPackable<T> extends  BitPackReadable<T>, BitPackWriteable<T> { }
