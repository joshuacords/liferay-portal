/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.atom;

import com.liferay.portal.kernel.atom.AtomRequestContext;
import com.liferay.portal.kernel.util.GetterUtil;

import org.apache.abdera.i18n.iri.IRI;
import org.apache.abdera.protocol.server.RequestContext;

/**
 * @author Igor Spasic
 */
public class AtomRequestContextImpl implements AtomRequestContext {

	public AtomRequestContextImpl(RequestContext requestContext) {
		_requestContext = requestContext;
	}

	@Override
	public Object getContainerAttribute(String name) {
		return _requestContext.getAttribute(
			RequestContext.Scope.CONTAINER, name);
	}

	@Override
	public String getHeader(String name) {
		return _requestContext.getHeader(name);
	}

	@Override
	public int getIntParameter(String name) {
		String value = _requestContext.getParameter(name);

		return GetterUtil.getInteger(value);
	}

	@Override
	public int getIntParameter(String name, int defaultValue) {
		String value = _requestContext.getParameter(name);

		return GetterUtil.getInteger(value, defaultValue);
	}

	@Override
	public long getLongParameter(String name) {
		String value = _requestContext.getParameter(name);

		return GetterUtil.getLong(value);
	}

	@Override
	public long getLongParameter(String name, long defaultValue) {
		String value = _requestContext.getParameter(name);

		return GetterUtil.getLong(value, defaultValue);
	}

	@Override
	public String getParameter(String name) {
		return _requestContext.getParameter(name);
	}

	@Override
	public String getParameter(String name, String defaultValue) {
		String value = _requestContext.getParameter(name);

		return GetterUtil.getString(value, defaultValue);
	}

	@Override
	public Object getRequestAttribute(String name) {
		return _requestContext.getAttribute(RequestContext.Scope.REQUEST, name);
	}

	@Override
	public String getResolvedUri() {
		IRI resolvedURI = _requestContext.getResolvedUri();

		return resolvedURI.toString();
	}

	@Override
	public Object getSessionAttribute(String name) {
		return _requestContext.getAttribute(RequestContext.Scope.SESSION, name);
	}

	@Override
	public String getTargetBasePath() {
		return _requestContext.getTargetBasePath();
	}

	@Override
	public void setContainerAttribute(String name, Object value) {
		_requestContext.setAttribute(
			RequestContext.Scope.CONTAINER, name, value);
	}

	@Override
	public void setRequestAttribute(String name, Object value) {
		_requestContext.setAttribute(RequestContext.Scope.REQUEST, name, value);
	}

	@Override
	public void setSessionAttribute(String name, Object value) {
		_requestContext.setAttribute(RequestContext.Scope.SESSION, name, value);
	}

	private final RequestContext _requestContext;

}