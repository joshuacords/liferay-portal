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
				active = licenseKey.getActive();
				complimentary = licenseKey.getComplimentary();
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