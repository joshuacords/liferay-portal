/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.spring.util;

import java.util.Map;

/**
 * @author     Brian Wing Shun Chan
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public interface SpringFactory {

	public Object newBean(String className) throws SpringFactoryException;

	public Object newBean(String className, Map<String, Object> properties)
		throws SpringFactoryException;

}