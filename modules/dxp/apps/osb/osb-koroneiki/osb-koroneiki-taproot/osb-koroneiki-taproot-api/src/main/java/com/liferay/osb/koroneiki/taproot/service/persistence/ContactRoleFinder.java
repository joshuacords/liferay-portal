/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.koroneiki.taproot.service.persistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public interface ContactRoleFinder {

	public int countByN_T(
		String name, String[] types,
		java.util.LinkedHashMap<String, Object> params);

	public java.util.List<com.liferay.osb.koroneiki.taproot.model.ContactRole>
		findByN_T(
			String name, String[] types,
			java.util.LinkedHashMap<String, Object> params, int start, int end);

}