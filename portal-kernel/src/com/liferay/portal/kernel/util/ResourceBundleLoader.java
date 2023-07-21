/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.util;

import java.util.Locale;
import java.util.ResourceBundle;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Carlos Sierra Andrés
 */
@FunctionalInterface
@ProviderType
public interface ResourceBundleLoader {

	public ResourceBundle loadResourceBundle(Locale locale);

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             #loadResourceBundle(Locale)}
	 */
	@Deprecated
	public default ResourceBundle loadResourceBundle(String languageId) {
		return loadResourceBundle(LocaleUtil.fromLanguageId(languageId));
	}

}