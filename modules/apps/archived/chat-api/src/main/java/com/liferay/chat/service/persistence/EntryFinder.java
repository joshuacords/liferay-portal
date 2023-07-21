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
public interface EntryFinder {

	public java.util.List<com.liferay.chat.model.Entry> findByEmptyContent(
		long fromUserId, long toUserId, int start, int end);

	public java.util.List<com.liferay.chat.model.Entry> findByNew(
		long userId, long createDate, int start, int end);

	public java.util.List<com.liferay.chat.model.Entry> findByOld(
		long createDate, int start, int end);

}