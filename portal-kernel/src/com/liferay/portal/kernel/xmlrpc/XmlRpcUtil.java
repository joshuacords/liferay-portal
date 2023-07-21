/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.xmlrpc;

/**
 * @author Brian Wing Shun Chan
 */
public class XmlRpcUtil {

	public static Fault createFault(int code, String description) {
		return getXmlRpc().createFault(code, description);
	}

	public static Success createSuccess(String description) {
		return getXmlRpc().createSuccess(description);
	}

	public static Response executeMethod(
			String url, String methodName, Object[] arguments)
		throws XmlRpcException {

		return getXmlRpc().executeMethod(url, methodName, arguments);
	}

	public static XmlRpc getXmlRpc() {
		return _xmlRpc;
	}

	public void setXmlRpc(XmlRpc xmlRpc) {
		_xmlRpc = xmlRpc;
	}

	private static XmlRpc _xmlRpc;

}