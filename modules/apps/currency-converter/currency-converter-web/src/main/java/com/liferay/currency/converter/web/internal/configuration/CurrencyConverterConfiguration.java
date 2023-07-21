/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.currency.converter.web.internal.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Peter Fellwock
 */
@ExtendedObjectClassDefinition(category = "localization")
@Meta.OCD(
	id = "com.liferay.currency.converter.web.internal.configuration.CurrencyConverterConfiguration",
	localization = "content/Language",
	name = "currency-converter-configuration-name"
)
public interface CurrencyConverterConfiguration {

	@Meta.AD(deflt = "GBP|CNY|EUR|JPY|USD", name = "symbols", required = false)
	public String[] symbols();

}