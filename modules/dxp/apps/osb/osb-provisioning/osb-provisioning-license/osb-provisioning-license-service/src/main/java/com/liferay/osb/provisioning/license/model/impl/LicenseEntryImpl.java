/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.license.model.impl;

import com.liferay.osb.provisioning.license.helper.constants.LicenseType;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;

/**
 * @author Kyle Bischof
 */
public class LicenseEntryImpl extends LicenseEntryBaseImpl {

	public LicenseEntryImpl() {
	}

	public String getDisplayName() {
		String name = getName();
		String typeLabel = LicenseType.getLabel(getType());

		if (!name.contains(typeLabel)) {
			return StringBundler.concat(
				name, StringPool.SPACE, StringPool.OPEN_PARENTHESIS, typeLabel,
				StringPool.CLOSE_PARENTHESIS);
		}

		return name;
	}

}