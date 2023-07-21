/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.powwow.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;

/**
 * @author Shinn Lok
 * @generated
 */
public class PowwowMeetingFinderUtil {

	public static int countByU_S(long userId, int[] statuses) {
		return getFinder().countByU_S(userId, statuses);
	}

	public static java.util.List<com.liferay.powwow.model.PowwowMeeting>
		findByU_S(
			long userId, int[] statuses, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				orderByComparator) {

		return getFinder().findByU_S(
			userId, statuses, start, end, orderByComparator);
	}

	public static PowwowMeetingFinder getFinder() {
		if (_finder == null) {
			_finder = (PowwowMeetingFinder)PortletBeanLocatorUtil.locate(
				com.liferay.powwow.service.ServletContextUtil.
					getServletContextName(),
				PowwowMeetingFinder.class.getName());
		}

		return _finder;
	}

	public void setFinder(PowwowMeetingFinder finder) {
		_finder = finder;
	}

	private static PowwowMeetingFinder _finder;

}