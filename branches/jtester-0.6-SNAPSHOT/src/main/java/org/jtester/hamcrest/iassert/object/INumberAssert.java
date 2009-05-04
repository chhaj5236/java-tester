package org.jtester.hamcrest.iassert.object;

import org.jtester.hamcrest.iassert.common.IBaseAssert;
import org.jtester.hamcrest.iassert.common.IComparableAssert;

/**
 * 数值型对象断言接口
 * 
 * @author darui.wudr
 * 
 * @param <T>
 * @param <E>
 */
public interface INumberAssert<T extends Number & Comparable<T>, E extends INumberAssert<T, ?>> extends
		IBaseAssert<T, E>, IComparableAssert<E> {

}