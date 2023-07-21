/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.powwow.service.persistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Shinn Lok
 * @generated
 */
@ProviderType
public interface PowwowMeetingFinder {

	public int countByU_S(long userId, int[] statuses);

	public java.util.List<com.liferay.powwow.model.PowwowMeeting> findByU_S(
		long userId, int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator);

}