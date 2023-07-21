/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.events;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.deploy.DeployUtil;
import com.liferay.portal.kernel.deploy.auto.AutoDeployDir;
import com.liferay.portal.kernel.deploy.auto.AutoDeployListener;
import com.liferay.portal.kernel.deploy.auto.AutoDeployUtil;
import com.liferay.portal.kernel.deploy.hot.HotDeployListener;
import com.liferay.portal.kernel.deploy.hot.HotDeployUtil;
import com.liferay.portal.kernel.events.SimpleAction;
import com.liferay.portal.kernel.javadoc.JavadocManagerUtil;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceActionsManagerUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.ServletContextPool;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.spring.context.PortalContextLoaderListener;
import com.liferay.portal.struts.AuthPublicPathRegistry;
import com.liferay.portal.util.BrowserLauncher;
import com.liferay.portal.util.PropsUtil;
import com.liferay.portal.util.PropsValues;

import java.io.File;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

/**
 * @author Brian Wing Shun Chan
 */
public class GlobalStartupAction extends SimpleAction {

	public static List<AutoDeployListener> getAutoDeployListeners(
		boolean reset) {

		if ((_autoDeployListeners != null) && !reset) {
			return _autoDeployListeners;
		}

		List<AutoDeployListener> autoDeployListeners = new ArrayList<>();

		String[] autoDeployListenerClassNames = PropsUtil.getArray(
			PropsKeys.AUTO_DEPLOY_LISTENERS);

		for (String autoDeployListenerClassName :
				autoDeployListenerClassNames) {

			try {
				if (_log.isDebugEnabled()) {
					_log.debug("Instantiating " + autoDeployListenerClassName);
				}

				AutoDeployListener autoDeployListener =
					(AutoDeployListener)InstanceFactory.newInstance(
						autoDeployListenerClassName);

				autoDeployListeners.add(autoDeployListener);
			}
			catch (Exception exception) {
				_log.error(
					"Unable to initialiaze auto deploy listener", exception);
			}
		}

		_autoDeployListeners = autoDeployListeners;

		return _autoDeployListeners;
	}

	public static List<HotDeployListener> getHotDeployListeners() {
		if (_hotDeployListeners != null) {
			return _hotDeployListeners;
		}

		List<HotDeployListener> hotDeployListeners = new ArrayList<>();

		String[] hotDeployListenerClassNames = PropsUtil.getArray(
			PropsKeys.HOT_DEPLOY_LISTENERS);

		for (String hotDeployListenerClassName : hotDeployListenerClassNames) {
			try {
				if (_log.isDebugEnabled()) {
					_log.debug("Instantiating " + hotDeployListenerClassName);
				}

				HotDeployListener hotDeployListener =
					(HotDeployListener)InstanceFactory.newInstance(
						hotDeployListenerClassName);

				hotDeployListeners.add(hotDeployListener);
			}
			catch (Exception exception) {
				_log.error(
					"Unable to initialiaze hot deploy listener", exception);
			}
		}

		_hotDeployListeners = hotDeployListeners;

		return _hotDeployListeners;
	}

	@Override
	public void run(String[] ids) {

		// Auto deploy

		try {
			File deployDir = new File(PropsValues.AUTO_DEPLOY_DEPLOY_DIR);
			File destDir = new File(DeployUtil.getAutoDeployDestDir());
			long interval = PropsValues.AUTO_DEPLOY_INTERVAL;

			AutoDeployDir autoDeployDir = new AutoDeployDir(
				AutoDeployDir.DEFAULT_NAME, deployDir, destDir, interval,
				getAutoDeployListeners(false));

			if (PropsValues.AUTO_DEPLOY_ENABLED) {
				if (_log.isInfoEnabled()) {
					_log.info("Registering auto deploy directories");
				}

				AutoDeployUtil.registerDir(autoDeployDir);
			}
			else {
				if (_log.isInfoEnabled()) {
					_log.info("Not registering auto deploy directories");
				}
			}
		}
		catch (Exception exception) {
			_log.error("Unable to register auto deploy directories", exception);
		}

		// Hot deploy

		if (_log.isDebugEnabled()) {
			_log.debug("Registering hot deploy listeners");
		}

		for (HotDeployListener hotDeployListener : getHotDeployListeners()) {
			HotDeployUtil.registerListener(hotDeployListener);
		}

		// Authentication

		AuthPublicPathRegistry.register(PropsValues.AUTH_PUBLIC_PATHS);

		// Javadoc

		Thread currentThread = Thread.currentThread();

		JavadocManagerUtil.load(
			StringPool.BLANK, currentThread.getContextClassLoader());

		// JSON web service

		ServletContext servletContext = ServletContextPool.get(
			PortalContextLoaderListener.getPortalServletContextName());

		JSONWebServiceActionsManagerUtil.registerServletContext(servletContext);

		// Launch browser

		if (Validator.isNotNull(PropsValues.BROWSER_LAUNCHER_URL)) {
			Thread browserLauncherThread = new Thread(new BrowserLauncher());

			browserLauncherThread.start();
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		GlobalStartupAction.class);

	private static List<AutoDeployListener> _autoDeployListeners;
	private static List<HotDeployListener> _hotDeployListeners;

}