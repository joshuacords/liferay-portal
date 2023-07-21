/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.sso.token.internal.events;

import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;
import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.settings.CompanyServiceSettingsLocator;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.security.sso.token.configuration.TokenConfiguration;
import com.liferay.portal.security.sso.token.constants.TokenConstants;
import com.liferay.portal.security.sso.token.events.LogoutProcessor;
import com.liferay.portal.security.sso.token.events.LogoutProcessorType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * Participates in the user logout process.
 *
 * <p>
 * <code>TokenLogoutAction</code> carries out two tasks:
 * </p>
 *
 * <ol>
 * <li>
 * If authentication cookies are configured, all named cookies are deleted by
 * the <code>@Component</code> defined in the class
 * {@link CookieLogoutProcessor} (which implements {@link LogoutProcessor})
 * </li>
 * <li>
 * If a logout redirect URL is set, then an HTTP redirect response to the
 * specified URL is issued by the <code>@Component</code> defined in the class
 * {@link RedirectLogoutProcessor} (which implements {@link
 * com.liferay.portal.security.sso.token.auto.events.LogoutProcessor})
 * </li>
 * </ol>
 *
 * @author Michael C. Han
 */
@Component(
	configurationPid = "com.liferay.portal.security.sso.token.configuration.TokenConfiguration",
	immediate = true, property = "key=logout.events.post",
	service = LifecycleAction.class
)
public class TokenLogoutAction extends Action {

	@Override
	public void run(
		HttpServletRequest httpServletRequest,
		HttpServletResponse httpServletResponse) {

		try {
			TokenConfiguration tokenCompanyServiceSettings =
				_configurationProvider.getConfiguration(
					TokenConfiguration.class,
					new CompanyServiceSettingsLocator(
						_portal.getCompanyId(httpServletRequest),
						TokenConstants.SERVICE_NAME));

			if (!tokenCompanyServiceSettings.enabled()) {
				return;
			}

			String[] authenticationCookies =
				tokenCompanyServiceSettings.authenticationCookies();

			if (ArrayUtil.isNotEmpty(authenticationCookies)) {
				LogoutProcessor cookieLogoutProcessor =
					_logoutProcessors.getService(LogoutProcessorType.COOKIE);

				if (cookieLogoutProcessor != null) {
					cookieLogoutProcessor.logout(
						httpServletRequest, httpServletResponse,
						authenticationCookies);
				}
			}

			String logoutRedirectURL =
				tokenCompanyServiceSettings.logoutRedirectURL();

			if (Validator.isNotNull(logoutRedirectURL)) {
				LogoutProcessor redirectLogoutProcessor =
					_logoutProcessors.getService(LogoutProcessorType.REDIRECT);

				if (redirectLogoutProcessor != null) {
					redirectLogoutProcessor.logout(
						httpServletRequest, httpServletResponse,
						logoutRedirectURL);
				}
			}
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_logoutProcessors = ServiceTrackerMapFactory.openSingleValueMap(
			bundleContext, LogoutProcessor.class, "logout.processor.type");
	}

	@Deactivate
	protected void deactivate() {
		_logoutProcessors.close();
	}

	@Reference(unbind = "-")
	protected void setConfigurationProvider(
		ConfigurationProvider configurationProvider) {

		_configurationProvider = configurationProvider;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		TokenLogoutAction.class);

	private ConfigurationProvider _configurationProvider;
	private ServiceTrackerMap<String, LogoutProcessor> _logoutProcessors;

	@Reference
	private Portal _portal;

}