/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.network.utilities.web.internal.util;

import com.liferay.network.utilities.web.internal.model.DNSLookup;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.InetAddressUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.webcache.WebCacheException;
import com.liferay.portal.kernel.webcache.WebCacheItem;

import java.net.InetAddress;

/**
 * @author Brian Wing Shun Chan
 */
public class DNSLookupWebCacheItem implements WebCacheItem {

	public DNSLookupWebCacheItem(String domain) {
		_domain = domain;
	}

	@Override
	public Object convert(String key) throws WebCacheException {
		DNSLookup dnsLookup = null;

		try {
			String results = null;

			String trimmedDomain = _domain.trim();

			char[] array = trimmedDomain.toCharArray();

			for (char c : array) {
				if ((c != '.') && !Character.isDigit(c)) {
					InetAddress inetAddress =
						InetAddressUtil.getInetAddressByName(_domain);

					results = inetAddress.getHostAddress();

					break;
				}
			}

			if (results == null) {
				InetAddress[] inetAddresses = InetAddress.getAllByName(_domain);

				if (inetAddresses.length == 0) {
					results = StringPool.BLANK;
				}
				else {
					StringBundler sb = new StringBundler(
						(inetAddresses.length * 2) - 1);

					for (int i = 0; i < inetAddresses.length; i++) {
						sb.append(inetAddresses[i].getHostName());

						if ((i + 1) <= inetAddresses.length) {
							sb.append(StringPool.COMMA);
						}
					}

					results = sb.toString();
				}
			}

			dnsLookup = new DNSLookup(_domain, results);
		}
		catch (Exception exception) {
			throw new WebCacheException(_domain + " " + exception.toString());
		}

		return dnsLookup;
	}

	@Override
	public long getRefreshTime() {
		return _REFRESH_TIME;
	}

	private static final long _REFRESH_TIME = Time.DAY;

	private final String _domain;

}