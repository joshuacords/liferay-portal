/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.web.internal.portlet.shared.task;

import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.search.web.internal.util.SearchArrayUtil;
import com.liferay.portal.search.web.internal.util.SearchStringUtil;

import java.util.Optional;

import javax.portlet.RenderRequest;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author André de Oliveira
 */
@Component(immediate = true, service = PortletSharedRequestHelper.class)
public class PortletSharedRequestHelperImpl
	implements PortletSharedRequestHelper {

	@Override
	public <T> Optional<T> getAttribute(
		String name, RenderRequest renderRequest) {

		return Optional.ofNullable(
			getAttribute(name, getSharedRequest(renderRequest)));
	}

	@Override
	public String getCompleteURL(RenderRequest renderRequest) {
		return SearchHttpUtil.getCompleteOriginalURL(
			portal.getHttpServletRequest(renderRequest));
	}

	@Override
	public Optional<String> getParameter(
		String name, RenderRequest renderRequest) {

		HttpServletRequest httpServletRequest = getSharedRequest(renderRequest);

		return SearchStringUtil.maybe(httpServletRequest.getParameter(name));
	}

	@Override
	public Optional<String[]> getParameterValues(
		String name, RenderRequest renderRequest) {

		HttpServletRequest httpServletRequest = getSharedRequest(renderRequest);

		return SearchArrayUtil.maybe(
			httpServletRequest.getParameterValues(name));
	}

	@Override
	public void setAttribute(
		String name, Object attributeValue, RenderRequest renderRequest) {

		HttpServletRequest httpServletRequest = getSharedRequest(renderRequest);

		httpServletRequest.setAttribute(name, attributeValue);
	}

	@SuppressWarnings("unchecked")
	protected <T> T getAttribute(
		String name, HttpServletRequest httpServletRequest) {

		return (T)httpServletRequest.getAttribute(name);
	}

	protected HttpServletRequest getSharedRequest(RenderRequest renderRequest) {
		return portal.getOriginalServletRequest(
			portal.getHttpServletRequest(renderRequest));
	}

	@Reference
	protected Portal portal;

}