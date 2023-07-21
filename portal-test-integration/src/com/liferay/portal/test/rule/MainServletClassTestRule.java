/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.test.rule;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.internal.servlet.MainServlet;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.search.SearchEngineHelperUtil;
import com.liferay.portal.kernel.servlet.ServletContextClassLoaderPool;
import com.liferay.portal.kernel.servlet.ServletContextPool;
import com.liferay.portal.kernel.test.rule.ArquillianUtil;
import com.liferay.portal.kernel.test.rule.ClassTestRule;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.PortalLifecycle;
import com.liferay.portal.kernel.util.PortalLifecycleUtil;
import com.liferay.portal.module.framework.ModuleFrameworkUtilAdapter;
import com.liferay.portal.service.test.ServiceTestUtil;
import com.liferay.portal.test.mock.AutoDeployMockServletContext;

import javax.servlet.ServletException;

import org.junit.runner.Description;

import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.mock.web.MockServletContext;

/**
 * @author     Shuyang Zhou
 * @deprecated As of Mueller (7.2.x), with no direct replacement
 */
@Deprecated
public class MainServletClassTestRule extends ClassTestRule<Void> {

	public static final MainServletClassTestRule INSTANCE =
		new MainServletClassTestRule();

	public static MainServlet getMainServlet() {
		return _mainServlet;
	}

	@Override
	public void afterClass(Description description, Void v)
		throws PortalException {

		if (ArquillianUtil.isArquillianTest(description)) {
			return;
		}

		SearchEngineHelperUtil.removeCompany(TestPropsValues.getCompanyId());
	}

	@Override
	public Void beforeClass(Description description) {
		if (ArquillianUtil.isArquillianTest(description)) {
			return null;
		}

		if (_mainServlet == null) {
			final MockServletContext mockServletContext =
				new AutoDeployMockServletContext(
					new FileSystemResourceLoader());

			mockServletContext.setServletContextName(StringPool.BLANK);

			Thread currentThread = Thread.currentThread();

			ServletContextClassLoaderPool.register(
				StringPool.BLANK, currentThread.getContextClassLoader());

			PortalLifecycleUtil.register(
				new PortalLifecycle() {

					@Override
					public void portalDestroy() {
					}

					@Override
					public void portalInit() {
						ModuleFrameworkUtilAdapter.registerContext(
							mockServletContext);
					}

				});

			ServletContextPool.put(StringPool.BLANK, mockServletContext);

			MockServletConfig mockServletConfig = new MockServletConfig(
				mockServletContext, "Main Servlet");

			mockServletConfig.addInitParameter(
				"config", "/WEB-INF/struts-config.xml");

			_mainServlet = new MainServlet();

			try {
				_mainServlet.init(mockServletConfig);
			}
			catch (ServletException servletException) {
				throw new RuntimeException(
					"The main servlet could not be initialized",
					servletException);
			}

			ServiceTestUtil.initStaticServices();
		}

		ServiceTestUtil.initServices();

		ServiceTestUtil.initPermissions();

		return null;
	}

	protected MainServletClassTestRule() {
	}

	private static MainServlet _mainServlet;

}