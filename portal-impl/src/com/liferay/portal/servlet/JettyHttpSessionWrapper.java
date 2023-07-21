/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.servlet;

import com.liferay.portal.kernel.servlet.HttpSessionWrapper;

import javax.servlet.http.HttpSession;

import org.eclipse.jetty.server.session.AbstractSession;
import org.eclipse.jetty.server.session.AbstractSessionManager;

/**
 * @author     Brian Wing Shun Chan
 * @deprecated As of Mueller (7.2.x), with no direct replacement
 */
@Deprecated
public class JettyHttpSessionWrapper
	extends HttpSessionWrapper implements AbstractSessionManager.SessionIf {

	public JettyHttpSessionWrapper(HttpSession session) {
		super(session);

		_session = session;
	}

	@Override
	public AbstractSession getSession() {
		HttpSessionWrapper sessionWrapper = (HttpSessionWrapper)_session;

		JettySharedSessionWrapper jettySharedSessionWrapper =
			(JettySharedSessionWrapper)sessionWrapper.getWrappedSession();

		return jettySharedSessionWrapper.getSession();
	}

	private final HttpSession _session;

}