/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.service.persistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public interface SyncDLObjectFinder {

	public java.util.List<Long> filterFindByR_U_T(
		long groupId, long userId, long[] typePKs);

	public java.util.List<com.liferay.sync.model.SyncDLObject>
		findByModifiedTime(
			long modifiedTime, long repositoryId, long parentFolderId,
			String type, int start, int end);

}