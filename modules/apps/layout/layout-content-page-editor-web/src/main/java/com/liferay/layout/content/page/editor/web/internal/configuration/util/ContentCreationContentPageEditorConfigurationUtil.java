/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.content.page.editor.web.internal.configuration.util;

import com.liferay.layout.content.page.editor.web.internal.configuration.ContentCreationContentPageEditorConfiguration;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(
	immediate = true,
	service = ContentCreationContentPageEditorConfigurationUtil.class
)
public class ContentCreationContentPageEditorConfigurationUtil {

	public static boolean isContentCreationEnabled(long companyId)
		throws ConfigurationException {

		if (_configurationProvider != null) {
			ContentCreationContentPageEditorConfiguration companyConfiguration =
				_configurationProvider.getCompanyConfiguration(
					ContentCreationContentPageEditorConfiguration.class,
					companyId);

			return companyConfiguration.contentCreationEnabled();
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