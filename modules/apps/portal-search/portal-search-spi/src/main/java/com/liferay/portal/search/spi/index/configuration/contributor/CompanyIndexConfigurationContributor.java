/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.spi.index.configuration.contributor;

import com.liferay.portal.search.spi.index.configuration.contributor.helper.MappingsHelper;
import com.liferay.portal.search.spi.index.configuration.contributor.helper.SettingsHelper;

/**
 * This interface defines methods for contributing mappings and settings to a
 * search engine index during its creation.
 *
 * @author Adam Brandizzi
 */
public interface CompanyIndexConfigurationContributor {

	/**
	 * This method allows contributors to add search engine mappings to the provided {@link MappingsHelper}.
	 *
	 * Implementations of this method should use the {@link MappingsHelper#putMappings(String)} method to
	 * add the desired mappings to the search engine.
	 *
	 * @param mappingsHelper An instance of {@link MappingsHelper} used to store search engine mappings.
	 * @throws Exception if there is an error contributing the mappings to the search engine.
	 */
	public void contributeMappings(MappingsHelper mappingsHelper)
		throws Exception;

	/**
	 * This method allows contributors to add search engine settings to the provided {@link SettingsHelper}.
	 *
	 * Implementations of this method should use the {@link SettingsHelper#putSettings(String)} method to
	 * add the desired settings to the search engine.
	 *
	 * @param settingsHelper An instance of {@link SettingsHelper} used to store with search engine settings.
	 * @throws Exception if there is an error contributing the settings to the search engine.
	 */
	public void contributeSettings(SettingsHelper settingsHelper)
		throws Exception;

}