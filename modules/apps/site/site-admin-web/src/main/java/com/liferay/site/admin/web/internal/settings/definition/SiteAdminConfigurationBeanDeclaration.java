/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.site.admin.web.internal.settings.definition;

import com.liferay.portal.kernel.settings.definition.ConfigurationBeanDeclaration;
import com.liferay.site.admin.web.internal.configuration.SiteAdminConfiguration;

import org.osgi.service.component.annotations.Component;

/**
 * @author Vendel Töreki
 */
@Component(service = ConfigurationBeanDeclaration.class)
public class SiteAdminConfigurationBeanDeclaration
	implements ConfigurationBeanDeclaration {

	@Override
	public Class<?> getConfigurationBeanClass() {
		return SiteAdminConfiguration.class;
	}

}