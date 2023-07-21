/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.service.configuration.configurator;

import com.liferay.portal.kernel.service.configuration.ServiceComponentConfiguration;

/**
 * @author     Miguel Pastor
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Deprecated
public interface ServiceConfigurator {

	public void destroyServices(
			ServiceComponentConfiguration serviceComponentConfiguration,
			ClassLoader classLoader)
		throws Exception;

	public void initServices(
			ServiceComponentConfiguration serviceComponentConfiguration,
			ClassLoader classLoader)
		throws Exception;

}