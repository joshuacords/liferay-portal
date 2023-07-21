/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.opensocial.shindig.servlet;

import com.liferay.exportimport.kernel.xstream.XStreamAliasRegistryUtil;
import com.liferay.opensocial.model.impl.GadgetImpl;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.BasePortalLifecycle;

import java.lang.reflect.Field;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Michael Young
 */
public class GuiceServletContextListener
	extends BasePortalLifecycle implements ServletContextListener {

	public GuiceServletContextListener() {
		_guiceServletContextListener =
			new org.apache.shindig.common.servlet.GuiceServletContextListener();

		try {
			Class<?> clazz = _guiceServletContextListener.getClass();

			Field field = clazz.getDeclaredField("jmxInitialized");

			field.setAccessible(true);

			field.set(_guiceServletContextListener, true);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					reflectiveOperationException, reflectiveOperationException);
			}
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		_destroyedServletContextEvent = servletContextEvent;

		XStreamAliasRegistryUtil.unregister(GadgetImpl.class, "Gadget");

		portalDestroy();
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		_initializedServletContextEvent = servletContextEvent;

		XStreamAliasRegistryUtil.register(GadgetImpl.class, "Gadget");

		registerPortalLifecycle();
	}

	@Override
	protected void doPortalDestroy() throws Exception {
		_guiceServletContextListener.contextDestroyed(
			_destroyedServletContextEvent);
	}

	@Override
	protected void doPortalInit() throws Exception {
		_guiceServletContextListener.contextInitialized(
			_initializedServletContextEvent);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		GuiceServletContextListener.class);

	private static ServletContextEvent _initializedServletContextEvent;

	private ServletContextEvent _destroyedServletContextEvent;
	private final ServletContextListener _guiceServletContextListener;

}