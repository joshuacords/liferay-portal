/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.weather.web.internal.util;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.util.URLCodec;
import com.liferay.portal.kernel.webcache.WebCacheException;
import com.liferay.portal.kernel.webcache.WebCacheItem;
import com.liferay.portal.kernel.xml.Attribute;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.weather.web.internal.model.Weather;

/**
 * @author Brian Wing Shun Chan
 * @author Samuel Kong
 * @author Preston Crary
 */
public class WeatherWebCacheItem implements WebCacheItem {

	public WeatherWebCacheItem(String zip, String apiKey) {
		if (zip.equals("Frankfurt/Main")) {
			zip = "Frankfurt, Germany";
		}

		_zip = zip;

		_apiKey = apiKey;
	}

	@Override
	public Object convert(String key) throws WebCacheException {
		Weather weather = null;

		try {
			weather = doConvert();
		}
		catch (Exception exception) {
			throw new WebCacheException(_zip, exception);
		}

		return weather;
	}

	@Override
	public long getRefreshTime() {
		return _REFRESH_TIME;
	}

	protected Weather doConvert() throws Exception {
		String xml = HttpUtil.URLtoString(
			StringBundler.concat(
				"http://api.openweathermap.org/data/2.5/weather?q=",
				URLCodec.encodeURL(_zip), "&units=imperial&mode=xml&APPID=",
				_apiKey));

		Document document = SAXReaderUtil.read(xml);

		Element rootElement = document.getRootElement();

		Element cityElement = rootElement.element("city");

		Attribute cityIdAttribute = cityElement.attribute("id");

		String cityId = cityIdAttribute.getText();

		Element temperatureElement = rootElement.element("temperature");

		Attribute temperatureAttribute = temperatureElement.attribute("value");

		float temperature = GetterUtil.getFloat(temperatureAttribute.getData());

		Element weatherElement = rootElement.element("weather");

		Attribute iconAttribute = weatherElement.attribute("icon");

		String iconURL =
			"http://openweathermap.org/img/w/" + iconAttribute.getText() +
				".png";

		return new Weather(_zip, cityId, iconURL, temperature);
	}

	private static final long _REFRESH_TIME = Time.MINUTE * 60;

	private final String _apiKey;
	private final String _zip;

}