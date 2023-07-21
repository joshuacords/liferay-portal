/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.frontend.internal;

import com.liferay.commerce.frontend.DefaultFilterImpl;
import com.liferay.commerce.frontend.Filter;
import com.liferay.commerce.frontend.FilterFactory;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Marco Leo
 */
public class DefaultFilterFactoryImpl implements FilterFactory {

	@Override
	public Filter create(HttpServletRequest httpServletRequest) {
		DefaultFilterImpl defaultFilterImpl = new DefaultFilterImpl();

		String keywords = ParamUtil.getString(httpServletRequest, "search");

		defaultFilterImpl.setKeywords(keywords);

		return defaultFilterImpl;
	}

}