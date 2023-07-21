/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.mobile.device;

import com.liferay.petra.string.StringBundler;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Abstract class containing common methods for all devices
 *
 * @author Milen Dyankov
 * @author Michael C. Han
 */
@ProviderType
public abstract class BaseDevice implements Device {

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{brand=");
		sb.append(getBrand());
		sb.append(", browser=");
		sb.append(getBrowser());
		sb.append(", browserVersion=");
		sb.append(getBrowserVersion());
		sb.append(", model=");
		sb.append(getModel());
		sb.append(", os=");
		sb.append(getOS());
		sb.append(", osVersion=");
		sb.append(getOSVersion());
		sb.append(", pointingMethod=");
		sb.append(getPointingMethod());
		sb.append(", qwertyKeyboard=");
		sb.append(hasQwertyKeyboard());
		sb.append(", screenPhysicalSize=");
		sb.append(getScreenPhysicalSize());
		sb.append(", screenResolution=");
		sb.append(getScreenResolution());
		sb.append(", tablet=");
		sb.append(isTablet());
		sb.append("}");

		return sb.toString();
	}

}