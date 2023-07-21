/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.lpkg.deployer.internal;

import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.portal.kernel.io.unsync.UnsyncByteArrayInputStream;
import com.liferay.portal.kernel.util.HashMapDictionary;
import com.liferay.portal.kernel.util.URLCodec;

import java.io.IOException;
import java.io.InputStream;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import java.util.Dictionary;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.url.AbstractURLStreamHandlerService;
import org.osgi.service.url.URLConstants;
import org.osgi.service.url.URLStreamHandlerService;

/**
 * @author Shuyang Zhou
 */
@Component(immediate = true, service = BytesURLProtocolSupport.class)
public class BytesURLProtocolSupport {

	public URL putBytes(String id, byte[] bytes) {
		try {
			URL url = new URL(
				"bytes://localhost/".concat(URLCodec.encodeURL(id)));

			_bytesMap.put(url, bytes);

			return url;
		}
		catch (MalformedURLException malformedURLException) {
			return ReflectionUtil.throwException(malformedURLException);
		}
	}

	public byte[] removeBytes(URL url) {
		return _bytesMap.remove(url);
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		Dictionary<String, Object> properties = new HashMapDictionary<>();

		properties.put(
			URLConstants.URL_HANDLER_PROTOCOL, new String[] {"bytes"});

		bundleContext.registerService(
			URLStreamHandlerService.class.getName(),
			new BytesURLStreamHandlerService(), properties);
	}

	private final Map<URL, byte[]> _bytesMap = new ConcurrentHashMap<>();

	private class BytesURLConnection extends URLConnection {

		@Override
		public void connect() {
		}

		@Override
		public InputStream getInputStream() throws IOException {
			byte[] bytes = _bytesMap.get(url);

			if (bytes == null) {
				throw new IOException("Unable to get bytes for URL " + url);
			}

			return new UnsyncByteArrayInputStream(bytes);
		}

		private BytesURLConnection(URL url) {
			super(url);
		}

	}

	private class BytesURLStreamHandlerService
		extends AbstractURLStreamHandlerService {

		@Override
		public URLConnection openConnection(URL url) {
			return new BytesURLConnection(url);
		}

	}

}