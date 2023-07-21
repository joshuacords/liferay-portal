/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.template.soy.data;

import com.liferay.portal.template.soy.util.SoyRawData;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Describes the API of an OSGi service that creates specialized complex types
 * that can be used in Soy templates.
 *
 * @author Iván Zaera Avellón
 * @review
 */
@ProviderType
public interface SoyDataFactory {

	/**
	 * @deprecated As of Mueller (7.2.x), replaced by {@link
	 *             #createSoyRawData(String)}
	 */
	@Deprecated
	public SoyHTMLData createSoyHTMLData(String html);

	public SoyRawData createSoyRawData(String html);

}