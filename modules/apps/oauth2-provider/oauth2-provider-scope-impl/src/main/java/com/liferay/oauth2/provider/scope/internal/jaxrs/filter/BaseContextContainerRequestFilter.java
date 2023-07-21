/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.oauth2.provider.scope.internal.jaxrs.filter;

import com.liferay.oauth2.provider.scope.internal.constants.OAuth2ProviderScopeConstants;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

/**
 * @author Tomas Polesovsky
 */
public abstract class BaseContextContainerRequestFilter
	implements ContainerRequestFilter {

	public Bundle getBundle() {
		return FrameworkUtil.getBundle(application.getClass());
	}

	public long getCompanyId() {
		return PortalUtil.getCompanyId(httpServletRequest);
	}

	protected String getApplicationName() {
		Bundle bundle = getBundle();

		if (bundle == null) {
			return null;
		}

		BundleContext bundleContext = bundle.getBundleContext();

		Class<?> applicationClass = application.getClass();

		String applicationClassName = applicationClass.getName();

		try {
			Collection<ServiceReference<Application>> serviceReferences =
				bundleContext.getServiceReferences(
					Application.class,
					StringBundler.concat(
						"(component.name=", applicationClassName, ")"));

			if (!serviceReferences.isEmpty()) {
				Iterator<ServiceReference<Application>> iterator =
					serviceReferences.iterator();

				ServiceReference<Application> serviceReference =
					iterator.next();

				return GetterUtil.getString(
					serviceReference.getProperty(
						OAuth2ProviderScopeConstants.OSGI_JAXRS_NAME),
					applicationClassName);
			}
		}
		catch (InvalidSyntaxException invalidSyntaxException) {
			throw new IllegalArgumentException(invalidSyntaxException);
		}

		return applicationClassName;
	}

	@Context
	protected Application application;

	@Context
	protected HttpServletRequest httpServletRequest;

}