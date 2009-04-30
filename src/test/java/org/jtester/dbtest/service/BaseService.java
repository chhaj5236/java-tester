package org.jtester.dbtest.service;

import org.hibernate.Session;
import org.jtester.dbtest.bean.BaseBean;

public interface BaseService<T extends BaseBean> {
	/**
	 * 根据key值获取相应的持久化对象
	 * 
	 * @param <E>
	 * @param claz
	 *            要查询的对象class
	 * @param id
	 * @return
	 */
	<E extends BaseBean> E getBeanById(Class<E> claz, int id);

	/**
	 * 根据key值获取相应的持久化对象
	 * 
	 * @param id
	 * @param siteId
	 * @return
	 */
	T getBeanById(int id);

	/**
	 * 持久化对象
	 * 
	 * @param <E>
	 * @param claz
	 * @param bean
	 * @return
	 */
	<E extends BaseBean> E save(E bean);

	Session session();
}