/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.blogs.service.persistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public interface BlogsStatsUserFinder {

	public int countByOrganizationId(long organizationId);

	public int countByOrganizationIds(java.util.List<Long> organizationIds);

	public java.util.List<com.liferay.blogs.model.BlogsStatsUser>
		findByGroupIds(long companyId, long groupId, int start, int end);

	public java.util.List<com.liferay.blogs.model.BlogsStatsUser>
		findByOrganizationId(
			long organizationId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.blogs.model.BlogsStatsUser> obc);

	public java.util.List<com.liferay.blogs.model.BlogsStatsUser>
		findByOrganizationIds(
			java.util.List<Long> organizationIds, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.blogs.model.BlogsStatsUser> obc);

}