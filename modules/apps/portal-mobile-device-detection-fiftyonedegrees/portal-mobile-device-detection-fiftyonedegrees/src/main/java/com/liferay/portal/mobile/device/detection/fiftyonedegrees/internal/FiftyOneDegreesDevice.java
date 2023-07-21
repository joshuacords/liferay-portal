/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.mobile.device.detection.fiftyonedegrees.internal;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.mobile.device.BaseDevice;
import com.liferay.portal.kernel.mobile.device.Capability;
import com.liferay.portal.kernel.mobile.device.Dimensions;
import com.liferay.portal.kernel.mobile.device.VersionableName;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.mobile.device.detection.fiftyonedegrees.internal.constants.FiftyOneDegreesPropertyNames;

import fiftyone.mobile.detection.Match;
import fiftyone.mobile.detection.entities.Values;

import java.io.IOException;

import java.util.Map;

/**
 * @author Brian Greenwald
 * @author Prathima Shreenath
 */
public class FiftyOneDegreesDevice extends BaseDevice {

	public FiftyOneDegreesDevice(Match match) {
		_match = match;
	}

	@Override
	public String getBrand() {
		return getValueString(FiftyOneDegreesPropertyNames.HARDWARE_VENDOR);
	}

	@Override
	public String getBrowser() {
		return getValueString(FiftyOneDegreesPropertyNames.BROWSER_NAME);
	}

	@Override
	public String getBrowserVersion() {
		return getValueString(FiftyOneDegreesPropertyNames.BROWSER_VERSION);
	}

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	@Override
	public Map<String, Capability> getCapabilities() {
		return null;
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
		return getValueString(FiftyOneDegreesPropertyNames.HARDWARE_MODEL);
	}

	@Override
	public String getOS() {
		return getValueString(FiftyOneDegreesPropertyNames.PLATFORM_NAME);
	}

	@Override
	public String getOSVersion() {
		return getValueString(FiftyOneDegreesPropertyNames.PLATFORM_VERSION);
	}

	@Override
	public String getPointingMethod() {
		for (String pointingMethod :
				FiftyOneDegreesPropertyNames.TOUCH_EVENTS) {

			if (getValueBoolean(pointingMethod)) {
				return pointingMethod;
			}
		}

		return _VALUE_UNKNOWN;
	}

	@Override
	public Dimensions getScreenPhysicalSize() {
		return getDimensions(
			FiftyOneDegreesPropertyNames.SCREEN_MM_HEIGHT,
			FiftyOneDegreesPropertyNames.SCREEN_MM_WIDTH);
	}

	@Override
	public Dimensions getScreenResolution() {
		return getDimensions(
			FiftyOneDegreesPropertyNames.SCREEN_PIXELS_HEIGHT,
			FiftyOneDegreesPropertyNames.SCREEN_PIXELS_WIDTH);
	}

	@Override
	public boolean hasQwertyKeyboard() {
		if (getValueBoolean(FiftyOneDegreesPropertyNames.HAS_QWERTY_PAD) ||
			getValueBoolean(FiftyOneDegreesPropertyNames.HAS_VIRTUAL_QWERTY)) {

			return true;
		}

		return false;
	}

	@Override
	public boolean isTablet() {
		return getValueBoolean(FiftyOneDegreesPropertyNames.IS_TABLET);
	}

	protected Dimensions getDimensions(
		String heightPropertyName, String widthPropertyName) {

		double height = getValueDouble(heightPropertyName);
		double width = getValueDouble(widthPropertyName);

		if ((height == 0) || (width == 0)) {
			return Dimensions.UNKNOWN;
		}

		return new Dimensions((float)height, (float)width);
	}

	protected boolean getValueBoolean(String propertyName) {
		boolean value = false;

		try {
			Values values = _match.getValues(propertyName);

			if (values != null) {
				value = values.toBool();
			}
		}
		catch (IOException ioException) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to get boolean value for property name: " +
						propertyName,
					ioException);
			}
		}

		return value;
	}

	protected double getValueDouble(String propertyName) {
		double value = 0;

		try {
			Values values = _match.getValues(propertyName);

			if (values != null) {
				value = values.toDouble();
			}
		}
		catch (IOException ioException) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to get double value for property name: " +
						propertyName,
					ioException);
			}
		}

		return value;
	}

	protected String getValueString(String propertyName) {
		String value = VersionableName.UNKNOWN.getName();

		try {
			Values values = _match.getValues(propertyName);

			if (values != null) {
				String matchValue = String.valueOf(values);

				if (!StringUtil.equalsIgnoreCase(matchValue, _VALUE_UNKNOWN)) {
					value = matchValue;
				}
			}
		}
		catch (IOException ioException) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Unable to get string value for property name: " +
						propertyName,
					ioException);
			}
		}

		return value;
	}

	private static final String _VALUE_UNKNOWN = "unknown";

	private static final Log _log = LogFactoryUtil.getLog(
		FiftyOneDegreesDevice.class);

	private final Match _match;

}