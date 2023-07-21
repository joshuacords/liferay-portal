/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.vulcan.util;

import com.liferay.petra.string.StringPool;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

/**
 * @author Brian Wing Shun Chan
 */
public class JaxRsLinkUtil {

	/**
	 * @deprecated As of Athanasius (7.3.x)
	 */
	@Deprecated
	public static String getJaxRsLink(
		Class clazz, String methodName, UriInfo uriInfo, Object... values) {

		String baseURIString = String.valueOf(uriInfo.getBaseUri());

		if (baseURIString.endsWith(StringPool.FORWARD_SLASH)) {
			baseURIString = baseURIString.substring(
				0, baseURIString.length() - 1);
		}

		URI resourceURI = UriBuilder.fromResource(
			clazz
		).build();

		URI methodURI = UriBuilder.fromMethod(
			clazz, methodName
		).build(
			values
		);

		return baseURIString + resourceURI.toString() + methodURI.toString();
	}

	public static String getJaxRsLink(
		String applicationPath, Class clazz, String methodName, UriInfo uriInfo,
		Object... values) {

		String baseURIString = String.valueOf(uriInfo.getBaseUri());

		if (baseURIString.endsWith(StringPool.FORWARD_SLASH)) {
			baseURIString = baseURIString.substring(
				0, baseURIString.length() - 1);
		}

		baseURIString =
			baseURIString.substring(0, baseURIString.lastIndexOf("/") + 1) +
				applicationPath;

		URI resourceURI = UriBuilder.fromResource(
			clazz
		).build();

		URI methodURI = UriBuilder.fromMethod(
			clazz, methodName
		).build(
			values
		);

		return baseURIString + resourceURI.toString() + methodURI.toString();
	}

}