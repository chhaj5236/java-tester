package org.jtester.hamcrest.iassert.object.impl;

import java.util.Map;

import org.jtester.hamcrest.iassert.common.impl.BaseAssert;
import org.jtester.hamcrest.iassert.object.IMapAssert;

public class MapAssert extends BaseAssert<Map<?, ?>, IMapAssert> implements
		IMapAssert {
	public MapAssert() {
		super(IMapAssert.class);
	}

	public MapAssert(Map<?, ?> map) {
		super(map, IMapAssert.class);
	}
}