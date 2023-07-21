/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.chat.web.internal.util;

import com.liferay.chat.util.BuddyFinder;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Wing Shun Chan
 * @author Ankit Srivastava
 * @author Peter Fellwock
 */
@Component(enabled = false, immediate = true, service = BuddyFinderUtil.class)
public class BuddyFinderUtil {

	public static List<Object[]> getBuddies(long companyId, long userId) {
		return getBuddyFinder().getBuddies(companyId, userId);
	}

	public static BuddyFinder getBuddyFinder() {
		return _buddyFinder;
	}

	@Reference(unbind = "-")
	protected void setBuddyFinder(BuddyFinder buddyFinder) {
		_buddyFinder = buddyFinder;
	}

	private static BuddyFinder _buddyFinder;

}