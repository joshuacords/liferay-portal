/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.expando.kernel.util;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.portal.kernel.search.Document;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Raymond Augé
 */
@ProviderType
public interface ExpandoBridgeIndexer {

	public void addAttributes(Document document, ExpandoBridge expandoBridge);

	/**
	 * @deprecated As of Judson (7.1.x), replaced by {@link
	 *             #encodeFieldName(String, int)}
	 */
	@Deprecated
	public String encodeFieldName(String columnName);

	public String encodeFieldName(String columnName, int indexType);

}