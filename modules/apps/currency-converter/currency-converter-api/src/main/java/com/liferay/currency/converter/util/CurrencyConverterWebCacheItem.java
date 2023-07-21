/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.currency.converter.util;

import com.liferay.currency.converter.model.CurrencyConverter;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.webcache.WebCacheException;
import com.liferay.portal.kernel.webcache.WebCacheItem;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

import java.util.List;
import java.util.Objects;

/**
 * @author Brian Wing Shun Chan
 * @author Hugo Huijser
 */
public class CurrencyConverterWebCacheItem implements WebCacheItem {

	public CurrencyConverterWebCacheItem(String symbol) {
		_symbol = symbol;
	}

	@Override
	public Object convert(String key) throws WebCacheException {
		String symbol = _symbol;
		double rate = 0.0;

		try {
			if (symbol.length() != 6) {
				throw new WebCacheException(symbol);
			}

			String fromSymbol = symbol.substring(0, 3);
			String toSymbol = symbol.substring(3, 6);

			if (!CurrencyConverterUtil.isCurrency(fromSymbol) ||
				!CurrencyConverterUtil.isCurrency(toSymbol)) {

				throw new WebCacheException(symbol);
			}

			String text = HttpUtil.URLtoString(
				"http://www.ecb.int/stats/eurofxref/eurofxref-daily.xml");

			Document document = SAXReaderUtil.read(text);

			Element rootElement = document.getRootElement();

			String fromRate = _getRate(fromSymbol, rootElement);
			String toRate = _getRate(toSymbol, rootElement);

			rate =
				GetterUtil.getDouble(toRate) / GetterUtil.getDouble(fromRate);
		}
		catch (Exception exception) {
			throw new WebCacheException(exception);
		}

		return new CurrencyConverter(symbol, rate);
	}

	@Override
	public long getRefreshTime() {
		return _REFRESH_TIME;
	}

	private String _getRate(String symbol, Element element) {
		if (symbol.equals(_SYMBOL_EURO)) {
			return "1.0";
		}

		String rate = null;

		for (Element cubeElement : (List<Element>)element.elements("Cube")) {
			if (Objects.equals(
					cubeElement.attributeValue("currency"), symbol)) {

				return cubeElement.attributeValue("rate");
			}

			rate = _getRate(symbol, cubeElement);
		}

		return rate;
	}

	private static final long _REFRESH_TIME = Time.MINUTE * 20;

	private static final String _SYMBOL_EURO = "EUR";

	private final String _symbol;

}