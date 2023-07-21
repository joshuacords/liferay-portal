/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.security.xml;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.XMLInputFactory;

import org.xml.sax.XMLReader;

/**
 * @author Tomas Polesovsky
 */
public class SecureXMLFactoryProviderUtil {

	public static SecureXMLFactoryProvider getSecureXMLFactoryProvider() {
		return _secureXMLFactoryProvider;
	}

	public static DocumentBuilderFactory newDocumentBuilderFactory() {
		return getSecureXMLFactoryProvider().newDocumentBuilderFactory();
	}

	public static XMLInputFactory newXMLInputFactory() {
		return getSecureXMLFactoryProvider().newXMLInputFactory();
	}

	public static XMLReader newXMLReader() {
		return getSecureXMLFactoryProvider().newXMLReader();
	}

	public void setSecureXMLFactoryProvider(
		SecureXMLFactoryProvider secureXMLFactoryProvider) {

		_secureXMLFactoryProvider = secureXMLFactoryProvider;
	}

	private static SecureXMLFactoryProvider _secureXMLFactoryProvider;

}