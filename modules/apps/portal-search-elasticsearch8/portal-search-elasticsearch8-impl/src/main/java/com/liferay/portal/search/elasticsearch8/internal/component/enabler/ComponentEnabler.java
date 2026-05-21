/**
 * SPDX-FileCopyrightText: (c) 2026 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.elasticsearch8.internal.component.enabler;

import com.liferay.osgi.util.ComponentUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.search.elasticsearch8.internal.ElasticsearchSearchEngine;
import com.liferay.portal.search.elasticsearch8.internal.index.CompanyIndexHelper;
import com.liferay.portal.search.elasticsearch8.internal.sidecar.SidecarManager;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Joshua Cords
 */
@Component(service = {})
public class ComponentEnabler {

	@Activate
	protected void activate(ComponentContext componentContext) {
		_log.error(
			"[LPD-82794] ComponentEnabler.@Activate fired; calling " +
				"ComponentUtil.enableComponents to wait for " +
					"SidecarManagerReady");

		ComponentUtil.enableComponents(
			ElasticsearchConfigurationReady.class, null, componentContext,
			CompanyIndexHelper.class, ElasticsearchSearchEngine.class,
			SidecarManager.class);

		_log.error(
			"[LPD-82794] ComponentEnabler.@Activate returning; " +
				"ServiceTracker is now watching for SidecarManagerReady");
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ComponentEnabler.class);

}