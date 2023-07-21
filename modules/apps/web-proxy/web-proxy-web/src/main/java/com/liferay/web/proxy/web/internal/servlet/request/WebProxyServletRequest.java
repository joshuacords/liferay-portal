/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.web.proxy.web.internal.servlet.request;

import com.liferay.portal.upload.LiferayServletRequest;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @author Daniel Couso
 */
public class WebProxyServletRequest extends HttpServletRequestWrapper {

	public WebProxyServletRequest(HttpServletRequest httpServletRequest)
		throws IOException {

		super(httpServletRequest);

		_liferayServletRequest = new LiferayServletRequest(httpServletRequest);

		readInputStream(_liferayServletRequest.getInputStream());

		_liferayServletRequest.setFinishedReadingOriginalStream(true);
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		if (_liferayServletRequest != null) {
			return _liferayServletRequest.getInputStream();
		}

		return super.getInputStream();
	}

	protected void readInputStream(InputStream inputStream) throws IOException {
		byte[] buffer = new byte[4096];

		while (inputStream.read(buffer, 0, 4096) > 0) {
		}
	}

	private final LiferayServletRequest _liferayServletRequest;

}