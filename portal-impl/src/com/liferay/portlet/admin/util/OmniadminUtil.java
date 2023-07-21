/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.admin.util;

import com.liferay.admin.kernel.util.Omniadmin;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.ServiceProxyFactory;

/**
 * Provides utility methods for determining if a user is a universal
 * administrator. Universal administrators have administrator permissions in
 * every company.
 *
 * <p>
 * A user can be made a universal administrator by adding their primary key to
 * the list in <code>portal.properties</code> under the key
 * <code>omniadmin.users</code>. If this property is left blank, administrators
 * of the default company will automatically be universal administrators.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
public class OmniadminUtil {

	public static boolean isOmniadmin(long userId) {
		return _omniadmin.isOmniadmin(userId);
	}

	public static boolean isOmniadmin(User user) {
		return _omniadmin.isOmniadmin(user);
	}

	private static volatile Omniadmin _omniadmin =
		ServiceProxyFactory.newServiceTrackedInstance(
			Omniadmin.class, OmniadminUtil.class, "_omniadmin", true);

}