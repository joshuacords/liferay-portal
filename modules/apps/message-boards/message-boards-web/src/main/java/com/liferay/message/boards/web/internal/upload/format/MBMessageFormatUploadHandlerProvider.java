/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.message.boards.web.internal.upload.format;

import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Alejandro Tardín
 */
@Component(service = MBMessageFormatUploadHandlerProvider.class)
public class MBMessageFormatUploadHandlerProvider {

	@Activate
	public void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openSingleValueMap(
			bundleContext, MBMessageFormatUploadHandler.class, "format");
	}

	@Deactivate
	public void deactivate() {
		_serviceTrackerMap.close();
	}

	public MBMessageFormatUploadHandler provide(String format) {
		return _serviceTrackerMap.getService(format);
	}

	private ServiceTrackerMap<String, MBMessageFormatUploadHandler>
		_serviceTrackerMap;

}