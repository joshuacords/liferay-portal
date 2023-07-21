/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.batch.engine.internal.item;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.odata.filter.ExpressionConvert;
import com.liferay.portal.odata.filter.FilterParserProvider;
import com.liferay.portal.odata.sort.SortParserProvider;

import java.io.Serializable;

import java.util.Map;

/**
 * @author Ivica Cardic
 */
@FunctionalInterface
public interface BatchEngineTaskItemDelegateExecutorCreator {

	public BatchEngineTaskItemDelegateExecutor create(
			Company company, ExpressionConvert<Filter> expressionConvert,
			FilterParserProvider filterParserProvider,
			Map<String, Serializable> parameters,
			SortParserProvider sortParserProvider, User user)
		throws ReflectiveOperationException;

}