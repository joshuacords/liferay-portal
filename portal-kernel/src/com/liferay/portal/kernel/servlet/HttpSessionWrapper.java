/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.servlet;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

/**
 * @author Brian Wing Shun Chan
 */
public class HttpSessionWrapper implements HttpSession {

	public HttpSessionWrapper(HttpSession session) {
		_session = session;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof HttpSessionWrapper) {
			HttpSessionWrapper sessionWrapper = (HttpSessionWrapper)obj;

			obj = sessionWrapper.getWrappedSession();
		}

		return _session.equals(obj);
	}

	@Override
	public Object getAttribute(String name) {
		return _session.getAttribute(name);
	}

	@Override
	public Enumeration<String> getAttributeNames() {
		return _session.getAttributeNames();
	}

	@Override
	public long getCreationTime() {
		return _session.getCreationTime();
	}

	@Override
	public String getId() {
		return _session.getId();
	}

	@Override
	public long getLastAccessedTime() {
		return _session.getLastAccessedTime();
	}

	@Override
	public int getMaxInactiveInterval() {
		return _session.getMaxInactiveInterval();
	}

	@Override
	public ServletContext getServletContext() {
		return _session.getServletContext();
	}

	/**
	 * @deprecated As of Bunyan (6.0.x)
	 */
	@Deprecated
	@Override
	public HttpSessionContext getSessionContext() {
		return _session.getSessionContext();
	}

	/**
	 * @deprecated As of Bunyan (6.0.x)
	 */
	@Deprecated
	@Override
	public Object getValue(String name) {
		return _session.getValue(name);
	}

	/**
	 * @deprecated As of Bunyan (6.0.x)
	 */
	@Deprecated
	@Override
	public String[] getValueNames() {
		return _session.getValueNames();
	}

	public HttpSession getWrappedSession() {
		return _session;
	}

	@Override
	public int hashCode() {
		return _session.hashCode();
	}

	@Override
	public void invalidate() {
		_session.invalidate();
	}

	@Override
	public boolean isNew() {
		return _session.isNew();
	}

	/**
	 * @deprecated As of Bunyan (6.0.x)
	 */
	@Deprecated
	@Override
	public void putValue(String name, Object value) {
		_session.putValue(name, value);
	}

	@Override
	public void removeAttribute(String name) {
		_session.removeAttribute(name);
	}

	/**
	 * @deprecated As of Bunyan (6.0.x)
	 */
	@Deprecated
	@Override
	public void removeValue(String name) {
		_session.removeValue(name);
	}

	@Override
	public void setAttribute(String name, Object value) {
		_session.setAttribute(name, value);
	}

	@Override
	public void setMaxInactiveInterval(int interval) {
		_session.setMaxInactiveInterval(interval);
	}

	private final HttpSession _session;

}