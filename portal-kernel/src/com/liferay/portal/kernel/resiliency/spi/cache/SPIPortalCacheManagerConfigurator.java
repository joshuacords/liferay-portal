/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.resiliency.spi.cache;

import com.liferay.portal.kernel.cache.PortalCacheManager;

import java.io.Serializable;

/**
 * @author Tina Tian
 */
public interface SPIPortalCacheManagerConfigurator {

	public PortalCacheManager<? extends Serializable, ? extends Serializable>
			createSPIPortalCacheManager(
				PortalCacheManager
					<? extends Serializable, ? extends Serializable>
						portalCacheManager)
		throws Exception;

}