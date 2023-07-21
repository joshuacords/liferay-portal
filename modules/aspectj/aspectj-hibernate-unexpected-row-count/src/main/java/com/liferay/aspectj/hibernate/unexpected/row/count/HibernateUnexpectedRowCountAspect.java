/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.aspectj.hibernate.unexpected.row.count;

import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.lang.reflect.Field;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.SuppressAjWarnings;

import org.hibernate.jdbc.AbstractBatcher;
import org.hibernate.jdbc.BatchingBatcher;

/**
 * @author Preston Crary
 */
@Aspect
@SuppressAjWarnings("adviceDidNotMatch")
public class HibernateUnexpectedRowCountAspect {

	@Before(
		"handler(java.lang.RuntimeException) &&" +
			"withincode(void org.hibernate.jdbc.BatchingBatcher." +
				"doExecuteBatch(java.sql.PreparedStatement)) &&" +
					"args(runtimeException) && this(batchingBatcher)"
	)
	public void logUpdateSQL(
		BatchingBatcher batchingBatcher, RuntimeException runtimeException) {

		try {
			_log.error(
				"batchUpdateSQL = " + _batchUpdateSQLField.get(batchingBatcher),
				runtimeException);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			runtimeException.addSuppressed(reflectiveOperationException);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		HibernateUnexpectedRowCountAspect.class);

	private static final Field _batchUpdateSQLField;

	static {
		try {
			_batchUpdateSQLField = ReflectionUtil.getDeclaredField(
				AbstractBatcher.class, "batchUpdateSQL");
		}
		catch (Exception exception) {
			throw new ExceptionInInitializerError(exception);
		}
	}

}