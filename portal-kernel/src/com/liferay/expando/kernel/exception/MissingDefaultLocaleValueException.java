/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.expando.kernel.exception;

import com.liferay.portal.kernel.exception.PortalException;

import java.util.Locale;

/**
 * @author Cristina Rodríguez
 */
public class MissingDefaultLocaleValueException extends PortalException {

	public MissingDefaultLocaleValueException() {
	}

	public MissingDefaultLocaleValueException(Locale locale) {
		super(
			"A value for the default locale (" + locale.getLanguage() +
				") must be defined");
	}

}