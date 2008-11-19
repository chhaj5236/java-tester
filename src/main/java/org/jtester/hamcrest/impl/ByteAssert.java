package org.jtester.hamcrest.impl;

import org.jtester.hamcrest.IAssert;
import org.jtester.hamcrest.IByteAssert;

public class ByteAssert extends BaseAssert<Byte, IByteAssert> implements IByteAssert {

	public ByteAssert(Byte value, Class<? extends IAssert> clazE) {
		super(value, clazE);
	}

	public ByteAssert(Class<? extends IAssert> clazE) {
		super(clazE);
	}

	public ByteAssert(Class<Byte> clazT, Class<? extends IAssert> clazE) {
		super(clazT, clazE);
	}

}
