/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.dao.orm;

import java.io.Serializable;

import java.sql.Connection;

/**
 * @author Brian Wing Shun Chan
 * @author Shuyang Zhou
 */
public interface Session {

	public void clear() throws ORMException;

	public Connection close() throws ORMException;

	public boolean contains(Object object) throws ORMException;

	public Query createQuery(String queryString) throws ORMException;

	public Query createQuery(String queryString, boolean strictName)
		throws ORMException;

	public SQLQuery createSQLQuery(String queryString) throws ORMException;

	public SQLQuery createSQLQuery(String queryString, boolean strictName)
		throws ORMException;

	public SQLQuery createSynchronizedSQLQuery(String queryString)
		throws ORMException;

	public SQLQuery createSynchronizedSQLQuery(
			String queryString, boolean strictName)
		throws ORMException;

	public void delete(Object object) throws ORMException;

	public void evict(Object object) throws ORMException;

	public void flush() throws ORMException;

	public Object get(Class<?> clazz, Serializable id) throws ORMException;

	public Object get(Class<?> clazz, Serializable id, LockMode lockMode)
		throws ORMException;

	public Object getWrappedSession() throws ORMException;

	public boolean isDirty() throws ORMException;

	public Object load(Class<?> clazz, Serializable id) throws ORMException;

	public Object merge(Object object) throws ORMException;

	public Serializable save(Object object) throws ORMException;

	public void saveOrUpdate(Object object) throws ORMException;

}