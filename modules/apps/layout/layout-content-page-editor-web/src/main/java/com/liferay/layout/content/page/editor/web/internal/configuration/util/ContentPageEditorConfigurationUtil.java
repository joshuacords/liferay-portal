/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.content.page.editor.web.internal.configuration.util;

import com.liferay.layout.content.page.editor.web.internal.configuration.ContentPageEditorConfiguration;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alejandro Tardín
 */
@Component(immediate = true, service = ContentPageEditorConfigurationUtil.class)
public class ContentPageEditorConfigurationUtil {

	public static boolean isCommentsEnabled(long companyId)
		throws ConfigurationException {

		if (_configurationProvider != null) {
			ContentPageEditorConfiguration companyConfiguration =
				_configurationProvider.getCompanyConfiguration(
					ContentPageEditorConfiguration.class, companyId);

			return companyConfiguration.commentsEnabled();
		}

		return false;
	}

	@Reference(unbind = "-")
	protected void setConfigurationProvider(
		ConfigurationProvider configurationProvider) {

		_configurationProvider = configurationProvider;
	}

	private static ConfigurationProvider _configurationProvider;

}