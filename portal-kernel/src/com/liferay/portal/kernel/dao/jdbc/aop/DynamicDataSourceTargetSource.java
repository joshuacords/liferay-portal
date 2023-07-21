/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.dao.jdbc.aop;

import java.util.Stack;

import javax.sql.DataSource;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public interface DynamicDataSourceTargetSource {

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public Stack<String> getMethodStack();

	public Operation getOperation();

	public DataSource getReadDataSource();

	public Object getTarget() throws Exception;

	public DataSource getWriteDataSource();

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public String popMethod();

	public Operation popOperation();

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public void pushMethod(String method);

	public void pushOperation(Operation operation);

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public void setOperation(Operation operation);

	public void setReadDataSource(DataSource readDataSource);

	public void setWriteDataSource(DataSource writeDataSource);

}