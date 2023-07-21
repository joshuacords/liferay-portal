/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.microsofttranslator;

/**
 * @author Hugo Huijser
 */
public interface MicrosoftTranslatorFactory {

	public MicrosoftTranslator getMicrosoftTranslator();

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public MicrosoftTranslator getMicrosoftTranslator(
		String clientId, String clientSecret);

}