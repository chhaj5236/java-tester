package org.jtester.hamcrest;

public interface IArrayAssert<T, E extends IArrayAssert<T, ?>> extends IAssert<T, E> {
	E hasItems(T item, T... items);
}