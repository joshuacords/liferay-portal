/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.organization.web.internal.organization.application.context.provider;

import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.ext.Provider;

import org.apache.cxf.jaxrs.ext.ContextProvider;
import org.apache.cxf.message.Message;

import org.osgi.service.component.annotations.Component;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = SortContextProvider.class)
@Provider
public class SortContextProvider implements ContextProvider<Sort> {

	@Override
	public Sort createContext(Message message) {
		HttpServletRequest httpServletRequest =
			(HttpServletRequest)message.getContextualProperty("HTTP.REQUEST");

		String sortString = ParamUtil.getString(
			httpServletRequest, "sort.field");

		if (Validator.isNull(sortString)) {
			return null;
		}

		String sortDir = ParamUtil.getString(httpServletRequest, "sort.dir");

		return SortFactoryUtil.create(
			StringUtil.trim(sortString), sortDir.equals("desc"));
	}

}