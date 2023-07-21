/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.mobile.device.detection.fiftyonedegrees.internal;

import com.liferay.portal.kernel.mobile.device.Device;
import com.liferay.portal.kernel.mobile.device.DeviceCapabilityFilter;
import com.liferay.portal.kernel.mobile.device.DeviceRecognitionProvider;
import com.liferay.portal.kernel.mobile.device.KnownDevices;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Brian Greenwald
 * @author Prathima Shreenath
 */
@Component(service = DeviceRecognitionProvider.class)
public class FiftyOneDegreesDeviceRecognitionProvider
	implements DeviceRecognitionProvider {

	@Override
	public Device detectDevice(HttpServletRequest httpServletRequest) {
		return _fiftyOneDegreesEngineProxy.getDevice(httpServletRequest);
	}

	@Override
	public KnownDevices getKnownDevices() {
		return _fiftyOneDegreesKnownDevices;
	}

	@Override
	public void reload() throws Exception {
		_fiftyOneDegreesKnownDevices.reload();
	}

	/**
	 * @deprecated As of Judson (7.1.x)
	 */
	@Deprecated
	@Override
	public void setDeviceCapabilityFilter(
		DeviceCapabilityFilter deviceCapabilityFilter) {
	}

	@Activate
	protected void activate() {
		try {
			reload();
		}
		catch (Exception exception) {
			throw new IllegalStateException(
				"Unable to load 51Degrees device data", exception);
		}
	}

	@Reference
	private FiftyOneDegreesEngineProxy _fiftyOneDegreesEngineProxy;

	@Reference
	private FiftyOneDegreesKnownDevices _fiftyOneDegreesKnownDevices;

}