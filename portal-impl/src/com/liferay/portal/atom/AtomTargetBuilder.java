/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.atom;

import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;

import org.apache.abdera.protocol.server.RequestContext;
import org.apache.abdera.protocol.server.TargetBuilder;
import org.apache.abdera.protocol.server.TargetType;

/**
 * @author Igor Spasic
 */
public class AtomTargetBuilder implements TargetBuilder {

	@Override
	public String urlFor(
		RequestContext requestContext, Object key, Object param) {

		String url = String.valueOf(requestContext.getBaseUri());

		if (url.endsWith(StringPool.SLASH)) {
			url = url.substring(0, url.length() - 1);
		}

		url += requestContext.getTargetPath();

		String query = StringPool.BLANK;

		int questionIndex = url.indexOf(CharPool.QUESTION);

		if (questionIndex != -1) {
			query = url.substring(questionIndex);

			url = url.substring(0, questionIndex);
		}

		String keyString = key.toString();

		if (keyString.equals(TargetType.SERVICE)) {
			return url + query;
		}

		if (!keyString.equals(TargetType.COLLECTION)) {
			return null;
		}

		String collectionName = CharPool.SLASH + (String)param;

		if (url.endsWith(collectionName)) {
			return url + query;
		}

		if (url.contains(collectionName + CharPool.SLASH)) {
			int collectionIndex = url.indexOf(collectionName);

			collectionIndex += collectionName.length() + 1;

			return url.substring(0, collectionIndex);
		}

		return url + collectionName + query;
	}

}