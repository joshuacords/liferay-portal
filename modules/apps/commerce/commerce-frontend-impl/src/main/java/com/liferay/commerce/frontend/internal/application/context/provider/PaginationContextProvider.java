/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.frontend.internal.application.context.provider;

import com.liferay.commerce.frontend.Pagination;
import com.liferay.commerce.frontend.PaginationImpl;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.ext.Provider;

import org.apache.cxf.jaxrs.ext.ContextProvider;
import org.apache.cxf.message.Message;

import org.osgi.service.component.annotations.Component;

/**
 * @author Marco Leo
 */
@Component(immediate = true, service = PaginationContextProvider.class)
@Provider
public class PaginationContextProvider implements ContextProvider<Pagination> {

	@Override
	public Pagination createContext(Message message) {
		HttpServletRequest httpServletRequest =
			(HttpServletRequest)message.getContextualProperty("HTTP.REQUEST");

		int pageNumber = ParamUtil.getInteger(httpServletRequest, "page", 1);
		int itemsPerPage = ParamUtil.getInteger(
			httpServletRequest, "pageSize", 20);

		return new PaginationImpl(itemsPerPage, pageNumber);
	}

}