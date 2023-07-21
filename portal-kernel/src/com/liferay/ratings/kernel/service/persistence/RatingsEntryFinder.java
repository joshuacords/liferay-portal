/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.ratings.kernel.service.persistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Brian Wing Shun Chan
 * @deprecated As of Judson (7.1.x), with no direct replacement
 * @generated
 */
@Deprecated
@ProviderType
public interface RatingsEntryFinder {

	public java.util.Map
		<java.io.Serializable, com.liferay.ratings.kernel.model.RatingsEntry>
			fetchByPrimaryKeys(java.util.Set<java.io.Serializable> primaryKeys);

	public java.util.List<com.liferay.ratings.kernel.model.RatingsEntry>
		findByU_C_C(
			long userId, long classNameId, java.util.List<Long> classPKs);

}