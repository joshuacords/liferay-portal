/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.registry.collections;

import com.liferay.registry.ServiceRegistration;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Brian Wing Shun Chan
 */
public class StringServiceRegistrationMapImpl<T>
	extends ConcurrentHashMap<String, ServiceRegistration<T>>
	implements StringServiceRegistrationMap<T> {
}