/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.currency.converter.web.internal.portlet.validator;

import com.liferay.currency.converter.model.CurrencyConverter;
import com.liferay.currency.converter.util.CurrencyConverterUtil;
import com.liferay.currency.converter.web.internal.constants.CurrencyConverterPortletKeys;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletPreferences;
import javax.portlet.PreferencesValidator;
import javax.portlet.ValidatorException;

import org.osgi.service.component.annotations.Component;

/**
 * @author Brian Wing Shun Chan
 */
@Component(
	immediate = true,
	property = "javax.portlet.name=" + CurrencyConverterPortletKeys.CURRENCY_CONVERTER,
	service = PreferencesValidator.class
)
public class CurrencyConverterPreferencesValidator
	implements PreferencesValidator {

	@Override
	public void validate(PortletPreferences portletPreferences)
		throws ValidatorException {

		List<String> badSymbols = new ArrayList<>();

		String[] symbols = portletPreferences.getValues(
			"symbols", new String[0]);

		for (String symbol : symbols) {
			CurrencyConverter currencyConverter =
				CurrencyConverterUtil.getCurrencyConverter(symbol);

			if (currencyConverter == null) {
				badSymbols.add(symbol);
			}
		}

		if (!badSymbols.isEmpty()) {
			throw new ValidatorException(
				"Unable to retrieve symbols", badSymbols);
		}
	}

}