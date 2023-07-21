/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.servlet;

import com.liferay.portal.kernel.util.TransientValue;

import java.io.Serializable;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

/**
 * @author     Alexander Chow
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public class PortalSessionActivationListener
	implements HttpSessionActivationListener, Serializable {

	public static PortalSessionActivationListener getInstance() {
		return _portalSessionActivationListener;
	}

	public static PortalSessionActivationListener getInstance(
		HttpSession session) {

		TransientValue<PortalSessionActivationListener> transientValue =
			(TransientValue<PortalSessionActivationListener>)
				session.getAttribute(
					PortalSessionActivationListener.class.getName());

		PortalSessionActivationListener portalSessionActivationListener = null;

		if (transientValue != null) {
			portalSessionActivationListener = transientValue.getValue();
		}

		return portalSessionActivationListener;
	}

	public static void setInstance(HttpSession session) {
		TransientValue<PortalSessionActivationListener> transientValue =
			new TransientValue<>(getInstance());

		session.setAttribute(
			PortalSessionActivationListener.class.getName(), transientValue);
	}

	@Override
	public void sessionDidActivate(HttpSessionEvent httpSessionEvent) {
		new PortalSessionCreator(httpSessionEvent);
	}

	@Override
	public void sessionWillPassivate(HttpSessionEvent httpSessionEvent) {
	}

	private static final PortalSessionActivationListener
		_portalSessionActivationListener =
			new PortalSessionActivationListener();

}