/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.info.display.contributor.util;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.info.display.contributor.field.ExpandoInfoDisplayContributorField;
import com.liferay.info.display.contributor.field.InfoDisplayContributorField;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author     Pavel Savinov
 * @deprecated As of Mueller (7.2.x), replaced by {@link
 *             com.liferay.info.display.field.ExpandoInfoDisplayFieldProvider}
 */
@Deprecated
public class ExpandoInfoDisplayContributorFieldUtil {

	public static List<InfoDisplayContributorField>
		getInfoDisplayContributorFields(String className) {

		List<InfoDisplayContributorField> infoDisplayContributorFields =
			new ArrayList<>();

		ExpandoBridge expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(
			CompanyThreadLocal.getCompanyId(), className, 0L);

		Enumeration<String> attributeNames = expandoBridge.getAttributeNames();

		while (attributeNames.hasMoreElements()) {
			String attributeName = attributeNames.nextElement();

			infoDisplayContributorFields.add(
				new ExpandoInfoDisplayContributorField(
					attributeName, expandoBridge));
		}

		return infoDisplayContributorFields;
	}

}