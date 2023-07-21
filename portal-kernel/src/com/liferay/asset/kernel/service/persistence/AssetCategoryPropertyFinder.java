/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.asset.kernel.service.persistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Brian Wing Shun Chan
 * @deprecated
 * @generated
 */
@Deprecated
@ProviderType
public interface AssetCategoryPropertyFinder {

	public int countByG_K(long groupId, String key);

	public java.util.List<com.liferay.asset.kernel.model.AssetCategoryProperty>
		findByG_K(long groupId, String key);

	public java.util.List<com.liferay.asset.kernel.model.AssetCategoryProperty>
		findByG_K(long groupId, String key, int start, int end);

}