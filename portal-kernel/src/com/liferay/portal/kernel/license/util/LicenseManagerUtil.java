/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.license.util;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.license.LicenseInfo;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Amos Fong
 */
public class LicenseManagerUtil {

	public static void checkLicense(String productId) {
		getLicenseManager().checkLicense(productId);
	}

	public static List<Map<String, String>> getClusterLicenseProperties(
		String clusterNodeId) {

		return getLicenseManager().getClusterLicenseProperties(clusterNodeId);
	}

	public static String getHostName() {
		return getLicenseManager().getHostName();
	}

	public static Set<String> getIpAddresses() {
		return getLicenseManager().getIpAddresses();
	}

	public static LicenseInfo getLicenseInfo(String productId) {
		return getLicenseManager().getLicenseInfo(productId);
	}

	public static LicenseManager getLicenseManager() {
		return _licenseManager;
	}

	public static List<Map<String, String>> getLicenseProperties() {
		return getLicenseManager().getLicenseProperties();
	}

	public static Map<String, String> getLicenseProperties(String productId) {
		return getLicenseManager().getLicenseProperties(productId);
	}

	public static int getLicenseState(Map<String, String> licenseProperties) {
		return getLicenseManager().getLicenseState(licenseProperties);
	}

	public static int getLicenseState(String productId) {
		return getLicenseManager().getLicenseState(productId);
	}

	public static Set<String> getMacAddresses() {
		return getLicenseManager().getMacAddresses();
	}

	public static void registerLicense(JSONObject jsonObject) throws Exception {
		getLicenseManager().registerLicense(jsonObject);
	}

	public void setLicenseManager(LicenseManager licenseManager) {
		_licenseManager = licenseManager;
	}

	private static LicenseManager _licenseManager;

}