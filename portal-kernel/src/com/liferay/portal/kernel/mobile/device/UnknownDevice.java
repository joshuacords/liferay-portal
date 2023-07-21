/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.mobile.device;

import java.util.Collections;
import java.util.Map;

/**
 * Class represents unknown device
 *
 * @author Milen Dyankov
 */
public class UnknownDevice extends BaseDevice {

	public static UnknownDevice getInstance() {
		return _unknownDevice;
	}

	@Override
	public String getBrand() {
		return VersionableName.UNKNOWN.getName();
	}

	@Override
	public String getBrowser() {
		return VersionableName.UNKNOWN.getName();
	}

	@Override
	public String getBrowserVersion() {
		return VersionableName.UNKNOWN.getName();
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public Map<String, Capability> getCapabilities() {
		return Collections.emptyMap();
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public String getCapability(String name) {
		return null;
	}

	@Override
	public String getModel() {
		return VersionableName.UNKNOWN.getName();
	}

	@Override
	public String getOS() {
		return VersionableName.UNKNOWN.getName();
	}

	@Override
	public String getOSVersion() {
		return VersionableName.UNKNOWN.getName();
	}

	@Override
	public String getPointingMethod() {
		return VersionableName.UNKNOWN.getName();
	}

	@Override
	public Dimensions getScreenPhysicalSize() {
		return Dimensions.UNKNOWN;
	}

	@Override
	public Dimensions getScreenResolution() {
		return Dimensions.UNKNOWN;
	}

	@Override
	public boolean hasQwertyKeyboard() {
		return true;
	}

	@Override
	public boolean isTablet() {
		return false;
	}

	private UnknownDevice() {
	}

	private static final UnknownDevice _unknownDevice = new UnknownDevice();

}