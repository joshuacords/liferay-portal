/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.registry;

import java.util.Map;

/**
 * @author Raymond Augé
 */
public interface ServiceRegistration<T> {

	public ServiceReference<T> getServiceReference();

	public void setProperties(Map<String, Object> properties);

	public void unregister();

}