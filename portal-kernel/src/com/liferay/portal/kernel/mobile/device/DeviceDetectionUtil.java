/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.mobile.device;

import com.liferay.portal.kernel.util.ServiceProxyFactory;

import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Milen Dyankov
 * @author Raymond Augé
 */
public class DeviceDetectionUtil {

	public static Device detectDevice(HttpServletRequest httpServletRequest) {
		DeviceRecognitionProvider deviceRecognitionProvider =
			_deviceRecognitionProvider;

		if (deviceRecognitionProvider == null) {
			return UnknownDevice.getInstance();
		}

		return deviceRecognitionProvider.detectDevice(httpServletRequest);
	}

	public static DeviceRecognitionProvider getDeviceRecognitionProvider() {
		return _deviceRecognitionProvider;
	}

	public static Set<VersionableName> getKnownBrands() {
		KnownDevices knownDevices = getKnownDevices();

		return knownDevices.getBrands();
	}

	public static Set<VersionableName> getKnownBrowsers() {
		KnownDevices knownDevices = getKnownDevices();

		return knownDevices.getBrowsers();
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public static Set<String> getKnownDeviceIdsByCapability(
		Capability capability) {

		KnownDevices knownDevices = getKnownDevices();

		Map<Capability, Set<String>> deviceIds = knownDevices.getDeviceIds();

		return deviceIds.get(capability);
	}

	public static Set<VersionableName> getKnownOperatingSystems() {
		KnownDevices knownDevices = getKnownDevices();

		return knownDevices.getOperatingSystems();
	}

	public static Set<String> getKnownPointingMethods() {
		KnownDevices knownDevices = getKnownDevices();

		return knownDevices.getPointingMethods();
	}

	protected static KnownDevices getKnownDevices() {
		DeviceRecognitionProvider deviceRecognitionProvider =
			_deviceRecognitionProvider;

		if (deviceRecognitionProvider == null) {
			return NoKnownDevices.getInstance();
		}

		return deviceRecognitionProvider.getKnownDevices();
	}

	private static volatile DeviceRecognitionProvider
		_deviceRecognitionProvider =
			ServiceProxyFactory.newServiceTrackedInstance(
				DeviceRecognitionProvider.class, DeviceDetectionUtil.class,
				"_deviceRecognitionProvider", false, true);

}