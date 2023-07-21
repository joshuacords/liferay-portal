/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.template.xsl.internal;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.util.InetAddressUtil;
import com.liferay.portal.xsl.XSLURIResolver;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;

/**
 * @author Marta Medio
 */
public class XSLSecureURIResolver implements XSLURIResolver {

	public XSLSecureURIResolver(XSLURIResolver xsluriResolver) {
		_xsluriResolver = xsluriResolver;
	}

	@Override
	public String getLanguageId() {
		if (_xsluriResolver != null) {
			return _xsluriResolver.getLanguageId();
		}

		return null;
	}

	@Override
	public Source resolve(String href, String base)
		throws TransformerException {

		try {
			URL url = new URL(href);

			if (InetAddressUtil.isLocalInetAddress(
					InetAddressUtil.getInetAddressByName(url.getHost()))) {

				throw new TransformerException(
					StringBundler.concat(
						"Denied access to resource: ", href,
						". Access to local network is disabled by the secure ",
						"processing feature."));
			}
			else if (_xsluriResolver != null) {
				return _xsluriResolver.resolve(href, base);
			}

			return null;
		}
		catch (MalformedURLException | UnknownHostException exception) {
			throw new TransformerException(
				"Unable to resolve URL reference", exception);
		}
	}

	private final XSLURIResolver _xsluriResolver;

}