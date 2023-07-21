/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.permission;

import java.util.Optional;

/**
 * @author Bryan Engler
 */
public interface SearchPermissionFilterContributor {

	public Optional<String> getParentEntryClassNameOptional(
		String entryClassName);

}