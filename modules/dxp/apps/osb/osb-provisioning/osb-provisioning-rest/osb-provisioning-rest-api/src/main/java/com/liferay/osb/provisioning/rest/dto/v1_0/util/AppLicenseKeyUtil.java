/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.rest.dto.v1_0.util;

import com.liferay.osb.provisioning.license.model.LicenseKey;
import com.liferay.osb.provisioning.rest.dto.v1_0.AppLicenseKey;

/**
 * @author Amos Fong
 */
public class AppLicenseKeyUtil {

	public static AppLicenseKey toAppLicenseKey(LicenseKey licenseKey)
		throws Exception {

		return new AppLicenseKey() {
			{
				active = licenseKey.isActive();
				complimentary = licenseKey.isComplimentary();
				createDate = licenseKey.getCreateDate();
				description = licenseKey.getDescription();
				expirationDate = licenseKey.getExpirationDate();
				hostName = licenseKey.getHostName();
				id = licenseKey.getLicenseKeyId();
				ipAddresses = licenseKey.getIpAddresses();
				key = licenseKey.getKey();
				licenseType = LicenseType.create(
					licenseKey.getLicenseEntryType());
				macAddresses = licenseKey.getMacAddresses();
				modifiedDate = licenseKey.getModifiedDate();
				modifiedUserName = licenseKey.getModifiedUserName();
				modifiedUserUuid = licenseKey.getModifiedUserUuid();
				orderId = licenseKey.getAssetReceiptLicenseUuid();
				owner = licenseKey.getOwner();
				productId = licenseKey.getProductId();
				productName = licenseKey.getProductName();
				productVersion = licenseKey.getProductVersion();
				startDate = licenseKey.getStartDate();
				userName = licenseKey.getUserName();
				userUuid = licenseKey.getUserUuid();
			}
		};
	}

}