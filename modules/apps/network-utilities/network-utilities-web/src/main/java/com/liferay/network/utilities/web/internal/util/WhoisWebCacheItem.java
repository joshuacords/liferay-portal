/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.network.utilities.web.internal.util;

import com.liferay.network.utilities.web.internal.model.Whois;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.io.unsync.UnsyncBufferedReader;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.kernel.webcache.WebCacheException;
import com.liferay.portal.kernel.webcache.WebCacheItem;

import java.io.InputStreamReader;
import java.io.PrintStream;

import java.net.Socket;

/**
 * @author Brian Wing Shun Chan
 */
public class WhoisWebCacheItem implements WebCacheItem {

	public static final String WHOIS_SERVER = "whois.geektools.com";

	public static final int WHOIS_SERVER_PORT = 43;

	public WhoisWebCacheItem(String domain) {
		_domain = domain;
	}

	@Override
	public Object convert(String key) throws WebCacheException {
		Whois whois = null;

		try (Socket socket = new Socket(WHOIS_SERVER, WHOIS_SERVER_PORT);
			UnsyncBufferedReader unsyncBufferedReader =
				new UnsyncBufferedReader(
					new InputStreamReader(socket.getInputStream()))) {

			PrintStream out = new PrintStream(socket.getOutputStream());

			out.println(_domain);

			StringBundler sb = new StringBundler();
			String line = null;

			while ((line = unsyncBufferedReader.readLine()) != null) {
				if (line.startsWith("Results ")) {
					break;
				}

				sb.append(line);
				sb.append("\n");
			}

			whois = new Whois(
				_domain,
				StringUtil.replace(
					StringUtil.trim(sb.toString()), "\n\n", "\n"));
		}
		catch (Exception exception) {
			throw new WebCacheException(_domain + " " + exception.toString());
		}

		return whois;
	}

	@Override
	public long getRefreshTime() {
		return _REFRESH_TIME;
	}

	private static final long _REFRESH_TIME = Time.DAY;

	private final String _domain;

}