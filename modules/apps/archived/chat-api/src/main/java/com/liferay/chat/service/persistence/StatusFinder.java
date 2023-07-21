/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.chat.service.persistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public interface StatusFinder {

	public java.util.List<Object[]> findByModifiedDate(
		long companyId, long userId, long modifiedDate, int start, int end);

	public java.util.List<Object[]> findBySocialRelationTypes(
		long userId, int[] types, long modifiedDate, int start, int end);

	public java.util.List<Object[]> findByUsersGroups(
		long userId, long modifiedDate, String[] groupNames, int start,
		int end);

}