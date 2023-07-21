/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.users.admin.web.internal.util;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.model.ExpandoColumnConstants;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.util.Enumeration;

/**
 * @author Drew Brokke
 */
public class CustomFieldsUtil {

	public static boolean hasVisibleCustomFields(
		long companyId, Class<?> clazz) {

		ExpandoBridge expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(
			companyId, clazz.getName());

		Enumeration<String> attributeNames = expandoBridge.getAttributeNames();

		while (attributeNames.hasMoreElements()) {
			String attributeName = attributeNames.nextElement();

			UnicodeProperties properties = expandoBridge.getAttributeProperties(
				attributeName);

			if (!GetterUtil.getBoolean(
					properties.get(ExpandoColumnConstants.PROPERTY_HIDDEN))) {

				return true;
			}
		}

		return false;
	}

}