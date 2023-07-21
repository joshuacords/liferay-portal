/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.dynamic.data.mapping.web.internal.context.util;

import com.liferay.dynamic.data.mapping.configuration.DDMGroupServiceConfiguration;
import com.liferay.dynamic.data.mapping.constants.DDMConstants;
import com.liferay.dynamic.data.mapping.web.internal.configuration.DDMWebConfiguration;
import com.liferay.portal.kernel.display.context.util.BaseRequestHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProviderUtil;
import com.liferay.portal.kernel.settings.GroupServiceSettingsLocator;
import com.liferay.portal.kernel.settings.ParameterMapSettingsLocator;
import com.liferay.portal.kernel.util.Validator;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Lino Alves
 */
public class DDMWebRequestHelper extends BaseRequestHelper {

	public DDMWebRequestHelper(HttpServletRequest httpServletRequest) {
		super(httpServletRequest);
	}

	public DDMGroupServiceConfiguration getDDMGroupServiceConfiguration() {
		try {
			if (_ddmGroupServiceConfiguration == null) {
				_ddmGroupServiceConfiguration = getConfiguration(
					DDMGroupServiceConfiguration.class);
			}

			return _ddmGroupServiceConfiguration;
		}
		catch (PortalException portalException) {
			throw new SystemException(portalException);
		}
	}

	public DDMWebConfiguration getDDMWebConfiguration() {
		try {
			if (_ddmWebConfiguration == null) {
				_ddmWebConfiguration = getConfiguration(
					DDMWebConfiguration.class);
			}

			return _ddmWebConfiguration;
		}
		catch (PortalException portalException) {
			throw new SystemException(portalException);
		}
	}

	protected <T> T getConfiguration(Class<T> clazz)
		throws ConfigurationException {

		if (Validator.isNotNull(getPortletResource())) {
			HttpServletRequest httpServletRequest = getRequest();

			return (T)ConfigurationProviderUtil.getConfiguration(
				clazz,
				new ParameterMapSettingsLocator(
					httpServletRequest.getParameterMap(),
					new GroupServiceSettingsLocator(
						getSiteGroupId(), DDMConstants.SERVICE_NAME)));
		}

		return (T)ConfigurationProviderUtil.getConfiguration(
			clazz,
			new GroupServiceSettingsLocator(
				getSiteGroupId(), DDMConstants.SERVICE_NAME));
	}

	private DDMGroupServiceConfiguration _ddmGroupServiceConfiguration;
	private DDMWebConfiguration _ddmWebConfiguration;

}