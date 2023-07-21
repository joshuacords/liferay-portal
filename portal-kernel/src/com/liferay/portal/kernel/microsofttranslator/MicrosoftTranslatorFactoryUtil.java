/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.microsofttranslator;

import com.liferay.portal.kernel.util.ServiceProxyFactory;

/**
 * @author Hugo Huijser
 */
public class MicrosoftTranslatorFactoryUtil {

	public static MicrosoftTranslator getMicrosoftTranslator() {
		return getMicrosoftTranslatorFactory().getMicrosoftTranslator();
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public static MicrosoftTranslator getMicrosoftTranslator(
		String clientId, String clientSecret) {

		return getMicrosoftTranslatorFactory().getMicrosoftTranslator(
			clientId, clientSecret);
	}

	public static MicrosoftTranslatorFactory getMicrosoftTranslatorFactory() {
		return _microsoftTranslatorFactory;
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public void setMicrosoftTranslatorFactory(
		MicrosoftTranslatorFactory microsoftTranslatorFactory) {
	}

	private static volatile MicrosoftTranslatorFactory
		_microsoftTranslatorFactory =
			ServiceProxyFactory.newServiceTrackedInstance(
				MicrosoftTranslatorFactory.class,
				MicrosoftTranslatorFactoryUtil.class,
				"_microsoftTranslatorFactory", false);

}