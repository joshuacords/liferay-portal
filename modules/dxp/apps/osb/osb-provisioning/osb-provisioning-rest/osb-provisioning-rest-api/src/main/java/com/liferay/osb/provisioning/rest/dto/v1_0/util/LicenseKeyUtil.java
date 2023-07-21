/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.provisioning.rest.dto.v1_0.util;

import com.liferay.osb.provisioning.license.helper.constants.LicenseSizing;
import com.liferay.osb.provisioning.license.model.LicenseEntry;
import com.liferay.osb.provisioning.rest.dto.v1_0.LicenseKey;

/**
 * @author Kyle Bischof
 */
public class LicenseKeyUtil {

	public static LicenseKey toLicenseKey(
			com.liferay.osb.provisioning.license.model.LicenseKey licenseKey)
		throws Exception {

		return new LicenseKey() {
			{
				accountKey = licenseKey.getAccountKey();
				accountName = licenseKey.getAccountName();
				active = licenseKey.getActive();
				additionalInfo = licenseKey.getAdditionalInfo();
				assetReceiptLicenseUuid =
					licenseKey.getAssetReceiptLicenseUuid();
				clusterId = licenseKey.getClusterId();
				complimentary = licenseKey.getComplimentary();
				createDate = licenseKey.getCreateDate();
				description = licenseKey.getDescription();
				expirationDate = licenseKey.getExpirationDate();
				hostName = licenseKey.getHostName();
				id = licenseKey.getLicenseKeyId();
				ipAddresses = licenseKey.getIpAddresses();
				key = licenseKey.getKey();

				LicenseEntry licenseEntry = licenseKey.fetchLicenseEntry();

				if (licenseEntry != null) {
					licenseEntryName = LicenseEntryName.create(
						licenseEntry.getName());
				}

				licenseEntryType = LicenseEntryType.create(
					licenseKey.getLicenseEntryType());
				licenseVersion = licenseKey.getLicenseVersion();
				macAddresses = licenseKey.getMacAddresses();
				maxClusterNodes = licenseKey.getMaxClusterNodes();
				maxHttpSessions = licenseKey.getMaxHttpSessions();
				maxServers = licenseKey.getMaxServers();
				modifiedDate = licenseKey.getModifiedDate();
				modifiedUserName = licenseKey.getModifiedUserName();
				modifiedUserUuid = licenseKey.getModifiedUserUuid();
				name = licenseKey.getName();
				owner = licenseKey.getOwner();
				productId = licenseKey.getProductId();
				productKey = licenseKey.getProductKey();
				productName = licenseKey.getProductName();
				productPurchaseKey = licenseKey.getProductPurchaseKey();
				productVersion = licenseKey.getProductVersion();
				serverId = licenseKey.getServerId();

				sizing = Sizing.create(
					LicenseSizing.getLabel(licenseKey.getSizing()));

				startDate = licenseKey.getStartDate();
				userName = licenseKey.getUserName();
				userUuid = licenseKey.getUserUuid();
			}
		};
	}

}