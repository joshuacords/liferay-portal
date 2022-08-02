/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.provisioning.license.helper.constants;

import java.util.Arrays;
import java.util.List;

/**
 * @author Kyle Bischof
 */
public class ProductVersion {

	public static final String COMMERCE_LICENSE_VERSION_1 = "1";

	public static final String DXP_MAJOR_VERSION_7 = "7";

	public static final String DXP_VERSION_7_0 = "7.0";

	public static final String DXP_VERSION_7_1 = "7.1";

	public static final String DXP_VERSION_7_2 = "7.2";

	public static final String DXP_VERSION_7_3 = "7.3";

	public static final String DXP_VERSION_7_4 = "7.4";

	public static final String[] DXP_VERSIONS = {
		DXP_VERSION_7_0, DXP_VERSION_7_1, DXP_VERSION_7_2, DXP_VERSION_7_3,
		DXP_VERSION_7_4
	};

	public static final String PORTAL_MAJOR_VERSION_6 = "6";

	public static final String PORTAL_MINOR_VERSION_6_1 = "6.1";

	public static final String PORTAL_MINOR_VERSION_6_2 = "6.2";

	public static final String[] PORTAL_MINOR_VERSIONS = {
		PORTAL_MINOR_VERSION_6_1, PORTAL_MINOR_VERSION_6_2
	};

	public static final String PORTAL_VERSION_5_1_3 = "5.1";

	public static final String PORTAL_VERSION_5_1_4 = "5.1 SP1";

	public static final String PORTAL_VERSION_5_1_5 = "5.1 SP2";

	public static final String PORTAL_VERSION_5_1_6 = "5.1 SP3";

	public static final String PORTAL_VERSION_5_1_7 = "5.1 SP4";

	public static final String PORTAL_VERSION_5_1_8 = "5.1 SP5";

	public static final String PORTAL_VERSION_5_2_4 = "5.2";

	public static final String PORTAL_VERSION_5_2_5 = "5.2 SP1";

	public static final String PORTAL_VERSION_5_2_6 = "5.2 SP2";

	public static final String PORTAL_VERSION_5_2_7 = "5.2 SP3";

	public static final String PORTAL_VERSION_5_2_8 = "5.2 SP4";

	public static final String PORTAL_VERSION_5_2_9 = "5.2 SP5";

	public static final String PORTAL_VERSION_6_0_10 = "6.0";

	public static final String PORTAL_VERSION_6_0_11 = "6.0 SP1";

	public static final String PORTAL_VERSION_6_0_12 = "6.0 SP2";

	public static final String PORTAL_VERSION_6_1_10 = "6.1 GA1";

	public static final String PORTAL_VERSION_6_1_20 = "6.1 GA2";

	public static final String PORTAL_VERSION_6_1_30 = "6.1 GA3";

	public static final String PORTAL_VERSION_6_2_10 = "6.2 EE";

	public static final String[] PORTAL_VERSIONS = {
		PORTAL_VERSION_5_1_3, PORTAL_VERSION_5_1_4, PORTAL_VERSION_5_1_5,
		PORTAL_VERSION_5_1_6, PORTAL_VERSION_5_1_7, PORTAL_VERSION_5_1_8,
		PORTAL_VERSION_5_2_4, PORTAL_VERSION_5_2_5, PORTAL_VERSION_5_2_6,
		PORTAL_VERSION_5_2_7, PORTAL_VERSION_5_2_8, PORTAL_VERSION_5_2_9,
		PORTAL_VERSION_6_0_10, PORTAL_VERSION_6_0_11, PORTAL_VERSION_6_0_12,
		PORTAL_VERSION_6_1_10, PORTAL_VERSION_6_1_20, PORTAL_VERSION_6_1_30,
		PORTAL_VERSION_6_2_10
	};

	public static final String[] SUPPORTED_PORTAL_VERSIONS = {
		PORTAL_VERSION_6_1_10, PORTAL_VERSION_6_1_20, PORTAL_VERSION_6_1_30,
		PORTAL_VERSION_6_2_10
	};

	public static final int getOrder(
		String productName, String productVersion, boolean supportedVersion) {

		if (_isDXP(productName)) {
			return _orderedDXPVersions.indexOf(productVersion);
		}
		else if (_isPortal(productName)) {
			if (supportedVersion) {
				return _orderedSupportedPortalVersions.indexOf(productVersion);
			}

			return _orderedPortalVersions.indexOf(productVersion);
		}

		return -1;
	}

	public static final String[] getProductGroupVersions(
		String productGroupName, boolean supportedVersion) {

		if (productGroupName.equals("Commerce")) {
			return new String[] {COMMERCE_LICENSE_VERSION_1};
		}
		else if (productGroupName.equals("DXP")) {
			return DXP_VERSIONS;
		}
		else if (productGroupName.equals("Portal")) {
			if (supportedVersion) {
				return SUPPORTED_PORTAL_VERSIONS;
			}

			return PORTAL_VERSIONS;
		}

		return new String[0];
	}

	public static final String[] getProductVersions(
		String productName, boolean supportedVersion) {

		if (productName.contains("Commerce Subscription")) {
			return new String[] {COMMERCE_LICENSE_VERSION_1};
		}
		else if (_isDXP(productName)) {
			return DXP_VERSIONS;
		}
		else if (_isPortal(productName)) {
			if (supportedVersion) {
				return SUPPORTED_PORTAL_VERSIONS;
			}

			return PORTAL_VERSIONS;
		}

		return new String[0];
	}

	private static boolean _isDXP(String productName) {
		if (productName.startsWith("DXP") &&
			!productName.contains("DXP Cloud")) {

			return true;
		}

		return false;
	}

	private static boolean _isPortal(String productName) {
		if ((productName.contains("Portal") &&
			 !productName.contains("Early Access Program")) ||
			productName.startsWith("TCAT Portal")) {

			return true;
		}

		return false;
	}

	private static final List<String> _orderedDXPVersions = Arrays.asList(
		DXP_VERSIONS);
	private static final List<String> _orderedPortalVersions = Arrays.asList(
		PORTAL_VERSIONS);
	private static final List<String> _orderedSupportedPortalVersions =
		Arrays.asList(SUPPORTED_PORTAL_VERSIONS);

}