/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.dashboard.web.internal.display.context;

import com.liferay.commerce.account.permission.CommerceAccountPermission;
import com.liferay.commerce.dashboard.web.internal.configuration.CommerceDashboardForecastPortletInstanceConfiguration;
import com.liferay.commerce.dashboard.web.internal.display.context.util.CommerceDashboardForecastRequestHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.theme.PortletDisplay;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Riccardo Ferrari
 */
public class CommerceDashboardForecastDisplayContext {

	public CommerceDashboardForecastDisplayContext(
			CommerceAccountPermission commerceAccountPermission,
			HttpServletRequest httpServletRequest)
		throws PortalException {

		_commerceAccountPermission = commerceAccountPermission;

		_commerceDashboardForecastRequestHelper =
			new CommerceDashboardForecastRequestHelper(httpServletRequest);

		PortletDisplay portletDisplay =
			_commerceDashboardForecastRequestHelper.getPortletDisplay();

		_commerceDashboardForecastPortletInstanceConfiguration =
			portletDisplay.getPortletInstanceConfiguration(
				CommerceDashboardForecastPortletInstanceConfiguration.class);
	}

	public String getAssetCategoryIds() {
		return _commerceDashboardForecastPortletInstanceConfiguration.
			assetCategoryIds();
	}

	public boolean hasViewPermission() {
		PermissionChecker permissionChecker =
			_commerceDashboardForecastRequestHelper.getPermissionChecker();

		try {
			return _commerceAccountPermission.contains(
				permissionChecker,
				_commerceDashboardForecastRequestHelper.getCommerceAccountId(),
				ActionKeys.VIEW);
		}
		catch (PortalException pe) {
			if (_log.isDebugEnabled()) {
				_log.debug(pe, pe);
			}

			return false;
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceDashboardForecastDisplayContext.class);

	private final CommerceAccountPermission _commerceAccountPermission;
	private final CommerceDashboardForecastPortletInstanceConfiguration
		_commerceDashboardForecastPortletInstanceConfiguration;
	private final CommerceDashboardForecastRequestHelper
		_commerceDashboardForecastRequestHelper;

}