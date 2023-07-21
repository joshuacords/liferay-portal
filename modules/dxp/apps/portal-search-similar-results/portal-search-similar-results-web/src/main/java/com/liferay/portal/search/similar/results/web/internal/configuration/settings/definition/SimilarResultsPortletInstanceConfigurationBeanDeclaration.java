/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.similar.results.web.internal.configuration.settings.definition;

import com.liferay.portal.kernel.settings.definition.ConfigurationBeanDeclaration;
import com.liferay.portal.search.similar.results.web.internal.configuration.SimilarResultsPortletInstanceConfiguration;

import org.osgi.service.component.annotations.Component;

/**
 * @author Kevin Tan
 */
@Component(service = ConfigurationBeanDeclaration.class)
public class SimilarResultsPortletInstanceConfigurationBeanDeclaration
	implements ConfigurationBeanDeclaration {

	@Override
	public Class<?> getConfigurationBeanClass() {
		return SimilarResultsPortletInstanceConfiguration.class;
	}

}