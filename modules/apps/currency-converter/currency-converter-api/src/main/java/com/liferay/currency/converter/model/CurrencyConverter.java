/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.currency.converter.model;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 */
public class CurrencyConverter implements Serializable {

	public static final String DEFAULT_FROM = "USD";

	public static final String DEFAULT_TO = "EUR";

	public CurrencyConverter(String symbol, double rate) {
		_symbol = symbol;
		_rate = rate;
	}

	public String getFromSymbol() {
		if ((_symbol != null) && (_symbol.length() == 6)) {
			return _symbol.substring(0, 3);
		}

		return DEFAULT_FROM;
	}

	public double getRate() {
		return _rate;
	}

	public String getSymbol() {
		return _symbol;
	}

	public String getToSymbol() {
		if ((_symbol != null) && (_symbol.length() == 6)) {
			return _symbol.substring(3, 6);
		}

		return DEFAULT_TO;
	}

	private final double _rate;
	private final String _symbol;

}