/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.util;

/**
 * @author     Brian Wing Shun Chan
 * @deprecated As of Bunyan (6.0.x), moved to {@link
 *             com.liferay.portal.kernel.util.InstancePool}
 */
@Deprecated
public class InstancePool {

	public static Object get(String className) {
		return com.liferay.portal.kernel.util.InstancePool.get(className);
	}

	public static void put(String className, Object obj) {
		com.liferay.portal.kernel.util.InstancePool.put(className, obj);
	}

}